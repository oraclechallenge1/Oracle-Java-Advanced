package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class MedicineDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String nameMedication;

    @NotBlank @Size(max = 20)
    private String statusMed;

    @NotEmpty(message = "Pelo menos 1 princípio ativo é obrigatório")
    private List<Long> activeIngredientIds;

    @NotEmpty(message = "Pelo menos 1 forma farmacêutica é obrigatória")
    private List<Long> pharmFormIds;

    @NotNull
    private Long categoryMedicineId;

    @NotNull
    private Long unitMeasureId;

    public static MedicineDTO fromEntity(Medicine m) {
        if (m == null) return null;

        List<Long> actIds = m.getActiveIngredients() == null ? List.of()
                : m.getActiveIngredients().stream()
                .map(mai -> mai.getActiveIngredient().getId())
                .toList();

        List<Long> formIds = m.getPharmForms() == null ? List.of()
                : m.getPharmForms().stream()
                .map(mpf -> mpf.getPharmaceuticalForm().getId())
                .toList();

        return MedicineDTO.builder()
                .id(m.getId())
                .nameMedication(m.getNameMedication())
                .statusMed(m.getStatusMed())
                .activeIngredientIds(actIds)
                .pharmFormIds(formIds)
                .categoryMedicineId(m.getCategoryMedicine() != null ? m.getCategoryMedicine().getId() : null)
                .unitMeasureId(m.getUnitMeasure() != null ? m.getUnitMeasure().getId() : null)
                .build();
    }

    public static Medicine toEntity(MedicineDTO d) {
        if (d == null) return null;

        Medicine m = new Medicine();
        m.setNameMedication(d.getNameMedication());
        m.setStatusMed(d.getStatusMed());

        if (d.getCategoryMedicineId() != null)
            m.setCategoryMedicine(CategoryMedicine.builder().id(d.getCategoryMedicineId()).build());

        if (d.getUnitMeasureId() != null)
            m.setUnitMeasure(UnitMeasure.builder().id(d.getUnitMeasureId()).build());

        // montar as tabelas de junção
        Set<MedicineActiveIngr> actLinks = new HashSet<>();
        if (d.getActiveIngredientIds() != null) {
            for (Long actId : d.getActiveIngredientIds()) {
                ActiveIngredient act = ActiveIngredient.builder().id(actId).build();
                MedicineActiveIngr link = MedicineActiveIngr.builder()
                        .medicine(m)
                        .activeIngredient(act)
                        .build();
                actLinks.add(link);
            }
        }
        m.setActiveIngredients(actLinks);

        Set<MedicinePharmForm> formLinks = new HashSet<>();
        if (d.getPharmFormIds() != null) {
            for (Long formId : d.getPharmFormIds()) {
                PharmaceuticalForm form = PharmaceuticalForm.builder().id(formId).build();
                MedicinePharmForm link = MedicinePharmForm.builder()
                        .medicine(m)
                        .pharmaceuticalForm(form)
                        .build();
                formLinks.add(link);
            }
        }
        m.setPharmForms(formLinks);

        return m;
    }
}
