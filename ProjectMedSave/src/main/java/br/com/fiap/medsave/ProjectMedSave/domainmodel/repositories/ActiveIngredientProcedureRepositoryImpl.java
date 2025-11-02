package br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ActiveIngredientProcedureRepositoryImpl implements ActiveIngredientProcedureRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long createActiveIngredient(String nameActiveIngre) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("oracle_challenge_create_active_ingredient");

        query.registerStoredProcedureParameter("p_act_ingredient", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_act_ingre_id", Long.class, ParameterMode.OUT);

        query.setParameter("p_act_ingredient", nameActiveIngre);

        query.execute();

        Object outValue = query.getOutputParameterValue("p_act_ingre_id");
        return (outValue != null) ? ((Number) outValue).longValue() : null;
    }

    @Override
    public void updateActiveIngredient(Long id, String newName) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("oracle_challenge_update_active_ingredient");

        query.registerStoredProcedureParameter("p_act_ingre_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_act_ingredient", String.class, ParameterMode.IN);

        query.setParameter("p_act_ingre_id", id);
        query.setParameter("p_act_ingredient", newName);

        query.execute();
    }

    @Override
    public void deleteActiveIngredient(Long id) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("oracle_challenge_delete_active_ingredient");

        query.registerStoredProcedureParameter("p_act_ingre_id", Long.class, ParameterMode.IN);

        query.setParameter("p_act_ingre_id", id);

        query.execute();
    }

}
