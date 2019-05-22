package com.fontys.kwetter.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Kweet object - similar to a Tweet.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "kweet.getAll",
                query = "SELECT k FROM Kweet k"),
        @NamedQuery(name = "kweet.getById",
                query = "SELECT k FROM Kweet k where k.id = :id"),
        @NamedQuery(name = "kweet.getForUser",
                query = "SELECT k FROM Kweet k where k.sender = :sender"),
        @NamedQuery(name = "kweet.search",
                query = "SELECT k FROM Kweet k where k.message LIKE CONCAT('%', :searchString, '%')")
})
public class Kweet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 140, nullable = false)
  private String message;
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private long sent;

  // TODO: Vragen hoe je die met JPA persist
//    @OneToMany(mappedBy = "kweet")
//    private List<User> likes;

  @ManyToOne
  @JsonBackReference
  private User sender;

  public Kweet() {
    this.sent = System.currentTimeMillis() / 1000L;
  }

  public Kweet(String message, User sender) {
    super();
    this.message = message;
    this.sender = sender;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getSent() {
    return sent;
  }

  public void setSent(long sent) {
    this.sent = sent;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  //    public List<User> getLikes() {
//        return likes;
//    }
//
//    public void setLikes(List<User> likes) {
//        this.likes = likes;
//    }
//
//    public void addLike(User user) {
//        this.likes.add(user);
//    }

  @Override
  public String toString() {
    return "Kweet {" +
            "id=" + id +
            ", message='" + message + '\'' +
            ", sent=" + sent +
            ", sender=" + sender +
            '}';
  }
}
