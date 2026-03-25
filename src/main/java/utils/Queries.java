package utils;

public class Queries {

    public static final String INSERT_CUSTOMER = "INSERT INTO Customers(CustomerName,BirthYear,Email,PhoneNumber,AadhaarNumber,Address) VALUES(?,?,?,?,?,?)";

    public static final String INSERT_ACCOUNT = "INSERT INTO Accounts(AccountNumber,CustomerID,Balance,Pin) VALUES(?,?,?,AES_ENCRYPT(?,?))";

    public static final String GET_CUSTOMERID_BY_AADHAAR = "SELECT CustomerID FROM Customers WHERE AadhaarNumber LIKE ?";

    public static final String GET_AC_NUMBER_BY_CUSTOMERID = "SELECT AccountNumber FROM Accounts WHERE CustomerID=?";

    public static final String GET_EXIST_AADHAAR = "SELECT * FROM Customers WHERE AadhaarNumber LIKE ?";

    public static final String GET_EXIST_USER = "SELECT * FROM Customers WHERE CustomerName LIKE ? AND PhoneNumber LIKE ? AND Email LIKE ?";

    public static final String GET_CUSTOMERS_COUNT = "SELECT count(*) FROM Customers";




}
