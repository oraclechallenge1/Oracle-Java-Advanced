package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.CategoryMedicine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CategoryMedicineDTO {

    private Long id;

    @NotBlank(message = "Categoria é obrigatória")
    @Size(max = 255, message = "Categoria deve ter no máximo 255 caracteres")
    private String categoryName;

    public static CategoryMedicineDTO fromEntity(CategoryMedicine e) {
        if (e == null) return null;
        return CategoryMedicineDTO.builder()
                .id(e.getId())
                .categoryName(e.getCategoryName())
                .build();
    }

    public static CategoryMedicine toEntity(CategoryMedicineDTO d) {
        if (d == null) return null;
        CategoryMedicine e = new CategoryMedicine();
        e.setId(d.getId());
        e.setCategoryName(d.getCategoryName());
        return e;
    }
}
