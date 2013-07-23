package org.avaje.jersey.usersecurity;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A SecurityContext based on WebSession and WebUser.
 */
public class WebSecurityContext implements SecurityContext {

  private static final Logger log = LoggerFactory.getLogger(WebSecurityContext.class);
  
  protected final WebSession session;
  
  protected final WebUser user;
  
  protected final String authenticationScheme;

  /**
   * Construct with a session and a user.
   */
  public WebSecurityContext(WebSession session, WebUser user) {
    this(session, user, SecurityContext.FORM_AUTH);
  }
  
  /**
   * Construct additionally with an authenticationScheme.
   */
  public WebSecurityContext(WebSession session, WebUser user, String authenticationScheme) {
    this.session = session;
    this.user = user;
    this.authenticationScheme = authenticationScheme;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (session == null) {
      sb.append("no session");
    } else {
      sb.append("session:").append(session.getId());
      if (user != null) {
        sb.append(" user:").append(user.getId());
      }
    } 
    return sb.toString();
  }
  
  /**
   * Return the user principal.
   */
  public Principal getUserPrincipal() {
    return user;
  }

  /**
   * Return true if the user is set and has the role.
   */
  public boolean isUserInRole(String role) {
    
    if (user == null) {
      return false;
    }

    try {
      return user.isUserInRole(role);
    
    } catch (Exception e) {
      log.error("Error with isUserInRole() check", e);
      return false;
    }
  }

  /**
   * Return true if the session is secure.
   */
  public boolean isSecure() {
    return (session != null) ? session.isSecure() : false;
  }

  /**
   * Return the authentication scheme.
   */
  public String getAuthenticationScheme() {
    return authenticationScheme;
  }

}
