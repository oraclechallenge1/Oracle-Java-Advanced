package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UserSysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSysServiceImpl implements UserSysService {

    private final UserSysRepository repository;

    @Override
    public UserSys create(UserSys userSys) {
        return this.repository.save(userSys);
    }

    @Override
    public List<UserSys> findAll() {
        return new ArrayList<>(
                this.repository.findAll()
        );
    }

    @Override
    public Optional<UserSys> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(UserSys userSys) {
        if (userSys != null && userSys.getId() != null) {
            this.repository.delete(userSys);
        }
    }

    @Override
    public UserSys update(Long id, UserSys userSys) {
        return null;
    }
}
