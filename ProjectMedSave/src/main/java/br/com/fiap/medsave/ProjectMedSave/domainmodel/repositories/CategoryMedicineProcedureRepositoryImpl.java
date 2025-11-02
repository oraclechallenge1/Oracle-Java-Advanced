package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CategoryMedicineProcedureRepositoryImpl implements CategoryMedicineProcedureRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long createCategoryMedicine(String categoryName) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("oracle_challenge_create_category_medicine");
        query.registerStoredProcedureParameter("p_category", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_category_med_id", Long.class, ParameterMode.OUT);

        query.setParameter("p_category", categoryName);

        query.execute();

        Object outValue = query.getOutputParameterValue("p_category_med_id");
        return (outValue != null) ? ((Number) outValue).longValue() : null;
    }

    @Override
    public void updateCategoryMedicine(Long id, String newCategoryName) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("oracle_challenge_update_category_medicine");

        query.registerStoredProcedureParameter("p_category_med_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_category", String.class, ParameterMode.IN);

        query.setParameter("p_category_med_id", id);
        query.setParameter("p_category", newCategoryName);

        query.execute();
    }

    @Override
    public void deleteCategoryMedicine(Long id) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("oracle_challenge_delete_category_medicine");

        query.registerStoredProcedureParameter("p_category_med_id", Long.class, ParameterMode.IN);

        query.setParameter("p_category_med_id", id);

        query.execute();
    }

}
