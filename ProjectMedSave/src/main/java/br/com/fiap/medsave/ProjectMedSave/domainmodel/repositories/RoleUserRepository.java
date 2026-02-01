package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
}
