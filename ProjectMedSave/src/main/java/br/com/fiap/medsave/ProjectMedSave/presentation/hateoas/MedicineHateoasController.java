package br.com.fiap.medsave.ProjectMedSave.presentation.hateoas;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.MedicineRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/hateoas/medicines")
public class MedicineHateoasController {

    private final MedicineRepository repository;

    public MedicineHateoasController(MedicineRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id")
    public EntityModel<ResponseEntity<Medicine>> getMedicine(@PathVariable Long id) { // deve mudar para DTO
        Medicine medicine = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return EntityModel.of(
                linkTo(methodOn(MedicineHateoasController.class).getMedicine(id)).withSelfRel(),
                linkTo(methodOn()) // aqui serao as classes que tem link com essa, por exemplo
                                   // pharma, unitMeasure...
        );
    }

    public ResponseEntity<Medicine> getAll() {
        List<EntityModel<Medicine>> medicines = this.repository.findAll()
                .stream()
                .map(medicine -> EntityModel.of(
                        medicine,
                        linkTo(methodOn(MedicineHateoasController.class)
                                .getMedicine(medicine.getId()))
                                .withSelfRel())
                )
                .toList();
        return null;
    }

}
