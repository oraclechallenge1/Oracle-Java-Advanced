package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UnitMeasure;

import java.util.List;
import java.util.Optional;

public interface UnitMeasureService {

    List<UnitMeasure> findAll();
    Optional<UnitMeasure> findById(Long id);
    UnitMeasure create(UnitMeasure um);
    boolean existsById(Long id);
    void removeById(Long id);
    UnitMeasure update(Long id, UnitMeasure um);

}
