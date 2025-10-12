package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @EntityGraph(attributePaths = {
            "categoryMedicine",
            "unitMeasure",
            "activeIngredients.activeIngredient",
            "pharmForms.pharmaceuticalForm"
    })
    List<Medicine> findAll();

    @EntityGraph(attributePaths = {
            "categoryMedicine",
            "unitMeasure",
            "activeIngredients.activeIngredient",
            "pharmForms.pharmaceuticalForm"
    })
    Optional<Medicine> findById(Long id);
}
