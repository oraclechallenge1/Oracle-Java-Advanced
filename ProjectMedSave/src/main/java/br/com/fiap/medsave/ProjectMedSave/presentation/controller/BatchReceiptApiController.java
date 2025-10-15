package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.BatchReceiptDTO;
import br.com.fiap.medsave.ProjectMedSave.service.BatchReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/batches/receipts")
@Tag(name = "Batches", description = "Registrar um lote")
public class BatchReceiptApiController {

    private final BatchReceiptService service;

    @PostMapping
    @Operation(summary = "Registrar um novo lote no sistema.", method = "POST")
    public ResponseEntity<Long> register(@Valid @RequestBody BatchReceiptDTO dto) {
        Long batchId = service.registerReceipt(dto);
        return new ResponseEntity<>(batchId, HttpStatus.CREATED);
    }

}
