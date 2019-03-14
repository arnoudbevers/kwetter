package dao.jpa;

import dao.UserDAO;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of the UserDAO for JPA.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless
public class UserDAOJPAImpl implements UserDAO {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<User> getAll() {
    Query query = em.createNamedQuery("user.getAll", User.class);
    return (List<User>) query.getResultList();
  }

  @Override
  public User getUserById(int id) {
    Query query = em.createNamedQuery("user.getById", User.class);
    query.setParameter("id", id);
    try {
      return (User) query.getSingleResult();
    } catch (NoResultException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<User> searchUsersByUsername(String username) {
    Query query = em.createNamedQuery("user.searchByUserName", User.class);
    query.setParameter("searchString", "%" + username + "%");
    try {
      return (List<User>) query.getResultList();
    } catch (NoResultException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<User> getFollowingForUser(User user) {
    User findUser = getUserById(Math.toIntExact(user.getId()));
    return findUser.getFollowing();
  }

  @Override
  public List<User> getFollowersForUser(User user) {
    User findUser = getUserById(Math.toIntExact(user.getId()));
    return findUser.getFollowers();
  }

  @Override
  public void followUser(User user, User userToFollow) {
    // TODO: Check if this works, if two users are needed
    em.merge(user);
    em.merge(userToFollow);
  }

  @Override
  public void unfollowUser(User user, User userToUnfollow) {
    em.merge(user);
    em.merge(userToUnfollow);
  }

  @Override
  public void addUser(User user) {
    em.persist(user);
  }

  @Override
  public void editUser(User user) {
    em.merge(user);
  }

  @Override
  public void removeUser(User user) {
    em.remove(user);
  }
}
