package fr.epsi.skeleton.auth;

import fr.epsi.skeleton.api.BDDFactory;
import fr.epsi.skeleton.api.User;
import fr.epsi.skeleton.api.UserDao;
import fr.epsi.skeleton.api.UserResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
    
    @Override
    public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
        String authorizationHeader = containerRequest.getHeaderString(HttpHeaders.AUTHORIZATION);
        String scheme = containerRequest.getUriInfo().getRequestUri().getScheme();
        logger.debug("authorizationHeader : " + authorizationHeader);

        if (authorizationHeader != null) {
            String[] loginPassword = BasicAuth.decode(authorizationHeader);
            checkLoginPassword(loginPassword);
            String login = loginPassword[0];
            String password = loginPassword[1];
            User user = dao.findByAlias(login);
            if (user.isGoodPassword(password)) {
                logger.debug("good password !");
                containerRequest.setSecurityContext(new AppSecurityContext(user, scheme));
            } else {
                logger.debug("wrong password !");
                containerRequest.setSecurityContext(new AppSecurityContext(User.getAnonymousUser(), scheme));
            }
        } else {
            containerRequest.setSecurityContext(new AppSecurityContext(User.getAnonymousUser(), scheme));
        }
    }
    
    /**
     * Check if the array "loginPassword" is not null and had 2 values.
     * If not, throw a new WebApplicationException with NOT ACCEPTABLE status (406).
     * @param loginPassword
     */
    private void checkLoginPassword(String[] loginPassword) {
        if (loginPassword == null || loginPassword.length != 2) {
            throw new WebApplicationException(Status.NOT_ACCEPTABLE);
        }
    }
}
