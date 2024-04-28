package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneBuildingHelper {

	public void loadNewFrame(String fxmlFileName, String title, Stage currentStage) {
		try {
			// Load the FXML file
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
			Parent root = loader.load();

			// Create a new stage (window) for the new scene
			Stage newStage = new Stage();
			newStage.setTitle(title);

			// Set the scene for the new stage
			Scene scene = new Scene(root);
			newStage.setScene(scene);

			// Close the current stage
			currentStage.close();

			// Show the new stage (window)
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
