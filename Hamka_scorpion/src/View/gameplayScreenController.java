package View;


import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.ResourceBundle;
import java.util.TimerTask;

import javax.management.openmbean.OpenType;
import javax.swing.Timer;
import javax.swing.event.ChangeListener;

import Controller.SysData;
import Controller.gameController;
import Model.Board;
import Model.Color;
import Model.Game;
import Model.Level;
import Model.Queen;
import Model.Soldier;
import Model.StopWatch;
import Model.Tile;
import Model.Winner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class gameplayScreenController extends Application implements Initializable {


	@FXML
	private Pane headerPane;
	@FXML
	private Pane paneBoard;

	@FXML
	private Pane paneBoard2;


	@FXML
	private Button tile5;

	@FXML
	private Button tile13;

	@FXML
	private Button tile21;

	@FXML
	private Button tile29;

	@FXML
	private Button tile1;

	@FXML
	private Button tile9;

	@FXML
	private Button tile17;

	@FXML
	private Button tile25;

	@FXML
	private Button tile6;

	@FXML
	private Button tile14;

	@FXML
	private Button tile22;

	@FXML
	private Button tile30;

	@FXML
	private Button tile2;

	@FXML
	private Button tile10;

	@FXML
	private Button tile18;

	@FXML
	private Button tile26;

	@FXML
	private Button tile7;

	@FXML
	private Button tile15;

	@FXML
	private Button tile23;

	@FXML
	private Button tile31;

	@FXML
	private Button tile3;

	@FXML
	private Button tile11;

	@FXML
	private Button tile19;

	@FXML
	private Button tile27;

	@FXML
	private Button tile8;

	@FXML
	private Button tile16;

	@FXML
	private Button tile24;

	@FXML
	private Button tile32;

	@FXML
	private Button tile4;

	@FXML
	private Button tile12;

	@FXML
	private Button tile20;

	@FXML
	private Button tile28;

	@FXML
	private Label p1;

	@FXML
	private Label p2;

	@FXML
	private Label pointsLabel;

	@FXML
	private Label p1Points;

	@FXML
	private Label pointsLabel1;

	@FXML
	private Label p2Points;

	@FXML
	private VBox deadBlackv;

	@FXML
	private VBox deadwhitev;

	@FXML
	private Label displayTimer;

	@FXML
	private Label winnerLabel;

	@FXML
	private Button exitBtn;

	@FXML
	private Button settingsButton;

	@FXML
	private Label live_pausedlbl;

	@FXML
	private Label streakLabel;

	public boolean isGameOver = false;
	public ArrayList<Tile> yellowTiles = new ArrayList<>();
	public Tile blueTile = null;
	public Tile greenTile = null;
	public Tile redTile = null;
	public int flag = 0;
	inGameSettings settings = new inGameSettings();;
	popupQuestion quesPop = new popupQuestion();
	winnerWindow winnerWin = new winnerWindow();
	public static String p1Name = "p1";
	public static String p2Name ="p2";
	/* Buttons to display queen movements.*/
	Button tl = new Button();
	Button br = new Button();
	Button tr = new Button();
	Button bl = new Button();

	//---------------------Timer RElated ! s
	private static final Integer STARTTIME = 120; // We can make it Max turn Time ! 
	private static Timeline timeline;
	@FXML
	private Label timelbl;
	private static IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);    
	// Method res setTimmer() ; 

	@FXML
	private Label GameTime;

	private final StringProperty time = new SimpleStringProperty() ; 
	//-----------------------------------
	public static HashMap<String, String> tilesBoardMap;
	public static String clickedSoldier = null;
	public static ArrayList<Tile> possible = null;
	public static HashMap<Tile, Soldier> possibleQueen = null;
	public static boolean lockedForStreak = false;
	public static boolean lockedForRedTile = false;
	public static boolean lockedForBlue = false;
	public static String blackStreak = null;
	public static String whiteStreak = null;
	public static Board loadedBoard = null;
	public static boolean InvalidMove = true;

	private static gameplayScreenController instance = new gameplayScreenController();

	public static int[][] startBoard = {
			{-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,22,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,11,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{1,-1,1,-1,1,-1,1,-1}
	};

	// check Blue tile Gen 
	//	private int[][] startBoard = {
	//			{-1,22,-1,0,-1,2,-1,2},
	//			{0,-1,0,-1,0,-1,0,-1},
	//			{-1,0,-1,0,-1,0,-1,0},
	//			{0,-1,0,-1,0,-1,0,-1},
	//			{-1,0,-1,0,-1,0,-1,11},
	//			{1,-1,11,-1,11,-1,0,-1},
	//			{-1,1,-1,1,-1,1,-1,1},
	//			{1,-1,1,-1,1,-1,1,-1}
	//	};

	public static Parent  root;
	public static Scene scene;
	GridPane queenArrows = new GridPane();

	String direction = null;

	//private Game game  = new Game("White", "Black", startBoard);
	//public static  Game game = new Game(p1Name, p2Name, startBoard); //Singletone changes to be in every method.

	public static gameplayScreenController getInstance() {
		return instance;
	}

	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		if(p1Name == null)
			p1Name = "p1";
		if(p2Name == null)
			p2Name = "p2";

		if(gameController.game == null)
			gameController.game = new Game(p1Name, p2Name, startBoard); //Singletone changes to be in every method.
		timeSeconds = new SimpleIntegerProperty(STARTTIME);
		root = FXMLLoader.load(getClass().getResource("gameplayScreen.fxml"));
		scene = new Scene(root);
		//FillBoard() ;
		//game.handTurn();
		SysData.getInstance().getQuestions();

		stage.setScene(scene);
		stage.setResizable(false);
		//java.io.FileInputStream fis = new FileInputStream("/System/Library/CoreServices/loginwindow.app/Contents/Resources/LogOut.png");
		buildTilesBoardMap();
		refreshBoard(gameController.game, scene, root);






		//((Button) scene.lookup("#tile3")).setGraphic(img);
		//	((Button)root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().indexOf( (Button) scene.lookup("#tile3")))).setGraphic(img); 
		stage.show();
		stage.setTitle("Hamka - Match");
		setTimmer() ; // Black's Turn Starts when opening window ! 




	}




	public static void main(String[] arg) {
		launch(arg);
	}

	@Override
	public void initialize (URL arg0, ResourceBundle arg1) {
		darkTheme(SysData.darkTheme);
		if(p1Name == null)
			p1Name = "p1";
		if(p2Name == null)
			p2Name = "p2";

		if(gameController.game == null)
			gameController.game = new Game(p1Name, p2Name, startBoard); //Singletone changes to be in every method.

		Timer t = new javax.swing.Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				// TODO Auto-generated method stub

				boolean x=true;
				long displayMinutes=0;
				//long secondspassed=0 ; 
				long starttime=System.currentTimeMillis();
				//   System.out.println("Timer:");
				while(  scene.getWindow()!=null && scene.getWindow().isShowing())
				{

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long timepassed=System.currentTimeMillis()-starttime;
					long  secondspassed=timepassed/1000;
					if(secondspassed==60)
					{
						secondspassed=0;
						starttime=System.currentTimeMillis();
					}
					if((secondspassed%60)==0)
						displayMinutes++;

					time.set( displayMinutes+" :: "+secondspassed);
					System.out.println(time.getValue());
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							//javaFX operations should go here	
							((Label) scene.lookup("#GameTime")).setText(time.getValue());

						}
					});


				}				

			}
		});
		t.start();

		//------------------------------
		buildTilesBoardMap();
		p1Points.setText(String.valueOf(gameController.getPlayerPoints(Color.Black))); 
		p2Points.setText(String.valueOf(gameController.getPlayerPoints(Color.White))); 
		p1.setText(gameController.getPlayer(Color.Black));
		p2.setText(gameController.getPlayer(Color.White));
		if(gameController.getTurn().equals(Color.Black)) {
			p1.setFont(Font.font("System",FontWeight.BOLD, FontPosture.REGULAR, 20));
			p1.setTextFill(javafx.scene.paint.Color.DARKORANGE);
		}else {
			p2.setFont(Font.font("System",FontWeight.BOLD, FontPosture.REGULAR, 20));
			p2.setTextFill(javafx.scene.paint.Color.DARKORANGE);
		}
		winnerLabel.setVisible(false);
		streakLabel.setVisible(false);

		settings.continueBtn.setOnAction(e ->{
			((Stage)settings.exitBtn.getScene().getWindow()).close();
			this.timeline.play();
			this.live_pausedlbl.setText("Live");
		});

		winnerWin.nextBtn.setOnAction(e -> {
			((Stage)winnerWin.nextBtn.getScene().getWindow()).close();

			Stage stage = (Stage)this.settingsButton.getScene().getWindow();
			Parent toLoad;
			try {
				toLoad = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
				Scene scene = new Scene(toLoad);
				stage.setScene(scene);
				stage.centerOnScreen();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		quesPop.nextBtn.setOnAction(e -> {
			quesPop.points.setText("");
			if(quesPop.nextBtn.getText().equals("Next")) {
				RadioButton rb = (RadioButton) quesPop.group.getSelectedToggle();

				if(SysData.getInstance().isQuestionAnsweredCorrectly(quesPop.question, rb.getText())) {
					rb.setStyle("-fx-background-color : #32CD32");
					quesPop.points.setTextFill(javafx.scene.paint.Color.FORESTGREEN);
					if(quesPop.question.getLevel().equals(Level.easy)) {
						quesPop.points.setText("You have earned 100 points");
						if(gameController.getTurn().equals(Color.Black))
							gameController.updatePlayerPoints(100, Color.Black);
						else
							gameController.updatePlayerPoints(100, Color.White);
					}else if(quesPop.question.getLevel().equals(Level.medium)) {
						quesPop.points.setText("You have earned 200 points");
						if(gameController.getTurn().equals(Color.Black))
							gameController.updatePlayerPoints(200, Color.Black);
						else
							gameController.updatePlayerPoints(200, Color.White);
					}else {
						quesPop.points.setText("You have earned 500 points");
						if(gameController.getTurn().equals(Color.Black))
							gameController.updatePlayerPoints(500, Color.Black);
						else
							gameController.updatePlayerPoints(500, Color.White);
					}

				}
				else {
					rb.setStyle("-fx-background-color : #EB1717");
					quesPop.points.setTextFill(javafx.scene.paint.Color.RED);
					quesPop.group.getToggles().forEach(toggle -> {
						RadioButton rad =(RadioButton) toggle;
						if(rad.getText().equals(quesPop.question.getRightAnswer())) {
							rad.setStyle("-fx-background-color : #32CD32");
						}
					});

					if(quesPop.question.getLevel().equals(Level.easy)) {
						quesPop.points.setText("You have lost 250 points");
						if(gameController.getTurn().equals(Color.Black))
							gameController.updatePlayerPoints(-250, Color.Black);
						else
							gameController.updatePlayerPoints(-250, Color.White);
					}else if(quesPop.question.getLevel().equals(Level.medium)) {
						quesPop.points.setText("You have lost 100 points");
						if(gameController.getTurn().equals(Color.Black))
							gameController.updatePlayerPoints(-100, Color.Black);
						else
							gameController.updatePlayerPoints(-100, Color.White);
					}else {
						quesPop.points.setText("You have lost 50 points");
						if(gameController.getTurn().equals(Color.Black))
							gameController.updatePlayerPoints(-50, Color.Black);
						else
							gameController.updatePlayerPoints(-50, Color.White);
					}

				}
				quesPop.nextBtn.setText("Close");
			}else {
				quesPop.window.close();
				if(!lockedForStreak && !lockedForRedTile && !lockedForBlue)
					SwapTurn();
				quesPop.nextBtn.setText("Next");
			}
		});
		settings.exitBtn.setOnAction(e -> {

			((Stage)settings.exitBtn.getScene().getWindow()).close();

			Stage stage = (Stage)this.settingsButton.getScene().getWindow();
			Parent toLoad;
			try {
				toLoad = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
				Scene scene = new Scene(toLoad);
				stage.setScene(scene);
				stage.centerOnScreen();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});


		settings.saveBtn.setOnAction(e -> {
			TextInputDialog td = new TextInputDialog();
			td.initStyle(StageStyle.UNDECORATED);
			td.setGraphic(null);
			td.setHeaderText("Save Game File :");
			Optional<String> result = td.showAndWait();
			if(result.isPresent()) {
				Board board = gameController.getBoardObj();
				Color turn = gameController.getTurn();
				String p1 = gameController.getPlayer(Color.Black);
				String p2 = gameController.getPlayer(Color.White);
				SysData.getInstance().saveGame(board, turn,p1,p2,gameController.getPlayerPoints(Color.Black),gameController.getPlayerPoints(Color.White),result.get());
			}


		});
		//----------Timer related
		timeSeconds.addListener((observable, oldTimeValue, newTimeValue) -> {
			// code to execute here...
			// e.g.
			//System.out.println("Time left: "+newTimeValue);

			//   System.out.println("Time left: "+timeSeconds.toString());

			//		    System.out.println("time left : "+newTimeValue);
			if(newTimeValue.intValue() == 90)     greenTile = GenerateGreenTiles(scene, gameController.getTurn());
			if(newTimeValue.intValue() == 30)     GenerateOrangeTiles(scene, gameController.getTurn());
			if(newTimeValue.intValue() == 1) {SwapTurn();  try {
				flag=0;
				clearBoard(gameController.game, scene, root);
				ClearColoredTiles(scene);
				refreshBoard(gameController.game,scene, root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}}
			//   System.err.println("oldEime Value : "+oldTimeValue);
			if(newTimeValue.intValue() > oldTimeValue.intValue()) {
				if(gameController.getTurn().equals(Color.White)) {	// now its white's turn - adding the time points from the Black's turn    
					System.out.println("its :------------------------ "+gameController.getTurn());  	
					gameController.updatePlayerPoints(oldTimeValue.intValue()-60, Color.Black);
					System.out.println("Adding points to Black " +(oldTimeValue.intValue()-60));
					((Label)scene.lookup("#p1Points")).setText(String.valueOf(gameController.getPlayerPoints(Color.Black)));

				}
				if(gameController.game.getTurn().equals(Color.Black)) {	   // white's Turn 
					System.out.println("its :------------------------ "+gameController.game.getTurn());  	
					gameController.updatePlayerPoints(oldTimeValue.intValue()-60, Color.White);
					System.out.println("Adding points to white  "+(oldTimeValue.intValue()-60));
					((Label)scene.lookup("#p2Points")).setText(String.valueOf(gameController.getPlayerPoints(Color.White)));

				}

			}
		});





		//  OverAllTimer();
		//------------------------------

		queenArrows.setVisible(false);
		queenArrows.setVgap(2);
		queenArrows.setHgap(2);
		queenArrows.setPadding(new Insets(0, 10, 0, 10));
		queenArrows.setMinHeight(20);
		queenArrows.setMinWidth(20);
		queenArrows.add(tl,5,5);
		queenArrows.add(br, 6, 6);
		queenArrows.add(tr, 6, 5);
		queenArrows.add(bl, 5, 6);


		paneBoard.setOnMouseClicked(e -> {
			queenArrows.setVisible(false);
		});
		paneBoard.getChildren().add(queenArrows);
		//pane.setVisible(true);
		queenArrows.setTranslateX(10);
		queenArrows.setTranslateY(10);
		//        orig.getChildren().add(b5);
		//        ss.getChildren().addAll(orig,pane);
		ImageView b1Image = new ImageView(
				new Image(getClass().getResourceAsStream("/Resources/TL.png"))); 
		tl.setGraphic(b1Image);
		ImageView b2Image = new ImageView(
				new Image(getClass().getResourceAsStream("/Resources/BR.png"))); 
		br.setGraphic(b2Image);
		ImageView b3Image = new ImageView(
				new Image(getClass().getResourceAsStream("/Resources/TR.png"))); 
		tr.setGraphic(b3Image);
		ImageView b4Image = new ImageView(
				new Image(getClass().getResourceAsStream("/Resources/BL.png"))); 
		bl.setGraphic(b4Image);
		//     pane.setVisible(false);
		tl.setStyle("-fx-background-color: transparent;");
		br.setStyle("-fx-background-color: transparent;");
		tr.setStyle("-fx-background-color: transparent;");
		bl.setStyle("-fx-background-color: transparent;");





	}


	public void buildTilesBoardMap() {
		tilesBoardMap = new HashMap<>();
		tilesBoardMap.put("tile1", "0,1");
		tilesBoardMap.put("tile2", "0,3");
		tilesBoardMap.put("tile3", "0,5");
		tilesBoardMap.put("tile4", "0,7");

		tilesBoardMap.put("tile5", "1,0");
		tilesBoardMap.put("tile6", "1,2");
		tilesBoardMap.put("tile7", "1,4");
		tilesBoardMap.put("tile8", "1,6");

		tilesBoardMap.put("tile9", "2,1");
		tilesBoardMap.put("tile10", "2,3");
		tilesBoardMap.put("tile11", "2,5");
		tilesBoardMap.put("tile12", "2,7");

		tilesBoardMap.put("tile13", "3,0");
		tilesBoardMap.put("tile14", "3,2");
		tilesBoardMap.put("tile15", "3,4");
		tilesBoardMap.put("tile16", "3,6");

		tilesBoardMap.put("tile17", "4,1");
		tilesBoardMap.put("tile18", "4,3");
		tilesBoardMap.put("tile19", "4,5");
		tilesBoardMap.put("tile20", "4,7");

		tilesBoardMap.put("tile21", "5,0");
		tilesBoardMap.put("tile22", "5,2");
		tilesBoardMap.put("tile23", "5,4");
		tilesBoardMap.put("tile24", "5,6");

		tilesBoardMap.put("tile25", "6,1");
		tilesBoardMap.put("tile26", "6,3");
		tilesBoardMap.put("tile27", "6,5");
		tilesBoardMap.put("tile28", "6,7");

		tilesBoardMap.put("tile29", "7,0");
		tilesBoardMap.put("tile30", "7,2");
		tilesBoardMap.put("tile31", "7,4");
		tilesBoardMap.put("tile32", "7,6");
	}


	//------------------------Timer Related  

	public static void setTimmer() {

		//	 Timeline timeline = new Timeline(); 
		//sprivate Label timerLabel = new Label();


		//	 IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

		if(timeline != null) {
			System.out.println("timeLine stoping ");
			timeline.stop();
		}

		if(((Label) scene.lookup("#"+"timelbl")) != null) {
			((Label) scene.lookup("#"+"timelbl")).textProperty().unbind();
			((Label) scene.lookup("#"+"timelbl")).setText(timeSeconds.toString());

			// ((Label) scene.lookup("#"+"timelbl")).setTextFill(Color.valueOf("#00000"));
			//		    ((Label) scene.lookup("#"+"timelbl")).setStyle("-fx-font-size: 4em;");

			// Bind the timerLabel text property to the timeSeconds property
			((Label) scene.lookup("#"+"timelbl")).textProperty().bind(timeSeconds.asString());

		}


		timeSeconds.set(STARTTIME);
		timeline = new Timeline();

		KeyValue keyValue = new KeyValue(timeSeconds, 0);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(STARTTIME + 1), keyValue);

		timeline.getKeyFrames().add(keyFrame);
		timeline.playFromStart();

		System.out.println("get every seconds value and display to console window");

	}

	//-------------------------------------

	@FXML
	void tileClicked(MouseEvent event) throws IOException {
		//		ClearColoredTiles(scene);
		queenArrows.setVisible(false);
		updateTurnLabels();
		streakLabel.setVisible(false);
		if(SysData.getInstance().getQuestionList().isEmpty())
			SysData.getInstance().refillQuestionList();
		Button currentTile;
		Board gBoard = gameController.getBoardObj();
		int[][] board = gBoard.getBoard();
		//System.out.println(board);



		String dest = tilesBoardMap.get((String)((Control)event.getSource()).getId());
		//		 root = FXMLLoader.load(getClass().getResource("gameplayScreen.fxml"));
		//		 scene = new Scene(root);
		currentTile = (Button) scene.lookup("#"+(String)((Control)event.getSource()).getId());

		//Convert tile to i,j
		String[] parts = dest.split(",");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		//Tile converted to i,j format to be used with the board 2d arary.
		Integer i = Integer.parseInt(part1);
		Integer j = Integer.parseInt(part2);



		ImageView chosenBlackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/chosenBlackSoldier.png")));
		chosenBlackSoldier.setFitHeight(45);
		chosenBlackSoldier.setFitWidth(45);
		ImageView chosenWhiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/chosenWhiteSoldier.png")));
		chosenWhiteSoldier.setFitHeight(45);
		chosenWhiteSoldier.setFitWidth(45);
		ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
		blackSoldier.setFitHeight(45);
		blackSoldier.setFitWidth(45);
		ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
		whiteSoldier.setFitHeight(45);
		whiteSoldier.setFitWidth(45);
		ImageView possibleMove = new ImageView(new Image(getClass().getResourceAsStream("/Resources/possibleMove.png")));
		possibleMove.setFitHeight(45);
		possibleMove.setFitWidth(45);
		ImageView chosenBlackQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/chosenBlackQueen.png")));
		chosenBlackQueen.setFitHeight(45);
		chosenBlackQueen.setFitWidth(45);
		ImageView chosenWhiteQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/chosenWhiteQueen.png")));
		chosenWhiteQueen.setFitHeight(45);
		chosenWhiteQueen.setFitWidth(45);
		ImageView blackQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackQueen.png")));
		blackQueen.setFitHeight(45);
		blackQueen.setFitWidth(45);
		ImageView whiteQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteQueen.png")));
		whiteQueen.setFitHeight(45);
		whiteQueen.setFitWidth(45);




		Tile current = new Tile(i, j);
		Soldier s = gameController.getTileContent(current);
		Color color = gameController.getTurn();



		tl.setOnAction(e -> {
			direction = "TL";
			queenArrows.setVisible(false);
			possibleQueen = gameController.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );
		tr.setOnAction(e -> {
			direction = "TR";
			queenArrows.setVisible(false);
			possibleQueen = gameController.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );
		bl.setOnAction(e -> {
			direction = "BL";
			queenArrows.setVisible(false);
			possibleQueen = gameController.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );
		br.setOnAction(e -> {
			direction = "BR";
			queenArrows.setVisible(false);
			possibleQueen = gameController.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );


		//Current player had a killstreak and missed it (by clicking on oposition piece) - > SwapTurns.
		//s!=null means this click is on a peice in the first place, AND if the turn is locked by a red tile dont switch turn (Red Tile overrides kill streak)
		if(s!=null && color != s.getColor() && lockedForStreak && !lockedForRedTile && !lockedForBlue) {
			SwapTurn();
			System.out.println(color.toString() + " - You Missed yourself a KillStreak Genius!");
			streakLabel.setText(color.toString() + " - You Missed yourself a KillStreak Genius!");
			color = gameController.getTurn();
			clickedSoldier = null;
			possible = null;
			possibleQueen = null;
			streakLabel.setVisible(true);
			lockedForStreak = false;
		}



		//*Kill streak, It's the killstreak owner turn unless they miss it an opposition soldier/queen is clicked, turn gets swapped automatically, in other words
		//*Current turn can only select & move killstreak piece, and the opposition can select and move any of their pieces (current killstreak owner missed their killstreak).


		if(color==Color.Black) { //Black's turn (not in the middle of a killstreak)
			System.out.println("Black Plays!!");
			blackTurn(i , j , s , currentTile , blackSoldier, chosenBlackSoldier, blackQueen,  chosenBlackQueen) ; 

		}
		else if(color==Color.White) { //White's turn (not in the middle of a killstreak)
			System.out.println("White Plays!!");
			whiteTurn( i, j,s, currentTile, whiteSoldier, chosenWhiteSoldier, whiteQueen, chosenWhiteQueen);

		}
	}


	public void blackTurn( int i , int j,Soldier s ,Button currentTile, ImageView blackSoldier , ImageView chosenBlackSoldier , ImageView blackQueen, ImageView chosenBlackQueen ) {
		// the colors switch
		//the timer Restarts 
		// allow the tiles of the turn to play 
		// generate Colored Tiles

		int blackSoldiers = gameController.getSoldiers();
		int blackQueens = gameController.getQueens();

		System.out.println(blackSoldiers + " now queens: "+ blackQueens + "   RIGHT???????");


		InvalidMove = true;
		Tile current = new Tile(i, j);

		//-------------------- This is a revive click (Blue tile revive).
		if(lockedForBlue && s==null) {
			if(gameController.ressurectSoldier(2, current)) {
				occupiedTilesOriginalColor(scene);
				ClearColoredTiles(scene);
				SwapTurn();
				flag=0;
				blueTile = null;
				lockedForBlue = false;
				InvalidMove = false;
			}else
				System.out.println("Invalid Position");

		}

		//Generate tiles.
		if(flag == 0 ) {
			yellowTiles = GenerateYellowTiles(scene);
			flag++;
			redTile = GenerateRedTiles(scene, Color.Black);
			if(blackSoldiers ==2 && blackQueens == 1 && !lockedForBlue)
				blueTile = GenerateBlueTile(scene);
		}

		//-------------------- This is a click after selection for a move (current tile is empty).
		if( !lockedForBlue && s==null) { //IF current Tile doesn't have a soldier and it's not a blue tile revive click.
			if(possible==null && possibleQueen == null)   //If possible is null then no soldier was selected before.
				System.out.println("Please click a black Soldier!");

			else if(clickedSoldier!=null){

				//Get previous Soldier and previous Tile (in String).
				String prev = tilesBoardMap.get(clickedSoldier);
				//Convert tile to i,j
				int prevSoldierNum = gameController.getSoldierNumber(prev);
				Tile prevT  = gameController.getTile(prev);
				Soldier prevS = gameController.getSoldier(prev);


				//Move Soldier
				if(prevSoldierNum==2) 
					moveSoldier(i,j, s, prevS, prevT);

				//Move Queen
				else if(prevSoldierNum==22) 
					moveQueen (i,  j, s, prevS, prevT);
			}

			//Move was Valid.
			if(!InvalidMove) {
				refreshBoard(gameController.game,scene, root);
				if(lockedForBlue)
					possibleRes(scene, Color.Black);
				Button b = getButtonById(currentTile.getId());
				clickedSoldier = b.getId();
				if(!lockedForStreak && !lockedForRedTile)  //Only if there is no killStreak and no redTile  do we want clickedSoldier  to be null (Switch turn) ,otherwise we want it to refer to the killStreak Piece, (see next part of the method)
					clickedSoldier = null;
				if(lockedForRedTile) {
					if(gameController.getBoard()[i][j]==2) {
						b.setGraphic(chosenBlackSoldier);
						yellowTiles = GenerateYellowTiles(scene);
						flag++;
						redTile = GenerateRedTiles(scene, Color.Black);
					} //If it's a queen, no need to mark it as chosen.
				}

			}

		}//End of move click 

		//-------------------- This click is a selections click (to click a soldier (current tile has a soldier); not to move).
		else if(  !lockedForBlue && s.getColor().equals(Color.Black)) { //Tile does have a Soldier/Queen
			Button b = getButtonById(currentTile.getId());
			//*change selection icon
			refreshBoard(gameController.game, scene, root);

			////Soldier Streak handling. (This is basically to show selection icon in-game when a streak is going)
			///in the case of a queen streak, we don't want to show a selection icon (because queen selection needs arrows) -
			///Thus in a queen streak situation it will be handeled like a normal queen selection (as opposed to a streak soldier selection,
			/// where we want it to be handeled differently - selection on current streak soldier).
			boolean queenStreak = false;
			if(clickedSoldier!=null &&( lockedForStreak || lockedForRedTile)) { //Click streak (Kill streak or red Tile streak).
				Button last = getButtonById(clickedSoldier);
				String prev = tilesBoardMap.get(clickedSoldier);
				String curr = tilesBoardMap.get(b.getId());
				if(prev==curr) {
					if(gameController.getBoard()[i][j]==2) {
						b.setGraphic(chosenBlackSoldier);
					}else if(gameController.getBoard()[i][j]==22) {
						queenStreak = true;
					}
				}else
					System.out.println("Select the killStreak Piece!");
			}
			////Soldier Streak Over.

			boolean locked =false;
			if(lockedForStreak || lockedForRedTile)
				locked =true;

			if(!locked || (locked && queenStreak)) {//Select Soldier/Queen
				if(clickedSoldier==null || queenStreak)  //This is the first click (Selection)
					firstSelectionBlack(i,j,b,blackSoldier,chosenBlackSoldier,blackQueen,chosenBlackQueen);

				else  //Change selection (Previously clicked on a piece)
					changeSelectionBlack(i,j,b,blackSoldier,chosenBlackSoldier,blackQueen,chosenBlackQueen);

				///For the queen (possibleQueen) we get them in the next click, we only need record the selected direction in this click.
				////*get Soldier's possible moves
				possible = gameController.getPossibleMovesForSoldier(s, Color.Black);
				if(possible!=null) {
					for (Tile tile : possible) {
						String possibleTile = tile.getX()+","+tile.getY();
						String check = null;
						String key = null;
						for (String ks : tilesBoardMap.keySet()) {
							check = tilesBoardMap.get(ks);
							if(check!=null) {
								if(check.equals(possibleTile)) {
									key = ks;
									break;
								}
							}
						}
					}
				}
			}//End of Selection.


			//----- Wrong input
		}else if( !lockedForBlue && s.getColor().equals(Color.White))
			System.out.println("White Soldier clicked!");

	}// End of BlackTurn method.



	/*Similar to blackTurn - For more comments see SwitchTurntoBlack*/
	public void whiteTurn( int i , int j ,Soldier s , Button currentTile,ImageView whiteSoldier , ImageView chosenWhiteSoldier, ImageView whiteQueen, ImageView chosenWhiteQueen) {

		int whiteSoldiers = gameController.getSoldiers();
		int whiteQueens = gameController.getQueens();

		// the colors switch
		//the timer Restarts 
		// allow the tiles of the turn to play 
		// generate Colored Tiles 


		//Tile current = new Tile(i, j);
		InvalidMove = true;
		Tile current = new Tile(i, j);

		//-------------------- This is a revive click (Blue tile revive).
		if(lockedForBlue && s==null) {
			if(gameController.ressurectSoldier(1, current)) {
				System.out.println("Revived");
				SwapTurn();
				flag=0;
				blueTile = null;
				lockedForBlue = false;
				InvalidMove = false;
			}else
				System.out.println("Invalid Position");

		}

		//Generate tiles.
		if(flag == 0 ) {
			yellowTiles = GenerateYellowTiles(scene);
			flag++;
			redTile = GenerateRedTiles(scene, Color.White);
			if(whiteSoldiers ==2 && whiteQueens == 1 && !lockedForBlue)
				blueTile = GenerateBlueTile(scene);
		}

		//-------------------- This is a click after selection for a move (current tile is empty).
		if( !lockedForBlue && s==null) {  //IF current Tile doesn't have a soldier and it's not a blue tile revive click.
			if(possible==null && possibleQueen == null) //If possible is null then no soldier was selected before.
				System.out.println("Please click a white  Soldier!");
			else if(clickedSoldier!=null){
				String prev = tilesBoardMap.get(clickedSoldier);

				String[] parts2= prev.split(",");
				String part21 = parts2[0]; 
				String part22 = parts2[1]; 
				//Tile converted to i,j format to be used with the board 2d arary.
				Integer desti = Integer.parseInt(part21);
				Integer destj = Integer.parseInt(part22);
				Tile prevT = new Tile(desti, destj);
				Soldier prevS = gameController.getTileContent(prevT);

				//Move Soldier
				if(prevS.getSoldierNumber()==1) 
					moveSoldier(i,j, s, prevS, prevT);

				//Move Queen
				else if(prevS.getSoldierNumber()==11) 
					moveQueen (i,  j, s, prevS, prevT);
			}

			//Move was Valid.
			if(!InvalidMove) {
				refreshBoard(gameController.game,scene, root);
				if(lockedForBlue)
					if(lockedForBlue)
						possibleRes(scene, Color.White);
				Button b = getButtonById(currentTile.getId());
				//				b.setGraphic(chosenBlackSoldier);
				clickedSoldier = b.getId();
				if(!lockedForStreak && !lockedForRedTile)  //Only if there is no killStreak and no redTile  do we want clickedSoldier  to be null (Switch turn) ,otherwise we want it to refer to the killStreak Piece, (see next part of the method)
					clickedSoldier = null;
				if(lockedForRedTile) {
					if(gameController.getBoard()[i][j]==1) {
						b.setGraphic(chosenWhiteSoldier);
						yellowTiles = GenerateYellowTiles(scene);
						flag++;
						redTile = GenerateRedTiles(scene, Color.White);
					} //If it's a queen, no need to mark it as chosen.
				}

			}

		}//End of move click 

		//-------------------- This click is a selections click (to click a soldier (current tile has a soldier); not to move).
		else if( s.getColor().equals(Color.White)) { //Tile does have a Soldier/Queen//Tile does have a Soldier/Queen
			Button b = getButtonById(currentTile.getId());
			//*change selection icon
			refreshBoard(gameController.game, scene, root);

			////Soldier Streak handling. (This is basically to show selection icon in-game when a streak is going)
			///in the case of a queen streak, we don't want to show a selection icon (because queen selection needs arrows) -
			///Thus in a queen streak situation it will be handeled like a normal queen selection (as opposed to a streak soldier selection,
			/// where we want it to be handeled differently - selection on current streak soldier).
			boolean queenStreak = false;
			if(clickedSoldier!=null &&  (lockedForStreak || lockedForRedTile)) { //Click streak (Kill streak or red Tile streak).
				Button last = getButtonById(clickedSoldier);
				String prev = tilesBoardMap.get(clickedSoldier);
				String curr = tilesBoardMap.get(b.getId());
				if(prev==curr) {
					if(gameController.getBoard()[i][j]==1) {
						b.setGraphic(chosenWhiteSoldier);
					}else if(gameController.getBoard()[i][j]==11) {
						queenStreak = true;
					}
				}else
					System.out.println("Select the killStreak Piece!");
			}
			////Soldier Streak Over.

			boolean locked =false;
			if(lockedForStreak || lockedForRedTile)
				locked =true;

			if(!locked || (locked && queenStreak)) { 
				if(clickedSoldier==null  || queenStreak)  //This is the first click (Selection)
					firstSelectionWhite(i, j, b, whiteSoldier, chosenWhiteSoldier, whiteQueen, chosenWhiteQueen);
				else //Change selection (Previously clicked on a piece)
					changeSelectionWhite(i, j, b, whiteSoldier, chosenWhiteSoldier, whiteQueen, chosenWhiteQueen);

				///For the queen (possibleQueen) we get them in the next click, we only need record the selected direction in this click.
				////*get Soldier's possible moves
				possible = gameController.getPossibleMovesForSoldier(s, Color.White);
				if(possible!=null) {
					for (Tile tile : possible) {
						String possibleTile = tile.getX()+","+tile.getY();
						String check = null;
						String key = null;
						for (String ks : tilesBoardMap.keySet()) {
							check = tilesBoardMap.get(ks);
							if(check!=null) {
								if(check.equals(possibleTile)) {
									key = ks;
									break;
								}
							}
						}
					}
				}
			}//End of Selection.

			//----- Wrong input
		}else if(s.getColor().equals(Color.Black))
			System.out.println("Black Soldier clicked!");


	}//End of WhiteTurn Method.



	public static void occupiedTilesOriginalColor(Scene s) {

		for (Tile tile :gameController.getBoardObj().getOccupiedTiles()) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//	System.out.println(key);
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #000000;");;
						((Button) s.lookup("#"+key)).setGraphic(null);
						//break;
					}
				}
			}
		}
	}






	ImageView yellowTileImage = new ImageView(new Image(getClass().getResourceAsStream("/Resources/yellowTile.png"))); 
	ImageView greenTileImage = new ImageView(
			new Image(getClass().getResourceAsStream("/Resources/greenTile.png"))); 
	ImageView blueTileImage = new ImageView(
			new Image(getClass().getResourceAsStream("/Resources/blueTile.png"))); 
	ImageView redTileImage = new ImageView(
			new Image(getClass().getResourceAsStream("/Resources/redTile.png"))); 


	//public Tile getTileFromButton ()

	public void refreshBoard(Game game, Scene scene, Parent root) {

		int whiteAliveCout = 0 ; 
		int blackAliveCount = 0 ;


		int [][]board = Controller.gameController.getBoard();
		gameController.getBoardObj().printBoard();

		for(int i = 0; i<=7; i++) {
			for(int j = 0; j<=7; j++) {


				ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
				blackSoldier.setFitHeight(45);
				blackSoldier.setFitWidth(45);
				ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
				whiteSoldier.setFitHeight(45);
				whiteSoldier.setFitWidth(45);
				ImageView blackQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackQueen.png")));
				blackQueen.setFitHeight(45);
				blackQueen.setFitWidth(45);
				ImageView whiteQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteQueen.png")));
				whiteQueen.setFitHeight(45);
				whiteQueen.setFitWidth(45);

				buildTilesBoardMap();

				String dest = i+","+j;
				String check = null;
				String key = null;
				for (String ks : tilesBoardMap.keySet()) {

					check = tilesBoardMap.get(ks);

					if(check!=null) {
						if(check.equals(dest)) {
							key = ks;
							break;
						}
					}
				}


				if(board[i][j]==1) {
					((Button) scene.lookup("#"+key)).setGraphic(whiteSoldier);
					whiteAliveCout++ ; 
				}else if(board[i][j]==2) { 
					((Button) scene.lookup("#"+key)).setGraphic(blackSoldier);
					blackAliveCount++; 
				}else if(board[i][j]==0 && key!=null ) {
					((Button) scene.lookup("#"+key)).setGraphic(null);

				}else if (board[i][j]==11) { 
					((Button) scene.lookup("#"+key)).setGraphic(whiteQueen);
					whiteAliveCout++ ; 

				}else if (board[i][j]==22) {
					((Button) scene.lookup("#"+key)).setGraphic(blackQueen);
					blackAliveCount++; 
				}


			}

		}

		//Generate Blue Tile - Maybe ! 

		//-----------------------Count Your Loses ! 
		if(this.deadBlackv !=null && this.deadwhitev !=null) {
			this.deadwhitev.getChildren().clear();
			this.deadBlackv.getChildren().clear();


			for(int i=0 ; i< 12 -whiteAliveCout ; i++) {

				ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
				whiteSoldier.setFitHeight(45);
				whiteSoldier.setFitWidth(45);
				this.deadwhitev.getChildren().add(whiteSoldier);
			}

			for(int i=0 ; i< 12 -blackAliveCount ; i++) {
				ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
				blackSoldier.setFitHeight(45);
				blackSoldier.setFitWidth(45);
				this.deadBlackv.getChildren().add(blackSoldier);
			}
		}

	}

	public int getWinnerPoints() {
		if(gameController.winner().equals(gameController.getPlayer(Color.Black)))
			return gameController.getPlayerPoints(Color.Black);
		else
			return gameController.getPlayerPoints(Color.White);
	}
	public void boardOFF() {
		SysData.getInstance().addWinnerToLeaderboard(new Winner(gameController.winner(),getWinnerPoints()," "));
		SysData.getInstance().writeWinnersIntoFile();
		timeline.stop();
		tile1.setOnMouseClicked(null);
		tile2.setOnMouseClicked(null);
		tile3.setOnMouseClicked(null);
		tile4.setOnMouseClicked(null);
		tile5.setOnMouseClicked(null);
		tile6.setOnMouseClicked(null);
		tile7.setOnMouseClicked(null);
		tile8.setOnMouseClicked(null);
		tile9.setOnMouseClicked(null);
		tile10.setOnMouseClicked(null);
		tile11.setOnMouseClicked(null);
		tile12.setOnMouseClicked(null);
		tile13.setOnMouseClicked(null);
		tile14.setOnMouseClicked(null);
		tile15.setOnMouseClicked(null);
		tile16.setOnMouseClicked(null);
		tile17.setOnMouseClicked(null);
		tile18.setOnMouseClicked(null);
		tile19.setOnMouseClicked(null);
		tile20.setOnMouseClicked(null);
		tile21.setOnMouseClicked(null);
		tile22.setOnMouseClicked(null);
		tile23.setOnMouseClicked(null);
		tile24.setOnMouseClicked(null);
		tile25.setOnMouseClicked(null);
		tile26.setOnMouseClicked(null);
		tile27.setOnMouseClicked(null);
		tile28.setOnMouseClicked(null);
		tile29.setOnMouseClicked(null);
		tile30.setOnMouseClicked(null);
		tile31.setOnMouseClicked(null);
		tile32.setOnMouseClicked(null);

	}
	public void clearBoard(Game game, Scene scene, Parent root) throws IOException {

		for(int i = 0; i<=7; i++) {
			for(int j = 0; j<=7; j++) {
				for (String ks : tilesBoardMap.keySet()) {
					((Button) scene.lookup("#"+ks)).setGraphic(null);
				}
			}
		}

	}


	//-------------------------


	public ArrayList<Tile> getEmptyNotColoredTiles(Scene s) {
		//Clear all Empty Tiles 
		ArrayList<Tile> toReturn = new ArrayList<>() ; 
		for (Tile tile :gameController.getBoardObj().getEmptyTiles()) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//						System.out.println(key);
						if(((Button) s.lookup("#"+key)).getStyle().equals(("-fx-background-color: #000000;"))){
							// the Tile is empty and Black
							toReturn.add(tile) ;
						}
						//break;
					}
				}
			}
		}
		//System.out.println("method empty not colored , returning Empty Array ! ");
		return toReturn  ; 

	}




	//---------------------------

	public static void ClearColoredTiles(Scene s) {
		//Clear all Empty Tiles 
		for (Tile tile : gameController.getBoardObj().getEmptyTiles()) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//						System.out.println(key);
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #000000;");;
						((Button) s.lookup("#"+key)).setGraphic(null);
						//break;
					}
				}
			}
		}

	}
	//------------------------------colored tiles  ;
	public Tile  GenerateRedTiles( Scene s, Model.Color Nowplaying )  {
		// color 3 random Empty tiles

		Tile redTile = gameController.generateRedTile(Nowplaying,getEmptyNotColoredTiles(scene))  ; 
		if(redTile != null ) { 
			String possibleTile = redTile.getX()+","+redTile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//						System.out.println("should be red  :: "+key);
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #ed492f;");;
						return redTile;

					}
				}
			}
		}


		return null;

	}


	//-----------------------green Tile


	public Tile GenerateGreenTiles( Scene s, Model.Color Nowplaying )  {
		// color 1 random Empty tile
		Tile greenTile = gameController.generateGreenTile(Nowplaying, getEmptyNotColoredTiles(scene)) ; 
		if(greenTile != null ) { 
			String possibleTile = greenTile.getX()+","+greenTile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #7EB77C;");;
						return greenTile;
					}
				}
			}
		}
		return null;
	}



	//-------------------------

	public void GenerateOrangeTiles( Scene s, Model.Color Nowplaying )  {

		// color 3 random Empty tiles
		for (Tile tile : gameController.generateOrangeTiles(Nowplaying) ) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//						System.out.println("should be orange  :: "+key);
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #e38d0b;");;
						break;
					}
				}
			}
		}

	}


	public void stackDeadsoldeirs() {

	}


	public ArrayList<Tile> GenerateYellowTiles(Scene s)  {

		ArrayList<Tile> tilesToReturn = new ArrayList<Tile>();
		Button b = null;
		// color 3 random Empty tiles
		for (Tile tile : gameController.generateYellowTiles(getEmptyNotColoredTiles(scene))) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						ImageView yellow = new ImageView(new Image(getClass().getResourceAsStream("/Resources/yellowTile.png")));
						yellow.setFitHeight(45);
						yellow.setFitWidth(45);
						key = ks;
						//	System.out.println(key);
						if(((Button) s.lookup("#"+key)).getStyle().equals("-fx-background-color: #000000;")) {
							System.out.println("the "+key+" is Black ! ");

							((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #FFFF00;");
							((Button) s.lookup("#"+key)).setGraphic(yellow);
							tilesToReturn.add(tile);
							break;
						}
					}
				}
			}
		}
		return tilesToReturn;


	}

	public Tile GenerateBlueTile( Scene s)  {

		// color 1 random Empty tile
		System.out.println(gameController.game == null);
		System.out.println(gameController.getTurn()+ " for Generating Blue Tile ");
		Tile BlueTile = gameController.generateBlueTile(gameController.getTurn() ,getEmptyNotColoredTiles(scene)) ; 
		if(BlueTile != null ) { 
			String possibleTile = BlueTile.getX()+","+BlueTile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//                        System.out.println("should be red  :: "+key);
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #2EB9FF;");;
						System.out.println("HERE IS THE BLUE TILE OK????: " + BlueTile);
						blueTile = BlueTile;
						return BlueTile;
					}
				}
			}
		}
		return null;


	}

	public void possibleRes( Scene s, Color c)  {

		// color 1 random Empty tile
		System.out.println(gameController.game == null);

		for (Tile tile :gameController.getBoardObj().getEmptyTiles()) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						int obj = -1;
						obj = (c.equals(Color.Black)) ? 2:  1;
						if(gameController.checkIfLegalPosition(obj, tile)) 
							((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #C0C0C0;");;
							//break;
					}
				}
			}
		}

	}


	public Button getButtonById(String id) {
		//Button toReturn =null;
		switch(id){
		case "tile1" : return tile1;
		case "tile2" : return tile2;
		case "tile3" : return tile3;
		case "tile4" : return tile4;
		case "tile5" : return tile5;
		case "tile6" : return tile6;
		case "tile7" : return tile7;
		case "tile8" : return tile8;
		case "tile9" : return tile9;
		case "tile10" : return tile10;
		case "tile11" : return tile11;
		case "tile12" : return tile12;
		case "tile13" : return tile13;
		case "tile14" : return tile14;
		case "tile15" : return tile15;
		case "tile16" : return tile16;
		case "tile17" : return tile17;
		case "tile18" : return tile18;
		case "tile19" : return tile19;
		case "tile20" : return tile20;
		case "tile21" : return tile21;
		case "tile22" : return tile22;
		case "tile23" : return tile23;
		case "tile24" : return tile24;
		case "tile25" : return tile25;
		case "tile26" : return tile26;
		case "tile27" : return tile27;
		case "tile28" : return tile28;
		case "tile29" : return tile29;
		case "tile30" : return tile30;
		case "tile31" : return tile31;
		case "tile32" : return tile32;
		}
		return null;
	}



	public boolean isTileEmpty(Scene s , Button btn) {
		ArrayList<Button> toCheck = new ArrayList<>() ; 
		for (Tile tile :gameController.getBoardObj().getEmptyTiles()) {
			String possibleTile = tile.getX()+","+tile.getY();
			String check = null;
			String key = null;
			for (String ks : tilesBoardMap.keySet()) {
				check = tilesBoardMap.get(ks);
				if(check!=null) {
					if(check.equals(possibleTile)) {
						key = ks;
						//						System.out.println(key);
						toCheck.add(((Button) s.lookup("#"+key)));
						//break;
					}
				}
			}
		}

		if(toCheck.contains(btn)) {
			//			System.out.println("the tile/Button  is Empty :  ");
			return true ; // is Empty 
		}


		return false ; 
	}


	@FXML
	void settingBtnClicked(ActionEvent event) throws Exception{
		this.timeline.stop();
		settings.display();
		this.live_pausedlbl.setText("Paused");

		settings.showWin();
	}





	void OverAllTimer() {

		boolean x=true;
		long displayMinutes=0;
		//long secondspassed=0 ; 
		long starttime=System.currentTimeMillis();
		System.out.println("Timer:");
		while(x)
		{

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long timepassed=System.currentTimeMillis()-starttime;
			long  secondspassed=timepassed/1000;
			if(secondspassed==60)
			{
				secondspassed=0;
				starttime=System.currentTimeMillis();
			}
			if((secondspassed%60)==0)
				displayMinutes++;

			System.out.println( displayMinutes+" : "+secondspassed);


		}
	}


	public  void SwapTurn() {
		gameController.swapTurn(); ///////////////Generating Colored Tiles Here
		updateTurnLabels();
		setTimmer();
		lockedForRedTile = false;
		lockedForStreak = false;
	}

	public void updateTurnLabels() {
		if(gameController.getTurn().equals(Color.White)) {
			p2.setFont(Font.font("System",FontWeight.BOLD, FontPosture.REGULAR, 20));
			p2.setTextFill(javafx.scene.paint.Color.DARKORANGE);
			p1.setFont(Font.font("System",FontWeight.NORMAL, FontPosture.REGULAR, 16));
			p1.setTextFill(javafx.scene.paint.Color.WHITE);
		}else {
			p1.setFont(Font.font("System",FontWeight.BOLD, FontPosture.REGULAR, 20));
			p1.setTextFill(javafx.scene.paint.Color.DARKORANGE);
			p2.setFont(Font.font("System",FontWeight.NORMAL, FontPosture.REGULAR, 16));
			p2.setTextFill(javafx.scene.paint.Color.WHITE);
		}
	}

	public void darkTheme(boolean isOn) {
		if(isOn) {
			headerPane.setStyle("-fx-background-color : #201C1C");
			paneBoard.setStyle("-fx-background-color :  #3E3E3E ;");

		}else {
			headerPane.setStyle("-fx-background-color :  #630000;");
			paneBoard.setStyle("-fx-background-color :   #6C3131 ;");

		}
	}

	public void moveSoldier (Integer i, Integer j, Soldier s, Soldier prevS, Tile prevT) {
		if(	(gameController.getSoldierNumber(prevS)==2 && gameController.getTurn()==Color.Black) || 
				(gameController.getSoldierNumber(prevS)==1 && gameController.getTurn()==Color.White)) {

			for (Tile t : possible) {
				int coordinateI = gameController.tileGetX(t);
				int coordinateJ = gameController.tileGetY(t);
				if(i==coordinateI && j == coordinateJ){
					InvalidMove=false;

					gameController.moveSoldier(prevS, t, possible);

					//check if a killstreak is available.
					boolean killed = false;
					if(gameController.isGameOver())
						isGameOver = true;
					ArrayList<Tile> kills = gameController.getKills(possible, prevT);
					Soldier afterKill;
					if(kills!=null && !kills.isEmpty() ) { //if this move was a kill, then we need to check for a killstreak.
						killed = true;
						afterKill = gameController.getTileContent(t);
						if(afterKill != null)
							possible = gameController.getKilStreakForSoldier(afterKill);
						else {
							possible.clear();
							possible = null;
						}
					}

					if(possible!=null && !possible.isEmpty() && killed)  //There is a streak
						lockedForStreak = true;
					else 
						lockedForStreak = false;

					if(greenTile != null && greenTile.equals(t)) {
						if(gameController.getTurn()==Color.Black)
							gameController.updatePlayerPoints(50, Color.Black);
						else
							gameController.updatePlayerPoints(50, Color.White);
						greenTile = null;
					}
					else if(redTile!=null && redTile.equals(t)) {
						lockedForRedTile = true;
						lockedForStreak = false;
						afterKill = gameController.getTileContent(t);
						if(afterKill!=null) {
							if(gameController.getTurn()==Color.Black)
								possible = gameController.getPossibleMovesForSoldier(afterKill, Color.Black);
							else 
								possible = gameController.getPossibleMovesForSoldier(afterKill, Color.White);
						}
						if(possible!=null && !possible.isEmpty()) { //if there are no possible moves dont lock.
							lockedForRedTile = true;
						}
						else {
							lockedForRedTile = false;
						}
					}// End red Tile
					else if(blueTile != null && blueTile.equals(t)) {
						lockedForBlue = true;
						flag++;
					}
					else if(yellowTiles.contains(t) && !isGameOver) {
						//Swapping turn occures after question is answered.
						quesPop.question = SysData.getInstance().randomQuestion();
						quesPop.display();
						SysData.getInstance().questionIsShown(quesPop.question);
					}
					if(redTile==null || !redTile.equals(t))
						lockedForRedTile = false;

					if(!lockedForStreak && !lockedForRedTile && !yellowTiles.contains(t) && !lockedForBlue) 
						SwapTurn();

					p1Points.setText(String.valueOf(gameController.getPlayerPoints(Color.Black))); 
					p2Points.setText(String.valueOf(gameController.getPlayerPoints(Color.White))); 
					flag=0;
					//Timer Related - 
					occupiedTilesOriginalColor(scene) ; 
					ClearColoredTiles(scene);
					blueTile = null;

					if(gameController.isGameOver()) {
						winnerLabel.setVisible(true);
						boardOFF();
						winnerLabel.setText(gameController.winner() + " Wins!");
						winnerWin.winnerLabel.setText(gameController.winner()+ "  Wins .");
						winnerWin.display();

					}

					System.out.println("Now It's Black's turn");
					break;
				}
			}




		}


	}


	public void moveQueen (Integer i, Integer j, Soldier s, Soldier prevS, Tile prevT) {

		if(possibleQueen!=null) { //Move not in a Queen's  killstreak.
			for (Tile t2 : possibleQueen.keySet()) {  //a tile was selected before, and current tile is used to make the move.
				int coordinateI2 = gameController.tileGetX(t2);
				int coordinateJ2 = gameController.tileGetY(t2);
				if(i==coordinateI2 && j == coordinateJ2){
					InvalidMove = false;

					Queen prevQ =(Queen) prevS;
					//System.out.println(prevS);

					Soldier k = possibleQueen.get(t2);
					boolean killed = false;
					gameController.moveQueen(prevQ, t2, possibleQueen);
					Queen afterKill;
					if(gameController.isGameOver())
						isGameOver = true;
					if(k!=null) { //if this move was a kill, then we need to check for a killstreak.
						killed = true;
						afterKill = (Queen) gameController.getTileContent(t2);
						if(afterKill!=null)
							possibleQueen = gameController.getPriorityKills(afterKill);
						else {
							possibleQueen.clear();
							possibleQueen = null;
						}
					}
					if(possibleQueen!=null && !possibleQueen.isEmpty() && killed) //Kill streak.
						lockedForStreak = true;
					else
						lockedForStreak = false;


					if(greenTile != null && greenTile.equals(t2)) {
						if(gameController.getTurn()==Color.Black)
							gameController.updatePlayerPoints(50, Color.Black);
						else
							gameController.updatePlayerPoints(50, Color.White);
						greenTile = null;
					}
					else if(redTile!=null && redTile.equals(t2)) {
						lockedForRedTile = true;
						lockedForStreak = false;
					}else if(blueTile != null && blueTile.equals(t2)) {
						lockedForBlue = true;
						flag++;
					}
					else if(yellowTiles.contains(t2) && !isGameOver) {
						quesPop.question = SysData.getInstance().randomQuestion();
						quesPop.display();
						SysData.getInstance().questionIsShown(quesPop.question);
					}

					if(redTile==null || !redTile.equals(t2))
						lockedForRedTile = false;

					if(!lockedForStreak && !lockedForRedTile && !yellowTiles.contains(t2) && !lockedForBlue) 
						SwapTurn();


					p1Points.setText(String.valueOf(gameController.getPlayerPoints(Color.Black))); 
					p2Points.setText(String.valueOf(gameController.getPlayerPoints(Color.White))); 
					flag=0;
					//Timer Related - 
					occupiedTilesOriginalColor(scene) ; 
					ClearColoredTiles(scene);
					blueTile = null;
					if(gameController.isGameOver()) {
						winnerLabel.setVisible(true);
						boardOFF();
						winnerLabel.setText(gameController.winner() + " Wins!");
						winnerWin.winnerLabel.setText(gameController.winner() + "  Wins .");
						winnerWin.display();

					}
					System.out.println("Now It's White's turn");
					break;
				}
			}
		}

	}



	public void firstSelectionBlack(Integer i,Integer j,Button b, ImageView blackSoldier , ImageView chosenBlackSoldier , ImageView blackQueen, ImageView chosenBlackQueen) {

		if(gameController.getBoard()[i][j]==2) {
			b.setGraphic(chosenBlackSoldier);
			queenArrows.setVisible(false);
		}
		if(gameController.getBoard()[i][j]==22) {
			queenArrows.setVisible(true);
			b.setGraphic(chosenBlackQueen);
			Bounds boundsInScene = b .localToScene(b.getBoundsInLocal());
			queenArrows.setTranslateX(boundsInScene.getMinX()-25);
			queenArrows.setTranslateY(boundsInScene.getMinY()-43);
		}
		clickedSoldier = b.getId();
	}

	public void firstSelectionWhite(Integer i,Integer j,Button b, ImageView whiteSoldier , ImageView chosenWhiteSoldier , ImageView whiteQueen, ImageView chosenWhiteQueen) {

		if(gameController.getBoard()[i][j]==1) {
			b.setGraphic(chosenWhiteSoldier);
			queenArrows.setVisible(false);
		}
		if(gameController.getBoard()[i][j]==11) {
			Bounds boundsInScene = b .localToScene(b.getBoundsInLocal());
			queenArrows.setTranslateX(boundsInScene.getMinX()-25);
			queenArrows.setTranslateY(boundsInScene.getMinY()-43);
			queenArrows.setVisible(true);
			b.setGraphic(chosenWhiteQueen);
		}
		clickedSoldier = b.getId();
	}

	public void changeSelectionBlack(Integer i,Integer j,Button b, ImageView blackSoldier , ImageView chosenBlackSoldier , ImageView blackQueen, ImageView chosenBlackQueen) {

		Button last = getButtonById(clickedSoldier);
		String prev = tilesBoardMap.get(clickedSoldier);

		String[] parts2= prev.split(",");
		String part21 = parts2[0]; 
		String part22 = parts2[1]; 
		//Tile converted to i,j format to be used with the board 2d arary.
		Integer desti = Integer.parseInt(part21);
		Integer destj = Integer.parseInt(part22);
		if(gameController.getBoard()[desti][destj]==2) {
			last.setGraphic(blackSoldier);
			queenArrows.setVisible(false);
		}
		if(gameController.getBoard()[desti][destj]==22) 
			last.setGraphic(blackQueen);

		if(gameController.getBoard()[i][j]==2) { //Change to Soldier piece
			queenArrows.setVisible(false);
			b.setGraphic(chosenBlackSoldier);
		}
		if(gameController.getBoard()[i][j]==22) { //Change to Queen piece

			queenArrows.setTranslateX(b.getLayoutX());
			queenArrows.setTranslateY(b.getLayoutY());
			b.setGraphic(chosenBlackQueen);
			queenArrows.setVisible(true);
			Bounds boundsInScene = b .localToScene(b.getBoundsInLocal());
			queenArrows.setTranslateX(boundsInScene.getMinX()-25);
			queenArrows.setTranslateY(boundsInScene.getMinY()-43);
		}
		clickedSoldier = b.getId();
	}


	public void changeSelectionWhite(Integer i,Integer j,Button b,  ImageView whiteSoldier , ImageView chosenWhiteSoldier , ImageView whiteQueen, ImageView chosenWhiteQueen) {

		Button last = getButtonById(clickedSoldier);
		String prev = tilesBoardMap.get(clickedSoldier);

		String[] parts2= prev.split(",");
		String part21 = parts2[0]; 
		String part22 = parts2[1]; 
		//Tile converted to i,j format to be used with the board 2d arary.
		Integer desti = Integer.parseInt(part21);
		Integer destj = Integer.parseInt(part22);
		if(gameController.getBoard()[desti][destj]==1) {
			last.setGraphic(whiteSoldier);
			queenArrows.setVisible(false);
		}
		if(gameController.getBoard()[desti][destj]==11)
			last.setGraphic(whiteQueen);
		if(gameController.game.getBoard().getBoard()[i][j]==1) {
			queenArrows.setVisible(false);
			b.setGraphic(chosenWhiteSoldier);
		}
		if(gameController.getBoard()[i][j]==11) {
			queenArrows.setVisible(true);
			queenArrows.setTranslateX(b.getLayoutX());
			queenArrows.setTranslateY(b.getLayoutY());
			Bounds boundsInScene = b .localToScene(b.getBoundsInLocal());
			queenArrows.setTranslateX(boundsInScene.getMinX()-25);
			queenArrows.setTranslateY(boundsInScene.getMinY()-43);
			b.setGraphic(chosenWhiteQueen);
		}
		clickedSoldier = b.getId();
	}


























}