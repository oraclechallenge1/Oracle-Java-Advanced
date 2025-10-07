package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.CategoryMedicine;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.CategoryMedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryMedicineServiceImpl implements CategoryMedicineService {

    private final CategoryMedicineRepository repository;

    @Override
    public List<CategoryMedicine> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Optional<CategoryMedicine> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CategoryMedicine create(CategoryMedicine cm) {
        return repository.save(cm);
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
    public CategoryMedicine update(Long id, CategoryMedicine cm) {
        if (!repository.existsById(id)) throw new IllegalArgumentException("Entity not found");
        cm.setId(id);
        return repository.save(cm);
    }
}
