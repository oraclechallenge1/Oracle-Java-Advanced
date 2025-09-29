package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS_SYS")
public class UserSys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId; // Numeric

    // Atributos da Entidade

    @Column(name = "NAME_USER", length = 150)
    private String nameUser; // Varchar(150)

    @Column(name = "POSITION_USER", length = 100)
    private String positionUser; // Varchar(100)

    @Column(name = "LOGIN", length = 50, unique = true) // Assumindo que o login é único
    private String login; // Varchar(50)

    @Column(name = "PASSWORD_USER", length = 255)
    private String passwordUser; // Varchar(255)

    @Column(name = "PROFILE_USER", length = 50)
    private String profileUser; // Varchar(50)

    @Column(name = "EMAIL", length = 255) // Não está no diagrama, mas é um bom palpite para o Varchar(255)
    private String email; // Varchar(255)

    @Column(name = "PHONE_NUMBER", length = 11)
    private String phoneNumber; // Varchar(11)

    // Relacionamento (Um Usuário pode fazer Múltiplas Dispensações)
    // Mapeamento One-to-Many com DISPENSATION
    @OneToMany(mappedBy = "userSys", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dispensation> dispensations;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserSys userSys = (UserSys) o;
        return Objects.equals(userId, userSys.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public String toString() {
        return "UserSys{" +
                "userId=" + userId +
                ", nameUser='" + nameUser + '\'' +
                ", positionUser='" + positionUser + '\'' +
                ", login='" + login + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", profileUser='" + profileUser + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dispensations=" + dispensations +
                '}';
    }
}
