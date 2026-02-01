package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ContactUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUserRepository extends JpaRepository<ContactUser, Long> {
}
