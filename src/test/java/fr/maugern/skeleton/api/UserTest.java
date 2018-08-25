package fr.maugern.skeleton.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
 
	@Test
    public void should_set_hash_at_build() {
        User user = new User("alias","name","email","password");
        assertNotNull(user.getPasswdHash());
		assertNotEquals("password", user.getPasswdHash());
	}
	
	@Test
	public void should_set_random_and_secure_hash() {
		User u1 = new User("@foo","foo","foo@email.com","password");
		User u2 = new User("@bar","bar","bar@email.com","password");
		assertNotEquals(u1.getPasswdHash(), u2.getPasswdHash());
		assertTrue(u1.getPasswdHash().matches("^\\$2[ayb]\\$.{56}$"));
		assertTrue(u1.getPasswdHash().matches("^\\$2[ayb]\\$.{56}$"));
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
