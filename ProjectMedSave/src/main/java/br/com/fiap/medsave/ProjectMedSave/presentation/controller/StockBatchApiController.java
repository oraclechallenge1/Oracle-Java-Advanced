package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.StockBatch;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockBatchDTO;
import br.com.fiap.medsave.ProjectMedSave.service.StockBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stockbatches")
@RequiredArgsConstructor
public class StockBatchApiController {

    private final StockBatchService stockBatchService;

    @PostMapping
    public ResponseEntity<StockBatchDTO> create(@Valid @RequestBody StockBatchDTO batchDTO) {
        StockBatch batchToCreate = StockBatchDTO.toEntity(batchDTO);
        StockBatch createdBatch = stockBatchService.create(batchToCreate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StockBatchDTO.fromEntity(createdBatch));
    }

    @GetMapping
    public ResponseEntity<List<StockBatchDTO>> findAll() {
        List<StockBatch> batches = stockBatchService.findAll();
        List<StockBatchDTO> batchDTOS = batches.stream()
                .map(StockBatchDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(batchDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockBatchDTO> findById(@PathVariable Long id) {
        StockBatch batch = stockBatchService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Stock Batch not found with ID: " + id
                ));
        return ResponseEntity.ok(StockBatchDTO.fromEntity(batch));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StockBatchDTO> partialUpdate(@PathVariable Long id,
                                                       @Valid @RequestBody StockBatchDTO batchDTO) {
        try {
            StockBatch batchUpdatePayload = StockBatchDTO.toEntity(batchDTO);
            StockBatch updatedBatch = stockBatchService.partialUpdate(id, batchUpdatePayload);
            return ResponseEntity.ok(StockBatchDTO.fromEntity(updatedBatch));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!stockBatchService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock Batch not found with ID: " + id);
        }
        stockBatchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody StockBatchDTO batchDTO) {
        StockBatch batchToDelete = StockBatchDTO.toEntity(batchDTO);
        stockBatchService.delete(batchToDelete);
        return ResponseEntity.noContent().build();
    }
}