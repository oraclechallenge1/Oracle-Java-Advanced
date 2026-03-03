package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.HealthcareProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long> {
}
