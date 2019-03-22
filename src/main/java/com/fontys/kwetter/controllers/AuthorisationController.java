package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.services.UserService;
import com.fontys.kwetter.utils.SessionUtils;

import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * Controller for all authorisation related methods (logging in, registering etc.)
 * This controller uses beans with JSF to implement authorisation.
 * JSF is only used here to prove learning goals.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Named("authorisation")
@SessionScoped
//@Path("authorisation")
public class AuthorisationController implements Serializable {

  @Inject @Named("userService")
  private UserService userService;
  private ObjectMapper mapper = new ObjectMapper();

  // JSF related variables + setters
  private String username;
  private String password;
  private User loggedInUser;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User getLoggedInUser() {
    return loggedInUser;
  }

  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  public String login() {
    // TODO: Implement login()
    System.out.println("<<< I AM LOGGING IN...");
    System.out.println("<<< USERNAME: " + username);
    System.out.println("<<< PASSWORD: " + password);
    try {
      loggedInUser = userService.logIn(username, password);
      System.out.println("<<< RETRIEVED USER " + loggedInUser);
      if (loggedInUser != null) {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("UUID", loggedInUser.getUuid());
        return "admin";
      } else {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Incorrect username and password",
                        "Please enter the correct username and password combination!"));
        return "login";
      }
    } catch (EJBTransactionRolledbackException | PersistenceException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String logout() {
    HttpSession session = SessionUtils.getSession();
    session.invalidate();
    return "login";
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("register")
  public Response register() {
    // TODO: Implement register()
    return Response.ok().build();
  }
}
