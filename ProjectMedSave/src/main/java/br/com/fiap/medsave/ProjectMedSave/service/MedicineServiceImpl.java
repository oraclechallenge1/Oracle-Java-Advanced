package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.MedicineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements  MedicineService {

    private final MedicineRepository repository;

    @Override
    public List<Medicine> findAll() {
        return new ArrayList<>(
                this.repository.findAll()
        );
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Medicine save(Medicine medicine) {
        return this.repository.save(medicine);
    }

    @Override
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void delete(Medicine medicine) {
        if (medicine != null && medicine.getId() != null) {
            this.repository.delete(medicine);
        }
    }

    @Override
    @Transactional
    public Medicine update(Long id, Medicine incoming) {
        Medicine current = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity not found."));

        current.setNameMedication(incoming.getNameMedication());
        current.setStatusMed(incoming.getStatusMed());
        current.setAnvisaCode(incoming.getAnvisaCode());
        current.setCategoryMedicine(incoming.getCategoryMedicine());
        current.setUnitMeasure(incoming.getUnitMeasure());

        current.getActiveIngredients().clear();
        if (incoming.getActiveIngredients() != null) {
            incoming.getActiveIngredients().forEach(link -> link.setMedicine(current));
            current.getActiveIngredients().addAll(incoming.getActiveIngredients());
        }

        current.getPharmForms().clear();
        if (incoming.getPharmForms() != null) {
            incoming.getPharmForms().forEach(link -> link.setMedicine(current));
            current.getPharmForms().addAll(incoming.getPharmForms());
        }

        return repository.save(current);
    }
}
