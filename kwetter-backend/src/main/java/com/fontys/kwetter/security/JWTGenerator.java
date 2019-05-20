package com.fontys.kwetter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import javax.crypto.KeyGenerator;
import javax.ejb.EJB;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Arnoud Bevers
 * @project kwetter-backend
 */
public class JWTGenerator {

  @EJB
  private KeyGenerator keyGenerator;

  public static String createJWT(String subject) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret");
      //TODO: Add KeyGenerator to JWT
      //TODO: Check what issuer is needed?
      return JWT.create()
              .withSubject(subject)
              .withIssuer("kwetter_arnoud_bevers")
              .withIssuedAt(new Date())
              .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
              .sign(algorithm);
    } catch(JWTCreationException ex) {
      // Invalid signing configuration
      ex.printStackTrace();
    }
    return "";
  }
}
