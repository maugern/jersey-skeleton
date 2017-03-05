package fr.maugern.skeleton.mail;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

import fr.maugern.skeleton.api.BDDFactory;
import fr.maugern.skeleton.api.User;
import fr.maugern.skeleton.api.UserDao;

public class ConfirmationMail implements Mail {

	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
	private String subject = "Jersey Skeleton - Confirmation Mail";
	
	public ConfirmationMail(String recipientName, String recipientEmail) {
		email.setFromAddress(senderName, senderEmail);
        email.addRecipient(recipientName, recipientEmail, null);
		email.setSubject(subject);
		
		User user = dao.findByEmail(recipientEmail);
		email.setText("Your account " + user.getAlias() + " has been created.");
	}

	@Override
	public void sendMail() {
		new Mailer( new ServerConfig("smtp.host.com", 587),
                    TransportStrategy.SMTP_TLS
                    ).sendMail(email);
	}
}

