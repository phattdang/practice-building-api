package fit.iuh.se.buildingapi.service.impl;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.ApiResponse;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.entity.Building;
import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import fit.iuh.se.buildingapi.exception.AppException;
import fit.iuh.se.buildingapi.mapper.BuildingMapper;
import fit.iuh.se.buildingapi.repository.BuildingRepository;
import fit.iuh.se.buildingapi.service.BuildingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class BuildingServiceImpl implements BuildingService {
    BuildingRepository buildingRepository;
    BuildingMapper buildingMapper;

    @Override
    public List<BuildingResponse> getAllBuildings() {
        return buildingRepository.findAll()
                .stream()
                .map(buildingMapper::toBuildingReponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BuildingResponse> getAllBuildingsV2(Map<String, Object> params) {
        return buildingRepository.findAllBuildings(params)
                .stream()
                .map(buildingMapper::toBuildingReponse)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingResponse addBuilding(BuildingCreationRequest request) {
        Building building = buildingMapper.toBuilding(request);
        if(buildingRepository.existsByName(request.getName()))
            throw new AppException(HttpCode.NAME_EXISTED);
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
