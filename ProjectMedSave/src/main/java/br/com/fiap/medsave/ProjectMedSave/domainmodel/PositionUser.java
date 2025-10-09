package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "POSITION_USER")
public class PositionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POS_USER_ID")
    private @Getter @Setter Long id;

    @Column(name = "USER_POSITION", nullable = false, length = 100)
    private @Getter @Setter String userPosition;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private @Getter @Setter Set<UserSys> users;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PositionUser that = (PositionUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PositionUser{" +
                "id=" + id +
                ", userPosition='" + userPosition + '\'' +
                '}';
    }
}