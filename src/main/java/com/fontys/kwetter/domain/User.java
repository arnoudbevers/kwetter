package com.fontys.kwetter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * User object containing the personal information of a user on the system.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "user.getAll", query = "select u from User as u"),
        @NamedQuery(name = "user.getById", query = "select u from User as u where u.id = :id"),
        @NamedQuery(name = "user.getByEmail", query = "select u from User as u where u.email = :email"),
        @NamedQuery(name = "user.searchByUserName", query = "select u from User as u where u.username LIKE :searchString"),
        @NamedQuery(name = "user.login", query = "select u from User as u where u.username = :username AND u.password = :password")
})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // TODO: Handle UUID registration
  // TODO: Save as String or UUID?
  @Column
  private String uuid;

  @Column(nullable = false, unique = true, length = 20)
  private String username;
  @Column(nullable = false, unique = true)
  private String email;
  @JsonIgnore
  @Column(nullable = false)
  private String password;
  @JsonIgnore
  @Column
  private String salt;
  @Column
  private String picture;
  @Column
  private String role;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Kweet> kweets;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "followers")
  private List<User> following;
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany
  private List<User> followers;


  /* User details */
  @Column
  private String location;
  @Column
  private String websiteUrl;
  @Column(length = 160)
  private String bio;


  // Constructor needed for JPA implementation
  public User() {
    this.uuid = UUID.randomUUID().toString();
    this.kweets = new ArrayList<>();
    this.following = new ArrayList<>();
    this.followers = new ArrayList<>();
  }

  public User(Role role, String email, String username) {
    super();
    this.role = role.toString();
    this.email = email;
    this.username = username;

    this.location = "";
    this.websiteUrl = "";
    this.bio = "";
    this.picture = "";
  }

  public User(Role role, String email, String username, String password) {
    this(role, email, username);
    this.password = password;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
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

  public String getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role.toString();
  }

  public List<Kweet> getKweets() {
    return kweets;
  }

  public void setKweets(List<Kweet> kweets) {
    this.kweets = kweets;
  }

  public List<User> getFollowing() {
    return following;
  }

  public void setFollowing(List<User> following) {
    this.following = following;
  }

  public List<User> getFollowers() {
    return followers;
  }

  public void setFollowers(List<User> followers) {
    this.followers = followers;
  }


  public void postKweet(Kweet kweet) {
    this.kweets.add(kweet);
  }

//    public void likeKweet(Kweet kweet) {
//        kweet.addLike(this);
//    }

  public void follow(User user) {
//    if (user.equals(this)) {
//      throw new FollowException("User cannot follow itself");
//    }
    this.following.add(user);
    user.addFollower(this);
  }

  public void unfollow(User follow) {
    this.following.remove(follow);
    follow.removeFollower(this);
  }

  private void addFollower(User user) {
    this.followers.add(user);
  }

  private void removeFollower(User follower) {
    this.followers.remove(follower);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", picture='" + picture + '\'' +
            ", role='" + role + '\'' +
            '}';
  }

  // TODO: Override equals of User properly
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id.equals(user.getId()) &&
            uuid.equals(user.getUuid()) &&
            username.equals(user.getUsername()) &&
            email.equals(user.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, picture, role, location, websiteUrl, bio);
  }
}
