package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.MedicineRepository;
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
    public Medicine update(Long id, Medicine medicine) {
        if (!this.repository.existsById(id)) {
            throw new IllegalArgumentException("Entity not found.");
        }
        medicine.setId(id);
        return this.repository.save(medicine);
    }
}
