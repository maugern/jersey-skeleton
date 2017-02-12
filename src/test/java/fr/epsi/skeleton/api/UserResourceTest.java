package fr.epsi.skeleton.api;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserResourceTest extends JerseyTest {
    public static final String PATH = "/user";
    private Helper h = new Helper();

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        h.initDb();
    }

    @Test @Ignore
    public void read_should_return_a_user_as_object() {
        h.createUserWithAlias("foo");
        User user = target(PATH + "/foo").request().get(User.class);
        assertEquals("foo", user.getAlias());
    }

    @Test @Ignore
    public void read_user_should_return_good_alias() {
        h.createUserWithAlias("rms");
        User user = target(PATH + "/rms").request().get(User.class);
        assertEquals("rms", user.getAlias());
    }

    @Test @Ignore
    public void read_user_should_return_good_email() {
        h.createUserWithEmail("Ian Murdock", "ian@debian.org");
        User user = target(PATH + "/Ian Murdock").request().get(User.class);
        assertEquals("ian@debian.org", user.getEmail());
    }

    @Test @Ignore
    public void create_should_return_the_user_with_valid_id() {
        User user = new User("thomas");
        Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
        String json = target(PATH).request().post(userEntity).readEntity(String.class);
        assertEquals("{\"id\":1,\"name\":\"thomas\"", json.substring(0, 23));
    }

    @Test @Ignore
    public void list_should_return_all_users() {
        h.createUserWithAlias("foo");
        h.createUserWithAlias("bar");
        List<User> users = target(PATH + "/").request().get(new GenericType<List<User>>() {});
        assertEquals(3, users.size()); // 3 because +1 for anonymous user
    }

    @Test @Ignore
    public void list_all_must_be_ordered() {
        h.createUserWithAlias("foo");
        h.createUserWithAlias("bar");
        List<User> users = target(PATH + "/").request().get(new GenericType<List<User>>() {});
        assertEquals("foo", users.get(0).getAlias());
    }

    @Test @Ignore
    public void after_delete_read_user_sould_return_202() {
        User u = h.createUserWithAlias("toto");
        int status = target(PATH + "/" + u.getId()).request().delete().getStatus();
        assertEquals(202, status);
    }
}

