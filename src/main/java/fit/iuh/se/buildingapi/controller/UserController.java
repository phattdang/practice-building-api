package fit.iuh.se.buildingapi.controller;

import com.nimbusds.jose.JOSEException;
import fit.iuh.se.buildingapi.dto.request.AuthenticationRequest;
import fit.iuh.se.buildingapi.dto.request.IntrospectRequest;
import fit.iuh.se.buildingapi.dto.request.UserCreationRequest;
import fit.iuh.se.buildingapi.dto.response.ApiResponse;
import fit.iuh.se.buildingapi.dto.response.AuthenticationResponse;
import fit.iuh.se.buildingapi.dto.response.IntrospectResponse;
import fit.iuh.se.buildingapi.dto.response.UserCreationResponse;
import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import fit.iuh.se.buildingapi.service.AuthenticationService;
import fit.iuh.se.buildingapi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/users")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;
    AuthenticationService authenticationService;

    @PostMapping
    ApiResponse<UserCreationResponse> addUser(@RequestBody UserCreationRequest request){
        return ApiResponse.<UserCreationResponse>builder()
                .code(HttpCode.OK.getCODE())
                .message(HttpCode.OK.getMESSAGE())
                .value(userService.addUser(request))
                .build();
    }

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(HttpCode.OK.getCODE())
                .message(HttpCode.OK.getMESSAGE())
                .value(
                        AuthenticationResponse
                                .builder()
                                .authenticated(result)
                                .build()
                )
                .build();
    }

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticateV2(@RequestBody AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .code(HttpCode.OK.getCODE())
                .message(HttpCode.OK.getMESSAGE())
                .value(authenticationService.authenticateV2(request))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(HttpCode.OK.getCODE())
                .message(HttpCode.OK.getMESSAGE())
                .value(result)
                .build();
    }
}
