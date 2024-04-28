package fxmlClass;

import application.DataBaseManager;
import application.SessionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import application.SceneBuildingHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageFrame {

	@FXML
	public Button forgetPasswordBtn;

	@FXML
	private Button goBackBtn;

	@FXML
	private Button loginBtn;

	@FXML
	private PasswordField passwordField;

	@FXML
	public Button signUpBtn;

	@FXML
	private TextField studentIDField;

	@FXML
	void forgetPassword(ActionEvent event) {
		// Handle forget password functionality if needed
	}

	@FXML
	void goBack(ActionEvent event) {
		SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();
		Stage currentStage = (Stage) goBackBtn.getScene().getWindow();
		sceneBuilder.loadNewFrame("/Homepage.fxml", "Home Page", currentStage);
	}

	@FXML
	void login(ActionEvent event) {
		// Get student ID and password from the input fields
		String studentID = studentIDField.getText();
		String password = passwordField.getText();

		// Attempt to make a database connection
		if (DataBaseManager.makeConnection("root", "root")) {
			// Construct the SQL query to fetch student data based on studentID and password
			String sqlQuery = "SELECT * FROM Student WHERE studentID = '" + studentID + "' AND password = '" + password + "'";

			// Execute the SQL query using executeQuery() for SELECT operation
			ResultSet resultSet = DataBaseManager.executeQuery(sqlQuery);

			try {
				// Check if resultSet is not null and if it contains any rows
				if (resultSet != null && resultSet.next()) {
					// If login is successful (student found), navigate to the profile page (profilePage.fxml)
					SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();
					Stage currentStage = (Stage) loginBtn.getScene().getWindow();
//setting up the session for current student
					System.out.println(studentID+"from loginpage");
					SessionHandler.setSession(studentID);

					sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page", currentStage);


				} else {
					// If login fails (student not found), show an error message
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Login Failed");
					alert.setHeaderText(null);
					alert.setContentText("Invalid student ID or password. Please try again.");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// Show an error message if database connection fails
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Database Connection Error");
			alert.setHeaderText(null);
			alert.setContentText("Failed to connect to the database. Please check your database settings.");
			alert.showAndWait();
		}

		// Close the database connection
		DataBaseManager.closeConnection();
	}

	@FXML
	void signUp(ActionEvent event) {


	}

}
