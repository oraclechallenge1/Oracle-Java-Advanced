package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "PHARMA_FORM", nullable = false, length = 100)
    private @Getter @Setter String name;

    @OneToMany(mappedBy = "pharmaceuticalForm", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Medicines> medicines;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PharmaceuticalForm that = (PharmaceuticalForm) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PharmaceuticalForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}