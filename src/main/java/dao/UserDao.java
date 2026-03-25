package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Account;
import model.Customer;
import model.DublicateAadhaarException;
import model.DublicateUserException;
import utils.Queries;



public class UserDao {
    Connection con;

    public UserDao() throws SQLException {
        con = DBConnection.getInstance().getConnection();
    }

    public boolean createAccount(Customer c , Account ac) throws DublicateAadhaarException, DublicateUserException {
        try {
            System.out.println("User-DAO started");
            if(checkIfAadhaarAlredyExist(c.getAadarNumber())) {
                System.out.println("Aadhaar check started");
                throw new DublicateAadhaarException("User Already exists for given Aadhaar");
            }
            if(checkIfUserAlreadyExist(c.getName(), c.getPhoneNumber(), c.getEmailAddress())) {
                System.out.println("User check started");
                throw new DublicateUserException("User Already exists for given Details");
            }
            con.setAutoCommit(false);
            System.out.println("Error-1");
            PreparedStatement ps1 = con.prepareStatement(Queries.INSERT_CUSTOMER);
            ps1.setString(1, c.getName());
            ps1.setInt(2, c.getBirthYear());
            ps1.setString(3, c.getEmailAddress());
            ps1.setString(4, c.getPhoneNumber());
            ps1.setString(5, c.getAadarNumber());
            ps1.setString(6, c.getAddress());
            ps1.execute();
            System.out.println("Error-2");

            ps1 = con.prepareStatement(Queries.GET_CUSTOMERID_BY_AADHAAR);
            ps1.setString(1, c.getAadarNumber());
            ps1.execute();
            System.out.println("Error-3");

            ResultSet rs = ps1.getResultSet();
            int customerID = 0;
            if(rs.next()) {
                customerID=rs.getInt("CustomerID");
            }
            System.out.println("Error-4");
            PreparedStatement ps2 = con.prepareStatement(Queries.INSERT_ACCOUNT);
            ps2.setString(1, ac.getAccountNumber());
            ps2.setInt(2,customerID);
            ps2.setDouble(3, ac.getAmount());
            ps2.setString(4,ac.getPassPin());
            ps2.setString(5, "secretCode");

            ps2.execute();

            System.out.println("Error-5");
            con.commit();
            System.out.println("Error-6");
        }catch(SQLException e) {
            System.out.println("vfvnfn ef ief i ");
        }
        return true;
    }


    private boolean checkIfAadhaarAlredyExist(String aadhaar){
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(Queries.GET_EXIST_AADHAAR);
            ps.setString(1,aadhaar );
            con.commit();
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage()+" aadhaar check");
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage()+" aadhaar check con rollback");
            }
        }
        return false;
    }

    private boolean checkIfUserAlreadyExist(String name,String mobile,String email){
        try {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(Queries.GET_EXIST_USER);
            ps.setString(1,name);
            ps.setString(2,mobile);
            ps.setString(3, email);
            con.commit();
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage()+" User check");
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e1.getMessage()+" User check con rollback");
            }
        }
        return false;
    }

    public int getAccountsCount() {
        int count = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.GET_CUSTOMERS_COUNT);
            if(rs.next()) {
                count = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println(e.getMessage()+" get customer count");
        }
        return count;
    }

    public int getCustonerIDByAadhaarNumber(String aadhaar) {
        try {
            PreparedStatement ps = con.prepareStatement(Queries.GET_CUSTOMERID_BY_AADHAAR);
            ps.setString(1, aadhaar);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("CustomerID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " getCustonerIDByAadhaarNumber");
        }
        return 0;
    }

    public String getAccountNumberByCustomerID(int customerID) {
        try {
            PreparedStatement ps = con.prepareStatement(Queries.GET_AC_NUMBER_BY_CUSTOMERID);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("AccountNumber");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " getAccountNumberByCustomerID");
        }
        return null;
    }

    public ResultSet login(String accountNumber, String pin, String secretKey) {
        try {
            PreparedStatement ps = con.prepareStatement(Queries.LOGIN);
            ps.setString(1, accountNumber);
            ps.setString(2, secretKey);
            ps.setString(3, pin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " login");
        }
        return null;
    }
}
