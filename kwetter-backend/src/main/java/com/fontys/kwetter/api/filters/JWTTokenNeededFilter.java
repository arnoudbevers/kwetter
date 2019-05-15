package com.fontys.kwetter.api.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fontys.kwetter.api.JWTTokenNeeded;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arnoud Bevers
 * @project kwetter-backend
 */
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

  private static final Logger LOGGER = Logger.getLogger(JWTTokenNeededFilter.class.getName());

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    //Get the authorization header from the request
    String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
    //Trim the bearer token from that header
    String jwt_token = authorizationHeader.substring("Bearer".length()).trim();

    // TODO: Validate JWS tokens with KyeGenerator
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret");
      JWTVerifier verifier = JWT.require(algorithm)
              .withIssuer("kwetter_arnoud_bevers")
              .build();
      DecodedJWT decodedJWT = verifier.verify(jwt_token);
      LOGGER.log(Level.INFO, "Valid JWT token: " + decodedJWT.getToken());
    } catch (JWTVerificationException ex) {
      LOGGER.log(Level.SEVERE, ex.toString(), ex);
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }
}
