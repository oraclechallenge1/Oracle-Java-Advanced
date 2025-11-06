package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.service.ActiveIngredientProcedureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/proc/active-ingredient")
@Tag(name = "Active Ingredient Procedure", description = "Procedures of Active Ingredient - JUST TEST FOR DB")
public class ActiveIngredientProcedureController {

    private final ActiveIngredientProcedureService activeService;

    public ActiveIngredientProcedureController(ActiveIngredientProcedureService activeService) {
        this.activeService = activeService;
    }

    // POST /api/proc/active-ingredient/insert
    // body: { "name": "Amoxicilina" }
    @PostMapping("/insert")
    public ResponseEntity<String> insertActiveIngredient(@RequestBody CreateActiveRequest request) {
        Long newId = activeService.createActiveIngredient(request.name());
        return ResponseEntity.ok("Princípio ativo criado via procedure com ID = " + newId);
    }

    // PUT /api/proc/active-ingredient/update
    // body: { "id": 10, "newName": "Amoxicilina 500mg" }
    @PutMapping("/update")
    public ResponseEntity<String> updateActiveIngredient(@RequestBody UpdateActiveRequest request) {
        activeService.updateActiveIngredient(request.id(), request.newName());
        return ResponseEntity.ok("Princípio ativo " + request.id() + " atualizado via procedure.");
    }
    // DELETE /api/proc/active-ingredient/delete/10
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActiveIngredient(@PathVariable Long id) {
        activeService.deleteActiveIngredient(id);
        return ResponseEntity.ok("Princípio ativo " + id + " removido via procedure.");
    }

    public record CreateActiveRequest(String name) {}
    public record UpdateActiveRequest(Long id, String newName) {}
}