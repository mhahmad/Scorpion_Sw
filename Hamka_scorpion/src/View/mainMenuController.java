package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class mainMenuController extends Application {

    @FXML
    private Button startBtn;

    @FXML
    private Button loadGameBtn;

    @FXML
    private Button leaderboardBtn;

    @FXML
    private Button tutorialBtn;

    @FXML
    private Button optionBtn;

    @FXML
    private Button exitBtn;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka - main menu");
	}
	
	
	
	public static void main(String[] arg) {
		launch(arg);
	}

	
	@FXML
	void exitGame(ActionEvent event) {
		Stage st = (Stage)this.exitBtn.getScene().getWindow();
		st.close();
	}
	

    @FXML
    void optionBtnClicked(ActionEvent event) throws IOException {
		Stage st = (Stage)this.optionBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("optionScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);

    }
}
