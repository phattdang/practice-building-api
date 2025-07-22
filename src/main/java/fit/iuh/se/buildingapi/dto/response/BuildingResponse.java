package fit.iuh.se.buildingapi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BuildingResponse {
    String name;
    String address;
    Integer numberOfBasement;
    Integer floorArea;
    Integer rentPrice;
    String rentPriceDescription;
    String managerName;
    String managerPhoneNumber;
    String rentArea;
}
