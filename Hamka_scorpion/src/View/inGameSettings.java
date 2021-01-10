package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public  class inGameSettings {

 public	Button continueBtn = new Button("Continue");
 public Button saveBtn = new Button("Save");
 public  Button exitBtn = new Button("Exit");
 public Stage window ;

	public  void display() {
		window = new Stage();
		window.setResizable(false);
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UNDECORATED);
		window.setTitle("Settings");
		window.setMinWidth(230);
		window.setMinHeight(400);
		Label label = new Label("Game stopped");
		label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24));
		label.setPadding(new Insets(0,0,50,0));
		label.setTextFill(Color.GOLD);
		continueBtn.setMinWidth(200);
		saveBtn.setMinWidth(200);
		exitBtn.setMinWidth(200);
		continueBtn.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		saveBtn.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		exitBtn.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		continueBtn.setMinHeight(50);
		saveBtn.setMinHeight(50);
		exitBtn.setMinHeight(50);
		continueBtn.setStyle("-fx-background-color : #fcdc3d");
		saveBtn.setStyle("-fx-background-color : #FDF14E");
		exitBtn.setStyle("-fx-background-color : #FEF899");
		 VBox layout = new VBox(10);
		 layout.getChildren().addAll(label,continueBtn,saveBtn,exitBtn);
		 layout.setAlignment(Pos.TOP_CENTER);
		 layout.setPadding(new Insets(20,20,20,20));
		 if(Controller.SysData.darkTheme)
			 layout.setStyle("-fx-background-color: #3E3E3E	");
		 else
			 layout.setStyle("-fx-background-color: #8B0000	");
		 Scene scene = new Scene(layout);
		 window.setScene(scene);
		
	}

	public void showWin() {
		 window.showAndWait();
	}
	
}