package fxml_Controller_Class;

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
import server.ServerLauncher;

import java.net.URL;
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

        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page",currentStage);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communicate.setCellFactory(param -> new ButtonCell((Stage) tableView.getScene().getWindow()));

        a_id_column.setCellValueFactory(new PropertyValueFactory<>("userID"));
        postColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Set up the Communicate column with a button to start a chat
        communicate.setCellFactory(param -> new ButtonCell((Stage) tableView.getScene().getWindow()));
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();


        // Populate the table view with sample data (you can replace this with actual data)
        tableView.getItems().addAll(DataBaseManager.getPostArrayList());


    }

    // Inner class for the Communicate column button cell
    private class ButtonCell extends TableCell<Post, String> {
        final Button communicateButton = new Button("Start Chat");
        final Button giveLoanButton = new Button("Give Loan");

        ButtonCell(Stage window) {
            communicateButton.setOnAction(event -> {
                String userID = getTableView().getItems().get(getIndex()).getUserID();
                openChatWindow(userID);
                System.out.println("Start Chat with user ID: " + userID);
//                server.ServerLauncher.main(new String[]{});

                Stage currentStage = (Stage) communicateButton.getScene().getWindow();

                sceneBuilder.loadNewFrame("../view/ServerForm.fxml", "Loan",currentStage);


//                ServerLauncher serverLauncher = new ServerLauncher();
//                serverLauncher.launch(new String[]{});



            });

            giveLoanButton.setOnAction(event -> {
                String anonymousUserID = getTableView().getItems().get(getIndex()).getUserID(); // Assuming userID is actually the anonymousUserID
                giveLoan(anonymousUserID);
                System.out.println("Give Loan to anonymous user ID: " + anonymousUserID);
            });
        }

        private void openChatWindow(String userID) {


         }

        private void giveLoan(String anonymousUserID) {
            SessionHandler.setCurrentLoanRecieversID(anonymousUserID);
             Stage currentStage = (Stage) giveLoanButton.getScene().getWindow();

               sceneBuilder.loadNewFrame("/PaymentWindow.fxml", "Payemnt Window",currentStage);
            System.out.println("Give Loan to anonymous user ID: " + anonymousUserID);
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

}
