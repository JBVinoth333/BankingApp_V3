package controller;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileInputStream;

@WebServlet("/api/sendEmail")
public class EmailServlet extends HttpServlet {
    final String from;
    final String password;

    private static final long serialVersionUID = 1L;

    public EmailServlet() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(
                    "/home/vinoth-zstk433/eclipse-workspace/BankingApplication_V3/resources/db.config"));
        } catch (IOException e) {
            System.out.println("error while Props load");
        }
        from = prop.getProperty("db.mailaddress");
        password = prop.getProperty("db.mailpass");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject responseObject = null;
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
            responseObject = new JSONObject();
            responseObject.put("status", 200);
            responseObject.put("message", "Email sent successfully!");
            response.getWriter().write(responseObject.toString());
        } catch (MessagingException e) {
            System.out.println("Error while send email");
            responseObject.put("status", 500);
            responseObject.put("message", "Email sent unsuccessfully!");
            response.getWriter().write(responseObject.toString());
        }
    }
}
