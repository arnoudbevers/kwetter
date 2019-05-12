package com.fontys.kwetter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.services.KweetService;
import com.fontys.kwetter.services.UserService;
import org.modelmapper.ModelMapper;

import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API controller used to call all Kweet model related calls.
 * Uses Service to communicate with the data storage option.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Named
@RequestScoped
@Path("kweets")
public class KweetController {

  @Inject
  @Named("kweetService")
  private KweetService kweetService;
  @Inject
  @Named("userService")
  private UserService userService;

  private ObjectMapper mapper = new ObjectMapper();
  private ModelMapper modelMapper = new ModelMapper();

  @POST
  @Produces("application/json")
  @Consumes("application/json")
  public Response postKweet(Kweet kweet) {
    try {
      final List<User> senderList = userService.getUserByUsername(kweet.getSender().getUsername());
      kweet.setSender(senderList.get(senderList.size() - 1));
      kweetService.postKweet(kweet);
      final String json = mapper.writeValueAsString(kweet);
      return Response.ok(json, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when posting kweet!").build();
    }
  }

  @GET
  @Path("/search/{searchString}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response searchKweets(@PathParam("searchString") String searchString) {
    try {
      final List<Kweet> kweetsForSearchString = kweetService.searchKweets(searchString);
      final String json = mapper.writeValueAsString(kweetsForSearchString);
      return Response.ok(json, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when searching for kweet!").build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllKweets() {
    try {
      final List<Kweet> allKweets = kweetService.getAllKweets();
      final String json = mapper.writeValueAsString(allKweets);
      return Response.ok(json, MediaType.APPLICATION_JSON).build();
    } catch (EJBTransactionRolledbackException | JsonProcessingException | PersistenceException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong when fetching all kweets!").build();
    }
  }

  @GET
  @Path("/{userId}")
  public Response getKweetsForUser() {
    // TODO: Implement getKweetsForUser()
    return Response.ok().build();
  }
}
