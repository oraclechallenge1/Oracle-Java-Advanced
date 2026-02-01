package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class CreateUserWithContactDTO {

    @Valid
    @NotNull(message = "Dados do usuário são obrigatórios")
    private UserSysCreateDTO user;

    @Valid
    @NotNull(message = "Dados de contato são obrigatórios")
    private ContactUserCreateDTO contact;

}
