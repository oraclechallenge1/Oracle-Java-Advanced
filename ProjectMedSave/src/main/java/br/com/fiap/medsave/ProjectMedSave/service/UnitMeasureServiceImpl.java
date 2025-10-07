package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UnitMeasure;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UnitMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitMeasureServiceImpl implements UnitMeasureService {

    private final UnitMeasureRepository repository;

    @Override
    public List<UnitMeasure> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<UnitMeasure> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UnitMeasure create(UnitMeasure um) {
        return repository.save(um);
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
    public UnitMeasure update(Long id, UnitMeasure um) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("Entity not found");
        um.setId(id);
        return repository.save(um);
    }
}
