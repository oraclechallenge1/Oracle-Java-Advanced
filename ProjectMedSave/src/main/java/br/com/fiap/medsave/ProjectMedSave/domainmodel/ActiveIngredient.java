package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
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

    @Column(name = "ACT_INGREDIENT", nullable = false, length = 200)
    private @Getter @Setter String name;

    @OneToMany(mappedBy = "activeIngredient", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Medicines> medicines;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ActiveIngredient that = (ActiveIngredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ActiveIngredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}