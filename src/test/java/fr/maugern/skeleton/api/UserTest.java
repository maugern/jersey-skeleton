package fr.maugern.skeleton.api;

import org.junit.Test;

import fr.maugern.skeleton.api.User;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class UserTest {
 
	@Test
    public void should_set_hash_at_build() {
        User user = new User("alias","name","email","password");
        assertNotNull(user.getPasswdHash());
        assertFalse(user.getPasswdHash().equals("password"));
	}
	
	@Test
	public void should_set_random_and_secure_hash() {
		User u1 = new User("@foo","foo","foo@email.com","password");
		User u2 = new User("@bar","bar","bar@email.com","password");
		assertFalse(u1.getPasswdHash().equals(u2.getPasswdHash()));
		assertTrue(u1.getPasswdHash().length() >= 32);
		assertTrue(u2.getPasswdHash().length() >= 32);
	}
	
	@Test
	public void should_accept_correct_password() {
		User user = new User("alias","name","email","password");
		assertTrue(user.isGoodPassword("password"));
	}
	
	@Test
	public void should_refuse_incorrect_password() {
		User user = new User("alias","name","email","password");
		assertFalse(user.isGoodPassword("pasSword"));
	}
	
}
