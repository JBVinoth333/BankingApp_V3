package model;

class Person {
    String name;
    String aadarNumber;
    int birthYear;
    String emailAddress;
    String phoneNumber;
    String address;

    Person(String name, String aadarNumber, int birthYear, String emailAddress, String phoneNumber, String address) {
        this.name = name;
        this.aadarNumber = aadarNumber;
        this.birthYear = birthYear;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadarNumber() {
        return aadarNumber;
    }

    public void setAadarNumber(String aadarNumber) {
        this.aadarNumber = aadarNumber;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}