package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STOCK_BATCH")
public class StockBatch {

    // Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assumindo que a PK é autoincrementável
    @Column(name = "BATCH_ID")
    private @Getter @Setter Long batchId; // Numeric

    // Chave Estrangeira (FK) - Relacionamento com MEDICINES
    // Mapeamento Many-to-One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEDICINE_ID", nullable = false) // A coluna real da FK no BD
    private @Getter @Setter Medicine medicine;

    // Atributos da Entidade
    @Column(name = "NUMBER_BATCH", length = 255)
    private @Getter @Setter String numberBatch; // Varchar(255)

    @Column(name = "DATE_ENTRY")
    private @Getter @Setter LocalDate dateEntry; // Date

    @Column(name = "DATE_VALIDITY")
    private @Getter @Setter LocalDate dateValidity; // Date

    @Column(name = "CURRENT_QUANTITY")
    private @Getter @Setter Long currentQuantity; // Numeric(8) - usando Long para garantir capacidade

    @Column(name = "UNITARY_COST", precision = 10, scale = 2)
    private @Getter @Setter BigDecimal unitaryCost; // Float (10)

    @Column(name = "LOCATION_MEDICINE", length = 100)
    private @Getter @Setter String locationMedicine; // Varchar(100)

    @Column(name = "SUPPLIER", length = 150)
    private @Getter @Setter String supplier; // Varchar(150)

    @Column(name = "STATUS", length = 20)
    private @Getter @Setter String status; // Varchar(20)

    // Relacionamento (Um Lote pode ter Múltiplas Dispensaçõees)
    // Mapeamento One-to-Many com DISPENSATION
    @OneToMany(mappedBy = "stockBatch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dispensation> dispensations;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockBatch that = (StockBatch) o;
        return Objects.equals(batchId, that.batchId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(batchId);
    }

    @Override
    public String toString() {
        return "=== StockBatch ===" +
                "batchId=" + batchId +
                ", medicine=" + medicine +
                ", numberBatch='" + numberBatch + '\'' +
                ", dateEntry=" + dateEntry +
                ", dateValidity=" + dateValidity +
                ", currentQuantity=" + currentQuantity +
                ", unitaryCost=" + unitaryCost +
                ", locationMedicine='" + locationMedicine + '\'' +
                ", supplier='" + supplier + '\'' +
                ", status='" + status + '\'' +
                ", dispensations=" + dispensations +
                '}';
    }
}
