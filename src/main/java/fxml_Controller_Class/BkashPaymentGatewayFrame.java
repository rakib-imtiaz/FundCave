package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClass.Account;
import mainClass.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class BkashPaymentGatewayFrame implements Initializable {
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();
    public TextField phoneNumberField;


    @FXML
    private TextField amountField;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button makePaymentBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordField1;

    @FXML
    private TextField studentDetailsLabel;

    @FXML
    void goBack(ActionEvent event) {
        Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/Homepage.fxml", "Sign Up Page", currentStage);


    }

    Student fetchStudentByStudentID(String studentID) {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        Student currentStudent = null;
        // Fetching student from database;
        for (Student student : DataBaseManager.getStudentArrayList()) {
            if (SessionHandler.getSession().contentEquals(student.getStudentID())) {
                currentStudent = student;
                break;
            }
        }

        return currentStudent;

    }

    @FXML
    void makePayment(ActionEvent event) {
        Student student = fetchStudentByStudentID(SessionHandler.getSession());
        double newAmount = Double.parseDouble(amountField.getText());
        double currentAmount = fetchAmountByStudentID(SessionHandler.getSession());

        if (student.getPassword().contentEquals(passwordField.getText())) {
            updateStudentsAmount(currentAmount, newAmount);
            Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

            sceneBuilder.loadNewFrame("/profilePage.fxml", "profile Page", currentStage);


        } else {
            showAlert("Error", "Password not matched!");
        }


    }

    private double fetchAmountByStudentID(String session) {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        Student currentStudent = null;
        // Fetching student from database;
        for (Account account : DataBaseManager.getAccountArraylist()) {
            if (SessionHandler.getSession().contentEquals(account.getStudentID())) {
                return account.getBalance();
            }
        }

        return 0;
    }

    private void updateStudentsAmount(double previousAmount, double newAmount) {
        // Update amount=amount+newAmount value to 0 for the given studentID

        double balance=previousAmount+newAmount;
        String updateQuery = "UPDATE Account SET balance = "+balance+" WHERE studentID = '" + SessionHandler.getSession() + "'";

        // Make database connection
        DataBaseManager.makeConnection();

        // Execute the update query
        boolean rowsAffected = DataBaseManager.executeUpdate(updateQuery);

        if (rowsAffected) {
            showAlert("Success", "Transaction Successfull!");
        } else {
            System.out.println("No rows were updated. Student ID might be incorrect.");
        }


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
        Student student = fetchStudentByStudentID(SessionHandler.getSession());
        String content = " Name: " + student.getName() + "\n" +
                " ID: " + student.getStudentID() + "\n" +
                " Email: " + student.getEmail() + "\n";

        studentDetailsLabel.setText(content);


    }
}
