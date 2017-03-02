package fr.maugern.skeleton.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import fr.maugern.skeleton.auth.AuthFilter;

@ApplicationPath("html")
public class WebConfig extends ResourceConfig {

    public WebConfig() {
        packages("fr.maugern.skeleton.web");
        register(JspMvcFeature.class);
        registerSecurity();
        // Tracing support.
        property(ServerProperties.TRACING, TracingConfig.ON_DEMAND.name());
    }

    private void registerSecurity() {
        register(AuthFilter.class);
        register(RolesAllowedDynamicFeature.class);
    }


}
