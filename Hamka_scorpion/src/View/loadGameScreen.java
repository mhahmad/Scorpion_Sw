package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controller.SysData;
import Controller.gameController;
import Model.Board;
import Model.Color;
import Model.Game;
import Model.Winner;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class loadGameScreen extends Application implements Initializable {

	@FXML
	private ImageView background;

	@FXML
	private Pane headerPane;

	@FXML
	private Pane middlePane;
	@FXML
	private Button backBtn;

	@FXML
	private Button btnChoose;
	@FXML
	private TableView<String> loadgameTable;
	@FXML
	private TableColumn<String, String> tableColumn;
	@FXML
	private Button loadGameBtn;

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
		Stage stage = (Stage) this.backBtn.getScene().getWindow();
		Parent toLoad = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
		Scene scene = new Scene(toLoad);
		stage.setScene(scene);
	}

	@FXML
	public void btnChooseAction(ActionEvent event) throws IOException {
		Stage window = new Stage();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Txt Files", "*.txt")); // file chooser shows only text
		// files.
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			String chosenFilePath = selectedFile.getAbsolutePath();
			Board board = new Board(SysData.getInstance().getBoard(chosenFilePath));
			Color turn = SysData.getInstance().getTurn(chosenFilePath);
			ArrayList<String> namesAndPoints = null;
			if (SysData.getInstance().IsNameAndPointsIncluded(chosenFilePath)) {
				namesAndPoints = SysData.getInstance().getNameAndPoints(chosenFilePath);
			}
			FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				window.setTitle("Hamka");
				window.setScene(scene);
				gameplayScreenController con = loader.getController();
				if (namesAndPoints == null) {
					gameplayScreenController.p1Name = "Player 1";
					gameplayScreenController.p2Name = "Player 1";
				} else {
					gameplayScreenController.p1Name = namesAndPoints.get(0);
					gameplayScreenController.p2Name = namesAndPoints.get(1);
				}
				gameController.setBoard(board);
				gameController.startBoard = board.getBoard();
				gameController.loadedBoard = board;
				gameController.game = new Game(gameplayScreenController.p1Name,
						gameplayScreenController.p2Name, board.getBoard()); // Singletone changes to be in every method.
				gameController.setTurn(turn);
				if (namesAndPoints != null) {
					gameController.game.setblackPlayerPoints(Integer.parseInt(namesAndPoints.get(2)));
					gameController.game.setWhitePlayerPoints(Integer.parseInt(namesAndPoints.get(3)));
				}
				con.start(window);
				window.show();
				window.centerOnScreen();
				Stage toClose = (Stage) this.backBtn.getScene().getWindow();
				toClose.close();
				// stage.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("File Chosen Succesfully");
		} else {
			System.out.println("Invalid File");

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.loadGameBtn.setDisable(true);
		tableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
		this.loadgameTable.setItems(getSavedFiles());

		this.loadgameTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				loadGameBtn.setDisable(false);
			}
		});
		darkTheme(SysData.darkTheme);

	}


	/***
	 * This method gets all saved games in savedGames directory
	 * @return
	 */
	public ObservableList<String> getSavedFiles(){
		ObservableList<String> savedFiles = FXCollections.observableArrayList();

		//		File folder = new File(System.getProperty("user.dir") + "/savedGames");
		System.out.println(System.getProperty("user.dir"));
		File folder = new File(System.getProperty("user.dir"));
		folder = folder.getParentFile();
		String toF = folder.toString();
		folder = new File(toF + "/savedGames");
		System.out.println(folder.getAbsolutePath());
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				savedFiles.add(file.getName());
				/* do somthing with content */
			} 
		}
		return savedFiles;
	}


	@FXML
	public void loadGameBtnClicked(ActionEvent event) throws IOException {
		Stage window = new Stage();

		File folder = new File(System.getProperty("user.dir"));
		folder = folder.getParentFile();
		String toF = folder.toString();

		String chosenFilePath = toF+ "/savedGames/" + this.loadgameTable.getSelectionModel().getSelectedItem();
		Board board = new Board(SysData.getInstance().getBoard(chosenFilePath));
		Color turn = SysData.getInstance().getTurn(chosenFilePath);
		ArrayList<String> namesAndPoints = null;
		if (SysData.getInstance().IsNameAndPointsIncluded(chosenFilePath)) {
			namesAndPoints = SysData.getInstance().getNameAndPoints(chosenFilePath);
		}
		FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			window.setTitle("Hamka");
			window.setScene(scene);
			gameplayScreenController con = loader.getController();
			if (namesAndPoints == null) {
				gameplayScreenController.p1Name = "Player 1";
				gameplayScreenController.p2Name = "Player 1";
			} else {
				gameplayScreenController.p1Name = namesAndPoints.get(0);
				gameplayScreenController.p2Name = namesAndPoints.get(1);
			}
			gameController.setBoard(board);
			gameController.startBoard = board.getBoard();
			gameController.loadedBoard = board;
			gameController.game = new Game(gameplayScreenController.p1Name,
					gameplayScreenController.p2Name, board.getBoard()); // Singletone changes to be in every method.
			gameController.game.setTurn(turn);
			if (namesAndPoints != null) {
				gameController.game.setblackPlayerPoints(Integer.parseInt(namesAndPoints.get(2)));
				gameController.game.setWhitePlayerPoints(Integer.parseInt(namesAndPoints.get(3)));
			}
			con.start(window);
			window.show();
			window.centerOnScreen();
			Stage toClose = (Stage) this.backBtn.getScene().getWindow();
			toClose.close();
			// stage.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("File Chosen Succesfully");


	}

	public void darkTheme(boolean isOn) {
		if(isOn) {
			background.setImage(new Image("Resources/darkThemeBackground.png"));
			headerPane.setStyle("-fx-background-color : #201C1C");
			middlePane.setStyle("-fx-background-color :  #272626 ; -fx-background-radius:  17; -fx-border-radius:  15; -fx-border-color: #444444; -fx-border-width:  5;");

		}else {
			background.setImage(new Image("Resources/mainMenuBackground.png"));
			headerPane.setStyle("-fx-background-color :  #630000;");
			middlePane.setStyle("-fx-background-color :  #630000 ; -fx-background-radius:  17; -fx-border-radius:  15; -fx-border-color: #444444; -fx-border-width:  5;");

		}
	}
}
