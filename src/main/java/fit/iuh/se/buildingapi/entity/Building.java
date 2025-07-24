package fit.iuh.se.buildingapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Integer id;

    String name;
    String street;
    String ward;

    @ManyToOne
    @JoinColumn(name = "districtid")
    District district;

    @Column(name = "numberofbasement")
    Integer numberOfBasement;

    @Column(name = "floorarea")
    Integer floorArea;

    @Column(name = "rentprice")
    Integer rentPrice;

    @Column(name = "rentpricedescription")
    String rentPriceDescription;

    @Column(name = "managername")
    String managerName;

    @Column(name = "managerphonenumber")
    String managerPhoneNumber;

    @OneToMany(mappedBy = "building")
    List<RentArea> rentAreas;

    @OneToMany(mappedBy = "building")
    List<BuildingRentType> buildingRentTypes;
}
