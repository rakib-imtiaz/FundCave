package fxml_Controller_Class;

import application.DataBaseManager;
import application.SceneBuildingHelper;
import application.SessionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClass.Student;
import mainClass.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LoanTakenFrame implements Initializable {

    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

    @FXML
    private Button goBackBtn;
    @FXML
    void goBack(ActionEvent event) {
        Stage currentStage = (Stage) goBackBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/profilePage.fxml", "Profile Page",currentStage);


    }
    @FXML
    public TableColumn<Transaction, String> idColumn;
    @FXML
    public TableColumn<Transaction, String> amountColumn;
    @FXML
    public TableColumn<Transaction, String> startDateColumn;
    @FXML
    public TableColumn<Transaction, String> endDateColumn;
    @FXML
    public TableColumn<Transaction, String> downloadPdfColumn;
    @FXML
    public TableView<Transaction> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("loadSendingDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanExpireDate"));

        // Create a button cell factory for the "Download PDF" column
        downloadPdfColumn.setCellFactory(param -> new TableCell<Transaction, String>() {
            final Button downloadPdfButton = new Button("Download PDF");

            {
                // Set action for the button
                downloadPdfButton.setOnAction(event -> {
                    // Get the selected transaction
                    Transaction transaction = getTableView().getItems().get(getIndex());
                    // Write transaction details to a text file
                    writeTransactionDetailsToFile(transaction);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(downloadPdfButton);
                }
            }
        });

        // Set selection mode to single row
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Add listener to handle row selection
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Print the selected row's content to the terminal
                System.out.println("Selected transaction: " + newSelection);
            }
        });

        // Initialize transactions list
        ObservableList<Transaction> transactionsList = FXCollections.observableArrayList();
        tableView.setItems(transactionsList);

        // Fetch transactions from the database
      //  DataBaseManager.getTransactionArrayList();

// Filter transactions based on the current student ID
//        ---------------------------------------------------------------------------------------------
        String currentStudentID = SessionHandler.getSession();
        ArrayList<Transaction> filteredTransactionList = new ArrayList<>();
        ArrayList<Transaction> transactionListModifiedAllAnonyUserIDToStudentID =new ArrayList<>();
        for (Transaction transaction:DataBaseManager.getTransactionArrayList())
        {
            transaction.setReceiverID(fetchStudentIDByAnonymousID(transaction.getReceiverID()));
            transactionListModifiedAllAnonyUserIDToStudentID.add(transaction);
        }
        System.out.println("MODIFED");
        System.out.println(transactionListModifiedAllAnonyUserIDToStudentID);



        for (Transaction transaction : DataBaseManager.getTransactionArrayList()) {

            String convertedStudentID=fetchStudentIDByAnonymousID(transaction.getReceiverID());

            if (convertedStudentID.equals(currentStudentID)) {
                filteredTransactionList.add(transaction);
            }
        }

        transactionsList.addAll(filteredTransactionList);
    }


//    ---------------------------------------------------------------------------------------
    String fetchStudentIDByAnonymousID(String id)
    {
        DataBaseManager.makeConnection("root","root");
        DataBaseManager.fetchDataFromDatabase();
       // DataBaseManager.getStudentArrayList();

        for(Student student:DataBaseManager.getStudentArrayList())
        {
            if(student.getA_password().contentEquals(id))
            {
                return student.getStudentID();
            }
        }

        return null;

    }

    private void writeTransactionDetailsToFile(Transaction transaction) {
        try {
            // Open a FileWriter to write to a text file (replace "transaction_details.txt" with your desired file path)
            FileWriter writer = new FileWriter("transaction_details.txt", true);
            // Write transaction details to the file
            writer.write("Transaction ID: " + transaction.getTransactionID() + "\n");
            writer.write("Sender ID: " + transaction.getSenderID() + "\n");
            writer.write("Receiver ID: " + transaction.getReceiverID() + "\n");
            writer.write("Amount: " + transaction.getAmount() + "\n");
            writer.write("Load Sending Date: " + transaction.getLoadSendingDate() + "\n");
            writer.write("Loan Expire Date: " + transaction.getLoanExpireDate() + "\n");
            // Write a newline to separate transactions
            writer.write("\n");
            // Close the FileWriter
            writer.close();
            System.out.println("Transaction details saved to transaction_details.txt");
        } catch (IOException e) {
            System.err.println("Error writing transaction details to file: " + e.getMessage());
        }
    }
}
