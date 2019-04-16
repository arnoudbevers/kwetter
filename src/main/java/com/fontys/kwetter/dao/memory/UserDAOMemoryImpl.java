package com.fontys.kwetter.dao.memory;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.domain.Role;
import com.fontys.kwetter.domain.User;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateful
@Default
public class UserDAOMemoryImpl implements UserDAO {
  private List<User> allUsers = new ArrayList<>();

  @Override
  public List<User> getAll() {
    return allUsers;
  }

  @Override
  public User getUserById(int id) {
    for (User u : allUsers) {
      if (u.getId().equals(Long.valueOf(id))) {
        return u;
      }
    }
    return null;
  }

  @Override
  public List<User> searchUsersByUsername(String username) {
    List<User> foundUsers = new ArrayList<>();
    for (User u : allUsers) {
      if (u.getUsername().contains(username)) {
        foundUsers.add(u);
      }
    }
    return foundUsers;
  }

  @Override
  public List<User> getFollowingForUser(User user) {
    for (User u : allUsers) {
      if (user.equals(u)) {
        return u.getFollowing();
      }
    }
    return null;
  }

  @Override
  public List<User> getFollowersForUser(User user) {
    for (User u : allUsers) {
      if (user.equals(u)) {
        return u.getFollowing();
      }
    }
    return null;
  }

  @Override
  public void followUser(User user, User userToFollow) {
    user.follow(userToFollow);
    editUser(user);
    editUser(userToFollow);
  }

  @Override
  public void unfollowUser(User user, User userToUnfollow) {
    user.unfollow(userToUnfollow);
    editUser(user);
    editUser(userToUnfollow);
  }

  @Override
  public void addUser(User user) {
    allUsers.add(user);
  }

  @Override
  public void editUser(User user) {
    for (User u : allUsers) {
      if (u.equals(user)) {
        allUsers.set(allUsers.indexOf(u), user);
      }
    }
  }

  @Override
  public void removeUser(User user) {
    allUsers.remove(user);
  }

  @Override
  public User login(String username, String password) {
    for (User u : allUsers) {
      if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
        return u;
      }
    }
    return null;
  }

  @Override
  public User register(User user) {
    for (User u : allUsers) {
      if (u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail())) {
        return null;
      }
    }
    allUsers.add(new User(Role.USER, user.getUsername(), user.getEmail(), user.getPassword()));
    return allUsers.get(allUsers.size() - 1);
  }

}
