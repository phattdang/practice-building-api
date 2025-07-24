package fit.iuh.se.buildingapi.repository.custom.impl;

import fit.iuh.se.buildingapi.entity.Building;
import fit.iuh.se.buildingapi.repository.custom.BuildingRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Building> findAllBuildings(Map<String, Object> params) {
        StringBuilder jpql = new StringBuilder("select b from Building b ");


        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (String key : params.keySet()) {
            switch (key) {
                case "type" -> {
                    jpql.append("join b.buildingRentTypes brt ");
                }
                case "districtCode" -> {
                    jpql.append("join b.district d ");
                }
                default -> {

                }
            }
        }

        jpql.append("where 1 = 1 ");

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value != null && !value.toString().isBlank()) {
                switch (key) {
                    case "type" -> {
                        keys.add(key);
                        jpql.append("and brt.rentType.name = :typeName ");
                        values.add(value);
                    }
                    case "name" -> {
                        jpql.append("and b.name like :name ");
                        keys.add(key);
                        values.add("%" + value + "%");
                    }
                    case "ward" -> {
                        jpql.append("and b.ward like :ward ");
                        keys.add(key);
                        values.add("%" + value + "%");
                    }
                    case "street" -> {
                        jpql.append("and b.street like :street ");
                        keys.add(key);
                        values.add("%" + value + "%");
                    }
                    case "floorAreaFrom" -> {
                        if (entry.getKey().contains("floorAreaTo"))
                            jpql.append("and b.floorArea between :floorAreaFrom and :floorAreaTo ");
                        else
                            jpql.append("and b.floorArea >= :floorAreaFrom ");
                        keys.add(key);
                        values.add(value);
                    }
                    case "floorAreaTo" -> {
                        if(!entry.getKey().contains("floorAreaFrom"))
                            jpql.append("and b.floorArea <= :floorAreaTo ");
                        keys.add(key);
                        values.add(value);
                    }
                    case "rentPriceFrom" -> {
                        if (entry.getKey().contains("priceTo"))
                            jpql.append("and b.rentPrice between :rentPriceFrom and :rentPriceTo ");
                        else
                            jpql.append("and b.rentPrice >= :rentPriceFrom ");
                        keys.add(key);
                        values.add(value);
                    }
                    case "rentPriceTo" -> {
                        if(!entry.getKey().contains("rentPriceFrom"))
                            jpql.append("and b.rentPrice <= :rentPriceTo ");
                        keys.add(key);
                        values.add(value);
                    }
                    case "districtCode" -> {
                        jpql.append("and d.code = :districtCode ");
                        keys.add(key);
                        values.add(value);
                    }
                    default -> {

                    }
                }
            }
        }
        try {
            TypedQuery<Building> query = em.createQuery(jpql.toString(), Building.class);
            for (int i = 0; i < keys.size(); i++) {
                query.setParameter(keys.get(i), values.get(i));
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
