package fit.iuh.se.buildingapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "rentarea")
public class RentArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Integer id;

    Integer value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    Building building;

    @Column(name = "createddate")
    LocalDate createdDate;

    @Column(name = "modifieddate")
    LocalDate modifiedDate;

    @Column(name = "createdby")
    String createdBy;

    @Column(name = "modifiedby")
    String modifiedBy;
}
