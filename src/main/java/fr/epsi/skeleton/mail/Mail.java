package fr.epsi.skeleton.mail;

import org.simplejavamail.email.Email;

public interface Mail {

	Email email = new Email();
	String sender_name  = "Jersey Skeleton";
	String sender_email = ""; // TODO add server mail

	public void sendMail();
		
}
