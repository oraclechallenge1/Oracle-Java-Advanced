package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NEIGHBOURHOOD")
public class Neighbourhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEIGH_ID")
    private @Getter @Setter Long id;

    @Column(name = "NEIGH_NAME", nullable = false, length = 255)
    private @Getter @Setter String neighName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private @Getter @Setter City city;

    @OneToOne(mappedBy = "neighbourhood", fetch = FetchType.LAZY)
    private @Getter @Setter AddressStock addressStock;

    @OneToMany(mappedBy = "neighbourhood", fetch = FetchType.LAZY)
    private @Getter @Setter Set<AddressManufacturer> addressManufacturers;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Neighbourhood that = (Neighbourhood) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Neighborhood{" +
                "id=" + id +
                ", neighName='" + neighName + '\'' +
                ", cityId=" + (city != null ? city.getId() : "null") +
                '}';
    }

}
