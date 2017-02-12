package fr.epsi.skeleton.api;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);

    public UserResource() {
		try {
			dao.createUserTable();
		} catch (DatabaseException e) {
			logger.info("Table already here !",e);
		}
	}

	@POST
	public User createUser(User user) {
        dao.insert(user);
        logger.debug("User " + user.getAlias() + " has been created.");
		return user;
	}

	@GET @Path("/{alias}")
	public User getUser(@PathParam("alias") String alias) {
		User user = dao.findByAlias(alias);
		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return user;
	}

	/**
     * Load User from the login.
     * @param Login can be alias or email.
     * @return User who match with the login, anonymous user if not found.
     */
	/*
	@GET
	@Path("/{alias}")
    public User getUserFromLogin(String login) {
        UserDao dao = BDDFactory.getDbi().open(UserDao.class);
        User user;
        if ((user = dao.findByAlias(login)) != null)
        	return user;
        else if ((user = dao.findByEmail(login)) != null)
            return user;
        else
        	return User.getAnonymousUser();
    }
    */
	
	@GET
	public List<User> getAllUsers() {
		return dao.all();
	}
	
}
