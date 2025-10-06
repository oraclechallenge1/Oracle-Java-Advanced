package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BATCH")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BATCH_ID")
    private @Getter @Setter Long id;

    @Column(name = "BATCH_NUMBER", nullable = false, length = 255)
    private @Getter @Setter String batchNumber;

    @Column(name = "CURRENT_QUANTITY", nullable = false)
    private @Getter @Setter Integer currentQuantity;

    @Column(name = "MANUFACTURING_DATE", nullable = false)
    private @Getter @Setter LocalDate manufacturingDate;

    @Column(name = "EXPIRATION_DATE", nullable = false)
    private @Getter @Setter LocalDate expirationDate;

    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Stock> stocks;

    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY)
    private @Getter @Setter Set<MedicineDispense> dispenses;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Batch batch = (Batch) o;
        return Objects.equals(id, batch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", batchNumber='" + batchNumber + '\'' +
                ", currentQuantity=" + currentQuantity +
                '}';
    }
}