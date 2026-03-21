package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.request;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record RegisterUserRequest(
        @NotEmpty(message = "Nome é obrigatório")
        String name,

        @NotEmpty(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotEmpty(message = "Senha é obrigatória")
        String password,

        @NotEmpty(message = "Telefone é obrigatório")
        String phone,

        @NotNull(message = "Role é obrigatória")
        Long roleId,

        @NotNull(message = "Profile é obrigatório")
        Long profileId
) {

}
