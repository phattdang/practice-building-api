package fit.iuh.se.buildingapi.mapper;

import fit.iuh.se.buildingapi.dto.request.UserCreationRequest;
import fit.iuh.se.buildingapi.dto.response.UserCreationResponse;
import fit.iuh.se.buildingapi.entity.User;
import fit.iuh.se.buildingapi.utils.PWEncoderSupporter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor

public class UserMapper {
    ModelMapper mapper;

    public User toUser(UserCreationRequest request){
        return User
                .builder()
                .username(request.getUsername())
                .password(PWEncoderSupporter.encode(request.getPassword()))
                .fullName(request.getFullName())
                .status(1)
                .build();
    }
    public UserCreationResponse toUserCreationResponse(User user){
        return UserCreationResponse
                .builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .build();
    }
}
