package com.fontys.kwetter.dao.jpa;

import com.fontys.kwetter.dao.UserDAO;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.utils.HashedPassword;
import com.fontys.kwetter.utils.PasswordEncrypt;

import javax.ejb.Stateless;
import javax.inject.Named;
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
@Named("userDAO")
public class UserDAOJPAImpl implements UserDAO {

  @PersistenceContext(unitName = "kwetterPU")
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
  public User getUserByUUID(String uuid) {
    Query query = em.createNamedQuery("user.getByUUID", User.class);
    query.setParameter("uuid", uuid);
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

  @Override
  public User login(String username, String password) {
    // 1. Get salt from user where username = equal to given username
    Query query = em.createQuery("select u from User as u where u.username = :username");
    query.setParameter("username", username);
    User u = (User) query.getSingleResult();
    // 2. Check if password in retrieved user is the same as
    // the given password
    HashedPassword checkPassword = PasswordEncrypt.hashPassword(password, u.getSalt());
    if(!checkPassword.getPassword().equals(u.getPassword())) { // 3. If passwords are not equal
      return null; // Return null object
    }
    return u; // Else return retrieved user object
  }

  @Override
  public User register(User user) {
    if (searchUsersByUsername(user.getUsername()) != null) {
      return null;
    }
    // Encrypt password
    HashedPassword hashedPassword = PasswordEncrypt.hashPassword(user.getPassword(), "");
    user.setPassword(hashedPassword.getPassword());
    user.setSalt(hashedPassword.getSalt());
    addUser(user);
    // This makes sure a UUID gets returned
    return searchUsersByUsername(user.getUsername()).get(0);
  }

}
