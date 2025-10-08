package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.MedicineDTO;
import br.com.fiap.medsave.ProjectMedSave.service.MedicineService;
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
@RequestMapping("/api/medicines")
@Tag(name = "Medicines", description = "Operações de remédio")
public class MedicineApiController {

    private final MedicineService medicineService;

    @Operation(summary = "Listar todos os medicamentos", method = "GET")
    @GetMapping
    public ResponseEntity<List<MedicineDTO>> findAll(){
        return ResponseEntity.ok(
                this.medicineService.findAll()
                        .stream()
                        .map(MedicineDTO::fromEntity)
                        .toList()
        );
    }

    @Operation(summary = "Buscar medicamento por ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> findById(@PathVariable("id") Long id){
        return this.medicineService.findById(id)
                .map(MedicineDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar novo medicamento", method = "POST")
    @PostMapping
    public ResponseEntity<MedicineDTO> save(@Valid @RequestBody MedicineDTO dto){
        Medicine saved = this.medicineService.save(MedicineDTO.toEntity(dto));
        return new ResponseEntity<>(MedicineDTO.fromEntity(saved), HttpStatus.CREATED);
    }

    @Operation(summary = "Remover por ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!this.medicineService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.medicineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualização total (PUT)", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<MedicineDTO> update(@PathVariable("id") Long id, @Valid @RequestBody MedicineDTO dto){
        if (!this.medicineService.existsById(id))
            return ResponseEntity.notFound().build();
        Medicine updated = this.medicineService.update(id, MedicineDTO.toEntity(dto));
        return new ResponseEntity<>(MedicineDTO.fromEntity(updated), HttpStatus.CREATED);
    }

}
