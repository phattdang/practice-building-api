package fit.iuh.se.buildingapi.repository;

import fit.iuh.se.buildingapi.entity.Building;
import fit.iuh.se.buildingapi.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer>, BuildingRepositoryCustom {
    boolean existsByName(String name);
}
