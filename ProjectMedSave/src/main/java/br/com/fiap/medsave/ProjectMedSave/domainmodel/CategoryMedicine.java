package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CATEGORY_MEDICINE")
public class CategoryMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_MED_ID")
    private @Getter @Setter Long id;

    @Column(name = "CATEGORY", nullable = false, length = 255)
    private @Getter @Setter String categoryName;

    @OneToMany(mappedBy = "categoryMedicine", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Medicines> medicines;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMedicine that = (CategoryMedicine) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoryMedicine{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}