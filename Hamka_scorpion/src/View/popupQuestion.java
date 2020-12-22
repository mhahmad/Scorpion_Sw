package View;

import Model.Question;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class popupQuestion extends Application {

	public Button nextBtn = new Button("Next");
	public Question question = null;
	public 	ToggleGroup group = new ToggleGroup();

	Stage window;

	public void display() {
		window = new Stage();
		window.initStyle(StageStyle.UNDECORATED);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setResizable(false);
		window.setTitle("Answer the question :");
		window.setMinHeight(370);
		window.setMinWidth(500);
		VBox layout = new VBox();
		Label question = new Label("What is the xxxx-xxxxxxx-xxxxxxx-xxxxxx 7+ 6 ?");
		if(this.question != null)
			question.setText(this.question.getContent());
		question.setMinHeight(Region.USE_PREF_SIZE);
		question.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
		RadioButton answer1 = new RadioButton("13");
		RadioButton answer2 = new RadioButton("14");
		RadioButton answer3 = new RadioButton("11");
		RadioButton answer4 = new RadioButton("12");
		if(this.question!=null) {
			answer1.setText(this.question.getAnswers().get(0));
			answer2.setText(this.question.getAnswers().get(1));
			answer3.setText(this.question.getAnswers().get(2));
			answer4.setText(this.question.getAnswers().get(3));
		}
		answer1.setToggleGroup(group);
		answer2.setToggleGroup(group);
		answer3.setToggleGroup(group);
		answer4.setToggleGroup(group);
		layout.setPadding(new Insets(20,20,20,20));
		answer1.setPadding(new Insets(30,20,0,20));
		answer2.setPadding(new Insets(15,20,0,20));
		answer3.setPadding(new Insets(15,20,0,20));
		answer4.setPadding(new Insets(15,20,50,20));
		layout.setStyle("-fx-background-color: #F1EDAE");
		answer1.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		answer2.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		answer3.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		answer4.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15));
		nextBtn.setMinWidth(80);
		VBox lay = new VBox(nextBtn);
		lay.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(question,answer1,answer2,answer3,answer4,lay);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
	}
 
	public static void main(String[] arg) {
		launch(arg);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		display();
	}
	
}
