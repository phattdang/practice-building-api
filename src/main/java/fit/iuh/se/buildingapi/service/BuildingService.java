package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.entity.Building;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BuildingService {
    List<BuildingResponse> getAllBuidings();
    BuildingResponse addBuilding(@RequestBody BuildingCreationRequest request);
    void deleteBuilding(@PathVariable Integer id);
    BuildingResponse updateBuilding(@PathVariable Integer buildingId, @RequestBody BuildingUpdationRequest request);
}
