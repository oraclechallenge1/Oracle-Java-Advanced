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
    private final ContactUserRepository contactRepository; // pode ficar, não quebra
    private final RoleUserRepository roleRepository;
    private final ProfileUserRepository profileRepository;

    @Override
    public UserSys create(
            UserSysCreateDTO userDto,
            ContactUserCreateDTO contactDto
    ) {

        ProfileUser profile = profileRepository
                .findById(userDto.getProfileUserId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado"));

        RoleUser role = roleRepository
                .findById(userDto.getRoleUserId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado"));

        ContactUser contact = new ContactUser();
        contact.setEmailUser(contactDto.getEmailUser());
        contact.setPhoneNumberUser(contactDto.getPhoneNumberUser());

        UserSys user = new UserSys();
        user.setUserName(userDto.getUserName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setProfile(profile);
        user.setRole(role);
        user.setContactUser(contact);

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