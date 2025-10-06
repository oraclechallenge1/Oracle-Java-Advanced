package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private @Getter @Setter Long id;

    // Fiel ao DER: COMPLEMENT (Sem asterisco, então nullable = true/default)
    @Column(name = "COMPLEMENT", length = 255)
    private @Getter @Setter String complement;

    // CORRIGIDO: O nome da coluna é NUMBER (Não NUMERIC) e é nullable = false (asterisco)
    @Column(name = "NUMBER", nullable = false, length = 7)
    private @Getter @Setter String number; // Mapeado como String, mas com length 7 (NUMERIC(7))

    // Fiel ao DER: DESCRIPTION (Com asterisco)
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private @Getter @Setter String description;

    // CORRIGIDO: O nome da coluna é CEP e é nullable = false (asterisco)
    @Column(name = "CEP", nullable = false, length = 8)
    private @Getter @Setter String cep; // Mapeado como String, mas com length 8 (NUMERIC(8))

    // Chave estrangeira N:1 com NEIGHBOURHOOD (Com asterisco, F*)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NEIGHB_ID", nullable = false)
    private @Getter @Setter Neighbourhood neighborhood;

    // Relacionamento 1:N com MANUFACTURER (campo de navegação)
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Manufacturer> manufacturers;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
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