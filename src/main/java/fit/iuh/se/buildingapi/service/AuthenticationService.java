package fit.iuh.se.buildingapi.service;

import com.nimbusds.jose.JOSEException;
import fit.iuh.se.buildingapi.dto.request.AuthenticationRequest;
import fit.iuh.se.buildingapi.dto.request.IntrospectRequest;
import fit.iuh.se.buildingapi.dto.response.AuthenticationResponse;
import fit.iuh.se.buildingapi.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    boolean authenticate(AuthenticationRequest request);
    AuthenticationResponse authenticateV2(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
