import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SMTPSSLSender implements SenderInterface {
    private String fromMail = null;
    private String username = null;
    private String password = null;

    private String host = null;
    private Properties prop = null;

    public SMTPSSLSender(Properties props) {
        this.username = props.getProperty("mail.smtp.username");
        this.password = props.getProperty("mail.smtp.password");
        this.fromMail = props.getProperty("mail.smtp.from");
        this.host = props.getProperty("mail.smtp.host");

        this.prop = new Properties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", this.host);
        prop.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        prop.put("mail.smtp.port", "465"); //SMTP Port 25
        prop.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        prop.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
    }

    @Override
    public Session getInstance() {
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    @Override
    public Message createMessage(Session session, Mail mail) {
        Message message = null;
        mail.setSender(this.fromMail);

        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getSender()));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getReceiver(), false));
            message.setSubject(mail.getSubject());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mail.getMessage(), "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return message;
    }

    public boolean send(Message message) {
        try {
            Transport.send(message);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
