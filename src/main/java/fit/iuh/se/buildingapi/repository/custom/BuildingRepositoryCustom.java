package fit.iuh.se.buildingapi.repository.custom;

import fit.iuh.se.buildingapi.entity.Building;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    List<Building> findAllBuildings(@RequestParam Map<String, Object> params);
}
