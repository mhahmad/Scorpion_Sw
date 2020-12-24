package Controller;

import java.io.File;
import java.io.IOException;

import Controller.SysData;
import Model.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class loadGameScreen extends Application{

	
	
    @FXML
    private Button backBtn;

    @FXML
    private Button btnChoose;
    @FXML
    private TableView<?> loadgameTable;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/View/LoadGameScreen.fxml"));
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
		   Parent toLoad = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
		   Scene scene = new Scene(toLoad);
		   stage.setScene(scene);
	    }
    
    @FXML
    public void btnChooseAction(ActionEvent event) throws IOException {
    	Stage window = new Stage();
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("Txt Files", "*.txt")); // file chooser shows only text files.
    	File selectedFile = fc.showOpenDialog(null);
    	if(selectedFile!=null) {
    		String chosenFilePath = selectedFile.getAbsolutePath();
    		int[][] board = SysData.getInstance().getBoard(chosenFilePath);
    		Color turn = SysData.getInstance().getTurn(chosenFilePath);
    		 FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
             Parent root;
           try {
               root = loader.load();
             Scene scene = new Scene(root);
             window.setTitle("Hamka");
             window.setScene(scene);
             gameplayScreenController con = loader.getController();
             con.p1Name= "Player 1" ;
             con.p2Name = "Player 2";
             //con.setBoard(board);
             con.start(window);
             window.show();
             window.centerOnScreen();
             
             //stage.close();
           } catch ( Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
           }
    		
			
			
			 
    		System.out.println("File Chosen Succesfully"); 
    	}else {
    		System.out.println("Invalid File"); 

    	}
    	
    }
    
    
    
}
