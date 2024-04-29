package Chat.client;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientThread extends Thread {

    private final DatagramSocket socket;
    private final byte[] incoming = new byte[1024]; // Increased buffer size for larger messages
    private final TextArea textArea;

    public ClientThread(DatagramSocket socket, TextArea textArea) {
        this.socket = socket;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        System.out.println("Starting client thread");
        while (!socket.isClosed()) { // Check if the socket is closed before receiving messages
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                // Socket was closed or encountered an error, exit the loop
                break;
            }
            String message = new String(packet.getData(), 0, packet.getLength()) + "\n";
            // Update UI on the JavaFX application thread using Platform.runLater()
            javafx.application.Platform.runLater(() -> appendToTextArea(message));
        }
        System.out.println("Client thread stopped");
    }

    private void appendToTextArea(String message) {
        String current = textArea.getText();
        textArea.setText(current + message);
    }
}
