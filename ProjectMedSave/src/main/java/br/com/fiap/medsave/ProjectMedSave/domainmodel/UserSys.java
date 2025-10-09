package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS_SYS")
public class UserSys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_USER", nullable = false, length = 150)
    private @Getter @Setter String userName;

    @Column(name = "PASSWORD_USER", length = 255)
    private @Getter @Setter String password;

    @Column(name = "LOGIN", unique = true, length = 50)
    private @Getter @Setter String login;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROF_USER_ID", nullable = false)
    private @Getter @Setter ProfileUser profile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POS_USER_ID", nullable = false)
    private @Getter @Setter PositionUser position;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTACT_USER_ID", nullable = false, unique = true)
    private @Getter @Setter ContactUser contactUser;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserSys userSys = (UserSys) o;
        return Objects.equals(id, userSys.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public String toString() {
        return "UserSys{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}