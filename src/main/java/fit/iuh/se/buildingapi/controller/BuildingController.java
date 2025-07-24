package fit.iuh.se.buildingapi.controller;

import fit.iuh.se.buildingapi.dto.request.BuildingCreationRequest;
import fit.iuh.se.buildingapi.dto.request.BuildingUpdationRequest;
import fit.iuh.se.buildingapi.dto.response.ApiResponse;
import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import fit.iuh.se.buildingapi.service.BuildingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("buildings")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class BuildingController {
    BuildingService buildingService;

//    @GetMapping
//    ApiResponse<List<BuildingResponse>> getAllBuildings() {
//        return ApiResponse.<List<BuildingResponse>>builder()
//                .code(HttpCode.OK.getCODE())
//                .message(HttpCode.OK.getMESSAGE())
//                .value(buildingService.getAllBuildings())
//                .build();
//    }

    @GetMapping
    ApiResponse<List<BuildingResponse>> getAllBuildings(@RequestParam Map<String, Object> params) {
        if(params.isEmpty())
            return ApiResponse.<List<BuildingResponse>>builder()
                .code(HttpCode.OK.getCODE())
                .message(HttpCode.OK.getMESSAGE())
                .value(buildingService.getAllBuildings())
                .build();
        else
            return ApiResponse.<List<BuildingResponse>>builder()
                    .code(HttpCode.OK.getCODE())
                    .message(HttpCode.OK.getMESSAGE())
                    .value(buildingService.getAllBuildingsV2(params))
                    .build();
    }

    @PostMapping("/v1")
    BuildingResponse addBuilding(@Valid @RequestBody BuildingCreationRequest request) {
        return buildingService.addBuilding(request);
    }

    @PostMapping("/v2")
    ApiResponse<BuildingResponse> addBuildingV2(@Valid @RequestBody BuildingCreationRequest request) {
        return ApiResponse.<BuildingResponse>builder()
                .code(HttpCode.CREATED.getCODE())
                .message(HttpCode.CREATED.getMESSAGE())
                .value(buildingService.addBuilding(request))
                .build();
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
