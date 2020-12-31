package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class tutorialScreen extends Application implements Initializable{

	@FXML
    private ImageView background;

    @FXML
    private Pane headerPane;

    @FXML
    private Pane middlePane;
	@FXML
    private ImageView queenConvertBtn;

    @FXML
    private ImageView killStreakBtn;

    @FXML
    private ImageView queenCrossBtn;

    @FXML
    private ImageView coloredTilesBtn;

    @FXML
    private Button backBtn;

		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub
			Parent root = FXMLLoader.load(getClass().getResource("/View/TutorialScreen.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Hamka - tutorials");
			stage.setResizable(false);
		}
		
		
		
		public static void main(String[] arg) {
			launch(arg);
		}
		
		  @FXML
		    void ColoredTileBtnClicked(MouseEvent event) {

				Stage window = (Stage)this.backBtn.getScene().getWindow();
			
					 FXMLLoader loader = new FXMLLoader(TiletutorialController.class.getResource("/View/tutorialColoredTiles.fxml"));
					// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
	              Parent root;
	            try {
	                root = loader.load();
	              Scene scene = new Scene(root);
	              window.setTitle("Hamka");
	              window.setScene(scene);
	              TiletutorialController con = loader.getController();
	       
	              con.start(window);
	              window.show();
	              window.centerOnScreen();
	          	//((Stage)this.NextTut.getScene().getWindow()).close();
	          	} catch ( Exception e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	            }
		    }

		    @FXML
		    void killStreakBtnClicked(MouseEvent event) {

				Stage window = (Stage)this.coloredTilesBtn.getScene().getWindow();
			//
					 FXMLLoader loader = new FXMLLoader(KillSTutrialController.class.getResource("/View/KillStreakTutrial.fxml"));
					// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
	                 Parent root;
	               try {
	                   root = loader.load();
	                 Scene scene = new Scene(root);
	                 window.setTitle("Hamka");
	                 window.setScene(scene);
	                 KillSTutrialController con = loader.getController();
	          
	                 con.start(window);
	                 window.show();
	                 window.centerOnScreen();
	             	//((Stage)this.NextTut.getScene().getWindow()).close();
	             	} catch ( Exception e1) {
	                   // TODO Auto-generated catch block
	                   e1.printStackTrace();
	               }
					 
		    }
		  
		  
	    @FXML
	    void queenConvertBtnClicked() throws Exception{
	    	System.out.println("SDSD");
	    	Stage window = (Stage)this.coloredTilesBtn.getScene().getWindow();
			//
					 FXMLLoader loader = new FXMLLoader(tutorial1Controller.class.getResource("/View/tutorial1.fxml"));
					// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
	                 Parent root;
	               try {
	                   root = loader.load();
	                 Scene scene = new Scene(root);
	                 window.setTitle("Hamka");
	                 window.setScene(scene);
	                 tutorial1Controller con = loader.getController();
	          
	                 con.start(window);
	                 window.show();
	                 window.centerOnScreen();
	             	//((Stage)this.NextTut.getScene().getWindow()).close();
	             	} catch ( Exception e1) {
	                   // TODO Auto-generated catch block
	                   e1.printStackTrace();
	               }
	    	
	    }
	    
	    @FXML
	    void queenConvertBtnMouseEntered() throws Exception{
	    	queenConvertBtn.setFitHeight(270);
	    	queenConvertBtn.setFitWidth(260);
	    }
	    
	    @FXML
	    void queenConvertBtnMouseExit() throws Exception{
	    	queenConvertBtn.setFitHeight(262);
	    	queenConvertBtn.setFitWidth(252);	  
	    }
	    
	    
	    @FXML
	    void queenCrossBtnMouseEntered() throws Exception{
	    	queenCrossBtn.setFitHeight(270);
	    	queenCrossBtn.setFitWidth(260);
	    }
	    
	    @FXML
	    void queenCrossBtnMouseExit() throws Exception{
	    	queenCrossBtn.setFitHeight(262);
	    	queenCrossBtn.setFitWidth(252);	  
	    }
	    
	    @FXML
	    void coloredTilesBtnMouseEntered() throws Exception{
	    	coloredTilesBtn.setFitHeight(270);
	    	coloredTilesBtn.setFitWidth(260);
	    }
	    
	    @FXML
	    void coloredTilesBtnMouseExit() throws Exception{
	    	coloredTilesBtn.setFitHeight(262);
	    	coloredTilesBtn.setFitWidth(252);	  
	    }
	    
	    @FXML
	    void killStreakBtnMouseEntered() throws Exception{
	    	killStreakBtn.setFitHeight(270);
	    	killStreakBtn.setFitWidth(260);
	    }
	    
	    @FXML
	    void killStreakBtnMouseExit() throws Exception{
	    	killStreakBtn.setFitHeight(262);
	    	killStreakBtn.setFitWidth(252);	  
	    }
	    
	    @FXML
		   public void backBtnClicked(ActionEvent event) throws IOException {
			   Stage stage = (Stage)this.backBtn.getScene().getWindow();
			   Parent toLoad = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
			   Scene scene = new Scene(toLoad);
			   stage.setScene(scene);
		    }


	    
	    public void darkTheme(boolean isOn) {
	    	if(isOn) {
	    		background.setImage(new Image("Resources/darkThemeBackground.png"));
	    		headerPane.setStyle("-fx-background-color : #201C1C");
	    		middlePane.setStyle("-fx-background-color :  #272626 ; -fx-background-radius: 33; -fx-border-radius: 30; -fx-border-color: #444444; -fx-border-width: 8;");
	    	
	    	}else {
	    		background.setImage(new Image("Resources/mainMenuBackground.png"));
	    		headerPane.setStyle("-fx-background-color :  #630000;");
	    		middlePane.setStyle("-fx-background-color :  #630000 ; -fx-background-radius: 33; -fx-border-radius: 30; -fx-border-color: #444444; -fx-border-width: 8;");
	    	
	    	}
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			darkTheme(SysData.darkTheme);

		}
}
