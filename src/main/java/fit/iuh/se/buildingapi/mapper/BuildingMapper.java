package fit.iuh.se.buildingapi.mapper;

import fit.iuh.se.buildingapi.dto.response.BuildingReponse;
import fit.iuh.se.buildingapi.entity.Building;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BuildingMapper {
    ModelMapper mapper;

    public BuildingReponse toBuildingReponse(Building building) {
        return mapper.map(building, BuildingReponse.class);
    }
}
