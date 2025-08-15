package fit.iuh.se.buildingapi.service.impl;

import fit.iuh.se.buildingapi.dto.request.AuthenticationRequest;
import fit.iuh.se.buildingapi.entity.User;
import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import fit.iuh.se.buildingapi.exception.AppException;
import fit.iuh.se.buildingapi.repository.UserRepository;
import fit.iuh.se.buildingapi.service.AuthenticationService;
import fit.iuh.se.buildingapi.utils.PWEncoderSupporter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    @Override
    public boolean authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if(user == null)
            throw new AppException(HttpCode.NOT_FOUND);
        return PWEncoderSupporter.match(request.getPassword(), user.getPassword());
    }
}
