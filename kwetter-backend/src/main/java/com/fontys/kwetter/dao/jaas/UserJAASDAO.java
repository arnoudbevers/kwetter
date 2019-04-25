package com.fontys.kwetter.dao.jaas;

import com.fontys.kwetter.domain.Group;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.utils.AuthenticationUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless
public class UserJAASDAO {

  @PersistenceContext
  private EntityManager em;

  public User createUser(User user) {
    try {
      user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
    } catch (Exception e) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
      e.printStackTrace();
    }
    Group group = new Group();
    group.setEmail(user.getEmail());
    group.setGroupname(Group.ADMIN_GROUP);
    em.persist(user);
    em.persist(group);

    return user;
  }
  public User findUserById(String email) {
    TypedQuery<User> query = em.createNamedQuery("user.getByEmail", User.class);
    query.setParameter("email", email);
    User user = null;
    try {
      user = query.getSingleResult();
    } catch (Exception e) {
      // getSingleResult throws NoResultException in case there is no user in DB
      // ignore exception and return NULL for user instead
    }
    return user;
  }

  public List<User> getAllUsers() {
    Query query = em.createNamedQuery("user.getAll", User.class);
    return query.getResultList();
  }

  public void deleteUser(Long id) {
    Query query = em.createNamedQuery("user.remove", User.class);
    query.setParameter("id", id);
    query.executeUpdate();
  }

}
