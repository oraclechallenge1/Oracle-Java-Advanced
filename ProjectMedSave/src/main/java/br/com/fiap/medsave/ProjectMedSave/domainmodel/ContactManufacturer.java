package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CONTACT_MANUFACTURER")
public class ContactManufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_MANU_ID")
    private @Getter @Setter Long id;

    @Column(name = "EMAIL_MANU", unique = true, nullable = false, length = 255)
    private @Getter @Setter String emailManu;

    @Column(name = "PHONE_NUMBER_MANU", unique = true, nullable = false, length = 11)
    private @Getter @Setter String phoneNumberManu;

    @OneToOne(mappedBy = "contactManufacturer", fetch = FetchType.LAZY)
    private @Getter @Setter Manufacturer manufacturer;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContactManufacturer that = (ContactManufacturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "ContactManufacturer{" +
                "id=" + id +
                ", emailManu='" + emailManu + '\'' +
                ", phoneNumberManu='" + phoneNumberManu + '\'' +
                '}';
    }
}