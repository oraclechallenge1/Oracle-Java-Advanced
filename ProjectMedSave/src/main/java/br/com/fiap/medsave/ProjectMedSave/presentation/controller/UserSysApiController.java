package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UserSysDTO;
import br.com.fiap.medsave.ProjectMedSave.service.UserSysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "UserSys", description = "UserSys operations")
public class UserSysApiController {

    private final UserSysService userSysService;

    @Operation(summary = "Insert User", method = "POST")
    @PostMapping
    public ResponseEntity<UserSysDTO> create(@Valid @RequestBody UserSysDTO userDTO) {
        UserSys userToCreate = UserSysDTO.toEntity(userDTO);
        UserSys createdUser = userSysService.create(userToCreate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserSysDTO.fromEntity(createdUser));
    }

    @Operation(summary = "Return User", method = "GET")
    @GetMapping
    public ResponseEntity<List<UserSysDTO>> findAll() {
        List<UserSys> users = userSysService.findAll();
        List<UserSysDTO> userDTOS = users.stream()
                .map(UserSysDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    @Operation(summary = "Return User by ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<UserSysDTO> findById(@PathVariable Long id) {
        UserSys user = userSysService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "System User not found with ID: " + id
                ));

        return ResponseEntity.ok(UserSysDTO.fromEntity(user));
    }

    @Operation(summary = "Partial Update User by ID", method = "PATCH")
    @PatchMapping("/{id}")
    public ResponseEntity<UserSysDTO> partialUpdate(@PathVariable Long id,
                                                    @Valid @RequestBody UserSysDTO userDTO) {
        try {
            UserSys userUpdatePayload = UserSysDTO.toEntity(userDTO);
            UserSys updatedUser = userSysService.partialUpdate(id, userUpdatePayload);
            return ResponseEntity.ok(UserSysDTO.fromEntity(updatedUser));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Delete User by ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!userSysService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "System User not found with ID: " + id);
        }
        userSysService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete User", method = "DELETE")
    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody UserSysDTO userDTO) {
        UserSys userToDelete = UserSysDTO.toEntity(userDTO);
        userSysService.delete(userToDelete);
        return ResponseEntity.noContent().build();
    }
}