package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ContactUser;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.ProfileUser;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.RoleUser;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.ProfileUserRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.RoleUserRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UserSysRepository;
import br.com.fiap.medsave.ProjectMedSave.insfrastructure.config.TokenConfig;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.request.LoginRequest;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.request.RegisterUserRequest;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.response.LoginResponse;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.response.RegisterUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserSysRepository userSysRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final RoleUserRepository roleUserRepository;
    private final ProfileUserRepository profileUserRepository;

    public AuthController(UserSysRepository userSysRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          TokenConfig tokenConfig,
                          RoleUserRepository roleUserRepository,
                          ProfileUserRepository profileUserRepository
    ) {
        this.userSysRepository = userSysRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.roleUserRepository = roleUserRepository;
        this.profileUserRepository = profileUserRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserSys userSys = (UserSys) authentication.getPrincipal();
        String token = tokenConfig.generateToken(userSys);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        RoleUser role = roleUserRepository.findById(request.roleId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        ProfileUser profile = profileUserRepository.findById(request.profileId())
                .orElseThrow(() -> new RuntimeException("Profile não encontrado"));

        ContactUser contactUser = new ContactUser();
        contactUser.setPhoneNumberUser(request.phone());
        contactUser.setEmailUser(request.email());

        UserSys newUser = new UserSys();
        newUser.setUserName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(role);
        newUser.setProfile(profile);
        newUser.setContactUser(contactUser);

        userSysRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterUserResponse(
                        newUser.getUserName(),
                        newUser.getEmail()
                )
        );
    }
}