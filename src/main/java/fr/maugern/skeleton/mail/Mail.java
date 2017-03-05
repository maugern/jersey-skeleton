package fr.maugern.skeleton.mail;

import org.simplejavamail.email.Email;

public interface Mail {

	Email email = new Email();
	String senderName  = "Jersey Skeleton";
	String senderEmail = ""; // TODO add server mail

	public void sendMail();
		
}
