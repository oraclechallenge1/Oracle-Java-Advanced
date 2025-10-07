package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "UNIT_MEASURE")
public class UnitMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIT_MEA_ID")
    private @Getter @Setter Long id;

    @Column(name = "UNIT_MEASURE_MEDICINE", nullable = false, length = 20)
    private @Getter @Setter String unitMeasureMedicine;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnitMeasure that = (UnitMeasure) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UnitMeasure{" +
                "id=" + id +
                ", unitMeasureMedicine='" + unitMeasureMedicine + '\'' +
                '}';
    }
}