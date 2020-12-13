package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class loadGameScreen extends Application{

    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> loadgameTable;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("LoadGameScreen.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka - Load game");
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
}
