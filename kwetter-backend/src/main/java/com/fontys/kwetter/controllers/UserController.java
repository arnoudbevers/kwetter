package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.api.JWTTokenNeeded;
import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.dto.KweetDTO;
import com.fontys.kwetter.dto.UserDTO;
import com.fontys.kwetter.services.KweetService;
import com.fontys.kwetter.services.UserService;
import org.modelmapper.ModelMapper;

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

  private ObjectMapper objectMapper = new ObjectMapper();
  private ModelMapper modelMapper = new ModelMapper();

  @POST
  @Consumes("application/json")
  @JWTTokenNeeded
  public Response createUser(User user) {
    try {
      userService.createUser(user);
      List<User> allUsers = userService.getUserByUsername(user.getUsername());
      final User registeredUser = allUsers.get(allUsers.size() - 1);
      final String jsonResult = objectMapper.writeValueAsString(registeredUser);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when registering user!").build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @JWTTokenNeeded
  public Response getAllUsers() {
    try {
      final List<User> allUsers = userService.getAllUsers();
      if (allUsers == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No users exist!").build();
      }
      allUsers.forEach(user -> user = userDTO.simplifyUser(user));
      UserDTO[] userDto = modelMapper.map(allUsers, UserDTO[].class);
      final String jsonResult = objectMapper.writeValueAsString(userDto);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON_TYPE).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when fetching all users!").build();
    }
  }

  @GET
  @Path("{uuid}")
  @JWTTokenNeeded
  public Response getUserById(@PathParam("uuid") String uuid) {
    try {
      User user = userService.getUserByUUID(uuid);
      user.setKweets(userService.getKweetsForUser(user));
      user = userDTO.simplifyUser(user);
      if (user == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not find user by id " + uuid + "!").build();
      }
      UserDTO userDto = modelMapper.map(user, UserDTO.class);
      final String jsonResult = objectMapper.writeValueAsString(userDto);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
  } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when fetching user!").build();
    }
  }

  @PUT
  @Path("{uuid}")
  @JWTTokenNeeded
  public Response updateUserById(@PathParam("uuid") String uuid, User user) {
    try {
      user = userService.updateUser(user);
      UserDTO userDto = modelMapper.map(user, UserDTO.class);
      final String jsonResult = objectMapper.writeValueAsString(userDto);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when updating user!").build();
    }
  }


  @GET
  @Path("{uuid}/kweets")
  @Produces(MediaType.APPLICATION_JSON)
  @JWTTokenNeeded
  public Response getKweetsForUser(@PathParam("uuid") String uuid) {
    try {
      User user = userService.getUserByUUID(uuid);
      user = userDTO.simplifyUser(user);
      List<Kweet> kweets = userService.getKweetsForUser(user);
      KweetDTO[] kweetDTOs = modelMapper.map(kweets, KweetDTO[].class);
      final String jsonResult = objectMapper.writeValueAsString(kweetDTOs);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException |PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when fetching kweets for user!").build();
    }

  }

  @GET
  @Path("{uuid}/timeline")
  @Produces(MediaType.APPLICATION_JSON)
  @JWTTokenNeeded
  public Response getTimelineForUser(@PathParam("uuid") String uuid) {
    try {
      User user = userService.getUserByUUID(uuid);
      user = userDTO.simplifyUser(user);
      List<Kweet> timeline = userService.getTimeline(user);
      KweetDTO[] kweetDTOs = modelMapper.map(timeline, KweetDTO[].class);
      final String jsonResult = objectMapper.writeValueAsString(kweetDTOs);
      return Response.ok(jsonResult,MediaType.APPLICATION_JSON).build();
    } catch(EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when fetching the timeline for user!").build();
    }
  }

  @GET
  @Path("search/{username}")
  @JWTTokenNeeded
  public Response getUserByUsername(@PathParam("username") String username) {
    try {
      final List<User> users = userService.getUserByUsername(username);
      if (users == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not find user by username " + username + "!").build();
      }
      users.forEach(user -> userDTO.simplifyUser(user));
      UserDTO[] userDto = modelMapper.map(users, UserDTO[].class);
      final String jsonResult = objectMapper.writeValueAsString(userDto);
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong when fetching user by username " + username + "!").build();
    }
  }

}
