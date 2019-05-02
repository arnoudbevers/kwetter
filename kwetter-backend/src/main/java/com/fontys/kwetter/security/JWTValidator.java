package com.fontys.kwetter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

/**
 * @author Arnoud Bevers
 * @project kwetter-backend
 */
public class JWTValidator {
  public static String createJWT(String issuer) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret");
      return JWT.create()
              .withIssuer(issuer)
              .sign(algorithm);
    } catch(JWTCreationException ex) {
      // Invalid signing configuration
      ex.printStackTrace();
    }
    return "";
  }
}
