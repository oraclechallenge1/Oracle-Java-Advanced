package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.TransferStockDTO;
import br.com.fiap.medsave.ProjectMedSave.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
@Tag(name = "Stock", description = "Operações de estoque.")
public class StockApiController {

    private final StockService stockService;

    @PostMapping("/transfer")
    @Operation(summary = "Transfere remédio de um estoque a outro", method = "POST")
    public ResponseEntity<Void> transferStock(@Valid @RequestBody TransferStockDTO dto) {
        stockService.transferStock(dto);
        return ResponseEntity.noContent().build();
    }

}
