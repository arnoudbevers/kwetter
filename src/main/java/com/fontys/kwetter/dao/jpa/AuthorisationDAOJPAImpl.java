package com.fontys.kwetter.dao.jpa;

import com.fontys.kwetter.dao.AuthorisationDAO;
import com.fontys.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless
public class AuthorisationDAOJPAImpl implements AuthorisationDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  public User login(String username, String password) {
    Query query = em.createNamedQuery("user.login", User.class);
    query.setParameter("username", username);
    query.setParameter("password", password);
    return (User) query.getSingleResult();
  }
}
