package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BatchReceiptDTO {

    @NotBlank
    private String batchNumber;

    @NotNull
    private LocalDate manufacturingDate;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long medicineId;

    @NotNull
    private Long locationId;

    @NotNull
    private Long manufacturerId;

}
