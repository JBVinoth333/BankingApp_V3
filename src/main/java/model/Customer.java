package model;

public class Customer extends Person {
    public static int count;
    int customerID;

    public Customer(String name, String aadaarNumber, int birthYear, String emailAddress, String phoneNumber, String address) {
        this(count, name, aadaarNumber, birthYear, emailAddress, phoneNumber, address);
        count++;
    }

    public Customer(int customerID, String name, String aadarNumber, int birthYear, String emailAddress, String phoneNumber,
                    String address) {
        super(name, aadarNumber, birthYear, emailAddress, phoneNumber, address);
        this.customerID = customerID;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Customer.count = count;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

}

