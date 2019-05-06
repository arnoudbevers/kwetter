package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.dao.jpa.UserDAOJPAImpl;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.utils.HashedPassword;
import com.fontys.kwetter.utils.PasswordEncrypt;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Service for the User model related methods defined in the DAO.
 * Used by the API controllers to call the needed methods.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateful @Named("userService")
public class UserService {

  @Inject @Named("userDAO")
  private UserDAO userDAO;

  public void createUser(User user) {
    HashedPassword hashedPassword = PasswordEncrypt.hashPassword(user.getPassword(), "");
    user.setPassword(hashedPassword.getPassword());
    user.setSalt(hashedPassword.getSalt());
    userDAO.addUser(user);
  }

  public List<User> getUserByUsername(String username) {
    return userDAO.searchUsersByUsername(username);
  }

  public User getUserById(int id) {
    return userDAO.getUserById(id);
  }

  public User getUserByUUID(String uuid) { return userDAO.getUserByUUID(uuid); }

  public List<User> getAllUsers() {
    return userDAO.getAll();
  }

  public User logIn(String username, String password) {
    return userDAO.login(username, password);
  }

  public User register(User user) {
    return userDAO.register(user);
  }

}
