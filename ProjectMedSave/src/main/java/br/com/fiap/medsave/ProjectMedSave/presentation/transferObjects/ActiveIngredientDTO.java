package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ActiveIngredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActiveIngredientDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome do princípio ativo é obrigatório")
    @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres")
    private String nameActiveIngre;

    public static ActiveIngredientDTO fromEntity(ActiveIngredient e) {
        if (e == null) return null;
        return ActiveIngredientDTO.builder()
                .id(e.getId())
                .nameActiveIngre(e.getNameActiveIngre())
                .build();
    }

    public static ActiveIngredient toEntity(ActiveIngredientDTO d) {
        if (d == null) return null;
        ActiveIngredient e = new ActiveIngredient();
        e.setId(d.getId());
        e.setNameActiveIngre(d.getNameActiveIngre());
        return e;
    }
}