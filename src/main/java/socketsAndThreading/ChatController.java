package socketsAndThreading;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController {
	private ChatSession chatSession;

	@FXML
	private ListView<String> messageList;
	@FXML
	private TextField messageInput;

	public void initialize() {
		chatSession = new ChatSession();
		messageList.setItems(chatSession.getMessages());
	}

	@FXML
	private void sendMessage() {
		String message = messageInput.getText();
		if (!message.isEmpty()) {
			chatSession.sendMessage("You: " + message);
			messageInput.clear();
		}
	}
}