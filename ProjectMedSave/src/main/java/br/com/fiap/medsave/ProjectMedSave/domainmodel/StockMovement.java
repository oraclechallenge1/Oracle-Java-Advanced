package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "STOCK_MOVEMENT")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_MOVEMENT_ID")
    private @Getter @Setter Long id;

    @Column(name = "DATE_MOVEMENT", nullable = false)
    private @Getter @Setter LocalDate dateMovement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MOVEMENT_TYPE_ID", nullable = false)
    private @Getter @Setter MovementType movementType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    private @Getter @Setter Stock stock;

    // opcional no DER (útil para rastrear baixas vindas de dispensação)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISPENSATION_ID")
    private @Getter @Setter MedicineDispense dispensation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private @Getter @Setter UserSys user;

    @Override public boolean equals(Object o){
        if(o==null||getClass()!=o.getClass())
            return false;
        return Objects.equals(id, ((StockMovement)o).id);
    }
    @Override public int hashCode(){
        return Objects.hashCode(id);
    }
    @Override public String toString(){
        return "StockMovement{" +
                "id="+ id +
                "}";
    }
}
