package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PROFILE_USER")
public class ProfileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROF_USER_ID")
    private @Getter @Setter Long id;

    @Column(name = "USER_PROFILE", length = 50)
    private @Getter @Setter String userProfile;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    @JsonIgnore
    private @Getter @Setter Set<UserSys> users;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProfileUser that = (ProfileUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProfileUser{" +
                "id=" + id +
                ", userProfile='" + userProfile + '\'' +
                '}';
    }
}