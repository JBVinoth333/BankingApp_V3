package service;

import java.sql.SQLException;

import dao.UserDao;
import model.Account;
import model.Customer;
import model.DublicateAadhaarException;
import model.DublicateUserException;

import java.sql.ResultSet;
import utils.EmailUtil;

public class UserService {
    UserDao userDao;

    public UserService() throws SQLException {
        userDao = new UserDao();
    }

    public String createAccount(String name,String aadhaar,int yob,String mobile,String email,String address,double balance,String loginPin) throws DublicateAadhaarException, SQLException, DublicateUserException {

        Customer c = new Customer(name,aadhaar,yob,email,mobile,address);
        Account ac = new Account(balance,loginPin);
        if (userDao.createAccount(c, ac)) {
            sendWelcomeEmail(name, email, ac.getAccountNumber());
            return ac.getAccountNumber();
        }
        return null;
    }

    public ResultSet login(String accountNumber, String pin) throws SQLException {
        return userDao.login(accountNumber, pin, "secretCode");
    }

    private void sendWelcomeEmail(String name, String email, String accountNumber) {
        try {
            String subject = "Welcome to ZS Bank - Your Account Details";
            String body = "Dear " + name + ",\n\n"
                    + "Your account has been created successfully!\n\n"
                    + "Account Number: " + accountNumber + "\n\n"
                    + "Please use this account number along with your PIN to login.\n\n"
                    + "Thank you for banking with us!\n"
                    + "ZS Bank";
            EmailUtil.sendEmail(email, subject, body);
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }
    }

}
