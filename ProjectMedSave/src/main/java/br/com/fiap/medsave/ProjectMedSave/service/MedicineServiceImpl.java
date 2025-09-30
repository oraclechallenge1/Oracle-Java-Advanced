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
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findAll() {
        return new ArrayList<>(
                this.medicineRepository.findAll()
        );
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        if (!this.medicineRepository.existsById(id)) {
           throw new IllegalArgumentException("Medicine not found.");
        }
        return this.medicineRepository.findById(id);
    }

    @Override
    public Medicine create(Medicine medicine) {
        return this.medicineRepository.save(medicine);
    }

    @Override
    public Medicine partialUpdate(Long id, Medicine medicine) {
        if (!this.medicineRepository.existsById(id)) {
            throw new IllegalArgumentException("Medicine not found.");
        }
        Medicine medicideFromDatabase = this.medicineRepository.findById(id).orElse(null);

        if (!medicideFromDatabase.getComercialName().equals(medicine.getComercialName())) {
            medicideFromDatabase.setComercialName(medicine.getComercialName());
        }
        if (!medicideFromDatabase.getActiveIngredient().equals(medicine.getActiveIngredient())) {
            medicideFromDatabase.setActiveIngredient(medicine.getActiveIngredient());
        }
        if (!medicideFromDatabase.getPharmaceuticalForm().equals(medicine.getPharmaceuticalForm())) {
            medicideFromDatabase.setPharmaceuticalForm(medicine.getPharmaceuticalForm());
        }
        if (!medicideFromDatabase.getUnitMeasure().equals(medicine.getUnitMeasure())) {
            medicideFromDatabase.setUnitMeasure(medicine.getUnitMeasure());
        }
        if (!medicideFromDatabase.getManufacturer().equals(medicine.getManufacturer())) {
            medicideFromDatabase.setManufacturer(medicine.getManufacturer());
        }
        if (!medicideFromDatabase.getAnvisaRecord().equals(medicine.getAnvisaRecord())) {
            medicideFromDatabase.setAnvisaRecord(medicine.getAnvisaRecord());
        }
        if (!medicideFromDatabase.getCategoryMedicine().equals(medicine.getCategoryMedicine())) {
            medicideFromDatabase.setCategoryMedicine(medicine.getCategoryMedicine());
        }
        if (!medicideFromDatabase.getMinimalStock().equals(medicine.getMinimalStock())) {
            medicideFromDatabase.setMinimalStock(medicine.getMinimalStock());
        }
        if (!medicideFromDatabase.getStatus().equals(medicine.getStatus())) {
            medicideFromDatabase.setStatus(medicine.getStatus());
        }
        return this.create(medicideFromDatabase);
    }

    @Override
    public boolean existsById(Long id) {
        return this.medicineRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.medicineRepository.deleteById(id);
    }

    @Override
    public void delete(Medicine medicine) {
        this.medicineRepository.delete(medicine);
    }
}
