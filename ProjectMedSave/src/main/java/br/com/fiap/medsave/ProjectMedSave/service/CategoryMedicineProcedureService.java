package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.CategoryMedicineProcedureRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CategoryMedicineProcedureService {

    private final CategoryMedicineProcedureRepository categoryRepo;

    public CategoryMedicineProcedureService(CategoryMedicineProcedureRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public Long createCategory(String categoryName) {
        return categoryRepo.createCategoryMedicine(categoryName);
    }

    @Transactional
    public void updateCategory(Long id, String newCategoryName) {
        categoryRepo.updateCategoryMedicine(id, newCategoryName);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepo.deleteCategoryMedicine(id);
    }

}
