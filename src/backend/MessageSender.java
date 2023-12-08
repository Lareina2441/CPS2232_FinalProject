package backend;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class MessageSender {

    public static void main(String[] args) {
        // Outlook email address and password
        final String username = "zhaoq.cs@outlook.com";
        final String password = "Zqj20030227";


        // Recipient's email address
        String to = "1392264819@qq.com";

        // Setup properties for the Outlook SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(username));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("JavaMail Outlook Test");

            // Now set the actual message
            message.setText("Hello, this is a test email sent from Java to Outlook!" + System.currentTimeMillis());

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    }
