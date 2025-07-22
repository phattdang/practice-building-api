package fit.iuh.se.buildingapi.controller;

import fit.iuh.se.buildingapi.dto.response.BuildingResponse;
import fit.iuh.se.buildingapi.service.BuildingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("buildings")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class BuildingController {
    BuildingService buildingService;

    @GetMapping
    List<BuildingResponse> getAllBuidings(){
        return buildingService.getAllBuidings();
    }
}
