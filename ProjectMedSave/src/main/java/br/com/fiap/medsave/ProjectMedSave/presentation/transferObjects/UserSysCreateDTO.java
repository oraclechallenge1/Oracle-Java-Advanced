package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSysCreateDTO {

    @NotBlank(message = "Nome do usuário é obrigatório")
    private String userName;

    @NotBlank(message = "Login é obrigatório")
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @NotNull(message = "Perfil é obrigatório")
    private Long profileUserId;

    @NotNull(message = "Cargo é obrigatório")
    private Long roleUserId;

    @NotBlank(message = "Email é obrigatório")
    private String emailUser;

    @NotBlank(message = "Telefone é obrigatório")
    private String phoneNumberUser;

}
