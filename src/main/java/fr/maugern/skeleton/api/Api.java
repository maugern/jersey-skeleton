package fr.maugern.skeleton.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import fr.maugern.skeleton.auth.AuthFilter;

@ApplicationPath("/api/")
public class Api extends ResourceConfig {

    public Api() {
        packages("fr.maugern.skeleton.api");
        //register(LoggingFilter.class);
        register(AuthFilter.class);
        register(RolesAllowedDynamicFeature.class);
    }

}
