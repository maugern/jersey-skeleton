package fr.epsi.skeleton.api;

import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static org.junit.Assert.assertEquals;

public class SecureResourceForAllTest extends JerseyTest {
	private Helper h;
    private String url = "/secure/forall";

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        h.initDb();
    }

    @Test
    @Ignore // missing MVC template injection
    public void should_return_current_user_with_authorization_header() {
        h.createUserWithPassword("tclavier", "motdepasse", "graindesel");
        String authorization = "Basic " + Base64.encodeAsString("tclavier:motdepasse");
        User user = target(url).request().header(AUTHORIZATION, authorization).get(User.class);
        assertEquals("tclavier", user.getAlias());
    }

    @Test
    @Ignore // missing MVC template injection
    public void should_return_anonymous_user_without_authorization_header() {
        User user = target(url).request().get(User.class);
        assertEquals("anonym", user.getAlias());
    }

    @Test
    @Ignore // missing MVC template injection
    public void should_return_anonymous_user_for_bad_user() {
        h.createUserWithPassword("tclavier", "motdepasse", "graindesel");
        String authorization = "Basic " + Base64.encodeAsString("tclavier:pasdemotdepasse");
        User user = target(url).request().header(AUTHORIZATION, authorization).get(User.class);
        assertEquals("anonym", user.getAlias());
    }

}
