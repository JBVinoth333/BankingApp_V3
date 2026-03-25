package model;


public class Account {
    String accountNumber;
    static int count;
    double amount;
    String passPin;
    boolean isLoan;
    boolean isPaid;

    public Account(double amount, String passPin) {
        this.amount = amount;
        this.passPin = passPin;
        accountNumber = "85689" + count + "34786" + count;
        count++;
        isLoan = false;
        isPaid = true;
    }

    public Account(String accountNumber, double amount, String passPin) {
        this.amount = amount;
        this.passPin = passPin;
        this.accountNumber = accountNumber;
        isLoan = false;
        isPaid = true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Account.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPassPin() {
        return passPin;
    }

    public void setPassPin(String passPin) {
        this.passPin = passPin;
    }

    public boolean isLoan() {
        return isLoan;
    }

    public void setLoan(boolean isLoan) {
        this.isLoan = isLoan;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }


}
