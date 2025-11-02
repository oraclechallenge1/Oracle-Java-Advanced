package br.com.fiap.medsave.ProjectMedSave.presentation.controller;

import br.com.fiap.medsave.ProjectMedSave.service.DemoProcedureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoProcedureController {

    private final DemoProcedureService demoProcedureService;

    public DemoProcedureController(DemoProcedureService demoProcedureService) {
        this.demoProcedureService = demoProcedureService;
    }

    @PostMapping("/active-ingredient")
    public ResponseEntity<String> runActiveIngredientDemo() {
        demoProcedureService.executarDemonstracaoActiveIngredient();
        return ResponseEntity.ok("Procedures executadas em ACTIVE_INGREDIENT com sucesso (2x INSERT, 2x UPDATE, 2x DELETE).");
    }

    @PostMapping("/category-medicine")
    public ResponseEntity<String> runCategoryMedicineDemo() {
        demoProcedureService.executarDemonstracaoCategoryMedicine();
        return ResponseEntity.ok(
                "CATEGORY_MEDICINE: 2 INSERT / 2 UPDATE / 2 DELETE executados via procedure."
        );
    }

    @PostMapping("/full")
    public ResponseEntity<String> runFullDemo() {
        demoProcedureService.executarDemonstracaoCompletaDuasTabelas();
        return ResponseEntity.ok(
                "ACTIVE_INGREDIENT e CATEGORY_MEDICINE processadas com sucesso via procedure."
        );
    }

}
