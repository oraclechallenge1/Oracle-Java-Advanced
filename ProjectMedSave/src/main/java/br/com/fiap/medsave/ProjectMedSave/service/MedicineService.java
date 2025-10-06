package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    List<Medicine> findAll();
    Optional<Medicine> findById(Long id);
    Medicine save(Medicine medicine);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(Medicine medicine);
    Medicine update(Medicine medicine);
    Medicine partialUpdate(Long id, Medicine medicine);

}
