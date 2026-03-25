package controller;

import java.sql.SQLException;
import dao.DBConnection;
import dao.UserDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import model.Customer;

@WebListener
public class BankingAppListener implements ServletContextListener, ServletRequestListener {

    UserDao userDao;

    public BankingAppListener() {
        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBConnection.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed");
        System.out.println("Listener Destroyed");
    }

    public void contextInitialized(ServletContextEvent sce)  {
        Customer.count=userDao.getAccountsCount();
        System.out.println("Listener Activated");
    }

}
