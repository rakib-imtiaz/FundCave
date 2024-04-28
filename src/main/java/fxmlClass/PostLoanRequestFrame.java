package fxmlClass;

import application.SceneBuildingHelper;
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

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class PostLoanRequestFrame implements Initializable {
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
        a_id_column.setCellValueFactory(new PropertyValueFactory<>("userID"));
        postColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Set up the Communicate column with a button to start a chat
        communicate.setCellFactory(param -> new ButtonCell());

        // Populate the table view with sample data (you can replace this with actual data)
        tableView.getItems().addAll(
                new Post("1", "User1", "Sample Post 1", new Date()),
                new Post("2", "User2", "Sample Post 2", new Date())
        );
    }

    // Inner class for the Communicate column button cell
    private class ButtonCell extends TableCell<Post, String> {
        final Button communicateButton = new Button("Start Chat");

        ButtonCell() {
            communicateButton.setOnAction(event -> {
                // Get the current post's user ID
                String userID = getTableView().getItems().get(getIndex()).getUserID();
                // Pass the user ID to the ChatWindow frame (for now, just print it)
                System.out.println("Start Chat with user ID: " + userID);
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(communicateButton);
            }
        }
    }
}
