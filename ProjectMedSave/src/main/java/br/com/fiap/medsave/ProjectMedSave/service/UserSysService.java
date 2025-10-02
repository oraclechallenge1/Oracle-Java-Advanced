package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;

import java.util.List;
import java.util.Optional;

public interface UserSysService {

    List<UserSys> findAll();
    Optional<UserSys> findById(Long id);
    UserSys create(UserSys userSys);
    UserSys partialUpdate(Long id, UserSys userSys);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(UserSys userSys);

}
