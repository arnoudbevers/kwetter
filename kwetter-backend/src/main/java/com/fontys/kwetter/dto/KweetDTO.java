package com.fontys.kwetter.dto;

import java.sql.Timestamp;

/**
 * @author Arnoud
 * @project kwetter-backend
 */
public class KweetDTO {
  private Long id;
  private String message;
  private long sent;
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

  public long getSent() {
    return sent;
  }

  public void setSent(long sent) {
    this.sent = sent;
  }

  public UserDTO getSender() {
    return sender;
  }

  public void setSender(UserDTO sender) {
    this.sender = sender;
  }
}
