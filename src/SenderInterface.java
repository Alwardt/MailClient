import javax.mail.Message;
import javax.mail.Session;

public interface SenderInterface {

    Session getInstance();

    Message createMessage(Session session, Mail mail);

    boolean send(Message message);
}
