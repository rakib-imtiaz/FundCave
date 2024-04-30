package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mainClass.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ProfilePageFrame implements Initializable {
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();
    public Button cashInBkashBtn;

    @FXML
    private Label balanceField;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label numberOfCoinField;

    @FXML
    private ImageView profilePictureImageField;

    @FXML
    private Button redemCoinBtn;

    @FXML
    private Button requestForLoanBtn;

    @FXML
    private Button returnMoneyBtn;

    @FXML
    private Button takenLoanDetails;

    @FXML
    private Label totalGivenField;

    @FXML
    private Label totalReviewField1;

    @FXML
    private Label totalTakenField;

    @FXML
    private Button uploadImageBtn;

    @FXML
    private Label userIdField;

    @FXML
    private Label userNameField;


    @FXML
    void givenLoanDetails(ActionEvent event) {
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/GivenLoanPage.fxml", "Details of Users that you have Given Loan", currentStage);


    }


    @FXML
    void takenLoanDetailsBtn(ActionEvent event) {
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/LoanTakenPage.fxml", "Details of Users that you have Taken Loan From ", currentStage);


    }

    @FXML
    private Button informationBtn;

    private static int findCoinNumberByStudentID(String studentID) {
        for (Coin coin : DataBaseManager.getCoinArrayList()) {
            if (coin.getStudentID().equals(studentID)) {
                return coin.getValue();
            }
        }
        return -1;

    }

    private Student findStudentByID(String studentID) {
        // Find the student object with the given ID in the studentArrayList
        for (Student student : DataBaseManager.getStudentArrayList()) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null; // Return null if student with the given ID is not found
    }

    private Review findReviewByID(String studentID) {
        // Find the student object with the given ID in the studentArrayList
        for (Review review : DataBaseManager.getReviewArrayList()) {
            if (review.getStudentID().equals(studentID)) {
                return review;
            }
        }
        return null; // Return null if student with the given ID is not found
    }

    private double calculateAverageReview(Review review) {
        double avg = 0.0;
        double sum = 0.0;
        for (int i = 0; i < review.getReview().size(); i++) {
            sum = sum + review.getReview().get(i);
        }
        avg = sum / review.getReview().size();

        return avg;

    }

    @FXML
    void availableLoanPost(ActionEvent event) {
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/PostedLoanRequestsPage.fxml", "Loan Requests From Others", currentStage);


    }

    @FXML
    void information(ActionEvent event) {


    }

    @FXML
    void logout(ActionEvent event) {

        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/Homepage.fxml", "Sign Up Page", currentStage);


    }

    @FXML
    void requestForLoan(ActionEvent event) {
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/PostFormPage.fxml", "Request For Loan", currentStage);


    }

    @FXML
    void uploadImage(ActionEvent event) {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();

        // Set title for the FileChooser dialog
        fileChooser.setTitle("Choose File");
        // Add filters to accept only PNG, JPG, and JPEG files
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);


        // Show the FileChooser dialog
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected
        if (selectedFile != null) {
            // Display the selected file path in the TextField or process the selected file
            String filePath = selectedFile.getAbsolutePath();
            //imageField.setText(filePath); // Assuming imageField is the TextField for displaying the file path
            Image image = new Image(selectedFile.toURI().toString());
            profilePictureImageField.setImage(image);
            updateProfilePicture(image);
        }

    }

    private void updateProfilePicture(Image image) {
        String studentID = SessionHandler.getSession();

        // Get the file path of the image
        String imagePath = saveImageToFile(image);

        // Prepare the SQL query to update the profile picture file path
        String query = "UPDATE Student SET profile_picture = '" + imagePath + "' WHERE studentID = '" + studentID + "'";

        // Make database connection
        DataBaseManager.makeConnection();

        // Execute the update query with the image file path and student ID as parameters
        boolean rowsAffected = DataBaseManager.executeUpdate(query);

        if (rowsAffected== true) {
            System.out.println("Profile picture updated successfully for student: " + studentID);
        } else {
            System.out.println("No rows were updated. Student ID might be incorrect.");
        }
    }

    // Utility method to save the image to a file and return the file path
    private String saveImageToFile(Image image) {
        String studentID = SessionHandler.getSession();
        String filePath = "profile_pictures/" + studentID + ".png"; // Example: profile_pictures/S001.png

        // Create the directory if it doesn't exist
        File directory = new File("profile_pictures");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Convert and save the image to the file
        File file = new File(filePath);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            System.err.println("Error saving image to file: " + e.getMessage());
            return null;
        }

        return filePath;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // Make database connection and fetch data
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();

        // Get the current session's student ID
        String studentID = SessionHandler.getSession();
        System.out.println(studentID);
        System.out.println(studentID);

        setGivenAndTakenLoanCountLabel();


        // Find the student object with the current session ID
        Student student = findStudentByID(studentID);
        System.out.println(student);
        Account account = findAccountByStudentID(studentID);

        // Initialize UI elements with student data
        if (student != null) {

            userIdField.setText(student.getStudentID() + "");
            userNameField.setText(student.getName() + "");
            numberOfCoinField.setText(findCoinNumberByStudentID(studentID) + "");
            balanceField.setText(account.getBalance() + "");

            String imagePath = student.getProfile_picture();
            System.out.println("Profile picture path: " + imagePath);

            if (imagePath != null && !imagePath.isEmpty()) {
                File file = new File(imagePath);
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    profilePictureImageField.setImage(image);
                } else {
                    System.out.println("Profile picture file does not exist.");
                }
            } else {
                System.out.println("Profile picture path is empty or null.");
            }


            System.out.println(balanceField.getText());
            System.out.println(account.getBalance());
            System.out.println(account.getAccountID());

            Review tempReviewObject = findReviewByID(studentID);

//            if(tempReviewObject==null){
////                averageReviewField.setText("0");
////                totalReviewField.setText("0");
//
//            }
//            else{
//                int totalreview=tempReviewObject.getReview().size();
//
//
////                averageReviewField.setText(calculateAverageReview(tempReviewObject)+"");
////                totalReviewField.setText(totalreview+"");
//            }


        }

    }

    private Account findAccountByStudentID(String studentID) {
        // Find the student object with the given ID in the studentArrayList
        for (Account account : DataBaseManager.getAccountArraylist()) {
            if (account.getStudentID().equals(studentID)) {
                return account;
            }
        }
        return null; // Return null if student with the given ID is not found
    }

    String fetchAnonymousIDByStudentID(String id) {

        System.out.println(id+" : ID");
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
//        DataBaseManager.getStudentArrayList();
        System.out.println(DataBaseManager.getStudentArrayList());

        for (Student student : DataBaseManager.getStudentArrayList()) {
            //if (student.getA_password() != null) {

                if (student.getStudentID() != null&& student.getStudentID().contentEquals(id)) {
                    return student.getA_password();
               // }
            }
        }
        return null;

    }

    public void returnMoney(ActionEvent actionEvent) {
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void redemCoin(ActionEvent actionEvent) {
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

        // Give necessary formatting to look professional
        String content = "Student Name: " + currentStudent.getName() + "\n" +
                "Student ID: " + currentStudent.getStudentID() + "\n" +
                "Coin: " + getCoinByStudentID(SessionHandler.getSession()) + "\n" +
                "Student Email: " + currentStudent.getEmail() + "\n" +
                "__________________________-GENERATED BY FUNDCAVE _______________________________";

        // Show folder chooser dialog
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder to Save PDF");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            // Save the content to a text file
            try {
                File file = new File(selectedDirectory, "redeem_coin_"+SessionHandler.getSession()+".txt");
                FileWriter writer = new FileWriter(file);
                writer.write(content);
                writer.close();
                System.out.println("PDF saved successfully.");
                updateCoinToDatabase( SessionHandler.getSession());


            } catch (IOException e) {
                showAlert("Error","Error creating PDF: " + e.getMessage());
                //System.err.println("Error creating PDF: " + e.getMessage());
            }
        } else {
            showAlert("Error","No directory selected.");

            System.out.println("No directory selected.");
        }
    }

    private void updateCoinToDatabase(String studentID) {
        // Update Coin value to 0 for the given studentID
        String updateQuery = "UPDATE Coin SET value = 0 WHERE studentID = '" + studentID + "'";

        // Make database connection
        DataBaseManager.makeConnection();

        // Execute the update query
        boolean rowsAffected = DataBaseManager.executeUpdate(updateQuery);

        if (rowsAffected) {
            showAlert("Success","Redeem Successfull!");
            System.out.println("Coin value updated successfully for student: " + studentID);
        } else {
            System.out.println("No rows were updated. Student ID might be incorrect.");
        }
    }

    private String getCoinByStudentID(String studentID) {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
       // System.out.println(DataBaseManager.getCoinArrayList());

        for(Coin coin:DataBaseManager.getCoinArrayList())
        {
            System.out.println(coin.getStudentID()+","+studentID);
            if(coin.getStudentID().contentEquals(studentID))
            {

                return coin.getValue()+"";
            }
        }
        return null;
    }


    public void setGivenAndTakenLoanCountLabel() {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        int givenCounter = 0;
        int takenCounter = 0;
        String std_id = fetchAnonymousIDByStudentID(SessionHandler.getSession());

        for (Transaction transaction : DataBaseManager.getTransactionArrayList()) {
            if (transaction.getSenderID() != null) {

                if (transaction.getSenderID().contentEquals(SessionHandler.getSession())) {
                    givenCounter++;
                }
            }

            System.out.println(std_id);
            if (std_id != null && transaction.getReceiverID() != null) {
                if (transaction.getReceiverID().contentEquals(std_id)) {
                    takenCounter++;
                }
            }

        }

        totalGivenField.setText(givenCounter + "");
        totalTakenField.setText(takenCounter + "");

        updateGivenAndTakenLoanToCoinHistory(givenCounter,takenCounter);


    }

    private void updateGivenAndTakenLoanToCoinHistory(int givenCounter, int takenCounter) {

        String studentID=SessionHandler.getSession();
        // Update givenLoan and takenLoan values for the specified studentID
        String query = "UPDATE CoinHistory SET givenLoan = " + givenCounter + ", takenLoan = " + takenCounter +
                " WHERE studentID = '" + studentID + "'";

        // Make database connection
        DataBaseManager.makeConnection();

        // Execute the update query
        boolean rowsAffected = DataBaseManager.executeUpdate(query);

        if (rowsAffected ==true) {
            System.out.println("Coin history updated successfully for student: " + studentID);
        } else {
            System.out.println("No rows were updated. Student ID might be incorrect.");
        }
    }

    public void cashInBkash(ActionEvent actionEvent) {
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/BkashPaymentGatewayPage.fxml", "Bkash Gateway", currentStage);

    }
}
