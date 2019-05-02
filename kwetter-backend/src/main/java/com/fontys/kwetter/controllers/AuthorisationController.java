package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.security.JWTValidator;
import com.fontys.kwetter.services.UserService;
import com.fontys.kwetter.utils.SessionUtils;

import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.RequestScoped;
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
@Named
@RequestScoped
@Path("auth")
public class AuthorisationController implements Serializable {

  @Inject @Named("userService")
  private UserService userService;
  private ObjectMapper mapper = new ObjectMapper();

  @Path("login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login() {
    System.out.println(JWTValidator.createJWT("arnoudbevers"));
    return Response.ok().build();
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
