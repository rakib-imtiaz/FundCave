package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {


		DataBaseManager.makeConnection();
		DataBaseManager.fetchDataFromDatabase();
		SessionHandler.setSession("S001");
		//System.out.println(SessionHandler.getSession());
		//System.out.println(SessionHandler.getStudentID());

//		Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("/profilePage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("HomePage");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SessionHandler.setSession("S001");


	}
}
