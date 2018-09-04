package md.ramaiana.ramaianawebapp.model;

import lombok.Data;
import md.ramaiana.ramaianawebapp.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "ROLES")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<AppUser> users;

    public AppRole(Role role) {
        this.role = role;
    }

}
