package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferStockDTO {

    @NotNull
    private Long medicineId;

    @NotNull
    private Long batchId;

    @NotNull
    private Long sourceLocationId;

    @NotNull
    private Long destinationLocationId;

    @NotNull
    @Positive
    private Integer quantity;
}
