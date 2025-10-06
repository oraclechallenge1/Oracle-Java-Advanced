package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MOVEMENT_TYPE")
public class MovementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVEMENT_TYPE_ID")
    private @Getter @Setter Long id;

    @Column(name = "TYPE", nullable = false, length = 30)
    private @Getter @Setter String type;

    @OneToMany(mappedBy = "movementType", fetch = FetchType.LAZY)
    private @Getter @Setter Set<MedicineDispense> dispenses;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MovementType that = (MovementType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MovementType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}