package com.fontys.kwetter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * @author Arnoud Bevers
 * @project kwetter-backend
 */
public class JWTValidator {
  public static String createJWT(String subject, String issuer) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret");
      return JWT.create()
              .withSubject(subject)
              .withIssuer(issuer)
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
