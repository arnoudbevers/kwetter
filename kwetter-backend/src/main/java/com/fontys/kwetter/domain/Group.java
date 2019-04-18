package com.fontys.kwetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Stores the group a user is in. This is used for JSF/JAAS based authorisation, since this combines the group name and email.
 * @author Arnoud Bevers
 * @project kwetter
 */
@Entity
@Table(name="user_groups")
public class Group implements Serializable {
  private static final long serialVersionUID = 1528447384986169065L;

  public static final String USERS_GROUP = "users";
  public static final String ADMIN_GROUP = "admins";

  @Id
  @Column(name="email", nullable=false, length=255)
  private String email;

  @Column(name="groupname", nullable=false, length=32)
  private String groupname;

  public Group() {}

  public Group(String email, String groupname) {
    this.email = email;
    this.groupname = groupname;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getGroupname() {
    return groupname;
  }
  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }
}
