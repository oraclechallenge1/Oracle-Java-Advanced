package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Stock;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.BatchRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.HealthcareProviderRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.MedicineRepository;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.StockRepository;
import br.com.fiap.medsave.ProjectMedSave.messaging.StockTransferProducer;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.TransferStockDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final MedicineRepository  medicineRepository;
    private final StockRepository stockRepository;
    private final BatchRepository batchRepository;
    private final HealthcareProviderRepository locationRepository;
    private final StockTransferProducer stockTransferProducer;


    @Override
    @Transactional
    public void transferStock(TransferStockDTO dto) {
        if (dto.getSourceLocationId().equals(dto.getDestinationLocationId())) {
            throw new IllegalArgumentException("Source and destination must be different.");
        }

        var medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found."));

        var batch =  batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new IllegalArgumentException("Batch not found."));

        var sourceLocation = locationRepository.findById(dto.getSourceLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Source location not found."));

        var destinationLocation = locationRepository.findById(dto.getDestinationLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Destination location not found."));

        var sourceStock = stockRepository
                .findByMedicine_IdAndBatch_IdAndHealthcareProvider_Id(medicine.getId(), batch.getId(), sourceLocation.getId())
                .orElseThrow(() -> new IllegalArgumentException("Stock not found."));

        sourceStock.debit(dto.getQuantity());
        stockRepository.save(sourceStock);

        stockRepository.findByMedicine_IdAndBatch_IdAndHealthcareProvider_Id(medicine.getId(), batch.getId(), destinationLocation.getId())
                .ifPresentOrElse(dest -> {
                    dest.credit(dto.getQuantity());
                    stockRepository.save(dest);
                }, () -> {
                    var dest = Stock.builder()
                            .medicine(medicine)
                            .batch(batch)
                            .healthcareProvider(destinationLocation)
                            .quantity(0)
                            .build();
                    dest.credit(dto.getQuantity());
                    stockRepository.save(dest);
                });

        StockTransferredEvent event = new StockTransferredEvent(
                medicine.getId(),
                medicine.getNameMedication(),
                batch.getId(),
                batch.getBatchNumber(),
                sourceLocation.getId(),
                sourceLocation.getProviderName(),
                destinationLocation.getId(),
                destinationLocation.getProviderName(),
                dto.getQuantity(),
                LocalDateTime.now()
        );

        stockTransferProducer.sendToQueue(event);
        stockTransferProducer.sendToTopic(event);
    }
}
