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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mainClass.Student;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PostForm implements Initializable {
    @FXML
    private TextField amountField;

    @FXML
    private Button chooseFileBtn;

    @FXML
    private TextField descriptionField;

    @FXML
    private ImageView imageViewer;
    @FXML
    private Button gobackBtn;

    @FXML
    private TextField imageField;

    @FXML
    private Button postBtn;


    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

    @FXML
    void chooseFile(ActionEvent event) {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();

        // Set title for the FileChooser dialog
        fileChooser.setTitle("Choose File");
        // Add filters to accept only PNG, JPG, and JPEG files
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);


        // Show the FileChooser dialog
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected
        if (selectedFile != null) {
            // Display the selected file path in the TextField or process the selected file
            String filePath = selectedFile.getAbsolutePath();
            imageField.setText(filePath); // Assuming imageField is the TextField for displaying the file path
            Image image = new Image(selectedFile.toURI().toString());
            imageViewer.setImage(image);
        }
    }

    @FXML
    void goback(ActionEvent event) {
        // Go back to the profile page
        Stage currentStage = (Stage) gobackBtn.getScene().getWindow();
        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page", currentStage);
    }

    String fetchAnonymousIDByStudentID(String studentID)
    {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        DataBaseManager.getStudentArrayList();

        for(Student student:DataBaseManager.getStudentArrayList())
        {
            if(student.getStudentID().contentEquals(studentID))
            {
                return student.getA_password();
            }
        }

        return null;

    }

    @FXML
    void post(ActionEvent event) {
        // Ensure all fields are filled
        if (descriptionField.getText().isEmpty() || amountField.getText().isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        // Make database connection and execute SQL INSERT statement to add a new post
        DataBaseManager.makeConnection();
        String postID = generateUniqueID();
        String userId = fetchAnonymousIDByStudentID(SessionHandler.getSession());
        // Format the current date into a string representation compatible with the database
        Date date = new Date(System.currentTimeMillis());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        String insertQuery = "INSERT INTO Post (postID, userID, content, time) " +
                "VALUES ('" + postID + "','" + userId + "','" + descriptionField.getText()+"\nAMOUNT:"+amountField.getText() + "','" + formattedDate + "')";

        System.out.println(insertQuery);
        boolean success = DataBaseManager.executeUpdate(insertQuery);
        DataBaseManager.closeConnection();

        if (success) {
            showAlert("Success", "Post successful.");
            clearFields();

            Stage currentStage = (Stage) postBtn.getScene().getWindow();
            sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page", currentStage);
        } else {
            showAlert("Error", "Post failed. Please try again.");
        }
    }

    private String generateUniqueID() {
        // Generate a unique ID based on current timestamp
        long timestamp = System.currentTimeMillis();
        return "POST_" + timestamp;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        descriptionField.clear();
        amountField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code here
    }
}
