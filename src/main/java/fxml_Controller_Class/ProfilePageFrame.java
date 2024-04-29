package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import com.mysql.cj.protocol.a.TracingPacketReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mainClass.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class ProfilePageFrame implements Initializable {
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();
    @FXML
    private Button availableLoanPostsBtn;

    @FXML
    private Label balanceField;

    @FXML
    private Button givenLoanDetailsBtn;

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
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // Make database connection and fetch data
        DataBaseManager.makeConnection("root", "root");
        DataBaseManager.fetchDataFromDatabase();

        // Get the current session's student ID
        String studentID = SessionHandler.getSession();
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
        DataBaseManager.makeConnection("root", "root");
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

    public void redemCoin(ActionEvent actionEvent) {
    }


    public void setGivenAndTakenLoanCountLabel() {
        DataBaseManager.makeConnection("root", "root");
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


    }
}
