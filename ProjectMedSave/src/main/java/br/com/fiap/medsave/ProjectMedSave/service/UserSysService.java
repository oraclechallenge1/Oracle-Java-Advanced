package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.ContactUserCreateDTO;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UserSysCreateDTO;

import java.util.List;
import java.util.Optional;

public interface UserSysService {


    // create
    UserSys create(UserSysCreateDTO userDto);

    // get
    List<UserSys> findAll();
    // get id
    Optional<UserSys> findById(Long id);
    // remove
    void delete(UserSys userSys);
    // update
    UserSys update(Long id, UserSys userSys);

}
