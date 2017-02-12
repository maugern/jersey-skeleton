package fr.epsi.skeleton.web;

import fr.epsi.skeleton.api.BDDFactory;
import fr.epsi.skeleton.api.User;
import fr.epsi.skeleton.api.UserDao;
import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public class UserViews {
    private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);

    @GET
    @Template
    public List<User> getAll() {
        return dao.all();
    }

    @GET
    @Path("/{alias}")
    public User getUserBdd(@PathParam("alias") String alias) {
        User user;
        if (alias.isEmpty())
            user = User.getAnonymousUser();
        else
            user = dao.findByAlias(alias);
        if (user == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return user;
    }

}
