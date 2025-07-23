package fit.iuh.se.buildingapi.controller;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.service.BuildingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("buildings")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class BuildingController {
    BuildingService buildingService;

    @GetMapping
    List<BuildingResponse> getAllBuidings() {
        return buildingService.getAllBuidings();
    }

    @PostMapping
    BuildingResponse addBuilding(@RequestBody BuildingCreationRequest request) {
        return buildingService.addBuilding(request);
    }

    @DeleteMapping("/{buildingId}")
    void deleteBuilding(@PathVariable Integer buildingId) {
        buildingService.deleteBuilding(buildingId);
    }

    @PutMapping("/{buildingId}")
    BuildingResponse updateBuilding(@PathVariable Integer buildingId, @RequestBody BuildingUpdationRequest request) {
        return buildingService.updateBuilding(buildingId, request);
    }
}
