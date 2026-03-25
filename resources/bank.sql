DROP DATABASE IF EXISTS BANK_V2;
CREATE DATABASE BANK_V2;
USE BANK_V2;

--CREATE TABLE MODE
CREATE TABLE Mode(
                     ModeID TINYINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     ModeName VARCHAR(20) NOT NULL UNIQUE );

--CREATE TABLE Operations
CREATE TABLE Operations(
                           OperationID TINYINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           OperationName VARCHAR(20) UNIQUE );

--CREATE TABLE EntryTypes
CREATE TABLE EntryTypes(
                           EntryID TINYINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           EntryName VARCHAR(20) NOT NULL);

--CREATE TABLE Bank
CREATE TABLE Bank(BankID TINYINT AUTO_INCREMENT PRIMARY KEY,
                  BankName VARCHAR(30) NOT NULL UNIQUE ,
                  MinimumLoanAmount DOUBLE,
                  MaximumPersonalLoanAmount DOUBLE,
                  MaximumHouseLoanAmount DOUBLE,
                  MaximumBusinessLoanAmount DOUBLE);

--CREATE TABLE BankManager
CREATE TABLE BankManager(
                            ManagerID TINYINT AUTO_INCREMENT PRIMARY KEY,
                            BankID TINYINT UNIQUE,
                            BirthYear INT,ManagerName VARCHAR(30) NOT NULL,
                            Mail VARCHAR(100) UNIQUE NOT NULL,MobileNumber VARCHAR(15) UNIQUE NOT NULL,
                            AadhaarNumber VARCHAR(12) UNIQUE,
                            Address VARCHAR(150),
                            Salary DOUBLE,
                            SecretNumber VARCHAR(6),
                            Pin VARBINARY(250),
                            FOREIGN KEY (BankID) REFERENCES Bank(BankID) ON DELETE CASCADE ON UPDATE CASCADE );

--CREATE TABLE Customers
CREATE TABLE Customers(CustomerID INT NOT NULL AUTO_INCREMENT,
                       CustomerName VARCHAR(50) NOT NULL,
                       BirthYear INT NOT NULL,
                       Email VARCHAR(50) NOT NULL UNIQUE ,
                       PhoneNumber VARCHAR(15) NOT NULL UNIQUE ,
                       AadhaarNumber VARCHAR(12) NOT NULL UNIQUE ,
                       Address VARCHAR(100),
                       PRIMARY KEY (CustomerID,CustomerName,Email,PhoneNumber));

--CREATE TABLE Accounts
CREATE TABLE Accounts(
                         AccountNumber VARCHAR(15) NOT NULL PRIMARY KEY,
                         Balance DOUBLE NOT NULL,
                         Pin VARBINARY(255) NOT NULL,
                         CustomerID INT NOT NULL,
                         FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE);

--CREATE TABLE Transactions
CREATE TABLE Transactions(
                             TransactionID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             Time DATETIME NOT NULL,
                             Amount DOUBLE NOT NULL,
                             ModeID TINYINT,
                             OperationID TINYINT,
                             FOREIGN KEY (OperationID) REFERENCES Operations(OperationID) ON UPDATE CASCADE,
                             FOREIGN KEY (ModeID) REFERENCES Mode(ModeID) ON UPDATE CASCADE);

--CREATE TABLE Entries
CREATE TABLE Entries(
                        TransactionACID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        AccountNumber VARCHAR(15) DEFAULT NULL,
                        EntryID TINYINT DEFAULT NULL,
                        TransactionID INT DEFAULT NULL,
                        FOREIGN KEY (EntryID) REFERENCES EntryTypes(EntryID) ON UPDATE CASCADE,
                        FOREIGN KEY (AccountNumber) REFERENCES Accounts(AccountNumber) ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (TransactionID) REFERENCES Transactions(TransactionID) ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (TransactionID) REFERENCES Transactions(TransactionID) ON DELETE CASCADE ON UPDATE CASCADE);

--CREATE TABLE Sms
CREATE TABLE Sms(
                    SmsID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    Title VARCHAR(20) NOT NULL,
                    Time DATETIME NOT NULL,
                    Discription VARCHAR(150) NOT NULL,
                    Type ENUM('Sent','Receive'),
                    AccountNumber VARCHAR(15),
                    FOREIGN KEY (AccountNumber) REFERENCES Accounts(AccountNumber) ON DELETE CASCADE ON UPDATE CASCADE);

--INSERT Bank
INSERT INTO Bank(
    BankName,
    MinimumLoanAmount,
    MaximumPersonalLoanAmount,
    MaximumHouseLoanAmount,
    MaximumBusinessLoanAmount) VALUES("ZS Bank", 10000, 100000000, 150000000, 8000000);

--INSERT BankManager
INSERT INTO BankManager(BankID,
                        BirthYear,
                        ManagerName,
                        Mail,
                        MobileNumber,
                        AadhaarNumber,
                        Address,
                        Salary,
                        SecretNumber,
                        Pin) VALUES(1,2003,"Vinoth","vinoth333@zmail.com","8883943592","987898766776","Tenkasi",200000,"432100",AES_ENCRYPT(123456,"uzumaki"));

INSERT INTO EntryTypes(EntryName) VALUES("Debit"),("Credit"),("Inquiry");

--INSERT DEFAULT VALUES IN Mode
INSERT INTO Mode(ModeName) VALUES("Digital Wallets"),("Card Payments"),("Bank Transfers"),("Net Banking");

--INSERT DEFAULT VALUES IN Operations
INSERT INTO Operations(OperationName) VALUES("Deposit"),("Withdraw"),("Transfer"),("Check Balance");

------------------------------------------------------------------------------------------------
