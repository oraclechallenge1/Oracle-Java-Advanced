package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UserSysCreateDTO;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UserSysResponseDTO;
import br.com.fiap.medsave.ProjectMedSave.service.UserSysService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/user")
@Tag(name = "UserSys", description = "Operações de usuário do sistema")
public class UserSysApiController {

    private final UserSysService userSysService;

    // create
    @PostMapping
    public ResponseEntity<UserSysResponseDTO> create (
            @RequestBody @Valid UserSysCreateDTO request
    ) {
        return ResponseEntity.ok(toDTO(userSysService.create(request)));
    }

    // findall
    @GetMapping
    public ResponseEntity<List<UserSysResponseDTO>> findAll() {

        List<UserSysResponseDTO> list = userSysService.findAll()
                .stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(list);
    }

    // find by id
    @GetMapping("/{id}")
    public ResponseEntity<UserSysResponseDTO> findById(@PathVariable Long id) {
        return userSysService.findById(id)
                .map(user -> ResponseEntity.ok(toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<UserSys> userOpt = userSysService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userSysService.delete(userOpt.get());
        return ResponseEntity.noContent().build();
    }

    private UserSysResponseDTO toDTO(UserSys user) {

        UserSysResponseDTO dto = new UserSysResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getContactUser().getEmailUser());
        dto.setPhone(user.getContactUser().getPhoneNumberUser());
        dto.setProfileName(user.getProfile().getUserProfile());
        dto.setRoleName(user.getRole().getUserRole());

        return dto;
    }

}
