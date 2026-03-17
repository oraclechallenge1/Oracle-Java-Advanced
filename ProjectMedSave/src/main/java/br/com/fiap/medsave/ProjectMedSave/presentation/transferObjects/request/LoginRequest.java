package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.request;

import javax.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "Email é obrigatório.") String email,
                           @NotEmpty(message = "Senha é obrigatória") String password) {
}
