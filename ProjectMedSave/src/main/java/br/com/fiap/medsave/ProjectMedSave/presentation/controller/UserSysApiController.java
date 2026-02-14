package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.ContactUserCreateDTO;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UserSysCreateDTO;
import br.com.fiap.medsave.ProjectMedSave.service.UserSysService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/user")
@Tag(name = "UserSys", description = "Operações de usuário do sistema")
public class UserSysApiController {

    private final UserSysService userSysService;

    // create
    public ResponseEntity<UserSys> create (
            @ResponseBody @Valid UserSysCreateDTO userDto,
            @RequestBody @Valid ContactUserCreateDTO contactDto
    ) {
        UserSys createdUser = userSysService.create(userDto, contactDto);
        return ResponseEntity.ok(createdUser);
    }

    // a aplicação não suporta dois requestBody, preciso criar um wrapper para arrumar isso

}
