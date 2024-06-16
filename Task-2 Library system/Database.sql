//Create the Database and Tables:
CREATE DATABASE LibraryDB;

USE LibraryDB;

CREATE TABLE Book (
    bookID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    availability BOOLEAN DEFAULT TRUE
);

CREATE TABLE Patron (
    patronID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contactInfo VARCHAR(255)
);

CREATE TABLE BorrowingRecord (
    recordID INT AUTO_INCREMENT PRIMARY KEY,
    bookID INT,
    patronID INT,
    borrowDate DATE,
    returnDate DATE,
    FOREIGN KEY (bookID) REFERENCES Book(bookID),
    FOREIGN KEY (patronID) REFERENCES Patron(patronID)
);
