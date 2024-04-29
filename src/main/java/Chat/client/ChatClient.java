package Chat.client;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;

public class ChatClient {

    private DatagramSocket socket;
    private InetAddress address;
    private String identifier = "Jeff";
    private int SERVER_PORT = 8000;
    private TextArea messageArea = new TextArea();
    private TextField inputBox = new TextField();

    public ChatClient() throws IOException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public void startChatClient() {
        messageArea.setMaxWidth(500);
        messageArea.setEditable(false);

        inputBox.setMaxWidth(500);
        inputBox.setOnAction(event -> {
            String temp = identifier + ";" + inputBox.getText(); // message to send
            messageArea.appendText(inputBox.getText() + "\n"); // update messages on screen
            byte[] msg = temp.getBytes(); // convert to bytes
            inputBox.clear(); // remove text from input box

            DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
            try {
                socket.send(send);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(new VBox(35, messageArea, inputBox), 550, 300);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void close() {
        socket.close();
    }
}
