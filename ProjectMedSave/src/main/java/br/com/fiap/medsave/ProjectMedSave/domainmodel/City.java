package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_CITY", nullable = false, length = 255)
    private @Getter @Setter String nameCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATE_ID", nullable = false)
    private @Getter @Setter State state;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Neighbourhood> neighborhoods;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", nameCity='" + nameCity + '\'' +
                ", stateId=" + (state != null ? state.getId() : "null") +
                '}';
    }
}