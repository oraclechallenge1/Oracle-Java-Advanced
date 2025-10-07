package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.PharmaceuticalForm;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.PharmaceuticalFormDTO;
import br.com.fiap.medsave.ProjectMedSave.service.PharmaceuticalFormService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pharmaceutical-forms")
@Tag(name = "Pharmaceutical Form", description = "Operações relacionadas a forma de medicamentos.")
public class PharmaceuticalFormApiController {

    private final PharmaceuticalFormService service;

    @GetMapping
    public ResponseEntity<List<PharmaceuticalFormDTO>> findAll() {
        List<PharmaceuticalFormDTO> list = service.findAll()
                .stream()
                .map(PharmaceuticalFormDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmaceuticalFormDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(PharmaceuticalFormDTO.fromEntity(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PharmaceuticalFormDTO> create(@Valid @RequestBody PharmaceuticalFormDTO dto) {
        PharmaceuticalForm created = service.create(PharmaceuticalFormDTO.toEntity(dto));
        return new ResponseEntity<>(PharmaceuticalFormDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PharmaceuticalFormDTO> update(@PathVariable Long id,
                                                        @Valid @RequestBody PharmaceuticalFormDTO dto) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        PharmaceuticalForm updated = service.update(id, PharmaceuticalFormDTO.toEntity(dto));
        return ResponseEntity.ok(PharmaceuticalFormDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
