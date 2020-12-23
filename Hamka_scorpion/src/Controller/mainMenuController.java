package Controller;

import java.io.IOException;

import Controller.SysData;
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
		Parent root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka - main menu");
		stage.setResizable(false);
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
		 FXMLLoader loader = new FXMLLoader(optionScreenController.class.getResource("/View/optionScreen.fxml"));
		Parent toLoad = loader.load();
		Scene scene = new Scene(toLoad);
		st.setScene(scene);
		 if(SysData.count == 1) {
			optionScreenController con = loader.getController();
			con.toggleMusicBtn.setText("ON");
			con.toggleMusicBtn.setStyle("-fx-text-fill: #19d300;");

		 }

    }
    
    @FXML
    void tutorialsBtnClicked(ActionEvent event) throws IOException {
		Stage st = (Stage)this.tutorialBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("/View/TutorialScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);

    }
    
    @FXML
    void leaderboardBtnClicked(ActionEvent event) throws IOException {
		Stage st = (Stage)this.leaderboardBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("/View/LeaderboardScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);

    }
    
    @FXML
    void loadgameBtnClicked(ActionEvent event) throws IOException {
		Stage st = (Stage)this.loadGameBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("/View/LoadGameScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);
    }
    
    @FXML
    void startBtnClicked(ActionEvent event) throws IOException {
		Controller.enterNameController.display((Stage)this.optionBtn.getScene().getWindow());
    }
}
