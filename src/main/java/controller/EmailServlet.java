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
import utils.EmailUtil;

@WebServlet("/api/sendEmail")
public class EmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmailServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject responseObject = new JSONObject();
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message");

        try {
            EmailUtil.sendEmail(to, subject, messageText);
            responseObject.put("status", 200);
            responseObject.put("message", "Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Error while send email");
            responseObject.put("status", 500);
            responseObject.put("message", "Email sent unsuccessfully!");
        }
        response.getWriter().write(responseObject.toString());
    }
}
