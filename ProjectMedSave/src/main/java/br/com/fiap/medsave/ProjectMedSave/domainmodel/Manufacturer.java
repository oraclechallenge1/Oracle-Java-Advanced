package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

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
    private @Getter @Setter String cnpj; // Mapeado como String (NUMERIC 14)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private @Getter @Setter Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTACT_MANU_ID", nullable = false)
    private @Getter @Setter ContactManufacturer contactManufacturer;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", nameManu='" + nameManu + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}