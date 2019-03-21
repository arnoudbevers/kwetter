package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.AuthorisationDAO;
import com.fontys.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Service for the authorisation related methods defined in the DAO.
 * Used by the API controllers to call the needed methods.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless
public class AuthorisationService {

  @Inject
  private AuthorisationDAO authDAO;

  public User logIn(String username, String password) {
    return authDAO.login(username, password);
  }
}
