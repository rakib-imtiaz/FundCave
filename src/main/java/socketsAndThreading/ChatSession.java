package socketsAndThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatSession {
	private static final String SERVER_ADDRESS = "localhost";
	private static final int SERVER_PORT = 8000;
	private ObservableList<String> messages;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public ChatSession() {
		messages = FXCollections.observableArrayList();
		try {
			socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);

			new Thread(() -> {
				try {
					String message;
					while ((message = input.readLine()) != null) {
						receiveMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void sendMessage(String message) {
		output.println(message);
	}

	public void receiveMessage(String message) {
		messages.add("Other: " + message);
	}

	public ObservableList<String> getMessages() {
		return messages;
	}
}