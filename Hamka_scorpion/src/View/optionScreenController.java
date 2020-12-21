package View;

import java.io.IOException;

import Controller.SysData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class optionScreenController extends Application {

    @FXML
    private Button mngQuesBtn;

    @FXML
    private ToggleButton toggleThemeBtn;

    @FXML
    private Button backBtn;
    
    @FXML
    public ToggleButton toggleMusicBtn;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("optionScreen.fxml"));
		Scene scene = new Scene(root,1200,960);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka - options");	
		stage.setResizable(false);
	}
	
	public static void main(String[] arg) {
		launch(arg);
	}

	   @FXML
	   public void backBtnClicked(ActionEvent event) throws IOException {
		   Stage stage = (Stage)this.backBtn.getScene().getWindow();
		   Parent toLoad = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		   Scene scene = new Scene(toLoad);
		   stage.setScene(scene);
	    }
	   
	   
	   @FXML
	   public void toggleMusicClicked(ActionEvent event) {
		   if(this.toggleMusicBtn.getText().equals("ON")) {
			   this.toggleMusicBtn.setText("OFF");
			   this.toggleMusicBtn.setStyle("-fx-text-fill: RED;");
			   SysData.getInstance().soundtrackOn(false);
		   }else {
			   this.toggleMusicBtn.setText("ON");
			   this.toggleMusicBtn.setStyle("-fx-text-fill: #19d300;");
			   SysData.getInstance().soundtrackOn(true);
		   }
	    }

	    @FXML
	    void toggleThemeClicked(ActionEvent event) {
	    	 if(this.toggleThemeBtn.getText().equals("ON")) {
				   this.toggleThemeBtn.setText("OFF");
				   this.toggleThemeBtn.setStyle("-fx-text-fill: RED;");
				   
			   }else {
				   this.toggleThemeBtn.setText("ON");
				   this.toggleThemeBtn.setStyle("-fx-text-fill: #19d300;");
			   }
	    }
	    
	    @FXML
		   public void manageQuestionClicked(ActionEvent event) throws IOException {
			   Stage stage = (Stage)this.mngQuesBtn.getScene().getWindow();
			   Parent toLoad = FXMLLoader.load(getClass().getResource("ManageQuestionScreen.fxml"));
			   Scene scene = new Scene(toLoad);
			   stage.setScene(scene);
		    }
}
