package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEDICINE_ACTIVE_INGR")
public class MedicineActiveIngr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MED_ACTIVE_INGR_ID")
    private @Getter @Setter Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEDICINE_ID", nullable = false)
    private @Getter @Setter Medicine medicine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACT_INGRE_ID", nullable = false)
    private @Getter @Setter ActiveIngredient activeIngredient;

    @Override public boolean equals(Object o){
        if(o==null||getClass()!=o.getClass())
            return false;
        return Objects.equals(id, ((MedicineActiveIngr)o).id);
    }
    @Override public int hashCode(){
        return Objects.hashCode(id);
    }
    @Override public String toString(){
        return "MedicineActiveIngr{" +
                "id="+id+"" +
                "}";
    }
}
