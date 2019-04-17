package com.fontys.kwetter.dao;

import com.fontys.kwetter.domain.User;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
public interface AuthorisationDAO {

  User register(User user);
  User login(String username, String password);

}
