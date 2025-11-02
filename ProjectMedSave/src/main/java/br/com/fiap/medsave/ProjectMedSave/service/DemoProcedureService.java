package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.ActiveIngredientProcedureRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.CategoryMedicineProcedureRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DemoProcedureService {

    private final ActiveIngredientProcedureRepository activeIngredientProcRepo;
    private final CategoryMedicineProcedureRepository categoryMedicineProcRepo;

    public DemoProcedureService(
            ActiveIngredientProcedureRepository activeIngredientProcRepo,
            CategoryMedicineProcedureRepository categoryMedicineProcRepo
    ) {
        this.activeIngredientProcRepo = activeIngredientProcRepo;
        this.categoryMedicineProcRepo = categoryMedicineProcRepo;
    }

    @Transactional
    public void executarDemonstracaoActiveIngredient() {

        Long id1 = activeIngredientProcRepo.createActiveIngredient("Amoxicilina");
        Long id2 = activeIngredientProcRepo.createActiveIngredient("Ibuprofeno");

        activeIngredientProcRepo.updateActiveIngredient(id1, "Amoxicilina 500mg");
        activeIngredientProcRepo.updateActiveIngredient(id2, "Ibuprofeno 400mg");

        activeIngredientProcRepo.deleteActiveIngredient(id1);
        activeIngredientProcRepo.deleteActiveIngredient(id2);
    }

    @Transactional
    public void executarDemonstracaoCategoryMedicine() {

        Long cat1 = categoryMedicineProcRepo.createCategoryMedicine("Antibiótico");
        Long cat2 = categoryMedicineProcRepo.createCategoryMedicine("Analgésico");

        categoryMedicineProcRepo.updateCategoryMedicine(cat1, "Antibiótico Sistêmico");
        categoryMedicineProcRepo.updateCategoryMedicine(cat2, "Analgésico Oral");

        categoryMedicineProcRepo.deleteCategoryMedicine(cat1);
        categoryMedicineProcRepo.deleteCategoryMedicine(cat2);
    }


    @Transactional
    public void executarDemonstracaoCompletaDuasTabelas() {
        executarDemonstracaoActiveIngredient();
        executarDemonstracaoCategoryMedicine();
    }

}
