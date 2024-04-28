create Database Fundcave;
use Fundcave;

CREATE TABLE Student (
    studentID VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    a_password VARCHAR(255) NOT NULL,
    profile_picture BLOB,
    address VARCHAR(255),
    coin INT DEFAULT 0
);
CREATE TABLE Review (
    reviewID INT AUTO_INCREMENT PRIMARY KEY,
    studentID VARCHAR(50) NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY (studentID) REFERENCES Student(studentID)
);
CREATE TABLE Transaction (
    transactionID VARCHAR(50) PRIMARY KEY,
    senderID VARCHAR(50) NOT NULL,
    receiverID VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    loadSendingDate DATETIME NOT NULL,
    loanExpireDate DATETIME,
    FOREIGN KEY (senderID) REFERENCES Student(studentID),
    FOREIGN KEY (receiverID) REFERENCES Student(studentID)
);
CREATE TABLE Post (
    postID VARCHAR(50) PRIMARY KEY,
    userID VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES Student(studentID)
);

-- Insert data into the Student table
INSERT INTO Student (studentID, name, email, password, a_password, address, coin)
VALUES ('S001', 'John Doe', 'johndoe@example.com', 'password123', 'anotherpassword', '123 Main St, Anytown', 100),
       ('S002', 'Jane Smith', 'janesmith@example.com', 'pass123', 'anotherpass', '456 Elm St, Othertown', 50);

-- Insert data into the Review table
INSERT INTO Review (studentID, rating)
VALUES ('S001', 4),
       ('S002', 5);

-- Insert data into the Transaction table
INSERT INTO Transaction (transactionID, senderID, receiverID, amount, loadSendingDate, loanExpireDate)
VALUES ('T001', 'S001', 'S002', 50.00, '2024-04-23 10:00:00', NULL),
       ('T002', 'S002', 'S001', 30.00, '2024-04-24 09:00:00', '2024-05-24 09:00:00');

-- Insert data into the Post table
INSERT INTO Post (postID, userID, content)
VALUES ('P001', 'S001', 'Hello, this is my first post!'),
       ('P002', 'S002', 'Check out this cool article.');
