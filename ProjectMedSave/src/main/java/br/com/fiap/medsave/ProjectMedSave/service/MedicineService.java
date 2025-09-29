package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    /*
    * create medicine
    * get all medicine
    * get medicine by id
    * update object medicine
    * partial update medicine by id
    * delete medicine
    * delete by id
    * */

    List<Medicine> findAll();
    Optional<Medicine> findById(Long id);
    Medicine create(Medicine medicine);
    void delete(Medicine medicine);
    Medicine partialUpdate(Long id, Medicine medicine);
    boolean existsById(Long id);
    void deleteById(Long id);

}
