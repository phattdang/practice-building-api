package fit.iuh.se.buildingapi.service.impl;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.entity.Building;
import fit.iuh.se.buildingapi.mapper.BuildingMapper;
import fit.iuh.se.buildingapi.repository.BuildingRepository;
import fit.iuh.se.buildingapi.service.BuildingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class BuildingServiceImpl implements BuildingService {
    BuildingRepository buildingRepository;
    BuildingMapper buildingMapper;

    @Override
    public List<BuildingResponse> getAllBuidings() {
        return buildingRepository.findAll()
                .stream()
                .map(buildingMapper::toBuildingReponse)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingResponse addBuilding(BuildingCreationRequest request) {
        Building building = buildingMapper.toBuilding(request);
        return buildingMapper.toBuildingReponse(buildingRepository.save(building));
    }

    @Override
    public void deleteBuilding(Integer id) {
        buildingRepository.deleteById(id);
    }

    @Override
    public BuildingResponse updateBuilding(Integer buildingId, BuildingUpdationRequest request) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Building not found!"));

        return buildingMapper.toBuildingReponse(buildingMapper.toBuilding(building, request));
    }
}
