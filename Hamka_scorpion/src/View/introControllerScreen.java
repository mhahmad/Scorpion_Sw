package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class introControllerScreen  extends Application implements Initializable{

    @FXML
    private MediaView mediaView;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("introScreen.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka");
		stage.setResizable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		String introPath =  (getClass().getResource("/Resources/GameIntro.mp4")).toString();
		System.out.println(introPath);
		Media media = new Media(introPath);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();
		mediaView.setFitHeight(960);
		
		mediaPlayer.setOnEndOfMedia(() -> {
			Stage st = (Stage)this.mediaView.getScene().getWindow();
			Parent toLoad;
			try {
				toLoad = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
				Scene scene = new Scene(toLoad);
				st.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}
	
	
	public static void main(String[] arg) {
		launch(arg);
	}

}
