package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Dispensation;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.StockBatch;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.UserSys;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispensationDTO {

    private Long dispensationId;

    @NotNull(message = "O ID do usuário é obrigatório.")
    @Positive(message = "O ID do usuário deve ser um valor positivo.")
    private Long userId;

    @NotNull(message = "O ID do lote é obrigatório.")
    @Positive(message = "O ID do lote deve ser um valor positivo.")
    private Long batchId;

    @NotNull(message = "A data de dispensa é obrigatória.")
    private LocalDateTime dateDispensation;

    @NotNull(message = "A quantidade retirada é obrigatória.")
    @Positive(message = "A quantidade retirada deve ser positiva.")
    private Integer withdrawalAmount;

    @NotBlank(message = "O setor de destino é obrigatório.")
    @Size(max = 100, message = "O setor de destino deve ter no máximo 100 caracteres.")
    private String sectorDestiny;

    @NotBlank(message = "O tipo de movimento é obrigatório.")
    @Size(max = 50, message = "O tipo de movimento deve ter no máximo 50 caracteres.")
    private String movementType;

    @Size(max = 100, message = "A observação deve ter no máximo 100 caracteres.")
    private String observation;

    public static Dispensation toEntity(DispensationDTO dto) {
        if (dto == null) return null;

        UserSys userSys = UserSys.builder().userId(dto.getUserId()).build();
        StockBatch stockBatch = StockBatch.builder().batchId(dto.getBatchId()).build();

        return Dispensation.builder()
                .dispensationId(dto.getDispensationId())
                .userSys(userSys)
                .stockBatch(stockBatch)
                .dateDispensation(dto.getDateDispensation())
                .withdrawalAmount(dto.getWithdrawalAmount())
                .sectorDestiny(dto.getSectorDestiny())
                .movementType(dto.getMovementType())
                .observation(dto.getObservation())
                .build();
    }

    public static DispensationDTO fromEntity(Dispensation entity) {
        if (entity == null) return null;

        Long userId = Optional.ofNullable(entity.getUserSys()).map(UserSys::getUserId).orElse(null);
        Long batchId = Optional.ofNullable(entity.getStockBatch()).map(StockBatch::getBatchId).orElse(null);

        return DispensationDTO.builder()
                .dispensationId(entity.getDispensationId())
                .userId(userId)
                .batchId(batchId)
                .dateDispensation(entity.getDateDispensation())
                .withdrawalAmount(entity.getWithdrawalAmount())
                .sectorDestiny(entity.getSectorDestiny())
                .movementType(entity.getMovementType())
                .observation(entity.getObservation())
                .build();
    }
}