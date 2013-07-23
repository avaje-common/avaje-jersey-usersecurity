package org.avaje.jersey.usersecurity;

/**
 * A session for use with {@link WebSecurityContext}.
 */
public interface WebSession {

  /**
   * Return true if the session is secure.
   */
  public boolean isSecure();

  /**
   * Return the session id.
   */
  public String getId();
  
  /**
   * Return the associated user id.
   */
  public String getUserId();

}
