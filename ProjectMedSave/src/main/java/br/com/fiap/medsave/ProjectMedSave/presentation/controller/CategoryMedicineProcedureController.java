package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.service.CategoryMedicineProcedureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/proc/category-medicine")
@Tag(name = "Category Medicine Procedure", description = "Procedures of Category Medicine")
public class CategoryMedicineProcedureController {

    private final CategoryMedicineProcedureService categoryService;

    public CategoryMedicineProcedureController(CategoryMedicineProcedureService categoryService) {
        this.categoryService = categoryService;
    }

    // POST /api/proc/category-medicine/insert
    // body JSON: { "categoryName": "Antibiótico" }
    @PostMapping("/insert")
    public ResponseEntity<String> insertCategory(@RequestBody CreateCategoryRequest request) {
        Long newId = categoryService.createCategory(request.categoryName());
        return ResponseEntity.ok("Categoria criada via procedure com ID = " + newId);
    }

    // PUT /api/proc/category-medicine/update
    // body JSON: { "id": 27, "newName": "Antibiótico Sistêmico" }
    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody UpdateCategoryRequest request) {
        categoryService.updateCategory(request.id(), request.newName());
        return ResponseEntity.ok("Categoria " + request.id() + " atualizada via procedure.");
    }

    // DELETE /api/proc/category-medicine/delete/27
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Categoria " + id + " removida via procedure.");
    }

    public record CreateCategoryRequest(String categoryName) {}
    public record UpdateCategoryRequest(Long id, String newName) {}
}
