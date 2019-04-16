package com.fontys.kwetter.utils;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
public class HashedPassword {
  private String password;
  private String salt;

  /**
   * Gets the hashed password.
   *
   * @return String instance of the hashed password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Gets the salt of the password.
   *
   * @return String instance of the password's salt.
   */
  public String getSalt() {
    return salt;
  }

  /**
   * Default constructor for HashedPassword.
   *
   * @param password String instance of hashed password.
   * @param salt     String instance of the password's salt.
   */
  public HashedPassword(String password, String salt) {
    this.password = password;
    this.salt = salt;
  }

  @Override
  public String toString() {
    return password + "\n" + salt;
  }

}
