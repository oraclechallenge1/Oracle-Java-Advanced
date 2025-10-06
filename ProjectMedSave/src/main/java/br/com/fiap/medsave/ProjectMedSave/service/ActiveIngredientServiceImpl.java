package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ActiveIngredient;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.ActiveIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActiveIngredientServiceImpl implements ActiveIngredientService {

    private final ActiveIngredientRepository repository;

    @Override
    public List<ActiveIngredient> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<ActiveIngredient> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ActiveIngredient create(ActiveIngredient ai) {
        return repository.save(ai);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ActiveIngredient update(Long id, ActiveIngredient ai) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("Entity not found");
        ai.setId(id);
        return repository.save(ai);
    }
}
