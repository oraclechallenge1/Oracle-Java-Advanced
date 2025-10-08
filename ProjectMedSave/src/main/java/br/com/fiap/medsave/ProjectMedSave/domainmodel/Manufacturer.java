package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MANUFACTURER")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANUFAC_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_MANU", nullable = false, length = 255)
    private @Getter @Setter String nameManu;

    @Column(name = "CNPJ", unique = true, nullable = false, length = 14)
    private @Getter @Setter String cnpj;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTACT_MANU_ID", nullable = false, unique = true)
    private @Getter @Setter ContactManufacturer contactManufacturer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID_MANUFACTURER", nullable = false, unique = true)
    private @Getter @Setter AddressManufacturer addressManufacturer;

    @OneToOne(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private @Getter @Setter Batch batch;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", nameManu='" + nameManu + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}