package org.avaje.jersey.usersecurity;

import javax.ws.rs.core.SecurityContext;

import com.sun.jersey.spi.container.ContainerRequest;

/**
 * Service that provides the SecurityContext for JAX-RS/Jersey method level security checks.
 * <p>
 * The application should provide an implementation of this interface as a Spring bean.
 * </p>
 */
public interface SecurityContextService {

  /**
   * Return the SecurityContext for a given request.
   */
  public SecurityContext getSecurityContext(ContainerRequest request);
}
