package fr.epsi.skeleton.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


@Path("/secure")
public class SecureResource {

    @GET
    @Path("/forall")
    public User secureForAll(@Context SecurityContext context) {
        return (User) context.getUserPrincipal();
    }

    @GET
    @Path("/byannotation")
    @RolesAllowed({"user"})
    public User secureByAnnotation(@Context SecurityContext context) {
        return (User) context.getUserPrincipal();
    }

}
