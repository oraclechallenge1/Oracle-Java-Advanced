package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.CategoryMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMedicineRepository extends JpaRepository<CategoryMedicine, Long> {
}
