package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSysRepository extends JpaRepository<UserSys, Long> {
}
