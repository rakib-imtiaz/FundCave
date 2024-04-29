package Chat;

import Chat.client.ChatClient;
import Chat.server.ChatServer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Start the chat server
        new Thread(() -> {
            ChatServer server = new ChatServer();
            server.startChatServer();
        }).start();

        // Start the chat client
        ChatClient client = new ChatClient();
        client.startChatClient();
    }

    public void startChatWithUserID(String userID) {
        // You can implement the logic here to start a chat with the specified user ID
        // For example, you might open a new chat window or send a message to the user
    }

    public static void main(String[] args) {
        launch(args);
    }
}
