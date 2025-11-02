package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

public interface ActiveIngredientProcedureRepository {

    Long createActiveIngredient(String nameActiveIngre);

    void updateActiveIngredient(Long id, String newName);

    void deleteActiveIngredient(Long id);

}
