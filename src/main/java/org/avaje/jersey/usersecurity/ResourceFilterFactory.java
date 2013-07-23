package org.avaje.jersey.usersecurity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * Jersey filter factory that registers the SecurityContextFilter.
 * <p>
 * This filter factory needs to be registered with the jersey servlet or filter.
 * </p>
 */
@Component
@Provider
public class ResourceFilterFactory extends RolesAllowedResourceFilterFactory {

  @Inject
  private SecurityContextFilter securityContextFilter;

  @Override
  public List<ResourceFilter> create(AbstractMethod am) {

    List<ResourceFilter> filters = super.create(am);
    if (filters == null) {
      filters = new ArrayList<ResourceFilter>();
    } else {
      // Convert into mutable List
      filters = new ArrayList<ResourceFilter>(filters);
    }

    // Add securityContextFilter as the first filter
    filters.add(0, securityContextFilter);

    return filters;
  }

}
