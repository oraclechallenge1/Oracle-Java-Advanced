package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "HEALTHCARE_PROVIDERS")
public class HealthcareProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEALTHCARE_PROVIDER_ID")
    private @Getter @Setter Long id;

    @Column(name = "PROVIDER_NAME", nullable = false, length = 30)
    private @Getter @Setter String providerName;

    @Column(name = "HEALTHCARE_PROVIDER_NAME", nullable = false, length = 100)
    private @Getter @Setter String healthcareProviderName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID_STOCK", nullable = false, unique = true)
    private @Getter @Setter AddressStock addressStock;

    @OneToMany(mappedBy = "healthcareProvider", fetch = FetchType.LAZY)
    private @Getter @Setter Set<Stock> stocks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVIDER_TYPE_ID", nullable = false)
    private @Getter @Setter ProviderType providerType;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HealthcareProvider that = (HealthcareProvider) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HealthcareProvider{" +
                "id=" + id +
                ", providerName='" + providerName + '\'' +
                ", healthcareProviderName='" + healthcareProviderName + '\'' +
                ", addressStock=" + addressStock +
                ", stocks=" + stocks +
                '}';
    }
}
