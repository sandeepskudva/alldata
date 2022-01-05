package Research;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachment {

public static void main(String[] args) {
	
	
  }
public void sendemail(String distributionlist,String subject,String filepath, String attachmentname)
{

final String username = "web_test@yahoo.com";
final String password = "Welcome.123";

Properties props = new Properties();
props.put("mail.smtp.auth", true);
props.put("mail.smtp.starttls.enable", true);
props.put("mail.smtp.host", "smtp.mail.yahoo.com");
props.put("mail.smtp.port", "587");

Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

try {

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(distributionlist));
    message.setSubject("Automation Testing Subject");
    message.setText("PFA");

    MimeBodyPart messageBodyPart = new MimeBodyPart();

    Multipart multipart = new MimeMultipart();

    messageBodyPart = new MimeBodyPart();
    String file = filepath;
    String fileName = attachmentname;
    DataSource source = new FileDataSource(file);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(fileName);
    multipart.addBodyPart(messageBodyPart);

    message.setContent(multipart);

    System.out.println("Sending Email");

    Transport.send(message);

    System.out.println("Done");

} catch (MessagingException e) {
    e.printStackTrace();
}
}
}