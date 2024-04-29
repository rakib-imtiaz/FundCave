package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClass.Account;
import mainClass.Student;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class PaymentWindow implements Initializable {

    @FXML
    private TextField amountField;

    @FXML
    private Button goBackBtn;

    @FXML
    private DatePicker loanReturnDate;
    @FXML
    private Button makePaymentBtn;

    @FXML
    private TextField passwordField;

    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

    @FXML
    void goBack(ActionEvent event) {
        Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page", currentStage);


    }

    public String getRecieverID() {
        return recieverID;
    }

    public void setRecieverID(String recieverID) {
        this.recieverID = recieverID;
    }

    public String recieverID;

    // Constructor with parameters
    public PaymentWindow(String recieverID) {
        this.recieverID = recieverID;
    }

    public PaymentWindow() {
        // Default constructor
    }


    @FXML
    void makePayment(ActionEvent event) {
        // Get values from text fields
        String transactionID = generateTransactionID();
        String senderID = SessionHandler.getSession();
        String reciverID = SessionHandler.getCurrentLoanRecieversID();
        System.out.println("ReceverID: "+reciverID);
        double amount = Double.parseDouble(amountField.getText());
        //  String loanExpireDate=loanReturnDate.toString(); // datepicker
        String fetchedPass = fetchPasswordByStudentID(senderID);
        // Get the selected date from the DatePicker
        LocalDate selectedDate = loanReturnDate.getValue();

        String loanExpireDate = null;
// Check if a date is selected
        if (selectedDate != null) {
            // Format the selected date into a string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            loanExpireDate = selectedDate.format(formatter);

            // Now you can use the loanExpireDate string as needed
            System.out.println("Selected date: " + loanExpireDate);
        } else {
            // Handle case where no date is selected
            System.out.println("No date selected.");
        }

// Get the current timestamp in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

// Create a Date object using the current timestamp
        Date currentDate = new Date(currentTimeMillis);

// Create a SimpleDateFormat object to format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

// Format the Date object into a string
        String loanSendingDate = dateFormat.format(currentDate);

// Now you can use the loanSendingDate string as needed
        System.out.println("Loan sending date: " + loanSendingDate);


        // Ensure all fields are filled
        if (amountField.getText().isEmpty() || loanReturnDate.getValue() == null || passwordField.getText().isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }


        if (!(passwordField.getText().contentEquals(fetchedPass))) {
            showAlert("Error", "Invalid Password.");
            return;
        }


        //INSERT INTO Transaction (transactionID, senderID, receiverID, amount, loanSendingDate, loanExpireDate)
        // Make database connection and execute SQL INSERT statement to add a new student
        DataBaseManager.makeConnection("root", "root");
        String insertQuery = "INSERT INTO Transaction (transactionID, senderID, receiverID, amount, loanSendingDate, loanExpireDate)" +
                "VALUES ('" + transactionID + "','" + senderID + "','" + reciverID + "','" + amount + "','" + loanSendingDate + "','" + loanExpireDate + "')";

        System.out.println(insertQuery);
        boolean success = DataBaseManager.executeUpdate(insertQuery);
        DataBaseManager.closeConnection();

        if (success) {
            showAlert("Success", "Registration successful.");
            updateBalances(amount, senderID, reciverID);
            SessionHandler.setCurrentLoanRecieversID(null);


            Stage currentStage = (Stage) makePaymentBtn.getScene().getWindow();

            sceneBuilder.loadNewFrame("/profilePage.fxml", "ProfilePage", currentStage);

        } else {
            showAlert("Error", "Registration failed. Please try again.");
        }

    }

    private String fetchPasswordByStudentID(String senderID) {
        DataBaseManager.makeConnection("root", "root");
        DataBaseManager.fetchDataFromDatabase();
        for (Student student : DataBaseManager.getStudentArrayList()) {
            if (student.getStudentID().contentEquals(senderID)) {
                return student.getPassword();
            }
        }

        return null;
    }


    private String generateTransactionID() {
        // Implement the logic to generate a transaction ID here
        // You can use a combination of timestamp and random numbers for generating a unique transaction ID
        return "TRX_" + System.currentTimeMillis(); // Example implementation using current timestamp
    }
    String fetchStudentIDByAnonymousID(String id)
    {
        DataBaseManager.makeConnection("root","root");
        DataBaseManager.fetchDataFromDatabase();
        DataBaseManager.getStudentArrayList();

        for(Student student:DataBaseManager.getStudentArrayList())
        {
            if(student.getA_password().contentEquals(id))
            {
                return student.getStudentID();
            }
        }

        return null;

    }
    private void updateBalances(double amount, String senderID, String receiverID) {
        // Implement the logic to update sender's and receiver's balances here

        receiverID=fetchStudentIDByAnonymousID(receiverID);
        try {
            // Deduct the amount from the sender's balance
            double senderBalance = getBalance(senderID);
            double updatedSenderBalance = senderBalance - amount;

            // Add the amount to the receiver's balance
            double receiverBalance = getBalance(receiverID);
            double updatedReceiverBalance = receiverBalance + amount;

            // Update the sender's balance in the database
            String updateSenderQuery = "UPDATE Account SET balance = " + updatedSenderBalance + " WHERE studentID = '" + senderID + "'";
            DataBaseManager.executeUpdate(updateSenderQuery);

            // Update the receiver's balance in the database
            String updateReceiverQuery = "UPDATE Account SET balance = " + updatedReceiverBalance + " WHERE studentID = '" + receiverID + "'";
            DataBaseManager.executeUpdate(updateReceiverQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double getBalance(String studentID) {
        DataBaseManager.makeConnection("root", "root");
        DataBaseManager.fetchDataFromDatabase();
        for (Account account : DataBaseManager.getAccountArraylist()) {
            if (account.getStudentID().contentEquals(studentID)) {
                return account.getBalance();
            }
        }

        return -1;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
