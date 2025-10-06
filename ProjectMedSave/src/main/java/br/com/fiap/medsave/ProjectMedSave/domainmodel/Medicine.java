package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
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

    // CORRIGIDO: STATUS_MED é obrigatório (*)
    @Column(name = "STATUS_MED", nullable = false, length = 20)
    private @Getter @Setter String statusMed;

    // Chaves Estrangeiras N:1 (Catálogo)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACT_INGRE_ID", nullable = false)
    private @Getter @Setter ActiveIngredient activeIngredient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PHARM_FORM_ID", nullable = false)
    private @Getter @Setter PharmaceuticalForm pharmaceuticalForm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_MED_ID", nullable = false)
    private @Getter @Setter CategoryMedicine categoryMedicine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNIT_MEA_ID", nullable = false)
    private @Getter @Setter UnitMeasure unitMeasure;

    // Relacionamentos 1:N (campos de navegação)
    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Stock> stocks;

    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY)
    private @Getter @Setter Set<MedicineDispense> dispenses;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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