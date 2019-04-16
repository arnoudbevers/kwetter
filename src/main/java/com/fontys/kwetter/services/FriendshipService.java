package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.domain.User;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Service for the methods related to friendships (follower/following).
 * Used by the API controllers to call the needed methods.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateful
@Named("friendshipService")
public class FriendshipService {
  // TODO: Move all friendship methods to this service
  @Inject
  @Named("userDAO")
  private UserDAO userDAO;

  public void follow(User instigator, User userToFollow) {
    instigator.follow(userToFollow);
    userDAO.followUser(instigator, userToFollow);
  }

  public void unfollow(User instigator, User userToUnfollow) {
    instigator = userDAO.getUserById(Math.toIntExact(instigator.getId()));
    userToUnfollow = userDAO.getUserById(Math.toIntExact(userToUnfollow.getId()));
    System.out.println(instigator);
    System.out.println(userToUnfollow);
    instigator.unfollow(userToUnfollow);
    userDAO.unfollowUser(instigator, userToUnfollow);
  }


}
