package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public boolean addUser(User user, String roles) {
        if (user.getFirstName() != null && user.getLastName() != null && user.getPassword() != null
                && user.getEmail() != null && user.getAge() != 0) {
            user.setRoles(getRoleForUser(roles));
            userDao.addUser(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateUser(User user, String roles) {
        if (user.getId() != null && user.getFirstName() != null && user.getLastName() != null && user.getPassword() != null
                && user.getEmail() != null && user.getAge() != 0 && roles != null) {

            user.setRoles(getRoleForUser(roles));
            userDao.updateUser(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteUser(User user) {
        if (user.getId() != null) {
            userDao.deleteUser(user.getId());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userMayBy = Optional.ofNullable(userDao.getUserByName(email));
        return userMayBy.orElseThrow(IllegalAccessError::new);
    }

    @Override
    public Set<Role> getRoleForUser(String role) {
        Set<Role> roles = new HashSet<>();
        try {
            String[] splitRoles = role.split(",");
            roles.add(new Role(splitRoles[1]));
            roles.add(new Role(splitRoles[0]));
            return roles;
        } catch (Exception e) {

        }
        roles.add(new Role(role));
        return roles;
    }

    @Transactional
    @Override
    public String ifPasswordNull(Long id, String password) {
        if (password.trim().length() == 0) {
            return userDao.getUserById(id).getPassword();
        } else {
            return password;
        }
    }
}
