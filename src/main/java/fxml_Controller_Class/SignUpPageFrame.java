package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageFrame  implements Initializable {

	@FXML
	private TextField addressField;

	@FXML
	private TextField anonymousPasswordField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button registerBtn;

	@FXML
	private TextField studentIDField;

	@FXML
	private TextField studentNameField;

	@FXML
	private TextField universityEmailField;
	@FXML
	private Button goBackBtn;
	private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();



	@FXML
	void goBack(ActionEvent event) {
		Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

		sceneBuilder.loadNewFrame("/Homepage.fxml", "Sign Up Page",currentStage);


	}
	@FXML
	void register(ActionEvent event) {
		// Get values from text fields
		String studentID = studentIDField.getText();
		String name = studentNameField.getText();
		String email = universityEmailField.getText();
		String password = passwordField.getText();
		String aPassword = anonymousPasswordField.getText();
		String address = addressField.getText();

		// Ensure all fields are filled
		if (studentID.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
			showAlert("Error", "All fields are required.");
			return;
		}

		// Make database connection and execute SQL INSERT statement to add a new student
		DataBaseManager.makeConnection("root", "root");
		String insertQuery = "INSERT INTO Student (studentID, name, email, password, a_password, address) " +
				"VALUES ('" + studentID + "','" + name + "','" + email + "','" + password + "','" + aPassword + "','" + address + "')";
String insertQuery2 = "INSERT INTO Account (studentID, balance) " +
				"VALUES ('" + studentID + "','" + 1000+ "')";

		System.out.println(insertQuery);
        boolean success = DataBaseManager.executeUpdate(insertQuery);
        boolean success2 = DataBaseManager.executeUpdate(insertQuery2);
		DataBaseManager.closeConnection();

		if (success && success2) {
			showAlert("Success", "Registration successful.");
			clearFields();

			Stage currentStage = (Stage) registerBtn.getScene().getWindow();

			sceneBuilder.loadNewFrame("/Homepage.fxml", "Sign Up Page",currentStage);

		} else {
			showAlert("Error", "Registration failed. Please try again.");
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void clearFields() {
		studentIDField.clear();
		studentNameField.clear();
		universityEmailField.clear();
		passwordField.clear();
		anonymousPasswordField.clear();
		addressField.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
