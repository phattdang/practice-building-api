package fit.iuh.se.buildingapi.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BuildingCreationRequest {
    @NonNull
    @Size(min = 8, message = "INVALID_NAME")
    String name;
    String address;
    Integer numberOfBasement;
    Integer floorArea;
    Integer rentPrice;
    String rentPriceDescription;
    String managerName;
    String managerPhoneNumber;
}
