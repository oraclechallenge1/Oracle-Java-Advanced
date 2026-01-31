package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUserCreateDTO {

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String emailUser;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 10, max = 11, message = "Telefone deve ter entre 10 e 11 dígitos")
    private String phoneNumberUser;

}
