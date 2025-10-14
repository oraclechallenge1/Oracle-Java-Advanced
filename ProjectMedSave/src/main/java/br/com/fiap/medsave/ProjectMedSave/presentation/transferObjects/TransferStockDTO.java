package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferStockDTO {

    @NotNull(message = "ID medicine is required.")
    private Long medicineId;

    @NotNull(message = "ID batch is required.")
    private Long batchId;

    @NotNull(message = "ID source location is required.")
    private Long sourceLocationId;

    @NotNull(message = "ID destination location is required.")
    private Long destinationLocationId;

    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;
}
