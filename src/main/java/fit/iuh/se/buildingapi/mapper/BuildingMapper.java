package fit.iuh.se.buildingapi.mapper;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.entity.Building;
import fit.iuh.se.buildingapi.entity.District;
import fit.iuh.se.buildingapi.entity.RentArea;
import fit.iuh.se.buildingapi.service.DistrictRepository;
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
    DistrictRepository districtRepository;

    public BuildingResponse toBuildingReponse(Building building) {
        BuildingResponse buildingResponse = mapper.map(building, BuildingResponse.class);

        String address = building.getWard() + "," + building.getStreet() + "," + building.getDistrict().getName();
        buildingResponse.setAddress(address);

        if(building.getRentAreas() != null){
            String rentArea = building.getRentAreas()
                    .stream()
                    .map(RentArea::getValue)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            buildingResponse.setRentArea(rentArea);
        }

        return buildingResponse;
    }

    public BuildingResponse toBuildingReponse(BuildingCreationRequest request) {
        BuildingResponse buildingResponse = mapper.map(request, BuildingResponse.class);
        buildingResponse.setRentArea("");
        return buildingResponse;
    }

    public Building toBuilding(BuildingCreationRequest request){
        Building building = mapper.map(request, Building.class);

        String[] address = request.getAddress()
                        .split(",");

        building.setWard(address[0]);
        building.setStreet(address[1]);

        String districtName = address[2];
        District district = districtRepository.findByName(districtName);

        building.setDistrict(district);

        return  building;
    }

    public Building toBuilding(Building building, BuildingUpdationRequest request){

        building.setName(request.getName());

        String[] address = request.getAddress()
                .split(",");

        building.setWard(address[0]);
        building.setStreet(address[1]);

        String districtName = address[2];
        District district = districtRepository.findByName(districtName);

        building.setDistrict(district);

        return  building;
    }
}
