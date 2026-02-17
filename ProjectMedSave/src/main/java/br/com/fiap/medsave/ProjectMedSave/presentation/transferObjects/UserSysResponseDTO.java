package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSysResponseDTO {

    private Long id;
    private String userName;
    private String login;
    private String email;
    private String phone;
    private String profileName;
    private String roleName;
}


