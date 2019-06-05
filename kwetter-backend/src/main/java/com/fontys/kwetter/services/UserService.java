package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.KweetDAO;
import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.security.HashedPassword;
import com.fontys.kwetter.security.PasswordEncrypt;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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

  @Inject @Named("kweetDAO")
  private KweetDAO kweetDAO;

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

  public User updateUser(User user) {
    return userDAO.editUser(user);
  }

  public List<Kweet> getKweetsForUser(User user) {
    List<Kweet> kweets = kweetDAO.getKweetsForUser(user);
    for (Kweet k : kweets) {
      k.setSender(user);
    }
    kweets.sort(new Kweet.KweetComparator());
    return kweets;
  }

  public List<Kweet> getTimeline(User user) {
    List<Kweet> timeline = new ArrayList<>();
    for(User u : user.getFollowing()) {
      timeline.addAll(this.getKweetsForUser(u)); //Add kweets for each following
    }
    timeline.addAll(this.getKweetsForUser(user)); //Add users own kweets
    timeline.sort(new Kweet.KweetComparator());
    // Sort timeline
    return timeline;
  }
}
