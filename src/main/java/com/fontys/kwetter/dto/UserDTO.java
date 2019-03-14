package com.fontys.kwetter.dto;

import com.fontys.kwetter.domain.User;

import javax.ejb.Stateful;
import java.util.ArrayList;

/**
 * Data transfer object, carrying the user data between processes.
 * Mostly handles the serialization of user to prevent infinite loops.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateful
public class UserDTO {

  public UserDTO() {
  }

  public User simplifyUser(User user) {
    for (User u : user.getFollowers()) {
      u.setFollowers(new ArrayList<>());
      u.setFollowing(new ArrayList<>());
    }
    for (User u : user.getFollowing()) {
      u.setFollowers(new ArrayList<>());
      u.setFollowing(new ArrayList<>());
    }
    return user;
  }
}
