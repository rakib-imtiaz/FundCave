package fxml_Controller_Class;

import Chat.ChatApplication;
import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mainClass.Post;
import mainClass.Student;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PostedLoanRequestsFrame implements Initializable {
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();


    @FXML
    public TableColumn<Post, String> a_id_column;

    @FXML
    public TableColumn<Post, String> communicate;

    @FXML
    public TableColumn<Post, String> postColumn;

    @FXML
    public TableView<Post> tableView;

    @FXML
    public TableColumn<Post, Date> timeColumn;
    @FXML
    private Button goBackBtn;

    @FXML
    void goBack(ActionEvent event) {
        Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page", currentStage);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        a_id_column.setCellValueFactory(new PropertyValueFactory<>("userID"));
        postColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Set up the Communicate column with a button to start a chat
        communicate.setCellFactory(param -> new ButtonCell());
        DataBaseManager.makeConnection("root", "root");
        DataBaseManager.fetchDataFromDatabase();


        // Populate the table view with sample data (you can replace this with actual data)
        ArrayList<Post> filteredPost=new ArrayList<>();

        for(Post post:DataBaseManager.getPostArrayList())
        {

            // filtering the post,so that the user logged in cant respond to his own loan request
            if(!(post.getUserID().contentEquals(fetchAnonymousIDByStudentID(SessionHandler.getSession()))))
            {
                filteredPost.add(post);
            }
        }

        tableView.getItems().addAll(filteredPost);


    }

    // Inner class for the Communicate column button cell
    private class ButtonCell extends TableCell<Post, String> {
        final Button communicateButton = new Button("Start Chat");
        final Button giveLoanButton = new Button("Give Loan");

        ButtonCell() {
            communicateButton.setOnAction(event -> {
                String userID = getTableView().getItems().get(getIndex()).getUserID();
                openChatWindow(userID);
                System.out.println("Start Chat with user ID: " + userID);
            });

            giveLoanButton.setOnAction(event -> {
                String anonymousUserID = getTableView().getItems().get(getIndex()).getUserID(); // Assuming userID is actually the anonymousUserID
             //   giveLoan(anonymousUserID);
                SessionHandler.setCurrentLoanRecieversID(anonymousUserID);
                System.out.println("SET RECIEVER ID: "+anonymousUserID);
                System.out.println("SET RECIEVER ID From session: "+SessionHandler.getCurrentLoanRecieversID());

                Stage currentStage = (Stage) giveLoanButton.getScene().getWindow();

                sceneBuilder.loadNewFrame("/PaymentWindow.fxml", "Payemnt Window",currentStage);

                System.out.println("Give Loan to anonymous user ID: " + anonymousUserID);
            });
        }

        private void openChatWindow(String userID) {
            // Create and show the chat window
            ChatApplication chatApp = new ChatApplication();
            chatApp.startChatWithUserID(userID); // Assuming you have a method in ChatApplication to start a chat with a specific user ID
        }

//        private void giveLoan(String anonymousUserID) {
//
//
//                SessionHandler.setCurrentLoanRecieversID(anonymousUserID);
//
//                Stage currentStage = (Stage) goBackBtn.getScene().getWindow();
//
//                sceneBuilder.loadNewFrame("/PaymentWindow.fxml", "Payemnt Window",currentStage);
//
//
//
//        }


        private void openPaymentGateway() {
            // Call the function to open PaymentGateway.fxml
            // Implement this function to open the PaymentGateway.fxml frame
            // For now, let's just print a message
            System.out.println("Opening Payment Gateway...");
            // You can use SceneBuildingHelper to load the PaymentGateway.fxml frame
            // sceneBuilder.loadNewFrame("/PaymentGateway.fxml", "Payment Gateway", primaryStage);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                HBox buttons = new HBox(communicateButton, giveLoanButton);
                setGraphic(buttons);
            }
        }
    }

    String fetchAnonymousIDByStudentID(String studentID)
    {
        DataBaseManager.makeConnection("root","root");
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

}