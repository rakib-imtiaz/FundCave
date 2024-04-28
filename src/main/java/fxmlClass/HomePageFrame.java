package fxmlClass;

import application.SceneBuildingHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageFrame {
    private final SceneBuildingHelper sceneBuilder = new SceneBuildingHelper();

    @FXML
    private Button AboutBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        Stage currentStage = (Stage) loginBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/loginPage.fxml", "Login Page",currentStage);

    }

    @FXML
    void signup(ActionEvent event) {

        Stage currentStage = (Stage) signupBtn.getScene().getWindow();

        sceneBuilder.loadNewFrame("/signupPage.fxml", "Sign Up Page",currentStage);

    }

}
