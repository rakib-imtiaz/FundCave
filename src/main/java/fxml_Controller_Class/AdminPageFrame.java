package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.TempStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClass.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPageFrame {

    public Button goBackBtn;
    public TableColumn anonymousIDColumn;
    @FXML
    private TableView<TempStudent> tableView;

    @FXML
    private TableColumn<TempStudent, String> studentIDColumn;

    @FXML
    private TableColumn<TempStudent, String> nameColumn;

    @FXML
    private TableColumn<TempStudent, String> emailColumn;

    @FXML
    private TableColumn<TempStudent, String> passwordColumn;

    @FXML
    private TableColumn<TempStudent, String> addressColumn;

    @FXML
    private TableColumn<TempStudent, Integer> coinColumn;

    @FXML
    private TableColumn<TempStudent, Double> balanceColumn;
    
    
     public void initialize() {
        // Populate data
         TempStudent.populateData();
         ObservableList<TempStudent> tempStudentList = FXCollections.observableArrayList();
         tableView.setItems(tempStudentList);


         // Set up columns
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        coinColumn.setCellValueFactory(new PropertyValueFactory<>("coin"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
         anonymousIDColumn.setCellValueFactory(new PropertyValueFactory<>("anonymousID"));

        tempStudentList.addAll(TempStudent.populatedDataForAdmin);


    }


    public void goBack(ActionEvent actionEvent) {
         final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

        Stage currentStage = (Stage) goBackBtn.getScene().getWindow();
        sceneBuilder.loadNewFrame("/Homepage.fxml", "Home Page", currentStage);
    }
}

