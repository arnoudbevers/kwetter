package controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import dto.UserDTO;
import services.FriendshipService;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
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

  @EJB
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
  // TODO: Remove list?
  public Response createFriendship(List<User> friendship) {
    try {
      friendshipService.follow(friendship.get(0), friendship.get(1));
      // TODO: Move to helper class

      final String jsonResult = mapper.writeValueAsString(userDTO.simplifyUser(friendship.get(1)));
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when creating friendship!").build();
    }
  }

  @POST @Path("destroy")
  @Consumes("application/json")
  public Response deleteFriendship(List<User> friendship) {
    System.out.println("I am here");
    try {
      friendshipService.unfollow(friendship.get(0), friendship.get(1));
      // TODO: Move to helper class
      for (User u : friendship.get(0).getFollowers()) {
        u.setFollowers(new ArrayList<>());
        u.setFollowing(new ArrayList<>());
      }
      for (User u : friendship.get(0).getFollowing()) {
        u.setFollowers(new ArrayList<>());
        u.setFollowing(new ArrayList<>());
      }
      final String jsonResult = mapper.writeValueAsString(friendship.get(0));
      return Response.ok(jsonResult, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when creating friendship!").build();
    }
  }
}
