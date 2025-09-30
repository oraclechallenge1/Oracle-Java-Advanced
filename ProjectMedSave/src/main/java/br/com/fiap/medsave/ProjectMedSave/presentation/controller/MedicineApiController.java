package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.MedicineDTO;
import br.com.fiap.medsave.ProjectMedSave.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class MedicineApiController {

    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineDTO> create(@Valid @RequestBody MedicineDTO medicineDTO) {
        Medicine medicineToCreate = MedicineDTO.toEntity(medicineDTO);
        Medicine createdMedicine = medicineService.create(medicineToCreate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MedicineDTO.fromEntity(createdMedicine));
    }

    @GetMapping
    public ResponseEntity<List<MedicineDTO>> findAll() {
        List<Medicine> medicines = medicineService.findAll();
        List<MedicineDTO> medicineDTOS = medicines.stream()
                .map(MedicineDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(medicineDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> findById(@PathVariable Long id) {
        Medicine medicine = medicineService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Medicine not found with ID: " + id
                ));

        return ResponseEntity.ok(MedicineDTO.fromEntity(medicine));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicineDTO> partialUpdate(@PathVariable Long id,
                                                     @Valid @RequestBody MedicineDTO medicineDTO) {
        try {
            Medicine medicineUpdatePayload = MedicineDTO.toEntity(medicineDTO);
            Medicine updatedMedicine = medicineService.partialUpdate(id, medicineUpdatePayload);
            return ResponseEntity.ok(MedicineDTO.fromEntity(updatedMedicine));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!medicineService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found with ID: " + id);
        }
        medicineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody MedicineDTO medicineDTO) {
        Medicine medicineToDelete = MedicineDTO.toEntity(medicineDTO);
        medicineService.delete(medicineToDelete);
        return ResponseEntity.noContent().build();
    }
}