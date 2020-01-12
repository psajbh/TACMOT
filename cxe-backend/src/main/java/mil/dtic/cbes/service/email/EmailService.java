//package mil.dtic.cbes.service.email;
//
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import mil.dtic.cbes.model.ContactUsEmail;
//import mil.dtic.cbes.service.config.ConfigurationService;
//
//@Service
//public class EmailService {
//	
//	@Autowired
//	private ConfigurationService configurationService;
//
//	public void sendAnEmail(ContactUsEmail contactUsEmail) {
//		Properties properties = System.getProperties();
//		properties.setProperty(configurationService.getEmailEnvironment(), configurationService.getEmailHost());
//		Session session = Session.getDefaultInstance(properties);
//		
//		try {
//			MimeMessage message = new MimeMessage(session);			
//			message.setFrom(new InternetAddress(configurationService.getEmailFrom()));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(configurationService.getEmailTo()));
//			message.addRecipients(Message.RecipientType.CC, contactUsEmail.getEmailAddress());
//			message.setSubject("CXE Message (From Contact Us Page)");
//			message.setText("Name: " + contactUsEmail.getFirstName() + " " + contactUsEmail.getLastName() + "\n"
//					+ "Email Address: " + contactUsEmail.getEmailAddress() + "\n"
//					+ "Phone Number: " + contactUsEmail.getPhoneNumber() + "\n\n"
//					+ "Message: " + contactUsEmail.getMessage() + "\n\n"
//					+ "------------------------------------------------\n" 
//					+ "R2 Team\n" + "This is an automatically generated email.\n" 
//					+ "------------------------------------------------");
//			Transport.send(message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//}
