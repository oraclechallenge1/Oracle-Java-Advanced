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
@Table(name = "ROLE_USER")
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_USER_ID")
    private @Getter @Setter Long id;

    @Column(name = "USER_ROLE", nullable = false, length = 100)
    private @Getter @Setter String userRole;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    private @Getter @Setter Set<UserSys> users;

    @Override public boolean equals(Object o){
        if(o==null||getClass()!=o.getClass())
            return false;
        return Objects.equals(
                id, ((RoleUser)o).id);
    }
    @Override public int hashCode(){
        return Objects.hashCode(id);
    }
    @Override public String toString(){
        return "RoleUser{" +
                "id="+id+", " +
                "userRole='"+userRole+"'" +
                "}";
    }
}