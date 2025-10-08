package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS_MANUFACTURER")
public class AddressManufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID_MANUFACTURER")
    private @Getter @Setter Long id;

    @Column(name = "COMPLEMENT", length = 255)
    private @Getter @Setter String complement;

    @Column(name = "NUMBER_MANU", nullable = false, length = 7)
    private @Getter @Setter Integer number;

    @Column(name = "ADDRESS_DESCRIPTION", nullable = false, length = 255)
    private @Getter @Setter String description;

    @Column(name = "CEP", nullable = false, length = 8)
    private @Getter @Setter String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NEIGH_ID", nullable = false)
    private @Getter @Setter Neighbourhood neighbourhood;

    @OneToOne(mappedBy = "addressManufacturer", fetch = FetchType.LAZY)
    private @Getter @Setter Manufacturer manufacturer;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddressManufacturer addressManufacturer = (AddressManufacturer) o;
        return Objects.equals(id, addressManufacturer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}