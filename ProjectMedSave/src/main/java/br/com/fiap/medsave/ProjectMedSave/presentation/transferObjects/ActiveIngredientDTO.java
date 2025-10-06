package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ActiveIngredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ActiveIngredientDTO {

    private Long id;

    @NotBlank(message = "Nome do princípio ativo é obrigatório")
    @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres")
    private String name; // mapeia ACT_INGREDIENT

    public static ActiveIngredientDTO fromEntity(ActiveIngredient e) {
        if (e == null) return null;
        return ActiveIngredientDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .build();
    }

    public static ActiveIngredient toEntity(ActiveIngredientDTO d) {
        if (d == null) return null;
        ActiveIngredient e = new ActiveIngredient();
        e.setId(d.getId());
        e.setName(d.getName());
        return e;
    }
}
