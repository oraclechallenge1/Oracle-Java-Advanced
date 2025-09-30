package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.StockBatch;

import java.util.List;
import java.util.Optional;

public interface StockBatchService {

    List<StockBatch> findAll();
    Optional<StockBatch> findById(Long id);
    StockBatch create(StockBatch stockBatch);
    StockBatch partialUpdate(Long id, StockBatch stockBatch);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(StockBatch stockBatch);

}
