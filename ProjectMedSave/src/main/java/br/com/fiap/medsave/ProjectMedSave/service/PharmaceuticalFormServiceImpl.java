package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.PharmaceuticalForm;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.PharmaceuticalFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PharmaceuticalFormServiceImpl implements PharmaceuticalFormService {

    private final PharmaceuticalFormRepository repository;

    @Override
    public List<PharmaceuticalForm> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<PharmaceuticalForm> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public PharmaceuticalForm create(PharmaceuticalForm pf) {
        return repository.save(pf);
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
    public PharmaceuticalForm update(Long id, PharmaceuticalForm pf) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("Entity not found");
        pf.setId(id);
        return repository.save(pf);
    }
}
