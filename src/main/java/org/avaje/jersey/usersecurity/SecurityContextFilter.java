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

  
  private final SecurityContextService securityService;
  
  /**
   * Construct with a SecurityContextService implementation.
   */
  @Inject
  public SecurityContextFilter(SecurityContextService securityService) {
    this.securityService = securityService;
  }
  
  /**
   * Sets the SecurityContext into the ContainerRequest.
   */
  public ContainerRequest filter(ContainerRequest request) {
    
    SecurityContext ctx = securityService.getSecurityContext(request);  
    request.setSecurityContext(ctx);
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
