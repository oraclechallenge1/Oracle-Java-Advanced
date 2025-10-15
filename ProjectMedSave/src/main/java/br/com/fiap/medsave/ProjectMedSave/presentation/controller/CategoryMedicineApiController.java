package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.CategoryMedicine;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.CategoryMedicineDTO;
import br.com.fiap.medsave.ProjectMedSave.service.CategoryMedicineService;
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
@RequestMapping("/api/v1/categories")
@Tag(name = "Category Medicine", description = "Operações relacionadas a categorias de medicamentos")
public class CategoryMedicineApiController {

    private final CategoryMedicineService service;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", method = "GET")
    public ResponseEntity<List<CategoryMedicineDTO>> findAll() {
        List<CategoryMedicineDTO> list = service.findAll()
                .stream()
                .map(CategoryMedicineDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar categorias por ID", method = "GET")
    public ResponseEntity<CategoryMedicineDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(CategoryMedicineDTO.fromEntity(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar nova categoria", method = "POST")
    public ResponseEntity<CategoryMedicineDTO> create(@Valid @RequestBody CategoryMedicineDTO dto) {
        CategoryMedicine created = service.create(CategoryMedicineDTO.toEntity(dto));
        return new ResponseEntity<>(CategoryMedicineDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria por ID", method = "PUT")
    public ResponseEntity<CategoryMedicineDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody CategoryMedicineDTO dto) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        CategoryMedicine updated = service.update(id, CategoryMedicineDTO.toEntity(dto));
        return ResponseEntity.ok(CategoryMedicineDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover categoria por ID", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
