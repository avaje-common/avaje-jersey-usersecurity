package org.avaje.jersey.usersecurity;

import com.sun.jersey.spi.container.ContainerRequest;

public interface WebSessionRepository {

  public WebSession getSession(ContainerRequest request);
  
  
}
