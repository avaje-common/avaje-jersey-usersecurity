package org.avaje.jersey.usersecurity;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * Jersey Filter that sets the SecurityContext into the ContainerRequest.
 * <p>
 * Typically a managed Spring bean with appropriate SecurityContextService
 * injected.
 * </p>
 */
@Component
@Provider
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter {

  private final WebSessionRepository sessionRepository;

  private final WebUserRepository userRepository;

  /**
   * Construct with a SecurityContextService implementation.
   */
  @Inject
  public SecurityContextFilter(WebSessionRepository sessionRepository, WebUserRepository userRepository) {
    this.sessionRepository = sessionRepository;
    this.userRepository = userRepository;
  }

  protected SecurityContext getSecurityContext(ContainerRequest request) {

    WebSession session = sessionRepository.getSession(request);

    WebUser user = null;
    if (session != null && session.getUserId() != null) {
      user = userRepository.getUser(session);
    }

    return new WebSecurityContext(session, user);
  }

  /**
   * Sets the SecurityContext into the ContainerRequest.
   */
  public ContainerRequest filter(ContainerRequest request) {

    request.setSecurityContext(getSecurityContext(request));
    return request;
  }

  public ContainerRequestFilter getRequestFilter() {
    return this;
  }

  public ContainerResponseFilter getResponseFilter() {
    // not interested in responses
    return null;
  }

}
