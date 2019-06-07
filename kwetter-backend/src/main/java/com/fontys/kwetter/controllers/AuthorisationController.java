package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.domain.api.Credentials;
import com.fontys.kwetter.dto.UserDTO;
import com.fontys.kwetter.security.JWTGenerator;
import com.fontys.kwetter.services.UserService;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
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

  @EJB
  private UserDTO userDTO;

  private ObjectMapper objectMapper = new ObjectMapper();
  private ModelMapper modelMapper = new ModelMapper();

  @POST
  @Path("login")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(Credentials credentials) {
    try {
      // 1. Call login method - return User object
      User user = userService.logIn(credentials.getUsername(), credentials.getPassword());
      // 2. Check if user is null - this means login has failed!
      if(user == null) {
        return Response.status(400).entity("The username and password combination is incorrect!").build();
      }
      // 3. Use username and UUID in JWT generator
      String jwt = JWTGenerator.createJWT(user.getUuid());
      JSONObject json = new JSONObject();
      json.put("jwt_token", jwt);
      json.put("uuid", user.getUuid());
      return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when logging in!").build();
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("register")
  public Response register(User user) {
    try {
      user = userService.register(user);
      user = userDTO.simplifyUser(user);
      UserDTO userDTO = modelMapper.map(user, UserDTO.class);
      final String jsonResult = objectMapper.writeValueAsString(userDTO);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when registering user!").build();
    }
  }
}
