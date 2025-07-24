package fit.iuh.se.buildingapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "buildingrenttype")
public class BuildingRentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Integer id;

    @ManyToOne
    @JoinColumn(name = "renttypeid")
    RentType rentType;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    Building building;
}
