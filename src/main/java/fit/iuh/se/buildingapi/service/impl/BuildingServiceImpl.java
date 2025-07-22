package fit.iuh.se.buildingapi.service.impl;

import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
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
}
