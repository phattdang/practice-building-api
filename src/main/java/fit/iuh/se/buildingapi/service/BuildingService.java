package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.dto.response.BuildingResponse;

import java.util.List;

public interface BuildingService {
    List<BuildingResponse> getAllBuidings();
}
