package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.dto.response.BuildingReponse;

import java.util.List;

public interface BuildingService {
    List<BuildingReponse> getAllBuidings();
}
