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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class optionScreenController extends Application  implements Initializable{

    @FXML
    private Button mngQuesBtn;

    @FXML
    private ToggleButton toggleThemeBtn;

    @FXML
    private Button backBtn;
    
    @FXML
    public ToggleButton toggleMusicBtn;
    @FXML
    private ImageView backgroundImage;

    @FXML
    private Pane headerPane;

    @FXML
    private Pane middlePane;
    @FXML
    private Label MusicLabel;

    @FXML
    private Label labelDarkTheme;

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
				   SysData.getInstance().darkTheme(false);
				   darkTheme(false);

			   }else {
				   this.toggleThemeBtn.setText("ON");
				   this.toggleThemeBtn.setStyle("-fx-text-fill: #19d300;");
				   SysData.getInstance().darkTheme(true);
				   darkTheme(true);
			   }
	    }
	    
	    @FXML
		   public void manageQuestionClicked(ActionEvent event) throws IOException {
			   Stage stage = (Stage)this.mngQuesBtn.getScene().getWindow();
			   Parent toLoad = FXMLLoader.load(getClass().getResource("ManageQuestionScreen.fxml"));
			   Scene scene = new Scene(toLoad);
			   stage.setScene(scene);
		    }
	    
	    
	    public void darkTheme(boolean isOn) {
	    	
	    	if(isOn) {
	    		backgroundImage.setImage(new Image("Resources/darkThemeBackground.png"));
	    		headerPane.setStyle("-fx-background-color : #201C1C");
	    		middlePane.setStyle("-fx-background-color :  #272626 ; -fx-background-radius: 33; -fx-border-radius: 30; -fx-border-color: #444444; -fx-border-width: 8;");
	    		mngQuesBtn.setStyle("-fx-border-radius : 15; -fx-background-color :   #6AF99E; -fx-border-color: #000000 ; -fx-background-radius: 17; -fx-border-width: 2; -fx-background-insets: 0;");
	    		labelDarkTheme.setTextFill(Color.AQUAMARINE);
	    		MusicLabel.setTextFill(Color.AQUAMARINE);
	    	}else {
	    		backgroundImage.setImage(new Image("Resources/mainMenuBackground.png"));
	    		headerPane.setStyle("-fx-background-color :  #630000;");
	    		middlePane.setStyle("-fx-background-color :  #630000 ; -fx-background-radius: 33; -fx-border-radius: 30; -fx-border-color: #444444; -fx-border-width: 8;");
	    		mngQuesBtn.setStyle("-fx-border-radius : 15; -fx-background-color :   #F1B237; -fx-border-color: #000000 ; -fx-background-radius: 17; -fx-border-width: 2; -fx-background-insets: 0;");
	    		MusicLabel.setTextFill(Color.ORANGE);
	    		labelDarkTheme.setTextFill(Color.ORANGE);
	    	}
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
	    		but.setStyle("-fx-background-color :  #F1B237 ; -fx-border-radius:15; -fx-border-color: #000000; -fx-background-radius: 18; -fx-border-width:3; -fx-background-insets:0;");
	    	else
	    		but.setStyle("-fx-background-color : #6AF99E ; -fx-border-radius:15; -fx-border-color: #000000; -fx-background-radius: 18; -fx-border-width:3; -fx-background-insets:0;");


	    }
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			darkTheme(SysData.darkTheme);
			if(SysData.darkTheme) {
				this.toggleThemeBtn.setText("ON");
				this.toggleThemeBtn.setStyle("-fx-text-fill: #19d300;");
			}else {
				 this.toggleThemeBtn.setText("OFF");
				 this.toggleThemeBtn.setStyle("-fx-text-fill: RED;");
			}
		}
}
