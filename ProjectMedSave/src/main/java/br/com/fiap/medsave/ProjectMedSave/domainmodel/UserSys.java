package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS_SYS")
public class UserSys implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private @Getter @Setter Long id;

    @Column(name = "NAME_USER", nullable = false, length = 150)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    } // com a adiçao do userdetails, o lombok nao gerou os getters e setters de userName, entao tive que criar manualmente

    @Column(name = "EMAIL", unique = true, length = 50)
    private @Getter @Setter String email;

    @Column(name = "PASSWORD_USER", length = 255)
    private @Getter @Setter String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_USER_ID", nullable = false)
    private @Getter @Setter RoleUser role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROF_USER_ID", nullable = false)
    private @Getter @Setter ProfileUser profile;

    @OneToOne(cascade = CascadeType.ALL)
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
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}