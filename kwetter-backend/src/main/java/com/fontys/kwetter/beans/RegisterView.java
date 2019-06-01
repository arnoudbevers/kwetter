package com.fontys.kwetter.beans;

import com.fontys.kwetter.dao.jaas.UserJAASDAO;
import com.fontys.kwetter.domain.Role;
import com.fontys.kwetter.domain.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class RegisterView implements Serializable {
  private static final Logger LOGGER = Logger.getLogger(RegisterView.class.getName());

  private static final long serialVersionUID = 1685823449195612778L;
  private static Logger log = Logger.getLogger(RegisterView.class.getName());

  @Inject
  private UserJAASDAO userJAASDAO;
  private String username;
  private String email;
  private String password;
  private String confirmPassword;

  public void validatePassword(ComponentSystemEvent event) {
    try {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      UIComponent components = event.getComponent();
      // get password
      UIInput uiInputPassword = (UIInput) components.findComponent("password");
      String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
      String passwordId = uiInputPassword.getClientId(facesContext);
      // get confirm password
      UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
      String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
              : uiInputConfirmPassword.getLocalValue().toString();
      // Let required="true" do its job.
      if (password.isEmpty() || confirmPassword.isEmpty()) {
        return;
      }
      if (!password.equals(confirmPassword)) {
        FacesMessage msg = new FacesMessage("Confirm password does not match password");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        facesContext.addMessage(passwordId, msg);
        facesContext.renderResponse();
      }
      if (userJAASDAO.findUserById(email) != null) {
        FacesMessage msg = new FacesMessage("User with this username already exists");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        facesContext.addMessage(passwordId, msg);
        facesContext.renderResponse();
      }
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.toString(), ex);
    }

  }

  public String register() {
    User user = new User(Role.ADMINISTRATOR, email, username, password);
    userJAASDAO.createUser(user);
    log.info("New user created with email: " + email + " and username: " + username);
    return "regdone";
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}