package application;

import Chat.ChatApplication;
import Chat.client.ChatClient;
import Chat.server.ChatServer;
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

		Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
		//SessionHandler.setSession("S001");

		Scene scene = new Scene(root);

		primaryStage.setTitle("HomePage");
		primaryStage.setScene(scene);
		primaryStage.show();
//

//		ChatServer server = new ChatServer();
//		server.startChatServer();
//
//		ChatClient chatClient = new ChatClient();
//		chatClient.startChatClient();

//		ChatApplication app = new ChatApplication();
//		app.start(primaryStage);
	}
}
