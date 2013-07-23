package org.avaje.jersey.usersecurity;

import java.security.Principal;

/**
 * A user for use with {@link WebSecurityContext}.
 */
public interface WebUser extends Principal {

  /**
   * Return the user id.
   */
  public String getId();
  
  /**
   * Return the user name.
   */
  public String getName();

  /**
   * Return true if the user has the given role.
   */
  public boolean isUserInRole(String role);
  
}
