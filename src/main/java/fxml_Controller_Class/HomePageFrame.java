package fxml_Controller_Class;

import application.SceneBuildingHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class HomePageFrame {
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

    @FXML
    private Button AboutBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Button adminBtn;

    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void adminlogin(ActionEvent event) {
        String adminID = "admin";
        String adminPass = "password";

        // Create a text input dialog for admin ID
        TextInputDialog adminIDDialog = new TextInputDialog();
        adminIDDialog.setTitle("Admin Login");
        adminIDDialog.setHeaderText("Enter Admin ID:");
        adminIDDialog.setContentText("Admin ID:");

        // Show the dialog and wait for user input
        Optional<String> adminIDResult = adminIDDialog.showAndWait();

        if (adminIDResult.isPresent()) {
            String enteredAdminID = adminIDResult.get();

            // Create a text input dialog for admin password
            TextInputDialog adminPassDialog = new TextInputDialog();
            adminPassDialog.setTitle("Admin Login");
            adminPassDialog.setHeaderText("Enter Admin Password:");
            adminPassDialog.setContentText("Password:");

            // Show the dialog and wait for user input
            Optional<String> adminPassResult = adminPassDialog.showAndWait();

            if (adminPassResult.isPresent()) {
                String enteredAdminPass = adminPassResult.get();

                if (adminID.equals(enteredAdminID) && adminPass.equals(enteredAdminPass)) {
                    // Successful login
                    Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                    sceneBuilder.loadNewFrame("/AdminPage.fxml", "Admin Page", currentStage);
                } else {
                    // Incorrect login, show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid admin ID or password. Please try again.");
                    alert.showAndWait();
                }
            }
        }
    }


    @FXML
    void login(ActionEvent event) {
        Stage currentStage = (Stage) loginBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/loginPage.fxml", "Login Page", currentStage);

    }

    @FXML
    void signup(ActionEvent event) {

        Stage currentStage = (Stage) signupBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/signupPage.fxml", "Sign Up Page", currentStage);

    }

}
