package com.fontys.kwetter.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
public class PasswordEncrypt {

  private static final Logger LOGGER = Logger.getLogger(PasswordEncrypt.class.getName());

  private PasswordEncrypt() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Encodes a byte array to a salt in String format.
   *
   * @param saltArray The byte array to encode.
   * @return The result of the encoded byte array as a Base64 String.
   */
  private static String encode64(byte[] saltArray) {
    return Base64.getEncoder().encodeToString(saltArray);
  }

  /**
   * Decodes a Base64 String into a byte array.
   *
   * @param salt The Base64 String to decode.
   * @return The result of the decoded String as a byte array.
   * @throws IllegalArgumentException if the String is not in correct Base64 format
   */
  private static byte[] decode64(String salt) {
    return Base64.getDecoder().decode(salt.getBytes());
  }

  /**
   * Hashes a password with the help of a salt, which is either supplied in the
   * method (when the user has already registered) or randomly generated (when the
   * user registers for the first time).
   *
   * @param pwdtoHash The password to hash.
   * @param salt      The salt to incorporate in the Hashing algorithm.
   * @return The result of the hashing algorithm in an object containing the
   * hashed password and the salt (to save externally).
   */
  public static HashedPassword hashPassword(String pwdtoHash, String salt) {

    MessageDigest messageDigest;
    byte[] byteData;
    byte[] hashedPwd;


    if (salt.isEmpty() || salt == null) {
      // Then the user has not registered yet, and the hash is generated
      // using a SecureRandom.
      // This makes sure no two hashed passwords are the same.
      SecureRandom random = new SecureRandom();
      byteData = new byte[20];
      random.nextBytes(byteData);
    } else {
      // The user has already registered, and is probably logging in to the system.
      // The salt, stored in the database, is decoded into a byte array to use in the
      // algorithm.
      byteData = decode64(salt);
    }
    try {
      // Algorithm uses SHA-512 algorithm.
      messageDigest = MessageDigest.getInstance("SHA-512");
      messageDigest.update(byteData);
      messageDigest.update(pwdtoHash.getBytes());
      hashedPwd = messageDigest.digest();
      StringBuilder sb = new StringBuilder();
      // Byte array (salt) is used to encrypt the passwords.
      // Since the array is randomly generated, no two passwords are hashed the same.
      for (int i = 0; i < hashedPwd.length; i++) {
        sb.append(Integer.toString((hashedPwd[i] & 0xff) + 0x100, 16).substring(1));
      }
      return new HashedPassword(sb.toString(), encode64(byteData));
    } catch (NoSuchAlgorithmException | NullPointerException ex) {
      LOGGER.log(Level.SEVERE, ex.toString(), ex);
    }
    return null;
    // Returns instance of object, used to store the hashed password and the salt.
  }
}

