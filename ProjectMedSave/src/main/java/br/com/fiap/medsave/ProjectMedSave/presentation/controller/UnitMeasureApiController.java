package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UnitMeasure;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.UnitMeasureDTO;
import br.com.fiap.medsave.ProjectMedSave.service.UnitMeasureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unit-measures")
@Tag(name = "Unit Measure", description = "Operações relacionadas a unidade de medida")
public class UnitMeasureApiController {

    private final UnitMeasureService service;

    @GetMapping
    public ResponseEntity<List<UnitMeasureDTO>> findAll() {
        List<UnitMeasureDTO> list = service.findAll()
                .stream()
                .map(UnitMeasureDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitMeasureDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(UnitMeasureDTO.fromEntity(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnitMeasureDTO> create(@Valid @RequestBody UnitMeasureDTO dto) {
        UnitMeasure created = service.create(UnitMeasureDTO.toEntity(dto));
        return new ResponseEntity<>(UnitMeasureDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitMeasureDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody UnitMeasureDTO dto) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        UnitMeasure updated = service.update(id, UnitMeasureDTO.toEntity(dto));
        return ResponseEntity.ok(UnitMeasureDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
