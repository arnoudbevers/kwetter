package com.fontys.kwetter.beans;

import com.fontys.kwetter.dao.jaas.UserJAASDAO;
import com.fontys.kwetter.domain.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arnoud Bevers
 * @project kwetter-backend
 */
@ManagedBean
@SessionScoped
public class AdminView {

  @Inject
  private UserJAASDAO userJAASDAO;
  private List<User> users = new ArrayList<>();

  @PostConstruct
  public void init() {
    getAllUsers();
  }

  private void getAllUsers() {
    try {
      this.users = userJAASDAO.getAllUsers();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void deleteUser(Long id) {
    try {
      this.userJAASDAO.deleteUser(id);
      getAllUsers();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
