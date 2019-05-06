package com.fontys.kwetter.dto;

import java.sql.Timestamp;

/**
 * @author Arnoud
 * @project kwetter-backend
 */
public class KweetDTO {
  private Long id;
  private String message;
  private Timestamp sent;
  private UserDTO sender;

  private KweetDTO() { }

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

  public Timestamp getSent() {
    return sent;
  }

  public void setSent(Timestamp sent) {
    this.sent = sent;
  }

  public UserDTO getSender() {
    return sender;
  }

  public void setSender(UserDTO sender) {
    this.sender = sender;
  }
}
