package com.fontys.kwetter.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.dto.UserDTO;
import com.fontys.kwetter.services.FriendshipService;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API controller used to call all 'friendship' related calls.
 * Friendships are the relations between users - following and followers.
 * Uses Services to communicate with the data storage option.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Named
@RequestScoped
@Path("friendships")
public class FriendshipController {

  @Inject
  @Named("friendshipService")
  private FriendshipService friendshipService;

  @EJB
  private UserDTO userDTO;

  private ObjectMapper mapper = new ObjectMapper();

  /**
   * Creates a friendship between two users.
   *
   * @param friendship List containing the two users who are creating a relation.
   * @return Returns the followed user when successful. Returns a string with failure condition when not successful.
   */
  @POST @Path("create")
  @Consumes("application/json")
  // TODO: Remove list? - replace with two UUIDs when implemented
  public Response createFriendship(List<User> friendship) {
    try {
      friendshipService.follow(friendship.get(0), friendship.get(1));
      // Remove followers + following from user's following + followers, prevent infinite recursion
      final String jsonResult = mapper.writeValueAsString(userDTO.simplifyUser(friendship.get(1)));
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when creating friendship!").build();
    }
  }

  @POST @Path("destroy")
  @Consumes("application/json")
  // TODO: Remove list? - replace with two UUIDs when implemented
  public Response deleteFriendship(List<User> friendship) {
    try {
      friendshipService.unfollow(friendship.get(0), friendship.get(1));
      final String jsonResult = mapper.writeValueAsString(userDTO.simplifyUser(friendship.get(0)));
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when creating friendship!").build();
    }
  }
}
