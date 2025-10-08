package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "LOCATION_STOCK")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_LOCATION", nullable = false, length = 30)
    private @Getter @Setter String nameLocation;

    @Column(name = "LOCATION_STOCK_NAME", nullable = false, length = 100)
    private @Getter @Setter String locationStock;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID_STOCK", nullable = false, unique = true)
    private @Getter @Setter AddressStock addressStock;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Stock> stocks;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", nameLocation='" + nameLocation + '\'' +
                ", locationStock='" + locationStock + '\'' +
                '}';
    }
}
