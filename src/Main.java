import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public Properties readProperties() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath.split("out")[0]);
        String appConfigPath = rootPath.split("out")[0] + "mail.properties";
        System.out.println(appConfigPath);

        props.load(new FileInputStream(appConfigPath));

        return props;
    }
    public static void main(String[] args) throws Exception {
        Properties props = new Main().readProperties();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter receiver > ");
        String receiver = scanner.nextLine();
        System.out.print("Enter Subject > ");
        String subject = scanner.nextLine();
        System.out.println("Enter Message > ");
        String message = scanner.nextLine();

        Mail mail = new Mail();
        mail.setReceiver(receiver);
        mail.setSubject(subject);
        mail.setMessage(message);
        //TODO: mail und secret ausladen
        SMTPSSLSender smtp = new SMTPSSLSender(props);
        Session session = smtp.getInstance();
        Message msg = smtp.createMessage(session, mail);
        smtp.send(msg);

        //java-mail@beck-homes.com
        //axv8@50T4
        //SMTP: mail.beck-homes.com
        //SMTP-Port: 465






    }
}