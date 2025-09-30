package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DISPENSATION")
public class Dispensation {

    // Chave Primária
    // P.K. 'DISPENSATION_ID'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISPENSATION_ID")
    private Long dispensationId; // Numeric

    // Chaves Estrangeiras (FKs)

    // FK com USERS_SYS (Usuário que realizou a dispensa)
    // F.K. 'USER_ID'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserSys userSys;

    // FK com STOCK_BATCH (Lote de onde o medicamento foi retirado)
    // F.K. 'BATCH_ID'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BATCH_ID", nullable = false)
    private StockBatch stockBatch;

    // Atributos da Entidade
    @Column(name = "DATE_DISPENSATION")
    private @Getter @Setter LocalDateTime dateDispensation; // Date (usando LocalDateTime para incluir hora/minuto)

    @Column(name = "WITHDRAWAL_AMOUNT")
    private @Getter @Setter Integer withdrawalAmount; // Numeric(8) - Usando Integer se o máximo for até 8 dígitos

    @Column(name = "SECTOR_DESTINY", length = 100)
    private @Getter @Setter String sectorDestiny; // Varchar(100)

    @Column(name = "MOVEMENT_TYPE", length = 50)
    private @Getter @Setter String movementType; // Varchar(50)

    @Column(name = "OBSERVATION", length = 100)
    private @Getter @Setter String observation; // Varchar(100)

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
