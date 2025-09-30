package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Builder
public class MedicineDTO {

    private Long medicineId;
    @NotBlank(message = "O nome comercial é obrigatório.")
    @Size(max = 255, message = "O nome comercial deve ter no máximo 255 caracteres.")
    private String comercialName;

    @NotBlank(message = "O princípio ativo é obrigatório.")
    @Size(max = 200, message = "O princípio ativo deve ter no máximo 200 caracteres.")
    private String activeIngredient;

    @NotBlank(message = "A forma farmacêutica é obrigatória.")
    @Size(max = 100, message = "A forma farmacêutica deve ter no máximo 100 caracteres.")
    private String pharmaceuticalForm;

    @NotBlank(message = "A unidade de medida é obrigatória.")
    @Size(max = 20, message = "A unidade de medida deve ter no máximo 20 caracteres.")
    private String unitMeasure;

    @NotBlank(message = "O fabricante é obrigatório.")
    @Size(max = 150, message = "O fabricante deve ter no máximo 150 caracteres.")
    private String manufacturer;

    @NotBlank(message = "O registro ANVISA é obrigatório.")
    @Size(max = 255, message = "O registro ANVISA deve ter no máximo 255 caracteres.")
    private String anvisaRecord;

    @NotBlank(message = "A categoria do medicamento é obrigatória.")
    @Size(max = 100, message = "A categoria deve ter no máximo 100 caracteres.")
    private String categoryMedicine;

    @NotNull(message = "O estoque mínimo é obrigatório.")
    @Min(value = 0, message = "O estoque mínimo não pode ser negativo.")
    private Integer minimalStock;

    @NotBlank(message = "O status é obrigatório.")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres.")
    private String status;

    public static Medicine toEntity(MedicineDTO dto) {
        if (dto == null) {
            return null;
        }
        return Medicine.builder()
                .medicineId(dto.getMedicineId())
                .comercialName(dto.getComercialName())
                .activeIngredient(dto.getActiveIngredient())
                .pharmaceuticalForm(dto.getPharmaceuticalForm())
                .unitMeasure(dto.getUnitMeasure())
                .manufacturer(dto.getManufacturer())
                .anvisaRecord(dto.getAnvisaRecord())
                .categoryMedicine(dto.getCategoryMedicine())
                .minimalStock(dto.getMinimalStock())
                .status(dto.getStatus())
                .build();
    }

    public static MedicineDTO fromEntity(Medicine entity) {
        if (entity == null) {
            return null;
        }
        return MedicineDTO.builder()
                .medicineId(entity.getMedicineId())
                .comercialName(entity.getComercialName())
                .activeIngredient(entity.getActiveIngredient())
                .pharmaceuticalForm(entity.getPharmaceuticalForm())
                .unitMeasure(entity.getUnitMeasure())
                .manufacturer(entity.getManufacturer())
                .anvisaRecord(entity.getAnvisaRecord())
                .categoryMedicine(entity.getCategoryMedicine())
                .minimalStock(entity.getMinimalStock())
                .status(entity.getStatus())
                .build();
    }
}