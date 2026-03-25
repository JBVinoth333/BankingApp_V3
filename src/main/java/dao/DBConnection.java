package dao;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class DBConnection {
    private static volatile DBConnection instance;

    private String url;
    private String userName;
    private String password;

    private static final Logger logger = LogManager.getLogger("DBConnection");

    private DBConnection() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("/home/vinoth-zstk433/eclipse-workspace/BankingApplication_V3/resources/db.config")) {
            prop.load(fis);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        url = prop.getProperty("db.url");
        userName = prop.getProperty("db.username");
        password = prop.getProperty("db.password");
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
