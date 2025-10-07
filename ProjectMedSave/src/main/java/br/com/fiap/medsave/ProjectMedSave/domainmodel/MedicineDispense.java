package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEDICINE_DISPENSE")
public class MedicineDispense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISPENSATION_ID")
    private @Getter @Setter Long id;

    @Column(name = "DATE_DISPENSATION", nullable = false)
    private @Getter @Setter LocalDate dateDispensation;

    @Column(name = "QUANTITY_DISPENSED", nullable = false)
    private @Getter @Setter Integer quantityDispensed;

    @Column(name = "DESTINATION", nullable = false, length = 255)
    private @Getter @Setter String destination;

    @Column(name = "OBSERVATION", length = 255)
    private @Getter @Setter String observation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private @Getter @Setter UserSys user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MOVEMENT_TYPE_ID", unique = true)
    private @Getter @Setter MovementType movementType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    private @Getter @Setter Stock stock;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MedicineDispense that = (MedicineDispense) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "MedicineDispense{" +
                "id=" + id +
                ", dateDispensation=" + dateDispensation +
                ", quantityDispensed=" + quantityDispensed +
                ", destination='" + destination + '\'' +
                '}';
    }
}
