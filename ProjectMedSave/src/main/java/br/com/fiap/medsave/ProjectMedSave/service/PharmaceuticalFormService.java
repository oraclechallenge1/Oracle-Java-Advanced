package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.PharmaceuticalForm;

import java.util.List;
import java.util.Optional;

public interface PharmaceuticalFormService {
    List<PharmaceuticalForm> findAll();
    Optional<PharmaceuticalForm> findById(Long id);
    PharmaceuticalForm create(PharmaceuticalForm pf);
    boolean existsById(Long id);
    void removeById(Long id);
    PharmaceuticalForm update(Long id, PharmaceuticalForm pf);
}
