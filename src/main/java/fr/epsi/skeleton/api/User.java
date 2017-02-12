package fr.epsi.skeleton.api;

import java.security.Principal;

import org.mindrot.jbcrypt.BCrypt;

public class User implements Principal {

	private int id = 0; // NOTE: id are auto-incremented with SQLite
	private String alias;
	private String name;
	private String email;
	private String passwdHash;

	private static User anonymous = new User("@nonymous");

	@Deprecated
	public User(String alias) { // NOTE : do not use this non secure constructor
		this.alias = alias;
	}
	
	/**
	 * User constructor.
	 * @param alias login or username, who must be unique
	 * @param name label
	 * @param email who must be unique
	 * @param password non crypted
	 */
	public User(String alias,
			String name,
			String email,
			String password)
	{
		this.alias = alias;
		this.name = name;
		this.email = email;
		setPassword(password);
	};

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set a new passwordHash using 
	 * <a href="https://en.wikipedia.org/wiki/Bcrypt">bCrypt algorithm</a>.
	 * Only hashed password will be save and no salt,
	 * to protect against rainbow attacks.
	 * @param password non crypted
	 */
	public void setPassword(String password) {
		this.passwdHash = BCrypt.hashpw(password, generateSalt());
	}

	/**
	 * Testing if password match hashed password using bCrypt algorithm. 
	 * @param password to test
	 * @return true if password match
	 */
	public boolean isGoodPassword(String password) {
		return BCrypt.checkpw(password, this.passwdHash);
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	/** 
	 * GenerateSalt log_rounds parameter determines the complexity
	 * the work factor is 2**log_rounds, and the default is 12.
	 * Always remember in computer science, is only a pseudo-random number.
	 */
	private String generateSalt() {
		return BCrypt.gensalt(12); // increase default values, who is 10.
	}

	public boolean isInUserGroup(){
		return id != anonymous.getId();
	}

	public static User getAnonymousUser() {
		return anonymous ;
	}

	public static boolean isAnonymous(User currentUser) {
		return currentUser.getId() == getAnonymousUser().getId();
	}

	@Override
	public String toString() {
		return "<" + id + "," + alias + "," + email + ">";
	}

}
