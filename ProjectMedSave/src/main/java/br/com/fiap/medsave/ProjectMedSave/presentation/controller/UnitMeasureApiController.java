package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UnitMeasure;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UnitMeasureDTO;
import br.com.fiap.medsave.ProjectMedSave.service.UnitMeasureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/unit-measures")
@Tag(name = "Unit Measure", description = "Operações relacionadas a unidade de medida")
public class UnitMeasureApiController {

    private final UnitMeasureService service;

    @GetMapping
    @Operation(summary = "Listar todas as unidades de medida", method = "GET")
    public ResponseEntity<List<UnitMeasureDTO>> findAll() {
        List<UnitMeasureDTO> list = service.findAll()
                .stream()
                .map(UnitMeasureDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar unidade de medida por ID", method = "GET")
    public ResponseEntity<UnitMeasureDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(UnitMeasureDTO.fromEntity(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar nova unidade de medida", method = "POST")
    public ResponseEntity<UnitMeasureDTO> create(@Valid @RequestBody UnitMeasureDTO dto) {
        UnitMeasure created = service.create(UnitMeasureDTO.toEntity(dto));
        return new ResponseEntity<>(UnitMeasureDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar unidade de medida por ID", method = "PUT")
    public ResponseEntity<UnitMeasureDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody UnitMeasureDTO dto) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        UnitMeasure updated = service.update(id, UnitMeasureDTO.toEntity(dto));
        return ResponseEntity.ok(UnitMeasureDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar unidade de medida por ID", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
