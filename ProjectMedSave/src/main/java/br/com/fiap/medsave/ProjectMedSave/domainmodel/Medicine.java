package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEDICINES")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICINE_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_MEDICATION", nullable = false, length = 255)
    private @Getter @Setter String nameMedication;

    @Column(name = "STATUS_MED", nullable = false, length = 20)
    private @Getter @Setter String statusMed;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CATEGORY_MED_ID", nullable = false)
    private @Getter @Setter CategoryMedicine categoryMedicine;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "UNIT_MEA_ID", nullable = false)
    private @Getter @Setter UnitMeasure unitMeasure;

    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Stock> stocks = new HashSet<>();

    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private @Getter @Setter Set<MedicineActiveIngr> activeIngredients = new HashSet<>();

    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private @Getter @Setter Set<MedicinePharmForm> pharmForms = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine other = (Medicine) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31; // valor fixo evita erro em entidades novas
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", nameMedication='" + nameMedication + '\'' +
                ", statusMed='" + statusMed + '\'' +
                '}';
    }
}
