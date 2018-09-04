package md.ramaiana.ramaianawebapp.service;

import md.ramaiana.ramaianawebapp.dao.AppUserDao;
import md.ramaiana.ramaianawebapp.enums.Role;
import md.ramaiana.ramaianawebapp.model.AppRole;
import md.ramaiana.ramaianawebapp.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserDao appUserDao;

    @Autowired
    public UserDetailsServiceImpl(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {

        AppUser appUser = appUserDao.getByEMail(eMail);
        if (appUser == null) {
            throw new UsernameNotFoundException("AppUser not found");
        }

        Set<AppRole> roles = appUser.getRoles();
        String username = appUser.getEMail();
        String password = appUser.getPassword();

        return new org.springframework.security.core
                .userdetails.User(username, password, getAuthorities(roles));
    }

    private static List<GrantedAuthority> getAuthorities(Set<AppRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().toString()))
                .collect(Collectors.toList());
    }

}
