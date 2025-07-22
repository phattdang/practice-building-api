package fit.iuh.se.buildingapi.repository;

import fit.iuh.se.buildingapi.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
