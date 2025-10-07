package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS_STOCK")
public class AddressStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID_STOCK")
    private @Getter @Setter Long id;

    @Column(name = "COMPLEMENT", length = 255)
    private @Getter @Setter String complement;

    @Column(name = "NUMBER", nullable = false, length = 7)
    private @Getter @Setter String number;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private @Getter @Setter String description;

    @Column(name = "CEP", nullable = false, length = 8)
    private @Getter @Setter String cep;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NEIGH_ID", nullable = false, unique = true)
    private @Getter @Setter Neighbourhood neighbourhood;

    @OneToOne(mappedBy = "addressStock", fetch = FetchType.LAZY)
    private @Getter @Setter Location location;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddressStock that = (AddressStock) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "AddressStock{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", cep='" + cep + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
