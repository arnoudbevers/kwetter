package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.domain.api.Friendship;
import com.fontys.kwetter.exceptions.FollowException;

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

  @Inject
  @Named("userDAO")
  private UserDAO userDAO;

  public void createFriendship(Friendship friendship) throws FollowException {
    User following = userDAO.getUserByUUID(friendship.getFollowing());
    User followed = userDAO.getUserByUUID(friendship.getFollowed());
    following.follow(followed);
    userDAO.followUser(following, followed);
  }

  public void destroyFriendship(Friendship friendship) {
    User following = userDAO.getUserByUUID(friendship.getFollowing());
    User followed = userDAO.getUserByUUID(friendship.getFollowed());
    following.unfollow(followed);
    userDAO.unfollowUser(following, followed);
  }


}
