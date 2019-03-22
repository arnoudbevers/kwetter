package com.fontys.kwetter.dao;

import com.fontys.kwetter.domain.User;
import java.util.List;

/**
 * DAO interface containing all methods related to the User model.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
public interface UserDAO {

  /**
   * Gets all users.
   *
   * @return List of all users.
   */
  List<User> getAll();

  /**
   * Gets a user by its id.
   *
   * @param id Id of the user to find.
   * @return User object corresponding to given id.
   */
  User getUserById(int id);

  /**
   * Gets user by its username.
   *
   * @param username Username of the user to find.
   * @return User object corresponding to given username.
   */
  List<User> searchUsersByUsername(String username);

  /**
   * Gets the list of users, the given user is following.
   *
   * @param user User whose 'following' to get.
   * @return List of users, following list of given user.
   */
  List<User> getFollowingForUser(User user);

  /**
   * Gets the list of users, the given user is being followed by.
   *
   * @param user User whose 'followers' to get.
   * @return List of users, followers list of given user.
   */
  List<User> getFollowersForUser(User user);

  /**
   * Follows a user.
   *
   * @param user         User object, who is following someone.
   * @param userToFollow User object, which user to follow.
   */
  void followUser(User user, User userToFollow);

  /**
   * Unfollows a user.
   *
   * @param user           User object, who will unfollow someone.
   * @param userToUnfollow User object of which user to unfollow.
   */
  void unfollowUser(User user, User userToUnfollow);

  /**
   * Registers/adds a user.
   *
   * @param user User object to add.
   */
  void addUser(User user);

  /**
   * Edits the information of a user.
   *
   * @param user User object to edit.
   */
  void editUser(User user);

  /**
   * Removes/deletes a user.
   *
   * @param user User object to remove
   */
  void removeUser(User user);

  /**
   * Checks username and password combination for correctness.
   *
   * @param username
   * @param password
   * @return Instance of user if username and password combination is correct, else null object.
   */
  User login(String username, String password);

  boolean register(String username, String email, String password);

}
