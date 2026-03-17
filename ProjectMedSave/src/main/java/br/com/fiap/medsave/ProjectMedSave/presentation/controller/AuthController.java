package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UserSysRepository;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.request.LoginRequest;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.request.RegisterUserRequest;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.response.LoginResponse;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.response.RegisterUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserSysRepository userSysRepository;

    public AuthController(UserSysRepository userSysRepository) {
        this.userSysRepository = userSysRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return null;
    }

    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        UserSys newUser = new UserSys();
        newUser.setUserName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(request.password());

        userSysRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterUserResponse(newUser.getUserName(),
                        newUser.getEmail()
                ));
    }
}