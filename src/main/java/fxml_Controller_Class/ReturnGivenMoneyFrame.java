package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnGivenMoneyFrame implements Initializable {

    public AnchorPane anchor;


    @FXML
    private TextField amountField;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button makePaymentBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField recieversIDField;

    @FXML
    private TextArea textArea;

    @FXML
    void goBack(ActionEvent event) {
        Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page",currentStage);


    }
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

    @FXML
    void makePayment(ActionEvent event) {
        String transactionID = recieversIDField.getText();

        // Construct the DELETE query to remove the transaction
        String query = "DELETE FROM Transaction WHERE transactionID = '" + transactionID + "'";

        // Execute the query
        DataBaseManager.makeConnection();
        DataBaseManager.executeUpdate(query);
        DataBaseManager.closeConnection();

        // Show a success message
        showAlert("Success!", "Successfully removed the transaction.");
        Stage currentStage = (Stage) makePaymentBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page",currentStage);


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

        textArea.setText(SessionHandler.getBorrowedDetails());



    }
}
