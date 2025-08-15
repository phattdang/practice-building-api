package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.dto.request.AuthenticationRequest;

public interface AuthenticationService {
    boolean authenticate(AuthenticationRequest request);
}
