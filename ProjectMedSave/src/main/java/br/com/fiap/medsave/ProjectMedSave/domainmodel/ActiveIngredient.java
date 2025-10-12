package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ACTIVE_INGREDIENT")
public class ActiveIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACT_INGRE_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_ACTIVE_INGRE", nullable = false, length = 255)
    private @Getter @Setter String nameActiveIngre;

    @OneToMany(mappedBy = "activeIngredient", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private @Getter @Setter Set<MedicineActiveIngr> medicines = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveIngredient that = (ActiveIngredient) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() { return 31; }

    @Override
    public String toString() {
        return "ActiveIngredient{" +
                "id=" + id +
                ", nameActiveIngre='" + nameActiveIngre + '\'' +
                '}';
    }
}