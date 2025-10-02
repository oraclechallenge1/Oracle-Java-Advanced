package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UserSysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSysServiceImpl implements UserSysService {

    private final UserSysRepository userSysRepository;

    @Override
    public List<UserSys> findAll() {
        return this.userSysRepository.findAll();
    }

    @Override
    public Optional<UserSys> findById(Long id) {
        return this.userSysRepository.findById(id);
    }

    @Override
    public UserSys create(UserSys userSys) {
        return this.userSysRepository.save(userSys);
    }

    @Override
    public UserSys partialUpdate(Long id, UserSys userSys) {

        UserSys userFromDatabase = this.userSysRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("System User not found for ID: " + id));

        Optional.ofNullable(userSys.getNameUser())
                .ifPresent(userFromDatabase::setNameUser);

        Optional.ofNullable(userSys.getPositionUser())
                .ifPresent(userFromDatabase::setPositionUser);

        Optional.ofNullable(userSys.getLogin())
                .ifPresent(userFromDatabase::setLogin);

        Optional.ofNullable(userSys.getPasswordUser())
                .ifPresent(userFromDatabase::setPasswordUser);

        Optional.ofNullable(userSys.getProfileUser())
                .ifPresent(userFromDatabase::setProfileUser);

        Optional.ofNullable(userSys.getEmail())
                .ifPresent(userFromDatabase::setEmail);

        Optional.ofNullable(userSys.getPhoneNumber())
                .ifPresent(userFromDatabase::setPhoneNumber);

        return this.userSysRepository.save(userFromDatabase);
    }

    @Override
    public boolean existsById(Long id) {
        return this.userSysRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.userSysRepository.deleteById(id);
    }

    @Override
    public void delete(UserSys userSys) {
        this.userSysRepository.delete(userSys);
    }
}