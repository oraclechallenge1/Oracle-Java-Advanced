package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MedicineDTO {

    private Long id;

    @NotBlank(message = "Nome do medicamento é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nameMedication;

    @NotBlank(message = "Status é obrigatório")
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    private String statusMed;

    @NotNull(message = "ID do princípio ativo é obrigatório")
    private Long activeIngredientId;

    @NotNull(message = "ID da forma farmacêutica é obrigatório")
    private Long pharmFormId;

    @NotNull(message = "ID da categoria é obrigatório")
    private Long categoryMedicineId;

    @NotNull(message = "ID da unidade de medida é obrigatório")
    private Long unitMeasureId;

    public static MedicineDTO fromEntity(Medicine m) {
        if (m == null) return null;
        return MedicineDTO.builder()
                .id(m.getId())
                .nameMedication(m.getNameMedication())
                .statusMed(m.getStatusMed())
                .activeIngredientId(m.getActiveIngredient() != null ? m.getActiveIngredient().getId() : null)
                .pharmFormId(m.getPharmaceuticalForm() != null ? m.getPharmaceuticalForm().getId() : null)
                .categoryMedicineId(m.getCategoryMedicine() != null ? m.getCategoryMedicine().getId() : null)
                .unitMeasureId(m.getUnitMeasure() != null ? m.getUnitMeasure().getId() : null)
                .build();
    }

    public static Medicine toEntity(MedicineDTO d) {
        if (d == null) return null;
        Medicine m = new Medicine();
        m.setId(d.getId());
        m.setNameMedication(d.getNameMedication());
        m.setStatusMed(d.getStatusMed());

        if (d.getActiveIngredientId() != null)
            m.setActiveIngredient(ActiveIngredient.builder().id(d.getActiveIngredientId()).build());
        if (d.getPharmFormId() != null)
            m.setPharmaceuticalForm(PharmaceuticalForm.builder().id(d.getPharmFormId()).build());
        if (d.getCategoryMedicineId() != null)
            m.setCategoryMedicine(CategoryMedicine.builder().id(d.getCategoryMedicineId()).build());
        if (d.getUnitMeasureId() != null)
            m.setUnitMeasure(UnitMeasure.builder().id(d.getUnitMeasureId()).build());

        return m;
    }
}
