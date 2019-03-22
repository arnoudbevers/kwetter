package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.dao.jpa.UserDAOJPAImpl;
import com.fontys.kwetter.domain.User;

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

  public UserService() {
    this.userDAO = new UserDAOJPAImpl();
  }

  public void createUser(User user) {
    userDAO.addUser(user);
  }

  public List<User> getUserByUsername(String username) {
    return userDAO.searchUsersByUsername(username);
  }

  public User getUserById(int id) {
    return userDAO.getUserById(id);
  }

  public List<User> getAllUsers() {
    return userDAO.getAll();
  }

  public User logIn(String username, String password) {
    return userDAO.login(username, password);
  }

}
