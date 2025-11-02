package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.ActiveIngredientProcedureRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActiveIngredientProcedureService {

    private final ActiveIngredientProcedureRepository activeRepo;

    public ActiveIngredientProcedureService(ActiveIngredientProcedureRepository activeRepo) {
        this.activeRepo = activeRepo;
    }

    @Transactional
    public Long createActiveIngredient(String name) {
        return activeRepo.createActiveIngredient(name);
    }

    @Transactional
    public void updateActiveIngredient(Long id, String newName) {
        activeRepo.updateActiveIngredient(id, newName);
    }

    @Transactional
    public void deleteActiveIngredient(Long id) {
        activeRepo.deleteActiveIngredient(id);
    }

}
