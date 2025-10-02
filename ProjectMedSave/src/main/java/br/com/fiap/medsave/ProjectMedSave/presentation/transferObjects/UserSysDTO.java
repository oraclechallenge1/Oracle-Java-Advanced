package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSysDTO {

    private Long userId;

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    private String nameUser;

    @NotBlank(message = "O cargo/posição é obrigatório.")
    @Size(max = 100, message = "A posição deve ter no máximo 100 caracteres.")
    private String positionUser;

    @NotBlank(message = "O login é obrigatório.")
    @Size(max = 50, message = "O login deve ter no máximo 50 caracteres.")
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 255, message = "A senha deve ter entre 6 e 255 caracteres.")
    private String passwordUser;

    @NotBlank(message = "O perfil do usuário é obrigatório.")
    @Size(max = 50, message = "O perfil deve ter no máximo 50 caracteres.")
    private String profileUser;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O formato do e-mail é inválido.")
    @Size(max = 255, message = "O e-mail deve ter no máximo 255 caracteres.")
    private String email;

    @Size(max = 11, message = "O telefone deve ter no máximo 11 dígitos (incluindo DDD).")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números.")
    private String phoneNumber;

    public static UserSys toEntity(UserSysDTO dto) {
        if (dto == null) return null;

        return UserSys.builder()
                .userId(dto.getUserId())
                .nameUser(dto.getNameUser())
                .positionUser(dto.getPositionUser())
                .login(dto.getLogin())
                .passwordUser(dto.getPasswordUser())
                .profileUser(dto.getProfileUser())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static UserSysDTO fromEntity(UserSys entity) {
        if (entity == null) return null;

        return UserSysDTO.builder()
                .userId(entity.getUserId())
                .nameUser(entity.getNameUser())
                .positionUser(entity.getPositionUser())
                .login(entity.getLogin())
                .passwordUser(null)
                .profileUser(entity.getProfileUser())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}