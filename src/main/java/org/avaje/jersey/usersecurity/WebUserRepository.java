package org.avaje.jersey.usersecurity;


public interface WebUserRepository {

  public WebUser getUser(WebSession session);
}
