package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.LocationStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationStock, Long> {
}
