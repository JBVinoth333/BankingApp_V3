package model;

class BankManager extends Person {
    Bank bank;
    double salary;
    String managerId;
    int managerPin;

    BankManager(String name, String aadarNumber, int birthYear, String emailAddress, String phoneNumber, String address,
                double salary, Bank bank, String managerId, int managerPin) {
        super(name, aadarNumber, birthYear, emailAddress, phoneNumber, address);
        this.salary = salary;
        this.bank = bank;
        this.managerId = managerId;
        this.managerPin = managerPin;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public int getManagerPin() {
        return managerPin;
    }

    public void setManagerPin(int managerPin) {
        this.managerPin = managerPin;
    }


}
