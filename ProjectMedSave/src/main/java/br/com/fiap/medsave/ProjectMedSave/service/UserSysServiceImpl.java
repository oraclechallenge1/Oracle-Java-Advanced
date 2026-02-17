package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ContactUser;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.ProfileUser;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.RoleUser;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.ContactUserRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.ProfileUserRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.RoleUserRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UserSysRepository;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.ContactUserCreateDTO;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UserSysCreateDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSysServiceImpl implements UserSysService {

    private final UserSysRepository userRepository;
    private final ContactUserRepository contactRepository;
    private final RoleUserRepository roleRepository;
    private final ProfileUserRepository profileRepository;

    @Override
    public UserSys create(UserSysCreateDTO dto) {

        RoleUser role = roleRepository.findById(dto.getRoleUserId())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        ProfileUser profile = profileRepository.findById(dto.getProfileUserId())
                .orElseThrow(() -> new RuntimeException("Profile não encontrado"));

        ContactUser contact = ContactUser.builder()
                .emailUser(dto.getEmailUser())
                .phoneNumberUser(dto.getPhoneNumberUser())
                .build();

        UserSys user = UserSys.builder()
                .userName(dto.getUserName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .role(role)
                .profile(profile)
                .contactUser(contact)
                .build();

        return userRepository.save(user);
    }

    @Override
    public List<UserSys> findAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public Optional<UserSys> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(UserSys userSys) {
        if (userSys != null && userSys.getId() != null) {
            userRepository.delete(userSys);
        }
    }

    @Override
    public UserSys update(Long id, UserSys userSys) {
        return null; // caso de uso futuro
    }
}