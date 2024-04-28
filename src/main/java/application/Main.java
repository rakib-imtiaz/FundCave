package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {


		DataBaseManager.makeConnection("root", "root");
		DataBaseManager.fetchDataFromDatabase();

		Parent root = FXMLLoader.load(getClass().getResource("/profilePage.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("HomePage");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
