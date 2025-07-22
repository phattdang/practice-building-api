package fit.iuh.se.buildingapi.mapper;

import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.entity.Building;
import fit.iuh.se.buildingapi.entity.RentArea;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BuildingMapper {
    ModelMapper mapper;

    public BuildingResponse toBuildingReponse(Building building) {
        BuildingResponse buildingResponse = mapper.map(building, BuildingResponse.class);

        String address = building.getWard() + "," + building.getStreet() + "," + building.getDistrict().getName();
        buildingResponse.setAddress(address);

        String rentArea = building.getRentAreas()
                .stream()
                .map(RentArea::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        buildingResponse.setRentArea(rentArea);

        return buildingResponse;
    }
}
