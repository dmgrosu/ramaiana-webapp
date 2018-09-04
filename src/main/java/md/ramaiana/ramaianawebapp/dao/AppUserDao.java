package md.ramaiana.ramaianawebapp.dao;

import md.ramaiana.ramaianawebapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserDao extends JpaRepository<AppUser, Integer> {

    AppUser getByEMail(String eMail);

}
