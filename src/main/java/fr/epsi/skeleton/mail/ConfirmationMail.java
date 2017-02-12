package fr.epsi.skeleton.mail;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

import fr.epsi.skeleton.api.BDDFactory;
import fr.epsi.skeleton.api.User;
import fr.epsi.skeleton.api.UserDao;

public class ConfirmationMail implements Mail {

	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
	private String subject = "Jersey Skeleton - Confirmation Mail";
	
	public ConfirmationMail(String recipient_name, String recipient_email) {
		email.setFromAddress(sender_name, sender_email);
        email.addRecipient(recipient_name, recipient_email, null);
		email.setSubject(subject);
		
		User user = dao.findByEmail(recipient_email);
		email.setText("Your account " + user.getAlias() + " has been created.");
	}

	@Override
	public void sendMail() {
		new Mailer( new ServerConfig("smtp.host.com", 587),
                    TransportStrategy.SMTP_TLS
                    ).sendMail(email);
	}
}

