package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

public interface CategoryMedicineProcedureRepository {

    Long createCategoryMedicine(String categoryName);
    void updateCategoryMedicine(Long id, String newCategoryName);
    void deleteCategoryMedicine(Long id);

}
