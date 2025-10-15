package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.ActiveIngredient;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.ActiveIngredientDTO;
import br.com.fiap.medsave.ProjectMedSave.service.ActiveIngredientService;
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
@RequestMapping("/api/v1/active-ingredients")
@Tag(name = "Active Ingredient", description = "Operações relacionadas a ingredientes ativos.")
public class ActiveIngredientApiController {

    private final ActiveIngredientService service;

    @GetMapping
    @Operation(summary = "Listar todos os ingredientes ativos", method = "GET")
    public ResponseEntity<List<ActiveIngredientDTO>> findAll() {
        List<ActiveIngredientDTO> list = service.findAll()
                .stream()
                .map(ActiveIngredientDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ingrediente ativo por ID", method = "GET")
    public ResponseEntity<ActiveIngredientDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(ActiveIngredientDTO.fromEntity(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo ingrediente ativo", method = "POST")
    public ResponseEntity<ActiveIngredientDTO> create(@Valid @RequestBody ActiveIngredientDTO dto) {
        ActiveIngredient created = service.create(ActiveIngredientDTO.toEntity(dto));
        return new ResponseEntity<>(ActiveIngredientDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ingrediente ativo por ID", method = "PUT")
    public ResponseEntity<ActiveIngredientDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody ActiveIngredientDTO dto) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        ActiveIngredient updated = service.update(id, ActiveIngredientDTO.toEntity(dto));
        return ResponseEntity.ok(ActiveIngredientDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover ingrediente ativo por ID", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
