package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;

import java.util.List;
import java.util.Optional;

public interface UserSysService {

    // create
    UserSys create(UserSys userSys);
    // get
    List<UserSys> findAll();
    // get id
    Optional<UserSys> findById(Long id);
    // remove
    void delete(UserSys userSys);
    // update
    UserSys update(Long id, UserSys userSys);

}
