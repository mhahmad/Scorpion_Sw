package View;


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

public class enterNameController{

	
	public static void display(Stage st) {
		Stage window = new Stage();
		window.setResizable(false);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Enter player names");
		window.setMinWidth(500);
		window.setMinHeight(350);
		Label label1 = new Label("Player 1 name :");
		 TextField player1 = new TextField();
		 player1.setMaxWidth(200);
		 Label label2 = new Label("Player 2 name :");
		 label1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		 label2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		 label2.setTextFill(Color.GOLD);
		 label1.setTextFill(Color.GOLD);

		 TextField player2 = new TextField();
		 player2.setMaxWidth(200);
		 StackPane  pane = new StackPane ();
		 Button closeButton = new Button("Close");
		 Button startButton = new Button("Start");
		 closeButton.setOnAction(e -> {
			window.close();
		 });
		 closeButton.setStyle("-fx-background-color : #FFA500 ; -fx-border-color : #000000; -fx-border-width: 1; -fx-background-insets : 0");
		 startButton.setStyle("-fx-background-color : #00FA9A ; -fx-border-color : #000000; -fx-border-width: 1; -fx-background-insets : 0");
		 closeButton.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,16));
		 startButton.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,16));
		 
		 Label errorLabel = new Label("You must enter names for both players");
		 startButton.setOnAction(e -> {
			 String p1 = player1.getText();
			 String p2 = player2.getText();
			 
			 if(p1.isEmpty() || p2.isEmpty()) {
				 if(p1.isEmpty()) {
					 errorLabel.setVisible(true);
					 player1.setStyle("-fx-border-color : #FF0000; -fx-border-width: 2");
				 }else {
					 player1.setStyle("-fx-border-width: 0");
				 }
				 if(p2.isEmpty()) {
					 errorLabel.setVisible(true);
					 player2.setStyle("-fx-border-color : #FF0000; -fx-border-width: 2");
				 }else {
					 player1.setStyle("-fx-border-width: 0");
				 }
			 }else {
				 // GO TO BOARD SCREEN
				 FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("gameplayScreen.fxml"));
                 Parent root;
               try {
                   root = loader.load();

                 Scene scene = new Scene(root);
                 window.setTitle("Hamka");
                 window.setScene(scene);
                 gameplayScreenController con = loader.getController();
                 con.p1Name= player1.getText() ;
                 con.p2Name = player2.getText() ; 
                 con.start(window);
                 window.show();
                 window.centerOnScreen();
                 st.close();
               } catch ( Exception e1) {
                   // TODO Auto-generated catch block
                   e1.printStackTrace();
               }
			 }
		 });
		 errorLabel.setVisible(false);
		 errorLabel.setFont(Font.font("Arial Black", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		 errorLabel.setTextFill(Color.RED);
		 HBox box = new HBox(10,startButton,closeButton);
		 pane.getChildren().addAll(box);
		 box.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(20,0,10,0));
		 VBox layout = new VBox(10);
		 layout.setAlignment(Pos.CENTER);
		 layout.getChildren().addAll(label1,player1,label2,player2,errorLabel,pane);
		 layout.setStyle("-fx-background-color: #8B0000	");
		 Scene scene = new Scene(layout);
		 window.setScene(scene);
		 window.showAndWait();
	}
}
