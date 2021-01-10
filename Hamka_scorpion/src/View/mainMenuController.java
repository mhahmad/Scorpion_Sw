package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.SysData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainMenuController extends Application implements Initializable{

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
    @FXML
    private ImageView background;

    @FXML
    private Pane headerPane;

    @FXML
    private Pane middlePane;
    
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
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
		 FXMLLoader loader = new FXMLLoader(optionScreenController.class.getResource("optionScreen.fxml"));
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
		Parent toLoad = FXMLLoader.load(getClass().getResource("TutorialScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);

    }
    
    @FXML
    void leaderboardBtnClicked(ActionEvent event) throws IOException {
		Stage st = (Stage)this.leaderboardBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("LeaderboardScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);

    }
    
    @FXML
    void loadgameBtnClicked(ActionEvent event) throws IOException {
		Stage st = (Stage)this.loadGameBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("LoadGameScreen.fxml"));
		Scene scene = new Scene(toLoad);
		st.setScene(scene);
    }
    
    @FXML
    void startBtnClicked(ActionEvent event) throws IOException {
		View.enterNameController.display((Stage)this.optionBtn.getScene().getWindow());
    }
    
    @FXML
    void BtnEntered(MouseEvent event) throws IOException {
    	Button but = (Button)event.getSource();
    	if(!Controller.SysData.darkTheme) 
    		but.setStyle("-fx-background-color : #FDB360 ; -fx-border-radius:15; -fx-border-color: #000000; -fx-background-radius: 18; -fx-border-width:3; -fx-background-insets:0;");
    	else
    		but.setStyle("-fx-background-color : #1FD862 ; -fx-border-radius:15; -fx-border-color: #000000; -fx-background-radius: 18; -fx-border-width:3; -fx-background-insets:0;");

    }
    
    @FXML
    void BtnExited(MouseEvent event) throws IOException {
    	Button but = (Button)event.getSource();
    	if(!Controller.SysData.darkTheme) 
    		but.setStyle("-fx-background-color : #F1B237 ; -fx-border-radius:15; -fx-border-color: #000000; -fx-background-radius: 18; -fx-border-width:3; -fx-background-insets:0;");
    	else
    		but.setStyle("-fx-background-color : #6AF99E ; -fx-border-radius:15; -fx-border-color: #000000; -fx-background-radius: 18; -fx-border-width:3; -fx-background-insets:0;");


    }
    public void darkTheme(boolean isOn) {
    	if(isOn) {
    		background.setImage(new Image("Resources/darkThemeBackground.png"));
    		headerPane.setStyle("-fx-background-color : #201C1C");
    		middlePane.setStyle("-fx-background-color :  #272626 ; -fx-background-radius: 33; -fx-border-radius: 30; -fx-border-color: #444444; -fx-border-width: 8;");
    		loadGameBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #6AF99E; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		optionBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #6AF99E; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		leaderboardBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #6AF99E; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		tutorialBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #6AF99E; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		exitBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #D33535; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		startBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #6AF99E; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    	}else {
    		background.setImage(new Image("Resources/mainMenuBackground.png"));
    		headerPane.setStyle("-fx-background-color :  #630000;");
    		middlePane.setStyle("-fx-background-color :  #630000 ; -fx-background-radius: 33; -fx-border-radius: 30; -fx-border-color: #444444; -fx-border-width: 8;");
    		startBtn.setStyle("-fx-border-radius : 15; -fx-background-color :  #F1B237; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		loadGameBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #F1B237; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		optionBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #F1B237; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		leaderboardBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #F1B237; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		tutorialBtn.setStyle("-fx-border-radius : 15; -fx-background-color : #F1B237; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");
    		exitBtn.setStyle("-fx-border-radius : 15; -fx-background-color :  #FF7777; -fx-border-color: #000000 ; -fx-background-radius: 18; -fx-border-width: 3; -fx-background-insets: 0;");

    	}
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SysData.getInstance();
		// TODO Auto-generated method stub
		darkTheme(SysData.darkTheme);
	}
}
