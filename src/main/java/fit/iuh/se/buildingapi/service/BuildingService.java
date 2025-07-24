package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingResponse> getAllBuildings();
    List<BuildingResponse> getAllBuildingsV2(@RequestParam Map<String, Object> params);
    BuildingResponse addBuilding(@RequestBody BuildingCreationRequest request);
    void deleteBuilding(@PathVariable Integer id);
    BuildingResponse updateBuilding(@PathVariable Integer buildingId, @RequestBody BuildingUpdationRequest request);
}
