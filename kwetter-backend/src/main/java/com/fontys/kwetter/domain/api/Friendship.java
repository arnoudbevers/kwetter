package com.fontys.kwetter.domain.api;

/**
 * @author Arnoud
 * @project kwetter-backend
 */
public class Friendship {
  private String following;
  private String followed;

  public Friendship() {
  }

  public Friendship(String following, String followed) {
    this.following = following;
    this.followed = followed;
  }

  public String getFollowing() {
    return following;
  }

  public void setFollowing(String following) {
    this.following = following;
  }

  public String getFollowed() {
    return followed;
  }

  public void setFollowed(String followed) {
    this.followed = followed;
  }

  @Override
  public String toString() {
    return "Friendship{" +
            "following='" + following + '\'' +
            ", followed='" + followed + '\'' +
            '}';
  }
}
