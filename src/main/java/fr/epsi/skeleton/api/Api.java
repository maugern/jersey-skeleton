package fr.epsi.skeleton.api;

import javax.ws.rs.ApplicationPath;

import fr.epsi.skeleton.auth.AuthFilter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("/api/")
public class Api extends ResourceConfig {

    public Api() {
        packages("fr.epsi.skeleton.api");
        //register(LoggingFilter.class);
        register(AuthFilter.class);
        register(RolesAllowedDynamicFeature.class);
    }

}
