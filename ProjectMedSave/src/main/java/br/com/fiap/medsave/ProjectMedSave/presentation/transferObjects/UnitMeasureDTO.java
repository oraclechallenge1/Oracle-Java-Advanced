package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.UnitMeasure;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class UnitMeasureDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Unidade de medida é obrigatória")
    @Size(max = 20, message = "Unidade deve ter no máximo 20 caracteres")
    private String unit;

    public static UnitMeasureDTO fromEntity(UnitMeasure e) {
        if (e == null) return null;
        return UnitMeasureDTO.builder()
                .id(e.getId())
                .unit(e.getUnitMeasureMedicine())
                .build();
    }

    public static UnitMeasure toEntity(UnitMeasureDTO d) {
        if (d == null) return null;
        UnitMeasure e = new UnitMeasure();
        e.setId(d.getId());
        e.setUnitMeasureMedicine(d.getUnit());
        return e;
    }
}
