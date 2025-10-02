package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "DISPENSATION")
public class Dispensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISPENSATION_ID")
    private @Getter @Setter Long dispensationId;

    @Column(name = "DATE_DISPENSATION")
    private @Getter @Setter LocalDateTime dateDispensation;

    @Column(name = "WITHDRAWAL_AMOUNT")
    private @Getter @Setter Integer withdrawalAmount;

    @Column(name = "SECTOR_DESTINY", length = 100)
    private @Getter @Setter String sectorDestiny;

    @Column(name = "MOVEMENT_TYPE", length = 50)
    private @Getter @Setter String movementType;

    @Column(name = "OBSERVATION", length = 100)
    private @Getter @Setter String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private @Getter @Setter UserSys userSys;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BATCH_ID", nullable = false)
    private @Getter @Setter StockBatch stockBatch;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dispensation that = (Dispensation) o;
        return Objects.equals(dispensationId, that.dispensationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dispensationId);
    }

    @Override
    public String toString() {
        return "=== Dispensation ===" +
                "dispensationId=" + dispensationId +
                ", userSys=" + userSys +
                ", stockBatch=" + stockBatch +
                ", dateDispensation=" + dateDispensation +
                ", withdrawalAmount=" + withdrawalAmount +
                ", sectorDestiny='" + sectorDestiny + '\'' +
                ", movementType='" + movementType + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
