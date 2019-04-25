package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.dto.UserDTO;
import com.fontys.kwetter.services.KweetService;
import com.fontys.kwetter.services.UserService;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API controller used to call all User model related calls.
 * Uses Service to communicate with the data storage option.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Named("userController")
@RequestScoped
@Path("users")
public class UserController {

  @Inject
  @Named("userService")
  private UserService userService;
  @Inject
  @Named("kweetService")
  private KweetService kweetService;
  @EJB
  private UserDTO userDTO;

  private ObjectMapper mapper = new ObjectMapper();

  @POST
  @Consumes("application/json")
  public Response createUser(User user) {
    try {
      userService.createUser(user);
      List<User> allUsers = userService.getUserByUsername(user.getUsername());
      final User registeredUser = allUsers.get(allUsers.size() - 1);
      final String jsonResult = mapper.writeValueAsString(registeredUser);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when registering user!").build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllUsers() {
    try {
      final List<User> allUsers = userService.getAllUsers();
      if (allUsers == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No users exist!").build();
      }
      allUsers.forEach(user -> user = userDTO.simplifyUser(user));
      final String jsonResult = mapper.writeValueAsString(allUsers);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON_TYPE).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when fetching all users!").build();
    }
  }

  // TODO: UUID instead of normal ID
  @GET
  @Path("{id}")
  public Response getUserById(@PathParam("id") int id) {
    User user;
    try {
      user = userService.getUserById(id);
      user = kweetService.getKweetsForUser(user);
      if (user == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not find user by id " + id + "!").build();
      }
      final String jsonResult = mapper.writeValueAsString(userDTO.simplifyUser(user));
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when fetching user!").build();
    }
  }

  @GET
  @Path("search/{username}")
  public Response getUserByUsername(@PathParam("username") String username) {
    try {
      final List<User> users = userService.getUserByUsername(username);
      if (users == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not find user by username " + username + "!").build();
      }
      users.forEach(user -> userDTO.simplifyUser(user));
      final String jsonResult = mapper.writeValueAsString(users);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when fetching user by username " + username + "!").build();
    }
  }
  
//  @POST
//  @Consumes(MediaType.APPLICATION_JSON)
//  @Produces(MediaType.APPLICATION_JSON)
//  public Response register(User user) {
//    try {
//      User registeredUser = userService.register(user);
//      final String jsonResult = mapper.writeValueAsString(userDTO.simplifyUser(registeredUser));
//      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
//    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
//      e.printStackTrace();
//      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when registering user!").build();
//    }
//  }
}
