package service;

import java.sql.SQLException;

import dao.UserDao;
import model.Account;
import model.Customer;
import model.DublicateAadhaarException;
import model.DublicateUserException;


public class UserService {
    UserDao userDao;

    public UserService() throws SQLException {
        userDao = new UserDao();
    }

    public boolean createAccount(String name,String aadhaar,int yob,String mobile,String email,String address,double balance,String loginPin) throws DublicateAadhaarException, SQLException, DublicateUserException {

        Customer c = new Customer(name,aadhaar,yob,email,mobile,address);
        Account ac = new Account(balance,loginPin);
        return userDao.createAccount(c,ac);
    }

}
