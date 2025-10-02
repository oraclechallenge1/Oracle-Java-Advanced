package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Dispensation;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.DispensationDTO;
import br.com.fiap.medsave.ProjectMedSave.service.DispensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dispensations")
@RequiredArgsConstructor
public class DispensationApiController {

    private final DispensationService dispensationService;

    @PostMapping
    public ResponseEntity<DispensationDTO> create(@Valid @RequestBody DispensationDTO dispensationDTO) {
        Dispensation dispensationToCreate = DispensationDTO.toEntity(dispensationDTO);
        Dispensation createdDispensation = dispensationService.create(dispensationToCreate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(DispensationDTO.fromEntity(createdDispensation));
    }

    @GetMapping
    public ResponseEntity<List<DispensationDTO>> findAll() {
        List<Dispensation> dispensations = dispensationService.findAll();
        List<DispensationDTO> dispensationDTOS = dispensations.stream()
                .map(DispensationDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dispensationDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispensationDTO> findById(@PathVariable Long id) {
        Dispensation dispensation = dispensationService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dispensation record not found with ID: " + id
                ));

        return ResponseEntity.ok(DispensationDTO.fromEntity(dispensation));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DispensationDTO> partialUpdate(@PathVariable Long id,
                                                         @Valid @RequestBody DispensationDTO dispensationDTO) {
        try {
            Dispensation dispensationUpdatePayload = DispensationDTO.toEntity(dispensationDTO);
            Dispensation updatedDispensation = dispensationService.partialUpdate(id, dispensationUpdatePayload);
            return ResponseEntity.ok(DispensationDTO.fromEntity(updatedDispensation));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!dispensationService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dispensation record not found with ID: " + id);
        }
        dispensationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody DispensationDTO dispensationDTO) {
        Dispensation dispensationToDelete = DispensationDTO.toEntity(dispensationDTO);
        dispensationService.delete(dispensationToDelete);
        return ResponseEntity.noContent().build();
    }
}