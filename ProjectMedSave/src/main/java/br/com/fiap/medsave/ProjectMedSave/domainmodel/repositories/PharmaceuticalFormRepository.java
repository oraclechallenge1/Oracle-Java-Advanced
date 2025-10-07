package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.PharmaceuticalForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmaceuticalFormRepository extends JpaRepository<PharmaceuticalForm, Long> {
}
