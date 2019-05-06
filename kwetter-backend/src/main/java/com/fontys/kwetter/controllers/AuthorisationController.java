package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.domain.api.Credentials;
import com.fontys.kwetter.security.JWTValidator;
import com.fontys.kwetter.services.UserService;
import com.fontys.kwetter.utils.SessionUtils;
import org.json.JSONObject;

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
import java.util.UUID;

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

  @POST
  @Path("login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(Credentials credentials) {
    // 1. Call login method - return User object
    User user = userService.logIn(credentials.getUsername(), credentials.getPassword());
    // 2. Check if user is null - this means login has failed!
    if(user == null) {
      return Response.status(400).entity("Username and password are incorrect!").build();
    }
    // 3. Use username and UUID in JWT generator
    String jwt = JWTValidator.createJWT(user.getUsername(), user.getUuid());
    String json = new JSONObject().put("jwt_token", jwt).toString();
    return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
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
