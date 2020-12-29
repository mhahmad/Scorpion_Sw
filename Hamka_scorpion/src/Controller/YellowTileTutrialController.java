package Controller;



import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import Model.Board;
import Model.Color;
import Model.Game;
import Model.Level;
import Model.Queen;
import Model.Question;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
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


public class YellowTileTutrialController  extends Application implements Initializable{

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
    private Button ExitBtn;
    
   

    @FXML
    private Button YellowTileBtn;

    @FXML
    private Button RedTileBtn;

    @FXML
    private Button OrangeGreenTileBtn;

    @FXML
    private Button BlueTileBtn;
    
    
 
    @FXML
    private Button firstNote;

    @FXML
    private Button instructions;

   
    
    

    
    
	inGameSettings settings = new inGameSettings();;
    public static String p1Name = "p1";
    public static String p2Name ="p2";
	/* Buttons to display queen movements.*/
	Button tl = new Button();
	Button br = new Button();
	Button tr = new Button();
	Button bl = new Button();
	
	popupQuestion quesPop = new popupQuestion();

 
    //-----------------------------------
	public static HashMap<String, String> tilesBoardMap;
	public static String clickedSoldier = null;
	public static ArrayList<Tile> possible = null;
	public static HashMap<Tile, Soldier> possibleQueen = null;


	private int[][] startBoard = {
			{-1,0,-1,2,-1,0,-1,0},
			{0,-1,0,-1,2,-1,2,-1},
			{-1,2,-1,0,-1,0,-1,0},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{0,-1,1,-1,1,-1,0,-1},
			{-1,1,-1,0,-1,1,-1,1},
			{0,-1,0,-1,0,-1,0,-1}
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
	GridPane instruction = new GridPane();

	String direction = null;
	
	//private Game game  = new Game("White", "Black", startBoard);
	private Game game = new Game(p1Name, p2Name, startBoard); //Singletone changes to be in every method.
	
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		root = FXMLLoader.load(getClass().getResource("/View/YellowTileTutrial.fxml"));
		scene = new Scene(root);
		//FillBoard() ;
		//game.handTurn();
		
		stage.setScene(scene);
		stage.setResizable(false);
		//java.io.FileInputStream fis = new FileInputStream("/System/Library/CoreServices/loginwindow.app/Contents/Resources/LogOut.png");
		buildTilesBoardMap();
		refreshBoard(game, scene, root);
        stage.show();
		stage.setTitle("Hamka - Match");
         

		


	}
	



	public static void main(String[] arg) {
		launch(arg);
	}

	@Override
	public void initialize (URL arg0, ResourceBundle arg1) {
		
		    
	      //------------------------------
		buildTilesBoardMap();
	  
		this.instructions.setVisible(true);
		this.firstNote.setVisible(true);
		
		settings.exitBtn.setOnAction(e -> {
			
			((Stage)settings.exitBtn.getScene().getWindow()).close();

			Stage stage = (Stage)this.ExitBtn.getScene().getWindow();
			Parent toLoad;
			try {
				toLoad = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
				Scene scene = new Scene(toLoad);
				stage.setScene(scene);
				stage.centerOnScreen();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		this.tile11.setStyle("-fx-background-Color :#fff23d ");
		this.tile13.setStyle("-fx-background-Color :#fff23d ");
		this.tile14.setStyle("-fx-background-Color :#fff23d ");

		
		this.tile12.setOnMouseClicked(null);
		this.tile15.setOnMouseClicked(null);
		this.tile16.setOnMouseClicked(null);
		this.tile17.setOnMouseClicked(null);
		this.tile18.setOnMouseClicked(null);
		//((Button)scene.lookup("#tile11")).setStyle("-fx-background-Color :#fff23d ");
		
		
		
		
	    //------------------------------
		instruction.setVisible(false);
		instruction.setVgap(2);
		instruction.setHgap(2);
		instruction.setMinHeight(20);
		instruction.setMinWidth(20);
		
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
			instruction.setVisible(true);
		});
		paneBoard.getChildren().add(queenArrows);
		paneBoard.getChildren().add(instruction);

		//pane.setVisible(true);
		queenArrows.setTranslateX(10);
		queenArrows.setTranslateY(10);
		
		instruction.setTranslateX(10);
		instruction.setTranslateY(10);
		
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
		
		

		OrangeGreenTileBtn.setStyle(" -fx-background-radius : 5em ; -fx-background-color: linear-gradient(from 0px 0px to 210px 0px, green 0%, green 36.84%, orange 36.84%, orange 100%);");

//------------------------------------------------
		
		quesPop.nextBtn.setOnAction(e -> {
			quesPop.points.setText("");
			if(quesPop.nextBtn.getText().equals("Next")) {
				RadioButton rb = (RadioButton) quesPop.group.getSelectedToggle();

				if(SysData.getInstance().isQuestionAnsweredCorrectly(quesPop.question, rb.getText())) {
					rb.setStyle("-fx-background-color : #32CD32");
					quesPop.points.setTextFill(javafx.scene.paint.Color.FORESTGREEN);
					if(quesPop.question.getLevel().equals(Level.easy)) {
						quesPop.points.setText("You have earned 100 points");
						if(game.getTurn().equals(Color.Black))
							game.setblackPlayerPoints(game.getblackPlayerPoints() + 100);
						else
							game.setWhitePlayerPoints(game.getwhitePlayerPoints() + 100);
					}else if(quesPop.question.getLevel().equals(Level.medium)) {
						quesPop.points.setText("You have earned 200 points");
						if(game.getTurn().equals(Color.Black))
							game.setblackPlayerPoints(game.getblackPlayerPoints() + 200);
						else
							game.setWhitePlayerPoints(game.getwhitePlayerPoints() + 200);
					}else {
						quesPop.points.setText("You have earned 500 points");
						if(game.getTurn().equals(Color.Black))
							game.setblackPlayerPoints(game.getblackPlayerPoints() + 500);
						else
							game.setWhitePlayerPoints(game.getwhitePlayerPoints() + 500);
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
						if(game.getTurn().equals(Color.Black))
							game.setblackPlayerPoints(game.getblackPlayerPoints() - 250);
						else
							game.setWhitePlayerPoints(game.getwhitePlayerPoints() - 250);
					}else if(quesPop.question.getLevel().equals(Level.medium)) {
						quesPop.points.setText("You have lost 100 points");
						if(game.getTurn().equals(Color.Black))
							game.setblackPlayerPoints(game.getblackPlayerPoints() - 100);
						else
							game.setWhitePlayerPoints(game.getwhitePlayerPoints() - 100);
					}else {
						quesPop.points.setText("You have lost 50 points");
						if(game.getTurn().equals(Color.Black))
							game.setblackPlayerPoints(game.getblackPlayerPoints() - 50);
						else
							game.setWhitePlayerPoints(game.getwhitePlayerPoints() - 50);
					}

				}
				quesPop.nextBtn.setText("Close");
			}else {
				quesPop.window.close();
				
			}
		});
		
		
		
	}

	//	@FXML
	//	public Stage flip() {
	//	 Stage s = (Stage)tile1.getScene().getWindow();
	//	 return s ;
	//	}
	//	
	//	public void FillBoard() {
	//		
	//		try {
	//		for(int i = 0; i<=7; i++) {
	//			for(int j = 0; j<=7; j++) {
	//            ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/black_piece.png")));
	//            ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/black_piece.png")));
	//
	//    		String value = i + ","+j;
	//    		String key = tilesBoardMap.get(value);
	//			Button btn = (Button) flip().getScene().lookup("#"+key);
	//         //   System.out.println(tile1);
	//            tile1.setGraphic(blackSoldier);
	//    		
	//			}
	//		}
	//		}catch (Exception e) {
	//			e.getCause();
	//			e.printStackTrace();
	//		}
	//	}

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
	  @FXML
	    void BlueTileBtnClicked(ActionEvent event) {

			 Stage window = (Stage)this.BlueTileBtn.getScene().getWindow();
				
			 FXMLLoader loader = new FXMLLoader(BlueTileTutController .class.getResource("/View/BlueTileTutorial.fxml"));
			// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
	        Parent root;
	      try {
	          root = loader.load();
	        Scene scene = new Scene(root);
	        window.setTitle("Hamka");
	        window.setScene(scene);
	        BlueTileTutController con = loader.getController();
	 
	        con.start(window);
	        window.show();
	        window.centerOnScreen();
	    	//((Stage)this.BlueTileBtn.getScene().getWindow()).close();
	    	} catch ( Exception e1) {
	          // TODO Auto-generated catch block
	          e1.printStackTrace();
	      }
        
	    }

	    @FXML
	    void OrangeTileBtnClicked(ActionEvent event) {
	    	Stage window = (Stage)this.BlueTileBtn.getScene().getWindow();
			
			 FXMLLoader loader = new FXMLLoader(GrnOrangetutrialController.class.getResource("/View/GreenOrangeTileTutorial.fxml"));
			// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
         Parent root;
       try {
           root = loader.load();
         Scene scene = new Scene(root);
         window.setTitle("Hamka");
         window.setScene(scene);
         GrnOrangetutrialController con = loader.getController();
  
         con.start(window);
         window.show();
         window.centerOnScreen();
    // 	((Stage)this.BlueTileBtn.getScene().getWindow()).close();
     	} catch ( Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
	    	
	    }

	    @FXML
	    void RedTileBtnClicked(ActionEvent event) {
	    	Stage window = (Stage)this.BlueTileBtn.getScene().getWindow();
			
			 FXMLLoader loader = new FXMLLoader(RedTileTutorialcontroller.class.getResource("/View/RedTileTutrial.fxml"));
			// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
        Parent root;
      try {
          root = loader.load();
        Scene scene = new Scene(root);
        window.setTitle("Hamka");
        window.setScene(scene);
        RedTileTutorialcontroller con = loader.getController();
 
        con.start(window);
        window.show();
        window.centerOnScreen();
 //   	((Stage)this.BlueTileBtn.getScene().getWindow()).close();
    	} catch ( Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
      }
	    }

	    @FXML
	    void YellowTileBtnClicked(ActionEvent event) {
	    	Stage window = (Stage)this.BlueTileBtn.getScene().getWindow();
			
			 FXMLLoader loader = new FXMLLoader(YellowTileTutrialController.class.getResource("/View/YellowTileTutrial.fxml"));
			// FXMLLoader loader = new FXMLLoader(gameplayScreenController.class.getResource("/View/gameplayScreen.fxml"));
       Parent root;
     try {
         root = loader.load();
       Scene scene = new Scene(root);
       window.setTitle("Hamka");
       window.setScene(scene);
       YellowTileTutrialController con = loader.getController();

       con.start(window);
       window.show();
       window.centerOnScreen();
  // 	((Stage)this.BlueTileBtn.getScene().getWindow()).close();
   	} catch ( Exception e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
     }
	    }
	    
	    
	
//-------------------------------------
	
	@FXML
	void tileClicked(MouseEvent event) throws IOException {

        if(((Button)event.getSource()).getId().equals("tile32") ) System.out.println("tile32");
        
        
		queenArrows.setVisible(false);
		//Clicked Button (black tile)
		//System.out.println(tilesBoardMap);
		//tile1.setStyle("-fx-background-color: yellow");
		//		if(!isTileEmpty(scene, (Button) scene.lookup("#"+(String)((Control)event.getSource()).getId()))) {
		//		GenerateYellowTiles(scene);
		//	}

		//	if((!this.game.noMoreMovesForPlayer(Color.White))||(!this.game.noMoreMovesForPlayer(Color.Black) )) {

		//	GenerateRedTiles(scene, this.game.getTurn());
		//GenerateGreenTiles(scene, this.game.getTurn());
		Button currentTile;
		Board gBoard = game.getBoard();
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
		
		ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
		blackSoldier.setFitHeight(45);
		blackSoldier.setFitWidth(45);
	
		ImageView possibleMove = new ImageView(new Image(getClass().getResourceAsStream("/Resources/possibleMove.png")));
		possibleMove.setFitHeight(45);
		possibleMove.setFitWidth(45);
		
		ImageView chosenBlackQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/chosenBlackQueen.png")));
		chosenBlackQueen.setFitHeight(45);
		chosenBlackQueen.setFitWidth(45);
		
		ImageView blackQueen = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackQueen.png")));
		blackQueen.setFitHeight(45);
		blackQueen.setFitWidth(45);
		


		//	System.out.println();
		//		System.out.println(currentTile);
		//	System.out.println("i is:" + i + ", j  is: "+ j);
		//System.out.println(currentTile.getId().equals("tile1"));
		//((Button) scene.lookup("#"+"tile1")).setGraphic(null);
		//		Button b = getButtonById(currentTile.getId());
		//		System.out.println("Here");
		//		System.out.println(b);
		//		System.out.println("End");
		//		b.setGraphic(chosenBlackSoldier);



		Tile current = new Tile(i, j);
		Soldier s = game.getTileContent(current);
		Color color = game.getTurn();
		
		
	

		//		p1Points.setText(String.valueOf(this.game.getblackPlayerPoints()) ); 
		//		p2Points.setText(String.valueOf(this.game.getwhitePlayerPoints()) ); 

		if(color==Color.Black) { //Black's turn
		

			System.out.println("switching to Black  !!");
			SwitchTurntoBlack(i , j , s , currentTile , blackSoldier, chosenBlackSoldier, blackQueen,  chosenBlackQueen) ; 

		}
		else if(color==Color.White) { //turn.color = Color.White
			System.out.println("switching to White !!");
			//SwitchTurntoWhite(s, i, j, currentTile, whiteSoldier, chosenWhiteSoldier, whiteQueen, chosenWhiteQueen);



		}
	}


	public void SwitchTurntoBlack( int i , int j,Soldier s ,Button currentTile, ImageView blackSoldier , ImageView chosenBlackSoldier , ImageView blackQueen, ImageView chosenBlackQueen ) {
		// the colors switch
		//the timer Restarts 
		// allow the tiles of the turn to play 
		// generate Colored Tiles 
		tl.setOnAction(e -> {
			direction = "TL";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );

		} );
		tr.setOnAction(e -> {
			direction = "TR";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );

		} );
		bl.setOnAction(e -> {
			direction = "BL";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );

		} );
		br.setOnAction(e -> {
			direction = "BR";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );

		} );



		//--------------------
		if(s==null) { //IF current Tile doesnt have a soldier.
			if(possible==null && possibleQueen == null)   //If possible is null then no soldier was selected before.
				System.out.println("Please click a black Soldier!");

			else if(clickedSoldier!=null){

				String prev = tilesBoardMap.get(clickedSoldier);
				//System.out.println("sdsds" + prev);
				//Convert tile to i,j
				String[] parts2= prev.split(",");
				String part21 = parts2[0]; 
				String part22 = parts2[1]; 
				//Tile converted to i,j format to be used with the board 2d arary.
				Integer desti = Integer.parseInt(part21);
				Integer destj = Integer.parseInt(part22);
				Tile prevT = new Tile(desti, destj);

				//System.out.println("Prev Tile: " + prevT);
				Soldier prevS = game.getTileContent(prevT);

				if(prevS.getSoldierNumber()==2) {
					for (Tile t : possible) {  //a tile was selected before, and current tile is used to make the move.
						int coordinateI = t.getX();
						int coordinateJ = t.getY();
						if(i==coordinateI && j == coordinateJ){

							
							System.out.println("SOLDIER");
							//	Button to = getButtonById(currentTile.getId());
							//to.setGraphic(blackSoldier);
							//Button from = getButtonById(clickedSoldier);
							//	from.setGraphic(null);
							//System.out.println(prevS);s
							game.moveBlackSoldier(prevS, t, possible);
							if((t.getX()== 2 && t.getY()==5) || (t.getX()== 3 && t.getY()==0) || (t.getX()== 3 && t.getY()==2) )  {
								ArrayList<String> Alist = new ArrayList<>()  ;
								Alist.add("Yes ,Sure !") ; 
								Alist.add("Nope " ) ; 
								Alist.add( "too Fun !"  ) ; 
								Alist.add( "i don't Know !" ) ; 
								Question q = new Question(" SoftWare Engineering is A Very Fun Course  ! ", "1" , Alist ,"Yes ,Sure !" ) ;
								quesPop.question = q ;
								quesPop.display();
								boardOFF() ; 
							}
							occupiedTilesOriginalColor(scene) ; 
							//ClearColoredTiles(scene);
							//GenerateYellowTiles(scene);
						//	GenerateRedTiles(scene, Color.White);
							//GenerateGreenTiles(scene, Color.White);
							System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
//							if(game.isGameOver()) {
//								winnerLabel.setText(game.winner() + " Wins!");
//								winnerLabel.setVisible(true);
//								boardOFF();
//							}

							System.out.println("Now It's White's turn");
							clickedSoldier=null;
							break;

							//							System.out.println("Yes" + currentTile.getId());
							//							System.out.println();
							//							System.out.println();
							//	board = game.getBoard();
							//	System.out.println("Game id: " + game.getGameID());
						}
					}
				}

				else if(prevS.getSoldierNumber()==22) {
					System.out.println("sdsdsdsddsdsdsdsdadwasdasdwqasdas  : " + possibleQueen);
					if(possibleQueen!=null) {
						
						for (Tile t2 : possibleQueen.keySet()) {  //a tile was selected before, and current tile is used to make the move.
							int coordinateI2 = t2.getX();
							int coordinateJ2 = t2.getY();
							if(i==coordinateI2 && j == coordinateJ2){

								System.out.println("QUEEN");
								//	Button to = getButtonById(currentTile.getId());
								//to.setGraphic(blackSoldier);
								//Button from = getButtonById(clickedSoldier);
								//	from.setGraphic(null);
								//System.out.println("Prev Tile: " + prevT);
							//	System.out.println("moves: "+t2.getX()+ " "+t2.getY());
								Queen prevSs =(Queen) prevS;
								//System.out.println(prevS);
								game.queenMove(prevSs, t2, possibleQueen);
								
								occupiedTilesOriginalColor(scene) ; 
							//	ClearColoredTiles(scene);
							//	GenerateYellowTiles(scene);
							//	GenerateRedTiles(scene, Color.White);
							 	//GenerateGreenTiles(scene, Color.White);
								System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
//								if(game.isGameOver()) {
//									winnerLabel.setText(game.winner() + " Wins!");
//									winnerLabel.setVisible(true);
//									boardOFF();
//									}
								System.out.println("Now It's White's turn");
								clickedSoldier=null;
								break;

								//							System.out.println("Yes" + currentTile.getId());
								//							System.out.println();
								//							System.out.println();
								//	board = game.getBoard();
								//	System.out.println("Game id: " + game.getGameID());
							}
						}
					}
				}


				System.out.println("Here are the possible moves: " + possible);
			}




			try {
				refreshBoard(game,scene, root);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.getCause() ; 
				e.printStackTrace();
			}


		}else if( s.getColor().equals(Color.Black)) { //Tile does have a Soldier/Queen
			Button b = getButtonById(currentTile.getId());
			//*change selection icon
			try {
				refreshBoard(game, scene, root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(clickedSoldier==null) { //This is the first click (Selection)
				//			System.out.println("Here");
				System.out.println(b);
				if(game.getBoard().getBoard()[i][j]==2) {
					b.setGraphic(chosenBlackSoldier);
					queenArrows.setVisible(false);
				}

				if(game.getBoard().getBoard()[i][j]==22) {
					queenArrows.setVisible(true);
					b.setGraphic(chosenBlackQueen);
					Bounds boundsInScene = b .localToScene(b.getBoundsInLocal());
					queenArrows.setTranslateX(boundsInScene.getMinX()-25);
					queenArrows.setTranslateY(boundsInScene.getMinY()-43);
				}
				clickedSoldier = b.getId();
			}else { //Change selection (Previously clicked on a piece)
				Button last = getButtonById(clickedSoldier);
				String prev = tilesBoardMap.get(clickedSoldier);
				//System.out.println("sdsds" + prev);
				//Convert tile to i,j
				String[] parts2= prev.split(",");
				String part21 = parts2[0]; 
				String part22 = parts2[1]; 
				//Tile converted to i,j format to be used with the board 2d arary.
				Integer desti = Integer.parseInt(part21);
				Integer destj = Integer.parseInt(part22);
				if(game.getBoard().getBoard()[desti][destj]==2) {
					last.setGraphic(blackSoldier);
					queenArrows.setVisible(false);
				}
				if(game.getBoard().getBoard()[desti][destj]==22) 
					last.setGraphic(blackQueen);

				if(game.getBoard().getBoard()[i][j]==2) { //Change to Soldier piece
					queenArrows.setVisible(false);
					b.setGraphic(chosenBlackSoldier);
				}
				if(game.getBoard().getBoard()[i][j]==22) { //Change to Queen piece

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
			////*get Soldier's possible moves
			possible = game.getPossibleMovesForBlackSoldier(s);
			 
			System.out.println("????????????????????");
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


		}else if(s.getColor().equals(Color.White))
			System.out.println("White Soldier clicked!");





	}// End of Blackturn method.


	
	/*Similar to SwitchTurntoBlack - For more comments see SwitchTurntoBlack*/
	public void SwitchTurntoWhite(Soldier s , int i , int j , Button currentTile,ImageView whiteSoldier , ImageView chosenWhiteSoldier, ImageView whiteQueen, ImageView chosenWhiteQueen) {


		// the colors switch
		//the timer Restarts 
		// allow the tiles of the turn to play 
		// generate Colored Tiles 
		tl.setOnAction(e -> {
			direction = "TL";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );
		tr.setOnAction(e -> {
			direction = "TR";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );
		bl.setOnAction(e -> {
			direction = "BL";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );
		br.setOnAction(e -> {
			direction = "BR";
			queenArrows.setVisible(false);
			possibleQueen = game.getQueenBiasMoves((Queen)s, direction);
			System.out.println("POSSIBLE FOR QUEEN: " +possibleQueen );
		} );

		if(s==null) {
			if(possible==null && possibleQueen == null)
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

				//System.out.println("Prev Tile: " + prevT);
				Soldier prevS = game.getTileContent(prevT);
				if(prevS.getSoldierNumber()==1) {
					for (Tile t : possible) {
						int coordinateI = t.getX();
						int coordinateJ = t.getY();
						if(i==coordinateI && j == coordinateJ){

							//System.out.println(prevS);
							game.moveWhiteSoldier(prevS, t, possible);
							
							game.handTurn(); //Switch  turn to black. ////////////////// Colored Tiles here
						
							//Timer Related - 
							occupiedTilesOriginalColor(scene) ; 
						//	ClearColoredTiles(scene);
						//	GenerateYellowTiles(scene);
						//	GenerateRedTiles(scene, Color.White);
							//GenerateGreenTiles(scene, Color.White);
						
							System.out.println("Now It's Black's  turn");
							clickedSoldier=null;
							break;

						}
					}
				}else if(prevS.getSoldierNumber()==11) {
					if(possibleQueen!=null) {
						for (Tile t2 : possibleQueen.keySet()) {  //a tile was selected before, and current tile is used to make the move.
							int coordinateI2 = t2.getX();
							int coordinateJ2 = t2.getY();
							if(i==coordinateI2 && j == coordinateJ2){

								System.out.println("QUEEN");
								//	Button to = getButtonById(currentTile.getId());
								//to.setGraphic(blackSoldier);
								//Button from = getButtonById(clickedSoldier);
								//	from.setGraphic(null);

								//System.out.println("Prev Tile: " + prevT);
								Queen prevSs =(Queen) prevS;
								//System.out.println(prevS);
								game.queenMove(prevSs, t2, possibleQueen);
								
								game.handTurn(); //Switch  turn to white. ///////////////Generating Colored Tiles Here
							
								//Timer Related - 
								occupiedTilesOriginalColor(scene) ; 
							//	ClearColoredTiles(scene);
							//	GenerateYellowTiles(scene);
							//	GenerateRedTiles(scene, Color.White);
								//GenerateGreenTiles(scene, Color.White);
								System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
//								if(game.isGameOver()) {
//									winnerLabel.setText(game.winner() + " Wins!");
//									winnerLabel.setVisible(true);	
//									boardOFF();
//								}
								System.out.println("Now It's Black's  turn");
								clickedSoldier=null;
								break;

								//							System.out.println("Yes" + currentTile.getId());
								//							System.out.println();
								//							System.out.println();
								//	board = game.getBoard();
								//	System.out.println("Game id: " + game.getGameID());
							}
						}
					}
				}
				try {
					refreshBoard(game,scene, root);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}else if( s.getColor().equals(Color.White)) {
			Button b = getButtonById(currentTile.getId());
			//*change selection icon
			try {
				refreshBoard(game, scene, root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(clickedSoldier==null) {
				System.out.println(b);
				if(game.getBoard().getBoard()[i][j]==1) {
					b.setGraphic(chosenWhiteSoldier);
					queenArrows.setVisible(false);
				}
				if(game.getBoard().getBoard()[i][j]==11) {
					Bounds boundsInScene = b .localToScene(b.getBoundsInLocal());
					queenArrows.setTranslateX(boundsInScene.getMinX()-25);
					queenArrows.setTranslateY(boundsInScene.getMinY()-43);
					queenArrows.setVisible(true);
					b.setGraphic(chosenWhiteQueen);
				}
				clickedSoldier = b.getId();
			}else {
				Button last = getButtonById(clickedSoldier);
				String prev = tilesBoardMap.get(clickedSoldier);
				//System.out.println("sdsds" + prev);
				//Convert tile to i,j
				String[] parts2= prev.split(",");
				String part21 = parts2[0]; 
				String part22 = parts2[1]; 
				//Tile converted to i,j format to be used with the board 2d arary.
				Integer desti = Integer.parseInt(part21);
				Integer destj = Integer.parseInt(part22);
				if(game.getBoard().getBoard()[desti][destj]==1) {
					last.setGraphic(whiteSoldier);
					queenArrows.setVisible(false);
				}

				if(game.getBoard().getBoard()[desti][destj]==11)
					last.setGraphic(whiteQueen);
				if(game.getBoard().getBoard()[i][j]==1) {
					queenArrows.setVisible(false);
					b.setGraphic(chosenWhiteSoldier);
				}
				if(game.getBoard().getBoard()[i][j]==11) {
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
			//*get possible moves
			possible = game.getPossibleMovesForWhiteSoldier(s);
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
					System.out.println("Possible Move:" + key);
				}

			}
			System.out.println("Here are the possible moves: " + possible);
			//System.out.println("TEST: "  + board[2][3]);
		}else if(s.getColor().equals(Color.Black))
			System.out.println("Black Soldier clicked!");


	}



	public void occupiedTilesOriginalColor(Scene s) {

		for (Tile tile :this.game.getBoard().getOccupiedTiles()) {
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
						//break;
					}
				}
			}
		}
	}






	ImageView yellowTile = new ImageView(new Image(getClass().getResourceAsStream("/Resources/yellowTile.png"))); 
	ImageView greenTile = new ImageView(
			new Image(getClass().getResourceAsStream("/Resources/greenTile.png"))); 
	ImageView blueTile = new ImageView(
			new Image(getClass().getResourceAsStream("/Resources/blueTile.png"))); 
	ImageView redTile = new ImageView(
			new Image(getClass().getResourceAsStream("/Resources/redTile.png"))); 


	//public Tile getTileFromButton ()

	public void refreshBoard(Game game, Scene scene, Parent root) throws IOException {

		int whiteAliveCout = 0 ; 
		int blackAliveCount = 0 ;
		
		int [][]board = game.getBoard().getBoard();
		System.out.println("In refresh");
		//Parent  root = FXMLLoader.load(getClass().getResource("gameplayScreen.fxml"));
		//Scene  scene = new Scene(root);
		//	clearBoard(game,  scene,  root);
		//	int c = 0;
		//clearBoard(game, scene, root);
		for(int i = 0; i<=7; i++) {
			for(int j = 0; j<=7; j++) {

				//for(int j = 2; j<8; j+=2) {
				
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
				//clearBoard(game, scene, root);


				//				for (String ks : tilesBoardMap.keySet()) {
				//					((Button) scene.lookup("#"+ks)).setGraphic(null);
				//				}
				buildTilesBoardMap();
                
				String dest = i+","+j;
				String check = null;
				String key = null;
				for (String ks : tilesBoardMap.keySet()) {
					//	((Button) scene.lookup("#"+ks)).setGraphic(null);
					//((Button) scene.lookup("#"+ks)).setGraphic(whiteSoldier);
					check = tilesBoardMap.get(ks);
					//					((Button) scene.lookup("#"+ks)).setGraphic(null);
					//	((Button) scene.lookup("#"+ks)).setGraphic(blackSoldier);
					if(check!=null) {
						if(check.equals(dest)) {
							key = ks;
							break;
						}
					}
				}
				//	System.out.println("Over herererere");
				//System.out.println(key);

				if(board[i][j]==1) {
					((Button) scene.lookup("#"+key)).setGraphic(whiteSoldier);
					whiteAliveCout++ ; 
				}else if(board[i][j]==2) { 
					((Button) scene.lookup("#"+key)).setGraphic(blackSoldier);
//					if((i == 2 && j==5) || (i == 3 && j==0) ||(i == 3 && j==2) ) {
//						 System.out.println( "i is : "+i + " and  j  :"+j);
//
//						quesPop.question = SysData.getInstance().randomQuestion();
//						quesPop.display();
//						SysData.getInstance().questionIsShown(quesPop.question);
//						
//						}
					// 3,2 
					// 3,0 
					//blackAliveCount++; 
				}else if(board[i][j]==0 && key!=null ) {
					((Button) scene.lookup("#"+key)).setGraphic(null);

				}else if (board[i][j]==11) { 
					((Button) scene.lookup("#"+key)).setGraphic(whiteQueen);
					whiteAliveCout++ ; 

				}else if (board[i][j]==22) {
					((Button) scene.lookup("#"+key)).setGraphic(blackQueen);
					blackAliveCount++; 
				}
				


				//					String value ; 
				//
				//					//value = i*4 + j ; 
				//					value=String.valueOf((i*4+j)) ; 

				//	String key = tilesBoardMap.get(value);
				//	    		System.out.println(value+"//");
				//	    		if(board[i][j]== 1) {
				//	    	    	((Button) scene.lookup("#tile"+value)).setGraphic(img2);
				//
				//	    		}
				//	    		
				//	    		if(board[i][j]== 2) {
				//	    	    	((Button) scene.lookup("#tile"+value)).setGraphic(img);
				//
				//	    		}

				//					if(Integer.parseInt(value) <13) {
				//						System.out.println("#tile"+value);
				//						((Button) scene.lookup("#tile"+value)).setGraphic(blackSoldier);
				//					}
				//					if(Integer.parseInt(value) >20) {
				//						((Button) scene.lookup("#tile"+value)).setGraphic(whiteSoldier);
				//					}



				// Button btn = (Button) scene.lookup("#"+key);
				//System.out.println(tile1);
				// btn.setGraphic(img);
			
			}
			


		}
		
		
		//Generate Blue Tile - Maybe ! 
		
		//-----------------------Count Your Loses ! 
//		if(this.deadBlackv !=null && this.deadwhitev !=null) {
//			this.deadwhitev.getChildren().clear();
//			this.deadBlackv.getChildren().clear();
//
//
//			for(int i=0 ; i< 12 -whiteAliveCout ; i++) {
//
//				ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
//				whiteSoldier.setFitHeight(45);
//				whiteSoldier.setFitWidth(45);
//				this.deadwhitev.getChildren().add(whiteSoldier);
//			}
//
//			for(int i=0 ; i< 12 -blackAliveCount ; i++) {
//				ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
//				blackSoldier.setFitHeight(45);
//				blackSoldier.setFitWidth(45);
//				this.deadBlackv.getChildren().add(blackSoldier);
//			}
//		}

	}

	
	public void boardOFF() {
	
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


	

	public void ClearColoredTiles(Scene s) {
		//Clear all Empty Tiles 
		for (Tile tile :this.game.getBoard().getEmptyTiles()) {
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
						//break;
					}
				}
			}
		}

	}
	//------------------------------colored tiles  ;
	public void GenerateRedTiles( Scene s, Model.Color Nowplaying )  {

		// color 3 random Empty tiles
		Tile redTile = this.game.generateRedTile(Nowplaying)  ; 
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
						break;
					}
				}
			}
		}

	}


	//-----------------------green Tile

//
//	public void GenerateGreenTiles( Scene s, Model.Color Nowplaying )  {
//
//		// color 1 random Empty tile
//		Tile greenTile = this.game.generateGreenTile(Nowplaying) ; 
//		if(greenTile != null ) { 
//			String possibleTile = greenTile.getX()+","+greenTile.getY();
//			String check = null;
//			String key = null;
//			for (String ks : tilesBoardMap.keySet()) {
//				check = tilesBoardMap.get(ks);
//				if(check!=null) {
//					if(check.equals(possibleTile)) {
//						key = ks;
//						//						System.out.println("should be red  :: "+key);
//						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #7EB77C;");;
//						break;
//					}
//				}
//			}
//		}
//
//	}



	//-------------------------

//	public void GenerateOrangeTiles( Scene s, Model.Color Nowplaying )  {
//
//		// color 3 random Empty tiles
//		for (Tile tile : this.game.generateOrangeTiles(Nowplaying) ) {
//			String possibleTile = tile.getX()+","+tile.getY();
//			String check = null;
//			String key = null;
//			for (String ks : tilesBoardMap.keySet()) {
//				check = tilesBoardMap.get(ks);
//				if(check!=null) {
//					if(check.equals(possibleTile)) {
//						key = ks;
//						//						System.out.println("should be orange  :: "+key);
//						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #e38d0b;");;
//						break;
//					}
//				}
//			}
//		}
//
//	}



	


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
		for (Tile tile :this.game.getBoard().getEmptyTiles()) {
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
    void ExitBtnClicked(ActionEvent event) {
    	
		((Stage)this.ExitBtn.getScene().getWindow()).close();

		Stage stage = (Stage)this.ExitBtn.getScene().getWindow();
		Parent toLoad;
		try {
			toLoad = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
			Scene scene = new Scene(toLoad);
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		  stage.show();
			stage.setTitle("Hamka - Match");
	         
    }
	
	
}
