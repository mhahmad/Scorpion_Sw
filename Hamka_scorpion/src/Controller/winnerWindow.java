package Controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class winnerWindow extends Application{

	public Button nextBtn = new Button("Back to main menu !");
	public Label winnerLabel = new Label("Player1 Wins ");
	public Label VicLabel = new Label("Victory !");
	public Stage window;
	
	
	public void display() {
		window = new Stage();
		window.setMinHeight(100);
		window.setMinWidth(300);
		window.initStyle(StageStyle.UNDECORATED);
		window.initModality(Modality.APPLICATION_MODAL);
		VBox layout = new VBox();
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(VicLabel,winnerLabel,nextBtn);
		layout.setAlignment(Pos.CENTER);
		VicLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 58));
		winnerLabel.setPadding(new Insets(20,0,50,0));
		winnerLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 26));
		VicLabel.setTextFill(Color.GOLD);
		VicLabel.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		Scene scene = new Scene(layout,450,300);
		layout.setStyle("  -fx-background-color: linear-gradient(from 0px 0px to 210px 210px, #B1B8C9 50%, #F7F7F2 40%, #B1B8C9 100% );");
		nextBtn.setStyle("-fx-background-color : #FDCC25 ; -fx-border-width: 1; -fx-border-color: #000000; -fx-border-radius:15; -fx-background-radius : 16");
		nextBtn.setFont(Font.font("Arial", FontWeight.MEDIUM, FontPosture.REGULAR, 22));
		window.setScene(scene);
		window.showAndWait();
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		display();
	}

	public static void main(String[] arg) {
		launch();
	}
	
	
	
}
