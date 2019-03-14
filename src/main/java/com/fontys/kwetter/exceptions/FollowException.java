package com.fontys.kwetter.exceptions;

import java.io.Serializable;

public class FollowException extends Exception implements Serializable {
  private static final long serialVersionUID = 1L;

  public FollowException(){}

  public FollowException(String message) {
    super(message);
  }

  public FollowException(String message, Exception e) {
    super(message, e);
  }
}
