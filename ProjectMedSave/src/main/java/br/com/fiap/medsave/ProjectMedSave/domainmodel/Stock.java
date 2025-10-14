package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID")
    private @Getter @Setter Long id;

    @Column(name = "QUANTITY", nullable = false)
    private @Getter @Setter Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEDICINE_ID", nullable = false)
    private @Getter @Setter Medicine medicine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BATCH_ID", nullable = false)
    private @Getter @Setter Batch batch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID_STOCK", nullable = false)
    private @Getter @Setter LocationStock location;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}