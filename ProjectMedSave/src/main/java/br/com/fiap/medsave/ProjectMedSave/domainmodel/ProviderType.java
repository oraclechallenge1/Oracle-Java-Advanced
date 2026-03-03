package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PROVIDER_TYPE")
public class ProviderType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVIDER_TYPE_ID")
    private @Getter @Setter Long id;

    @Column(name = "PROVIDER_NAME", nullable = false, length = 100)
    private @Getter @Setter String providerName;

    @OneToMany(mappedBy = "providerType", fetch = FetchType.LAZY)
    private @Getter @Setter Set<HealthcareProvider> healthcareProviders;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProviderType that = (ProviderType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProviderType{" +
                "id=" + id +
                ", providerName='" + providerName + '\'' +
                '}';
    }
}
