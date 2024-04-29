drop database if exists Fundcave;
create Database Fundcave;
use Fundcave;

CREATE TABLE Student
(
    studentID VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    a_password VARCHAR(255) NOT NULL,
    profile_picture BLOB,
    address VARCHAR(255)
);
CREATE TABLE Review
(
    reviewID INT
    AUTO_INCREMENT PRIMARY KEY,
    studentID VARCHAR
    (50) NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY
    (studentID) REFERENCES Student
    (studentID)
);
    CREATE TABLE Transaction
    (
        transactionID VARCHAR(50) PRIMARY KEY,
        senderID VARCHAR(50) NOT NULL,
        receiverID VARCHAR(50) NOT NULL,
        amount DECIMAL(10, 2) NOT NULL,
        loanSendingDate DATETIME NOT NULL,
        loanExpireDate DATETIME
    );
    CREATE TABLE Post
    (
        postID VARCHAR(50) PRIMARY KEY,
        userID VARCHAR(50) NOT NULL,
        content TEXT NOT NULL,
        time DATETIME NOT NULL

    );

    CREATE TABLE Coin
    (
        value INT NOT NULL,
        studentID VARCHAR(50) PRIMARY KEY,
        FOREIGN KEY (studentID) REFERENCES Student(studentID)
    );

    CREATE TABLE Account
    (
        accountID INT
        AUTO_INCREMENT PRIMARY KEY,
    studentID VARCHAR
        (50) UNIQUE NOT NULL,
    balance DECIMAL
        (10, 2) NOT NULL,
    FOREIGN KEY
        (studentID) REFERENCES Student
        (studentID)
);


        -- Insert data into the Student table
        INSERT INTO Student
            (studentID, name, email, password, a_password, address)
        VALUES
            ('S001', 'John Doe', 'johndoe@example.com', 'password123', 'anotherpassword', '123 Main St, Anytown'),
            ('S002', 'Jane Smith', 'janesmith@example.com', 'pass123', 'anotherpass', '456 Elm St, Othertown');

        INSERT INTO Account
            (studentID, balance)
        VALUES
            ('S001', 1000.00),
            -- Student S001 has an initial balance of 100.00
            ('S002', 1000.00);
        -- Student S002 has an initial balance of 50.00

        -- Insert data into the Review table
        INSERT INTO Review
            (studentID, rating)
        VALUES
            ('S001', 4),
            ('S002', 5);

        -- Insert data into the Transaction table

        -- Insert data into the Post table


        INSERT INTO Coin
            (value, studentID)
        VALUES
            (10, 'S001'),
            -- Student S001 has 10 coins
            (5, 'S002');   -- Student S002 has 5 coins
