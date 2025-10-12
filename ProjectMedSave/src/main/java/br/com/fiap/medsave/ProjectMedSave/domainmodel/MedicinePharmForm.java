package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEDICINE_PHARM_FORM")
public class MedicinePharmForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MED_PHARM_FORM_ID")
    private @Getter @Setter Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEDICINE_ID", nullable = false)
    private @Getter @Setter Medicine medicine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PHARM_FORM_ID", nullable = false)
    private @Getter @Setter PharmaceuticalForm pharmaceuticalForm;

    @Override public boolean equals(Object o){
        if(o==null||getClass()!=o.getClass())
            return false;
        return Objects.equals(id, ((MedicinePharmForm)o).id);
    }
    @Override public int hashCode(){
        return Objects.hashCode(id);
    }
    @Override public String toString(){
        return "MedicinePharmForm{" +
                "id="+id+"" +
                "}";
    }
}
