package com.fontys.kwetter.dto;

import com.fontys.kwetter.domain.User;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object, carrying the user data between processes.
 * Mostly handles the serialization of user to prevent infinite loops.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateful
public class UserDTO {

  private Long id;
  private String uuid;
  private String username;
  private String email;
  private String picture;
  private String location;
  private String websiteUrl;
  private String bio;
  private List<KweetDTO> kweets;
  private List<UserDTO> following;
  private List<UserDTO> followers;

  public UserDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public List<KweetDTO> getKweets() {
    return kweets;
  }

  public void setKweets(List<KweetDTO> kweets) {
    this.kweets = kweets;
  }

  public List<UserDTO> getFollowing() {
    return following;
  }

  public void setFollowing(List<UserDTO> following) {
    this.following = following;
  }

  public List<UserDTO> getFollowers() {
    return followers;
  }

  public void setFollowers(List<UserDTO> followers) {
    this.followers = followers;
  }

  /**
   *
   * @param user
   * @return
   */
  public User simplifyUser(User user) {
    for (User u : user.getFollowers()) {
      u.setKweets(new ArrayList<>());
      u.setFollowers(new ArrayList<>());
      u.setFollowing(new ArrayList<>());
    }
    for (User u : user.getFollowing()) {
      u.setKweets(new ArrayList<>());
      u.setFollowers(new ArrayList<>());
      u.setFollowing(new ArrayList<>());
    }
    return user;
  }
}
