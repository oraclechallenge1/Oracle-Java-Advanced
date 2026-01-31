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

    @Column(name = "EMAIL_USER", unique = true, nullable = false, length = 255)
    private @Getter @Setter String emailUser;

    @Column(name = "PHONE_NUMBER_USER", unique = true, nullable = false, length = 11)
    private @Getter @Setter String phoneNumberUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactUser)) return false;
        return id != null && id.equals(((ContactUser) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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
