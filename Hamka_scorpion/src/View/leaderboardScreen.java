package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.SysData;
import Model.Question;
import Model.Winner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class leaderboardScreen extends Application implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private ImageView background;

    @FXML
    private Pane headerPane;

    @FXML
    private Pane middlePane;
    @FXML
    private TableView<Winner> leaderboardTable;
    @FXML
    private TableColumn<Winner, String> playerColumn;


    @FXML
    private TableColumn<Winner, Integer> resultColumn;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("LeaderboardScreen.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka - leadboard");
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



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 this.playerColumn.setCellValueFactory(new PropertyValueFactory<>("winnerName"));
		 this.resultColumn.setCellValueFactory(new PropertyValueFactory<>("winnerPoints"));

	        this.leaderboardTable.setItems(getWinners());
	        darkTheme(SysData.darkTheme);
	}
	
	
	//Gets all winners names
	public ObservableList<Winner> getWinners(){
		ObservableList<Winner> winners = FXCollections.observableArrayList();
		winners.addAll(SysData.getInstance().getLeaderboard());
		System.out.println(winners);
		return winners;
	} 
	
	public void darkTheme(boolean isOn) {
    	if(isOn) {
    		background.setImage(new Image("Resources/darkThemeBackground.png"));
    		headerPane.setStyle("-fx-background-color : #201C1C");
    		middlePane.setStyle("-fx-background-color :  #272626 ; -fx-background-radius:  17; -fx-border-radius:  15; -fx-border-color: #444444; -fx-border-width: 6;");
    	
    	}else {
    		background.setImage(new Image("Resources/mainMenuBackground.png"));
    		headerPane.setStyle("-fx-background-color :  #630000;");
    		middlePane.setStyle("-fx-background-color :  #630000 ; -fx-background-radius:  17; -fx-border-radius:  15; -fx-border-color: #444444; -fx-border-width: 6;");
    	
    	}
    }
}
