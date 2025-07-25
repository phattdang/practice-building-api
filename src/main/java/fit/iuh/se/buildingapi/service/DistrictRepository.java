package fit.iuh.se.buildingapi.service;

import fit.iuh.se.buildingapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    District findByName(String name);
}
