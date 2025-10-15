package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.PharmaceuticalForm;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class PharmaceuticalFormDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome da forma farmacêutica é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    public static PharmaceuticalFormDTO fromEntity(PharmaceuticalForm e) {
        if (e == null) return null;
        return PharmaceuticalFormDTO.builder()
                .id(e.getId())
                .name(e.getNamePharmForm())
                .build();
    }

    public static PharmaceuticalForm toEntity(PharmaceuticalFormDTO d) {
        if (d == null) return null;
        PharmaceuticalForm e = new PharmaceuticalForm();
        e.setId(d.getId());
        e.setNamePharmForm(d.getName());
        return e;
    }
}
