package com.fontys.kwetter.dao.jpa;

import com.fontys.kwetter.dao.KweetDAO;
import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of the Kweet DAO for JPA.
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless @Named("kweetDAO")
public class KweetDAOJPAImpl implements KweetDAO {

  @PersistenceContext(unitName = "kwetterPU")
  private EntityManager em;

  @Override
  public List<Kweet> getAll() {
    Query query = em.createNamedQuery("kweet.getAll", Kweet.class);
    return (List<Kweet>) query.getResultList();
  }

  @Override
  public List<Kweet> getKweetsForUser(User user) {
    Query query = em.createNamedQuery("kweet.getForUser", Kweet.class);
    query.setParameter("sender", user);
    return (List<Kweet>) query.getResultList();
  }

  @Override
  public Kweet getKweetById(int id) {
    Query query = em.createNamedQuery("kweet.getById", Kweet.class);
    query.setParameter("id", id);
    return (Kweet) query.getSingleResult();
  }

  @Override
  public List<Kweet> searchKweets(String searchString) {
    Query query = em.createNamedQuery("kweet.search", Kweet.class);
    query.setParameter("searchString", searchString);
    return (List<Kweet>) query.getResultList();
  }

  @Override
  public void postKweet(Kweet kweet) {
    em.persist(kweet);
  }

  @Override
  public void removeKweet(Kweet kweet) {
    em.remove(kweet);
  }

//  @Override
//  public void likeKweet(User user, Kweet kweet) {
//
//  }
}
