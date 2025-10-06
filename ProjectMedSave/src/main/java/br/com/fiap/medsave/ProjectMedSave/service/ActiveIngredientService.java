package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ActiveIngredient;

import java.util.List;
import java.util.Optional;

public interface ActiveIngredientService {
    List<ActiveIngredient> findAll();
    Optional<ActiveIngredient> findById(Long id);
    ActiveIngredient create(ActiveIngredient ai);
    boolean existsById(Long id);
    void removeById(Long id);
    ActiveIngredient update(Long id, ActiveIngredient ai);
}
