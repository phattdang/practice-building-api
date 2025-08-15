package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.dto.request.UserCreationRequest;
import fit.iuh.se.buildingapi.dto.response.ApiResponse;
import fit.iuh.se.buildingapi.dto.response.UserCreationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    UserCreationResponse addUser(@RequestBody UserCreationRequest request);
}
