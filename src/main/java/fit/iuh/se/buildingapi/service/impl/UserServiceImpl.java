package fit.iuh.se.buildingapi.service.impl;

import fit.iuh.se.buildingapi.dto.request.UserCreationRequest;
import fit.iuh.se.buildingapi.dto.response.UserCreationResponse;
import fit.iuh.se.buildingapi.entity.User;
import fit.iuh.se.buildingapi.mapper.UserMapper;
import fit.iuh.se.buildingapi.repository.UserRepository;
import fit.iuh.se.buildingapi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    @Override
    public UserCreationResponse addUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        return userMapper.toUserCreationResponse(userRepository.save(user));
    }
}
