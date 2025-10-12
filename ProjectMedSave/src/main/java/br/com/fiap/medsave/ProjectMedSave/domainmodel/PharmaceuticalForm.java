package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PHARMACEUTICAL_FORM")
public class PharmaceuticalForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHARM_FORM_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_PHARM_FORM", nullable = false, length = 100)
    private @Getter @Setter String namePharmForm;

    @OneToMany(mappedBy = "pharmaceuticalForm", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private @Getter @Setter Set<MedicinePharmForm> medicines = new HashSet<>();

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmaceuticalForm other = (PharmaceuticalForm) o;
        return id != null && id.equals(other.id);
    }
    @Override public int hashCode(){ return 31; }
    @Override public String toString(){
        return "PharmaceuticalForm{" +
                "id=" + id +
                ", namePharmForm='" + namePharmForm + '\'' +
                '}';
    }
}