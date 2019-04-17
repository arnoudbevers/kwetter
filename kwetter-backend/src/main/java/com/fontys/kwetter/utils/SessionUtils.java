package com.fontys.kwetter.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Holds session info for the pages that use JSP.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
public class SessionUtils {

  public static HttpSession getSession() {
    return (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
  }

  public static HttpServletRequest getRequest() {
    return (HttpServletRequest) FacesContext.getCurrentInstance()
            .getExternalContext().getRequest();
  }

  public static String getUserName() {
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
    return session.getAttribute("username").toString();
  }

  public static String getUuid() {
    HttpSession session = getSession();
    if (session != null)
      return (String) session.getAttribute("uuid");
    else
      return null;
  }
}
