package fr.epsi.skeleton.mail;

import java.security.SecureRandom;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

import fr.epsi.skeleton.api.BDDFactory;
import fr.epsi.skeleton.api.User;
import fr.epsi.skeleton.api.UserDao;

public class NewPasswordMail implements Mail {

	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
	private String subject = "Jersey skeleton - Reseting passworld";
	
	public NewPasswordMail(String recipient_name, String recipient_email) {
		email.setSubject(subject);
		String generatePassword = randomString(12);
		email.setText("Hello " + recipient_name + ", this is your new temporary password :" + generatePassword);
		User recipient = dao.findByEmail(recipient_email);
		recipient.setPassword(generatePassword);
		dao.update(recipient);
	}
	
	@Override
	public void sendMail() {
		new Mailer(new ServerConfig("smtp.host.com", 587), TransportStrategy.SMTP_TLS).sendMail(email);
	}

	private String randomString( int len ){
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}
}
