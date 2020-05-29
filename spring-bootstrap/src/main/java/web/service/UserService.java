package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public boolean addUser(User user, String roles);

    public boolean updateUser(User user, String roles);

    public boolean deleteUser(User user);

    public List<User> getAllUser();

    public Set<Role> getRoleForUser(String role);

    public String ifPasswordNull(Long id, String password);
}
