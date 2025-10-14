package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
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
    @Min(value = 0, message = "Quantity must be >= 0")
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

    public void debit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Debit amount must be greater than zero");
        }
        if (this.quantity == null) {
            throw new IllegalStateException("Quantity is null");
        }
        if (this.quantity < amount) {
            throw new IllegalArgumentException("Insufficient stock quantity");
        }
        this.quantity -= amount;
    }

    public void credit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Credit amount must be greater than zero");
        }
        if (this.quantity == null) {
            this.quantity = amount;
        } else {
            this.quantity += amount;
        }
    }

    public boolean sameLogicalKey(Stock stock) {
        if (stock == null) return false;
        return Objects.equals(this.medicine != null ? this.medicine.getId() : null,
                            stock.medicine != null ? stock.medicine.getId() : null)
                && Objects.equals(this.batch != null ? this.batch.getId() : null,
                            stock.batch != null ? stock.batch.getId() : null)
                && Objects.equals(this.location != null ? this.location.getId() : null,
                            stock.location != null ? stock.location.getId() : null);
    }

    @PrePersist
    @PreUpdate
    private void validateInvariant() {
        if (this.quantity == null) {
            throw new IllegalArgumentException("Quantity is null");
        }
        if (this.quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

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