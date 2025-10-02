package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Dispensation;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.DispensationDTO;
import br.com.fiap.medsave.ProjectMedSave.service.DispensationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Dispensation", description = "Dispensation operations")
public class DispensationApiController {

    private final DispensationService dispensationService;

    @Operation(summary = "Insert dispensation", method = "POST")
    @PostMapping
    public ResponseEntity<DispensationDTO> create(@Valid @RequestBody DispensationDTO dispensationDTO) {
        Dispensation dispensationToCreate = DispensationDTO.toEntity(dispensationDTO);
        Dispensation createdDispensation = dispensationService.create(dispensationToCreate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(DispensationDTO.fromEntity(createdDispensation));
    }

    @Operation(summary = "Return dispensations", method = "GET")
    @GetMapping
    public ResponseEntity<List<DispensationDTO>> findAll() {
        List<Dispensation> dispensations = dispensationService.findAll();
        List<DispensationDTO> dispensationDTOS = dispensations.stream()
                .map(DispensationDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dispensationDTOS);
    }

    @Operation(summary = "Return dispensations by ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<DispensationDTO> findById(@PathVariable Long id) {
        Dispensation dispensation = dispensationService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dispensation record not found with ID: " + id
                ));

        return ResponseEntity.ok(DispensationDTO.fromEntity(dispensation));
    }

    @Operation(summary = "Partial Update dispensation by ID", method = "PATCH")
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

    @Operation(summary = "Delete dispensation by ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!dispensationService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dispensation record not found with ID: " + id);
        }
        dispensationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all dispensations", method = "DELETE")
    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody DispensationDTO dispensationDTO) {
        Dispensation dispensationToDelete = DispensationDTO.toEntity(dispensationDTO);
        dispensationService.delete(dispensationToDelete);
        return ResponseEntity.noContent().build();
    }
}