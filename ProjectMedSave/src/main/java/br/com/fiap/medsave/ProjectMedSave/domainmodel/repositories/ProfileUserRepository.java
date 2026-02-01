package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Long> {
}
