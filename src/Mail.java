public class Mail {

    private String sender;
    private String receiver;
    private String[] cc;
    private String subject;
    private String message;
    private Object[] attachment;

    public Mail() {
    }

    public Mail(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public Mail(String sender, String receiver, String subject, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getAttachment() {
        return attachment;
    }

    public void setAttachment(Object[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "\nSender: " + this.sender
                + "\nReceiver: " + this.receiver
                + "\nSubject: " + this.subject
                + "\nMessage: " + this.message;
    }
}
