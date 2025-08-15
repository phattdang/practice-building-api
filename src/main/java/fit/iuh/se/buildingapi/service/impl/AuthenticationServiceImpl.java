package fit.iuh.se.buildingapi.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import fit.iuh.se.buildingapi.dto.request.AuthenticationRequest;
import fit.iuh.se.buildingapi.dto.request.IntrospectRequest;
import fit.iuh.se.buildingapi.dto.response.AuthenticationResponse;
import fit.iuh.se.buildingapi.dto.response.IntrospectResponse;
import fit.iuh.se.buildingapi.entity.User;
import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import fit.iuh.se.buildingapi.exception.AppException;
import fit.iuh.se.buildingapi.repository.UserRepository;
import fit.iuh.se.buildingapi.service.AuthenticationService;
import fit.iuh.se.buildingapi.utils.PWEncoderSupporter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    @NonFinal
    protected static final String SIGNER_KEY = "VztBKqb09RpaqX2+UTaFugJGZrddqPyuckJubjqlrWt4ZVW5zAo0FYWF5V/gBRXe";
    @Override
    public boolean authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if(user == null)
            throw new AppException(HttpCode.NOT_FOUND);
        return PWEncoderSupporter.match(request.getPassword(), user.getPassword());
    }

    @Override
    public AuthenticationResponse authenticateV2(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if(user == null)
            throw new AppException(HttpCode.NOT_FOUND);
        boolean authenticated = PWEncoderSupporter.match(request.getPassword(), user.getPassword());

        if(!authenticated)
            throw new AppException(HttpCode.UNAUTHENTICATED);

        var token = generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTokenTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expirationTokenTime.after(new Date()))
                .build();
    }

    private String generateToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512); // header của token

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder() // các thành phần của payload
                .subject(username)// đại diện cho user đăng nhập
                .issuer("phatdang") // xác định issuer từ ai, thường là từ domain
                .issueTime(new Date()) //
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                )) // thời hạn của token
                .claim("customClaim", "Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());// payload của token

        JWSObject jwsObject = new JWSObject(header, payload);

        // tạo token xong phải kí token
        // dùng 1 thuật toán để kí
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
