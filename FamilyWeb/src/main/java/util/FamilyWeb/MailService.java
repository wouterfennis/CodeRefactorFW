package util.FamilyWeb;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.FamilyWeb.User;

public class MailService {

    public static final String MAIL_FROM = "info.familyweb@gmail.com";
    public static final String SMTP_HOST = "smtp.gmail.com";
    public static final String PASSWORD = "familyweb12345";
    public static final int SMTP_PORT = 465;
    public static final String MAIL_FROM_NAME = "FamilyWeb";
    private User employee = null;
    private String message;
    private String subject;

    public MailService(User employee, String subject, String message) {
        this.employee = employee;
        this.subject = subject;
        this.message = message;
    }

    public boolean createAndSendMail() {
        try {
            Transport.send(this.createMail());
            return true;
        } catch (Exception e) {
            Logger.getLogger("sp.lesson5").warning("send failed: " + e.getMessage());
            return false;
        }
    }

    public MimeMessage createMail() throws UnsupportedEncodingException, MessagingException {
        MimeMessage mailObject = new MimeMessage(this.createMailSession());
        mailObject.setFrom(new InternetAddress(MAIL_FROM, MAIL_FROM_NAME));
        mailObject.setRecipients(Message.RecipientType.TO, this.employee.getEmail());
        mailObject.setSubject(subject);
        mailObject.setSentDate(Calendar.getInstance().getTime());
        mailObject.setContent(this.createMailContent(), "text/html");
        return mailObject;
    }

    public Session createMailSession() {
        return Session.getDefaultInstance(this.createMailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_FROM, PASSWORD);
            }

        });
    }

    private Properties createMailProperties() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.from", MAIL_FROM);
        mailProperties.put("mail.smtp.host", SMTP_HOST);
        mailProperties.put("mail.smtp.port", SMTP_PORT);
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.ssl.enable", true);
        return mailProperties;
    }

    private String createMailContent() {
        return "<html>" +
                "<head>" +
                    "<style>" +
                        this.createMailContentStyle()+
                    "</style>" +
                "</head>" +
                this.createMailBody()+
                "</html>";
    }

    private String createMailBody() {
        return "<body>" +
                    "<div class='wrapper'>" +
                        "<div class='header'>" +
                            "<h1>FamilyWeb</h1>" +
                        "</div>" +
                        "<div class='content'> " +
                            message +
                        "</div>" +
                    "</div>" +
                "</body>";
    }

    private String createMailContentStyle(){
        return"body{" +
                    "width: 100%;" +
                    "height: auto;" +
                    "background-color: white;" +
                    "font-family: 'Arial';" +
                "}" +

                ".wrapper{" +
                    "width: 90%;" +
                    "min-width: 365px;" +
                    "margin: 10px auto;" +
                    "padding: 10px;" +
                    "background: #49c0f0; " +
                    "/* Old browsers */background: -moz-linear-gradient(top,  #49c0f0 0%, #2cafe3 100%);" +
                    "/* FF3.6+ */background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#49c0f0), color-stop(100%,#2cafe3));" +
                    "/* Chrome,Safari4+ */background: -webkit-linear-gradient(top,  #49c0f0 0%,#2cafe3 100%);" +
                    "/* Chrome10+,Safari5.1+ */background: -o-linear-gradient(top,  #49c0f0 0%,#2cafe3 100%);" +
                    "/* Opera 11.10+ */background: -ms-linear-gradient(top,  #49c0f0 0%,#2cafe3 100%);" +
                    "/* IE10+ */background: linear-gradient(to bottom,  #49c0f0 0%,#2cafe3 100%);" +
                    "/* W3C */filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#49c0f0', endColorstr='#2cafe3',GradientType=0 );" +
                    "/* IE6-9 */-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;" +
                "}" +

                ".content{" +
                    "padding: 5px;" +
                    "background: rgb(249,252,247);" +
                    "/* Old browsers */background: -moz-linear-gradient(top,  rgba(249,252,247,1) 0%, rgba(245,249,240,1) 100%);" +
                    "/* FF3.6+ */background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(249,252,247,1)), color-stop(100%,rgba(245,249,240,1)));" +
                    "/* Chrome,Safari4+ */background: -webkit-linear-gradient(top,  rgba(249,252,247,1) 0%,rgba(245,249,240,1) 100%);" +
                    "/* Chrome10+,Safari5.1+ */background: -o-linear-gradient(top,  rgba(249,252,247,1) 0%,rgba(245,249,240,1) 100%);" +
                    "/* Opera 11.10+ */background: -ms-linear-gradient(top,  rgba(249,252,247,1) 0%,rgba(245,249,240,1) 100%);" +
                    "/* IE10+ */background: linear-gradient(to bottom,  rgba(249,252,247,1) 0%,rgba(245,249,240,1) 100%);" +
                    "/* W3C */filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f9fcf7', endColorstr='#f5f9f0',GradientType=0 );" +
                    "/* IE6-9 */-webkit-border-radius: 5px;" +
                    "-moz-border-radius: 5px;border-radius: 5px;" +
                "}" +

                ".bold_text{" +
                    "font-weight: bold;" +
                "}" +

                ".custom_table{" +
                    "margin-left: 20px;" +
                    "padding: 2px;" +
                    "background-color: #079FD9;" +
                "}" +

                ".row {" +
                    "padding: 5px;" +
                    "background-color: #71D1F5;" +
                "}" +

                ".data {" +
                    "width: 150px;" +
                "}";

    }
}
