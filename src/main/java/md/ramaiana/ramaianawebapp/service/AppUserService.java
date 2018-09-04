package md.ramaiana.ramaianawebapp.service;

import lombok.Data;
import md.ramaiana.ramaianawebapp.dao.AppRoleDao;
import md.ramaiana.ramaianawebapp.dao.AppUserDao;
import md.ramaiana.ramaianawebapp.enums.Role;
import md.ramaiana.ramaianawebapp.model.AppRole;
import md.ramaiana.ramaianawebapp.model.AppUser;
import md.ramaiana.ramaianawebapp.model.dto.AppUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Data
@Service
public class AppUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserService.class);

    private AppUserDao appUserDao;
    private AppRoleDao appRoleDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserDao appUserDao, AppRoleDao appRoleDao,
                          BCryptPasswordEncoder passwordEncoder) {
        this.appUserDao = appUserDao;
        this.appRoleDao = appRoleDao;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser findUserByEmail(String email) {
        return appUserDao.getByEMail(email);
    }

    @Transactional
    public AppUser registerNewUser(AppUserDto userDto) {
        AppUser user = appUserDao.save(convertUserDtoToUser(userDto));
        LOGGER.info(String.format("User %s was registered", user.getEMail()));
        return user;
    }

    private AppUser convertUserDtoToUser(AppUserDto userDto) {

        if (userDto == null) {
            throw new IllegalArgumentException();
        }

        AppUser user = new AppUser();
        user.setEMail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setRoles(addRolesToUser());

        return user;
    }

    private String encodePassword(final String password) {
        return passwordEncoder.encode(password);
    }

    private Set<AppRole> addRolesToUser() {

        AppRole role = appRoleDao.getByRole(Role.USER);

        if (role == null) {
            role = appRoleDao.save(new AppRole(Role.USER));
            LOGGER.info(String.format("new role %s was created", Role.USER));
        }

        Set<AppRole> roles = new HashSet<>();
        roles.add(role);

        return roles;
    }

}
