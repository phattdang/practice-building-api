package fit.iuh.se.buildingapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
