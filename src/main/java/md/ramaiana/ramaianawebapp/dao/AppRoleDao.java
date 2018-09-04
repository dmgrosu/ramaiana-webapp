package md.ramaiana.ramaianawebapp.dao;

import md.ramaiana.ramaianawebapp.enums.Role;
import md.ramaiana.ramaianawebapp.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleDao extends JpaRepository<AppRole, Integer> {

    public AppRole getByRole(Role role);

}
