package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.StockBatch;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.StockBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockBatchServiceImpl implements StockBatchService{

    private final StockBatchRepository stockBatchRepository;

    @Override
    public List<StockBatch> findAll() {
        return this.stockBatchRepository.findAll();
    }

    @Override
    public Optional<StockBatch> findById(Long id) {
        return this.stockBatchRepository.findById(id);
    }

    @Override
    public StockBatch create(StockBatch stockBatch) {
        return this.stockBatchRepository.save(stockBatch);
    }

    @Override
    public StockBatch partialUpdate(Long id, StockBatch stockBatch) {

        StockBatch batchFromDatabase = this.stockBatchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stock Batch not found for ID: " + id));

        Optional.ofNullable(stockBatch.getNumberBatch())
                .ifPresent(batchFromDatabase::setNumberBatch);

        Optional.ofNullable(stockBatch.getDateEntry())
                .ifPresent(batchFromDatabase::setDateEntry);

        Optional.ofNullable(stockBatch.getDateValidity())
                .ifPresent(batchFromDatabase::setDateValidity);

        Optional.ofNullable(stockBatch.getCurrentQuantity())
                .ifPresent(batchFromDatabase::setCurrentQuantity);

        Optional.ofNullable(stockBatch.getUnitaryCost())
                .ifPresent(batchFromDatabase::setUnitaryCost);

        Optional.ofNullable(stockBatch.getLocationMedicine())
                .ifPresent(batchFromDatabase::setLocationMedicine);

        Optional.ofNullable(stockBatch.getSupplier())
                .ifPresent(batchFromDatabase::setSupplier);

        Optional.ofNullable(stockBatch.getStatus())
                .ifPresent(batchFromDatabase::setStatus);

        Optional.ofNullable(stockBatch.getMedicine())
                .ifPresent(batchFromDatabase::setMedicine);

        return this.stockBatchRepository.save(batchFromDatabase);
    }

    @Override
    public boolean existsById(Long id) {
        return this.stockBatchRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.stockBatchRepository.deleteById(id);
    }

    @Override
    public void delete(StockBatch stockBatch) {
        this.stockBatchRepository.delete(stockBatch);
    }

}
