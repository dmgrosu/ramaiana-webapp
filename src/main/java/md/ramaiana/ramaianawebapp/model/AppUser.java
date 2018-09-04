package md.ramaiana.ramaianawebapp.model;

import lombok.Data;
import md.ramaiana.ramaianawebapp.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String eMail;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<AppRole> roles;
}
