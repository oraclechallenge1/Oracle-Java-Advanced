package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.CategoryMedicine;

import java.util.List;
import java.util.Optional;

public interface CategoryMedicineService {
    List<CategoryMedicine> findAll();
    Optional<CategoryMedicine> findById(Long id);
    CategoryMedicine create(CategoryMedicine cm);
    boolean existsById(Long id);
    void removeById(Long id);
    CategoryMedicine update(Long id, CategoryMedicine cm);
}
