package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.*;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.*;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.BatchReceiptDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchReceiptServiceImpl implements BatchReceiptService {

    private final BatchRepository batchRepository;
    private final StockRepository stockRepository;
    private final MedicineRepository medicineRepository;
    private final LocationRepository locationRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Transactional
    @Override
    public Long registerReceipt(BatchReceiptDTO dto) {

        Medicine medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found."));

        LocationStock location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found."));

        Manufacturer manufacturer = manufacturerRepository.findById(dto.getManufacturerId())
                .orElseThrow(() -> new EntityNotFoundException("Manufacturer not found."));

        Batch batch = new Batch();
        batch.setBatchNumber(dto.getBatchNumber());
        batch.setManufacturingDate(dto.getManufacturingDate());
        batch.setExpirationDate(dto.getExpirationDate());
        batch.setCurrentQuantity(dto.getQuantity());
        batch.setManufacturer(manufacturer);
        batch = batchRepository.save(batch);

        Stock stock = new Stock();
        stock.setMedicine(medicine);
        stock.setBatch(batch);
        stock.setLocation(location);
        stock.setQuantity(dto.getQuantity());
        stockRepository.save(stock);

        return batch.getId();
    }
}
