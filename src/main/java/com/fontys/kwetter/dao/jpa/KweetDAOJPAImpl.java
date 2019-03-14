package dao.jpa;

import dao.KweetDAO;
import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of the Kweet for JPA.
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless
public class KweetDAOJPAImpl implements KweetDAO {

  @PersistenceContext
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
