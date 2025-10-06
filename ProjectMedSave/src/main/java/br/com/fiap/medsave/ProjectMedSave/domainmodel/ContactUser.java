package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CONTACT_USER")
public class ContactUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_USER_ID")
    private @Getter @Setter Long id;

    @Column(name = "EMAIL_USER", unique = true, nullable = false, length = 200)
    private @Getter @Setter String emailUser;

    @Column(name = "PHONE_NUMBER_USER", unique = true, nullable = false, length = 15)
    private @Getter @Setter String phoneNumberUser;

    @OneToOne(mappedBy = "contactUser", fetch = FetchType.LAZY)
    private @Getter @Setter UserSys userSys;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContactUser that = (ContactUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ContactUser{" +
                "id=" + id +
                ", emailUser='" + emailUser + '\'' +
                ", phoneNumberUser='" + phoneNumberUser + '\'' +
                '}';
    }
}