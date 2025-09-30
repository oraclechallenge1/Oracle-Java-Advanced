package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.StockBatch;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockBatchDTO {

    private Long batchId;

    @NotNull(message = "O ID do medicamento (FK) é obrigatório.")
    @Positive(message = "O ID do medicamento deve ser um valor positivo.")
    private Long medicineId;

    @NotBlank(message = "O número do lote é obrigatório.")
    @Size(max = 255, message = "O número do lote deve ter no máximo 255 caracteres.")
    private String numberBatch;

    @NotNull(message = "A data de entrada é obrigatória.")
    private LocalDate dateEntry;

    @NotNull(message = "A data de validade é obrigatória.")
    @Future(message = "A data de validade deve ser futura.")
    private LocalDate dateValidity;

    @NotNull(message = "A quantidade atual é obrigatória.")
    @Positive(message = "A quantidade atual deve ser positiva.")
    private Long currentQuantity; // Numeric(8)

    @NotNull(message = "O custo unitário é obrigatório.")
    @DecimalMin(value = "0.01", message = "O custo unitário deve ser maior que zero.")
    private BigDecimal unitaryCost;

    @NotBlank(message = "A localização é obrigatória.")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres.")
    private String locationMedicine;

    @NotBlank(message = "O fornecedor é obrigatório.")
    @Size(max = 150, message = "O fornecedor deve ter no máximo 150 caracteres.")
    private String supplier;

    @NotBlank(message = "O status é obrigatório.")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres.")
    private String status;

    public static StockBatch toEntity(StockBatchDTO dto) {
        if (dto == null) return null;

        Medicine medicine = Medicine.builder().medicineId(dto.getMedicineId()).build();

        return StockBatch.builder()
                .batchId(dto.getBatchId())
                .medicine(medicine)
                .numberBatch(dto.getNumberBatch())
                .dateEntry(dto.getDateEntry())
                .dateValidity(dto.getDateValidity())
                .currentQuantity(dto.getCurrentQuantity())
                .unitaryCost(dto.getUnitaryCost())
                .locationMedicine(dto.getLocationMedicine())
                .supplier(dto.getSupplier())
                .status(dto.getStatus())
                .build();
    }

    public static StockBatchDTO fromEntity(StockBatch entity) {
        if (entity == null) return null;

        Long medicineId = Optional.ofNullable(entity.getMedicine())
                .map(Medicine::getMedicineId)
                .orElse(null);

        return StockBatchDTO.builder()
                .batchId(entity.getBatchId())
                .medicineId(medicineId) // Usa o ID para o DTO
                .numberBatch(entity.getNumberBatch())
                .dateEntry(entity.getDateEntry())
                .dateValidity(entity.getDateValidity())
                .currentQuantity(entity.getCurrentQuantity())
                .unitaryCost(entity.getUnitaryCost())
                .locationMedicine(entity.getLocationMedicine())
                .supplier(entity.getSupplier())
                .status(entity.getStatus())
                .build();
    }
}