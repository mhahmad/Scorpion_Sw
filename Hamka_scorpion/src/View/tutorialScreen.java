package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class tutorialScreen extends Application{

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
			Parent root = FXMLLoader.load(getClass().getResource("TutorialScreen.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Hamka - tutorials");
		}
		
		
		
		public static void main(String[] arg) {
			launch(arg);
		}
		
		
	    @FXML
	    void queenConvertBtnClicked() throws Exception{
	    	System.out.println("SDSD");
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
			   Parent toLoad = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
			   Scene scene = new Scene(toLoad);
			   stage.setScene(scene);
		    }
}
