package com.fontys.kwetter.dao;

import com.fontys.kwetter.domain.User;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
public interface AuthorisationDAO {

  /**
   * Checks username and password combination for correctness.
   *
   * @param username
   * @param password
   * @return Instance of user if username and password combination is correct, else null object.
   */
  User login(String username, String password);
}
