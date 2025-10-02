package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Dispensation;

import java.util.List;
import java.util.Optional;

public interface DispensationService {

    List<Dispensation> findAll();
    Optional<Dispensation> findById(Long id);
    Dispensation create(Dispensation dispensation);
    Dispensation partialUpdate(Long id, Dispensation dispensation);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(Dispensation dispensation);

}
