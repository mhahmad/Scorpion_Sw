package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import Controller.SysData;
import Model.Level;
import Model.Question;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class manageQuestionScreen extends Application implements Initializable {

    @FXML
    private TableView<Question> quesTable;

    @FXML
    private Button backBtn;
    
    @FXML
    private Button removeBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button addBtn;
    
    @FXML
    private Button innerBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Pane innerPanel ;
    
    @FXML
    private TextField quesField;

    @FXML
    private TextField ans1Field;

    @FXML
    private TextField ans2Field;

    @FXML
    private TextField ans3Field;

    @FXML
    private TextField ans4Field;

    @FXML
    private ComboBox<String> combo ;
    @FXML
    private ComboBox<String> levelCombo;
    @FXML
    private TableColumn<Question, String> questionCol;
   
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "1",
			        "2",
			        "3",
			        "4"
			    );
		combo.setItems(options);
	        levelCombo.getItems().addAll(
	            "1",
	            "2",
	            "3"  
	            );
	        this.innerPanel.setVisible(false);
	        this.removeBtn.setDisable(true);
	        this.editBtn.setDisable(true);
	        this.innerBtn.disableProperty().bind(
	        	    Bindings.isEmpty(quesField.textProperty())
	        	    .or(Bindings.isEmpty(ans1Field.textProperty()))
	        	    .or(Bindings.isEmpty(ans2Field.textProperty()))
	        	    .or(Bindings.isEmpty(ans3Field.textProperty()))
	        	    .or(Bindings.isEmpty(ans4Field.textProperty()))
	        	    .or(Bindings.isNull(combo.valueProperty()))
	        	    .or(Bindings.isNull(levelCombo.valueProperty()))
	        	);

	        // intializing the table View 
	        
	        this.questionCol.setCellValueFactory(new PropertyValueFactory<>("content"));

	        this.quesTable.setItems(getQuestions());
	        
	        this.quesTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                	editBtn.setDisable(false);
                	removeBtn.setDisable(false);
                }
            });
	}
 
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub   
		Parent root = FXMLLoader.load(getClass().getResource("ManageQuestionScreen.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Hamka - Question Management");
		stage.setResizable(false);
	}
	
	
	public static void main(String[] arg) {
		launch(arg);
	}
	
	
	@FXML
	   public void backBtnClicked(ActionEvent event) throws IOException {
		   Stage stage = (Stage)this.backBtn.getScene().getWindow();
		   Parent toLoad = FXMLLoader.load(getClass().getResource("optionScreen.fxml"));
		   Scene scene = new Scene(toLoad);
		   stage.setScene(scene);
	    }
	
	@FXML
	   public void removeBtnClicked(ActionEvent event) throws IOException {
			this.editBtn.setDisable(true);
			this.innerPanel.setVisible(false);
			this.removeBtn.setDisable(true);
			Question ques = this.quesTable.getSelectionModel().getSelectedItem();
			this.quesTable.getItems().remove(ques);
			SysData.getInstance().removeQuestion(ques);
	    }
	
	@FXML
	   public void editBtnClicked(ActionEvent event) throws IOException {
			this.innerBtn.setText("Save");
			this.editBtn.setDisable(true);
			this.innerPanel.setVisible(true);
			this.removeBtn.setDisable(true);
			String ind = "0";
			Question question = this.quesTable.getSelectionModel().getSelectedItem();
			this.quesField.setText(question.getContent());
			this.ans1Field.setText(question.getAnswers().get(0));
			this.ans2Field.setText(question.getAnswers().get(1));
			this.ans3Field.setText(question.getAnswers().get(2));
			this.ans4Field.setText(question.getAnswers().get(3));
			for(int i=0 ; i < question.getAnswers().size() ; i++)
				if(question.getAnswers().get(i).equals(question.getRightAnswer()))
					ind = Integer.toString(1+i);
			
			this.combo.setValue(ind);
			this.levelCombo.setValue(question.getLevelNumber());
	    }
	@FXML
	   public void addBtnClicked(ActionEvent event) throws IOException {
			this.innerBtn.setText("Add");
			this.addBtn.setDisable(true);
			this.innerPanel.setVisible(true);
	}
	
	@FXML
	public void innerBtnClicked(ActionEvent event) throws IOException{
		String rightAnswer = "";
		ArrayList<String> answers = new ArrayList<>();
		answers.add(this.ans1Field.getText());
		answers.add(this.ans2Field.getText());
		answers.add(this.ans3Field.getText());
		answers.add(this.ans4Field.getText());
		rightAnswer = answers.get((Integer.parseInt(this.combo.getSelectionModel().getSelectedItem())-1));
		if(this.innerBtn.getText().equals("Add")) {
		Question ques = new Question(this.quesField.getText(),this.levelCombo.getSelectionModel().getSelectedItem(),answers,rightAnswer);
		quesTable.getItems().add(ques);
		if(SysData.getInstance().addQuestion(ques))
			SysData.getInstance().writeQuestionsToJson();
		}
		else if(this.innerBtn.getText().equals("Save")) {
			Question ques = this.quesTable.getSelectionModel().getSelectedItem();
			SysData.getInstance().editQuestion(ques, this.quesField.getText(), answers, this.levelCombo.getSelectionModel().getSelectedItem(), rightAnswer);
			this.quesTable.getItems().clear();
			this.quesTable.setItems(getQuestions());
			
		}
		this.ans1Field.clear();
		this.ans2Field.clear();
		this.ans3Field.clear();
		this.ans4Field.clear();
		this.quesField.clear();
		this.combo.getSelectionModel().clearSelection();
		this.levelCombo.getSelectionModel().clearSelection();;
		this.innerPanel.setVisible(false);
		this.addBtn.setDisable(false);
		
		

	}
	
	@FXML
	public void cancelBtnClicked(ActionEvent event) throws IOException{
		this.innerPanel.setVisible(false);
		this.addBtn.setDisable(false);
		this.ans1Field.clear();
		this.ans2Field.clear();
		this.ans3Field.clear();
		this.ans4Field.clear();
		this.quesField.clear();
	}
	// Get questions in observable list 
	public ObservableList<Question> getQuestions(){
		ObservableList<Question> questions = FXCollections.observableArrayList();
		questions.addAll(SysData.getInstance().getQuestions());
		System.out.println(questions);
		return questions;
	}

}
