//Create the Database and Tables: 
CREATE DATABASE BankingDB;

USE BankingDB;

CREATE TABLE User (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    pinCode INT NOT NULL
);

CREATE TABLE Account (
    accountID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    accountType VARCHAR(50),
    balance DOUBLE,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Transaction (
    transactionID INT AUTO_INCREMENT PRIMARY KEY,
    accountID INT,
    type VARCHAR(50),
    amount DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (accountID) REFERENCES Account(accountID)
);

CREATE TABLE Loan (
    loanID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    amount DOUBLE,
    interestRate DOUBLE,
    term INT,
    startDate DATE,
    FOREIGN KEY (userID) REFERENCES User(userID)
);
