package application;

import javafx.scene.control.Alert;
import mainClass.*;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseManager {

    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Fundcave";
    private static String USERNAME;
    private static String PASSWORD;
    private static Connection connection;
    private static final ArrayList<Student> studentArrayList = new ArrayList<>();
    private static final ArrayList<Post> postArrayList = new ArrayList<>();
    private static final ArrayList<Review> reviewArrayList = new ArrayList<>();
    private static final ArrayList<Coin> coinArrayList = new ArrayList<>();
    private static final ArrayList<Transaction> transactionArrayList = new ArrayList<>();
    private static final ArrayList<Account> accountArrayList = new ArrayList<>();

    public static ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public static ArrayList<Post> getPostArrayList() {
        return postArrayList;
    }

    public static ArrayList<Review> getReviewArrayList() {
        return reviewArrayList;
    }

    public static ArrayList<Coin> getCoinArrayList() {
        return coinArrayList;
    }

    public static ArrayList<Transaction> getTransactionArrayList() {
        return transactionArrayList;
    }

    public static ArrayList<Account> getAccountArraylist() {
        return accountArrayList;
    }


    public static void fetchDataFromDatabase() {
        // Fetch data from the 'Student' table
        String studentQuery = "SELECT * FROM Student";
        ResultSet studentResult = executeQuery(studentQuery);
        populateStudentArrayList(studentResult);
        String accountQuery = "SELECT * FROM Account";
        ResultSet accountResult = executeQuery(accountQuery);
        populateAccountArrayList(accountResult);

        // Fetch data from the 'Post' table
        String postQuery = "SELECT * FROM Post";
        ResultSet postResult = executeQuery(postQuery);
        populatePostArrayList(postResult);

        // Fetch data from the 'Review' table
        String reviewQuery = "SELECT * FROM Review";
        ResultSet reviewResult = executeQuery(reviewQuery);
        populateReviewArrayList(reviewResult);

        // Fetch data from the 'Coin' table
        String coinQuery = "SELECT * FROM Coin";
        ResultSet coinResult = executeQuery(coinQuery);
        populateCoinArrayList(coinResult);

        // Fetch data from the 'Transaction' table
        String transactionQuery = "SELECT * FROM Transaction";
        ResultSet transactionResult = executeQuery(transactionQuery);
        populateTransactionArrayList(transactionResult);
    }

    private static void populateStudentArrayList(ResultSet resultSet) {
        studentArrayList.clear(); // Clear the existing studentArrayList before populating

        try {
            while (resultSet.next()) {
                // Create a new Student object from the result set
                Student student = new Student();
                // Populate student attributes from the result set
                student.setStudentID(resultSet.getString("studentID"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setA_password(resultSet.getString("a_password"));
                // Set profile picture if needed
                // student.setProfile_picture(resultSet.getBlob("profile_picture"));
                student.setAddress(resultSet.getString("address"));

                // Add the student to the studentArrayList
                studentArrayList.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error populating Student ArrayList: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void populatePostArrayList(ResultSet resultSet) {
        postArrayList.clear(); // Clear the existing postArrayList before populating

        try {
            while (resultSet.next()) {
                // Create a new Post object from the result set
                Post post = new Post();
                // Populate post attributes from the result set
                post.setPostID(resultSet.getString("postID"));
                post.setUserID(resultSet.getString("userID"));
                post.setContent(resultSet.getString("content"));
                post.setTime(resultSet.getTimestamp("time"));

                // Add the post to the postArrayList
                postArrayList.add(post);
            }
        } catch (SQLException e) {
            System.out.println("Error populating Post ArrayList: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void populateReviewArrayList(ResultSet resultSet) {
        reviewArrayList.clear(); // Clear the existing reviewArrayList before populating

        try {
            while (resultSet.next()) {
                // Create a new Review object from the result set
                Review review = new Review();
                // Populate review attributes from the result set
                review.setStudentID(resultSet.getString("studentID"));
                int rating = resultSet.getInt("rating");
                review.setReview(rating); // Set the review rating using setReview method

                // Add the review to the reviewArrayList
                reviewArrayList.add(review);
            }
        } catch (SQLException e) {
            System.out.println("Error populating Review ArrayList: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static void populateCoinArrayList(ResultSet resultSet) {
        coinArrayList.clear(); // Clear the existing coinArrayList before populating

        try {
            while (resultSet.next()) {
                // Create a new Coin object from the result set
                Coin coin = new Coin();
                // Populate coin attributes from the result set
                coin.setValue(resultSet.getInt("value"));
                coin.setStudentID(resultSet.getString("studentID"));

                // Add the coin to the coinArrayList
                coinArrayList.add(coin);
            }
        } catch (SQLException e) {
            System.out.println("Error populating Coin ArrayList: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void populateTransactionArrayList(ResultSet resultSet) {
        transactionArrayList.clear(); // Clear the existing transactionArrayList before populating

        try {
            while (resultSet.next()) {
                // Create a new Transaction object from the result set
                Transaction transaction = new Transaction();
                // Populate transaction attributes from the result set
                transaction.setTransactionID(resultSet.getString("transactionID"));
                transaction.setSenderID(resultSet.getString("senderID"));
                transaction.setReceiverID(resultSet.getString("receiverID"));
                transaction.setAmount(resultSet.getString("amount"));


                // Parse date strings to Date objects if needed
                transaction.setLoadSendingDate(resultSet.getTimestamp("loanSendingDate"));
                transaction.setLoanExpireDate(resultSet.getTimestamp("loanExpireDate"));

                // Add the transaction to the transactionArrayList
                transactionArrayList.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Error populating Transaction ArrayList: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void populateAccountArrayList(ResultSet resultSet) {
        accountArrayList.clear(); // Clear the existing transactionArrayList before populating

        try {
            while (resultSet.next()) {
                // Create a new Transaction object from the result set
                Account account = new Account();
                // Populate transaction attributes from the result set
                account.setAccountID(resultSet.getString("accountID"));
                account.setStudentID(resultSet.getString("studentID"));
                account.setBalance(resultSet.getDouble("balance"));


                // Add the transaction to the transactionArrayList
                accountArrayList.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Error populating account ArrayList: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Method to update a review in the database
    private static void updateReview(Review review) {
        // Implement SQL UPDATE statement to update the review in the database
        String updateReviewQuery = "UPDATE Review SET rating = ? WHERE reviewID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateReviewQuery);
            statement.setInt(1, review.getReview().get(0)); // Assuming there's only one rating per review
            // Set the review ID based on your database schema and review object
            // statement.setInt(2, review.getReviewID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating Review table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to update coins associated with a student in the database
    private static void updateCoins(Coin coin) {
        // Implement SQL UPDATE statement to update the number of coins associated with a student in the database
        String updateCoinsQuery = "UPDATE Coin SET value = ? WHERE studentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateCoinsQuery);
            statement.setInt(1, coin.getValue());
            statement.setString(2, coin.getStudentID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error updating Coin table: " + e.getMessage());
            e.printStackTrace();
        }
    }


    static void updateDatabase() {
        /*
         * Update data in post, student, transaction, review, and coin tables
         */

        // Update data in the 'Post' table
        for (Post post : postArrayList) {
            String updatePostQuery = "UPDATE Post SET content = ? WHERE postID = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(updatePostQuery);
                statement.setString(1, post.getContent());
                statement.setString(2, post.getPostID());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error updating Post table: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Update data in the 'Student' table
        for (Student student : studentArrayList) {
            String updateStudentQuery = "UPDATE Student SET name = ?, email = ?, password = ?, a_password = ?, profile_picture = ?, address = ? WHERE studentID = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(updateStudentQuery);
                statement.setString(1, student.getName());
                statement.setString(2, student.getEmail());
                statement.setString(3, student.getPassword());
                statement.setString(4, student.getA_password());
                // Set profile picture if needed
                // statement.setBlob(5, student.getProfile_picture());
                statement.setString(6, student.getAddress());
                statement.setString(7, student.getStudentID());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error updating Student table: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Update data in the 'Transaction' table
        for (Transaction transaction : transactionArrayList) {
            String updateTransactionQuery = "UPDATE Transaction SET amount = ?, loadSendingDate = ?, loanExpireDate = ? WHERE transactionID = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(updateTransactionQuery);
                statement.setString(1, transaction.getAmount());
                // Set loadSendingDate and loanExpireDate if needed
                // statement.setTimestamp(2, new Timestamp(transaction.getLoadSendingDate().getTime()));
                // statement.setTimestamp(3, new Timestamp(transaction.getLoanExpireDate().getTime()));
                statement.setString(4, transaction.getTransactionID());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error updating Transaction table: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // No updates are allowed for the 'Review' table as per the provided schema

        // No updates are allowed for the 'Coin' table as per the provided schema
    }


    private static void executeUpdateWithParameters(String sqlQuery, Object... parameters) {
        if (connection == null) {
            System.out.println("Database connection is not established.");
            return;
        }

        try {
            // Create a PreparedStatement for executing SQL updates with parameters
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set parameters for the PreparedStatement
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            // Execute the SQL update
            int rowsAffected = preparedStatement.executeUpdate();

            // Close the PreparedStatement
            preparedStatement.close();

            // Print the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error executing SQL update with parameters: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean makeConnection(String userName, String password) {
        USERNAME = userName;
        PASSWORD = password;
        boolean isConnected = false;

        try {
            // Load the MySQL JDBC driver
            Class.forName(DRIVER_CLASS);

            // Establish the connection
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            if (connection != null) {
                System.out.println("Database connection successful!");
                isConnected = true;
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check console for errors.");
            e.printStackTrace();
        }

        return isConnected;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }
        }
    }

    public static ResultSet executeQuery(String sqlQuery) {
        if (connection == null) {
            System.out.println("Database connection is not established.");
            return null;
        }

        try {
            // Create a Statement object for executing SQL queries
            Statement statement = connection.createStatement();

            // Execute the SQL query
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Return the ResultSet for SELECT queries
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query.");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean executeUpdate(String sqlQuery) {
        if (connection == null) {
            System.out.println("Database connection is not established.");
            return false;
        }

        try {
            // Create a Statement object for executing SQL updates (INSERT, UPDATE, DELETE)
            Statement statement = connection.createStatement();

            // Execute the SQL update query
            int rowsAffected = statement.executeUpdate(sqlQuery);

            // Close the Statement
            statement.close();

            // Return true if rows were affected, indicating successful update
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error executing SQL update.");
            showAlert("Error", e.getMessage());

            e.printStackTrace();
            return false;
        }
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
