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
import java.util.ResourceBundle;
import java.util.TimerTask;

import javax.management.openmbean.OpenType;
import javax.swing.Timer;

import Model.Board;
import Model.Color;
import Model.Game;
import Model.Soldier;
import Model.StopWatch;
import Model.Tile;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class gameplayScreenController extends Application implements Initializable {



	@FXML
	private Pane paneBoard;

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
    
    private Timer turnTimer;
    
	public static HashMap<String, String> tilesBoardMap;
	public static String clickedSoldier = null;
	public static ArrayList<Tile> possible = null;



	private int[][] startBoard = {
			{-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,1,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{1,-1,1,-1,1,-1,1,-1}
	};
	public static Parent  root;
	public static Scene scene;

	//private Game game  = new Game("White", "Black", startBoard);
	private Game game =  Game.getInstance("Black", "White", startBoard); //Singletone changes to be in every method.

	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		root = FXMLLoader.load(getClass().getResource("gameplayScreen.fxml"));
		scene = new Scene(root);
		//FillBoard() ;
		//game.handTurn();

		stage.setScene(scene);
		//java.io.FileInputStream fis = new FileInputStream("/System/Library/CoreServices/loginwindow.app/Contents/Resources/LogOut.png");
		buildTilesBoardMap();
		refreshBoard(game, scene, root);
		

		//		try {
		//			for(int i = 0; i<=7; i++) {
		//				for(int j = 0; j<=7; j++) {
		//					//for(int j = 2; j<8; j+=2) {
		//
		//					ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
		//					blackSoldier.setFitHeight(45);
		//					blackSoldier.setFitWidth(45);
		//					ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
		//					whiteSoldier.setFitHeight(45);
		//					whiteSoldier.setFitWidth(45);
		//
		//					String dest = i+","+j;
		//					String check = null;
		//					String key = null;
		//					for (String ks : tilesBoardMap.keySet()) {
		//						check = tilesBoardMap.get(ks);
		//						if(check!=null) {
		//							if(check.equals(dest)) {
		//								key = ks;
		//								break;
		//							}
		//						}
		//					}
		//					//	System.out.println("Over herererere");
		//					//System.out.println(key);
		//
		//					if(startBoard[i][j]==1) {
		//						((Button) scene.lookup("#"+key)).setGraphic(whiteSoldier);
		//					}
		//					if(startBoard[i][j]==2) {
		//						((Button) scene.lookup("#"+key)).setGraphic(blackSoldier);
		//					}
		//
		//					//					String value ; 
		//					//
		//					//					//value = i*4 + j ; 
		//					//					value=String.valueOf((i*4+j)) ; 
		//
		//					//	String key = tilesBoardMap.get(value);
		//					//	    		System.out.println(value+"//");
		//					//	    		if(board[i][j]== 1) {
		//					//	    	    	((Button) scene.lookup("#tile"+value)).setGraphic(img2);
		//					//
		//					//	    		}
		//					//	    		
		//					//	    		if(board[i][j]== 2) {
		//					//	    	    	((Button) scene.lookup("#tile"+value)).setGraphic(img);
		//					//
		//					//	    		}
		//
		//					//					if(Integer.parseInt(value) <13) {
		//					//						System.out.println("#tile"+value);
		//					//						((Button) scene.lookup("#tile"+value)).setGraphic(blackSoldier);
		//					//					}
		//					//					if(Integer.parseInt(value) >20) {
		//					//						((Button) scene.lookup("#tile"+value)).setGraphic(whiteSoldier);
		//					//					}
		//
		//
		//
		//					// Button btn = (Button) scene.lookup("#"+key);
		//					//System.out.println(tile1);
		//					// btn.setGraphic(img);
		//
		//				}
		//			}
		//
		//			//	((Button) scene.lookup("#tile12")).setGraphic(img);
		//		}catch (Exception e) {
		//			System.out.println("hello");
		//			e.getCause();
		//			e.printStackTrace();
		//		}






		//((Button) scene.lookup("#tile3")).setGraphic(img);
		//	((Button)root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().indexOf( (Button) scene.lookup("#tile3")))).setGraphic(img); 
		stage.show();
		stage.setTitle("Hamka - Match");



		//System.out.println("START   " + tile1);



		//		try {
		//		for(int i = 0; i<=7; i++) {
		//			for(int j = 0; j<=7; j++) {
		//            ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
		//            ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
		//
		//    		String value = i + ","+j;
		//    		String key = tilesBoardMap.get(value);
		//            Button btn = (Button) scene.lookup("#"+key);
		//            System.out.println(tile1);
		//            tile1.setGraphic(blackSoldier);
		//    		
		//			}
		//		}
		//		}catch (Exception e) {
		//			e.getCause();
		//			e.printStackTrace();
		//		}





	}


	public static void main(String[] arg) {
		launch(arg);
	}

	@Override
	public void initialize (URL arg0, ResourceBundle arg1) {
		buildTilesBoardMap();
		p1Points.setText(String.valueOf(this.game.getblackPlayerPoints()) ); 
		p2Points.setText(String.valueOf(this.game.getwhitePlayerPoints()) ); 
		p1.setText(game.getblackPlayer());
		p2.setText(game.getwhitePlayer());
		
		

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


	
	

	@FXML
	void tileClicked(MouseEvent event) throws IOException {
		
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
               
			SwitchTurntoBlack(scene ,i , j , s , currentTile , blackSoldier, chosenBlackSoldier) ; 
		}
		else if(color==Color.White) { //turn.color = Color.White
			System.out.println("switching to Wwhite !!");
            SwitchTurntoWhite(s, i, j, currentTile, whiteSoldier, chosenWhiteSoldier);


	
	}
	}
	
	public void SwitchTurntoWhite(Soldier s , int i , int j , Button currentTile,ImageView whiteSoldier , ImageView chosenWhiteSoldier) {
	
	
		if(s==null) {
			if(possible==null)
				System.out.println("Please click a white  Soldier!");
			else if(clickedSoldier!=null){
				for (Tile t : possible) {
					int coordinateI = t.getX();
					int coordinateJ = t.getY();
					if(i==coordinateI && j == coordinateJ){

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
						//System.out.println(prevS);
						game.moveWhiteSoldier(prevS, t, possible);
						game.handTurn(); //Switch  turn to black. ////////////////// Colored Tiles here
						occupiedTilesOriginalColor(scene) ; 
						ClearColoredTiles(scene);
			 			GenerateYellowTiles(scene);
			 			  GenerateRedTiles(scene, Color.White);
	                        GenerateGreenTiles(scene, Color.White);
	                        
						System.out.println("Now It's Black's  turn");
						clickedSoldier=null;
						break;


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
				b.setGraphic(chosenWhiteSoldier);
				clickedSoldier = b.getId();
			}else {
				Button last = getButtonById(clickedSoldier);
				last.setGraphic(whiteSoldier);
				b.setGraphic(chosenWhiteSoldier);
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
	
	public void SwitchTurntoBlack( Scene scene , int i , int j,Soldier s ,Button currentTile, ImageView blackSoldier , ImageView chosenBlackSoldier  ) {
		// the colors switch
		//the timer Restarts 
		// allow the tiles of the turn to play 
		// generate Colored Tiles 
	
		
		//--------------------
		if(s==null) {
			if(possible==null)
				System.out.println("Please click a black Soldier!");
		         else if(clickedSoldier!=null){

				for (Tile t : possible) {
					int coordinateI = t.getX();
					int coordinateJ = t.getY();
					if(i==coordinateI && j == coordinateJ){
						//	Button to = getButtonById(currentTile.getId());
						//to.setGraphic(blackSoldier);
						//Button from = getButtonById(clickedSoldier);
						//	from.setGraphic(null);
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
						//System.out.println(prevS);
						game.moveBlackSoldier(prevS, t, possible);
						game.handTurn(); //Switch  turn to white. ///////////////Generating Colored Tiles Here
						occupiedTilesOriginalColor(scene) ; 
						ClearColoredTiles(scene);
			 			GenerateYellowTiles(scene);
                        GenerateRedTiles(scene, Color.White);
                        GenerateGreenTiles(scene, Color.White);
						System.out.println("Now It's White's turn");
						clickedSoldier=null;
						break;

						//							System.out.println("Yes" + currentTile.getId());
						//							System.out.println();
						//							System.out.println();
						//	board = game.getBoard();
						//	System.out.println("Game id: " + game.getGameID());
					}
					
				System.out.println("Here are the possible moves: " + possible);
					}

			
		         }
			/*       
		}else if(s.getColor().equals(Color.White)) {
				System.out.println("White Soldier clicked!");

		
		         }else if(color==Color.White) { //turn.color = Color.White
			if(s==null) {
				if(possible==null)
					System.out.println("Please click a white  Soldier!");
				else if(clickedSoldier!=null){
					for (Tile t : possible) {
						int coordinateI = t.getX();
						int coordinateJ = t.getY();
						if(i==coordinateI && j == coordinateJ){

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
							//System.out.println(prevS);
							game.moveWhiteSoldier(prevS, t, possible);
							p2Points.setText(String.valueOf(this.game.getwhitePlayerPoints()) ); 
							game.handTurn(); //Switch  turn to black.
							System.out.println("Now It's Black's  turn");
							clickedSoldier=null;
							break;


						}
//>>>>>>> master

					}
					//Else don't do anything.
					//						else {
					//							System.out.println("Nope");
					//						}

				}
			*/
				try {
					refreshBoard(game,scene, root);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.getCause() ; 
					e.printStackTrace();
				}
		
			
		
	
		
		}else if( s.getColor().equals(Color.Black)) {
			Button b = getButtonById(currentTile.getId());
			//*change selection icon
			try {
				refreshBoard(game, scene, root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(clickedSoldier==null) {
				//			System.out.println("Here");
				System.out.println(b);
				//			System.out.println("End");
				b.setGraphic(chosenBlackSoldier);
				clickedSoldier = b.getId();
			}else {
				Button last = getButtonById(clickedSoldier);
				last.setGraphic(blackSoldier);
				b.setGraphic(chosenBlackSoldier);
				clickedSoldier = b.getId();
			}
			//*get possible moves
			possible = game.getPossibleMovesForBlackSoldier(s);
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
				
			}else if(s.getColor().equals(Color.White))
			System.out.println("White Soldier clicked!");
		
		}
		
	
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
		                           System.out.println(key);
		                           ((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #000000;");;
		                           //break;
		                       }
		                   }
		               }
		           }
		         }
		      
		         
		         
		
		
	
	

		
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
				             blackAliveCount++; 
				}else if(board[i][j]==0 && key!=null ) {
					((Button) scene.lookup("#"+key)).setGraphic(null);
				
				}else if (board[i][j]==11) { 
					((Button) scene.lookup("#"+key)).setGraphic(whiteQueen);

				}else if (board[i][j]==22)
					((Button) scene.lookup("#"+key)).setGraphic(blackQueen);
				
				


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
		if(this.deadBlackv !=null && this.deadwhitev !=null) {
		this.deadwhitev.getChildren().clear();
		this.deadBlackv.getChildren().clear();
		
		
		for(int i=0 ; i<12-whiteAliveCout ; i++) {
		
			ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
			whiteSoldier.setFitHeight(45);
			whiteSoldier.setFitWidth(45);
			this.deadwhitev.getChildren().add(whiteSoldier);
		}
		
for(int i=0 ; i<12-blackAliveCount ; i++) {
	ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
	blackSoldier.setFitHeight(45);
	blackSoldier.setFitWidth(45);
			this.deadBlackv.getChildren().add(blackSoldier);
		}
		}

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
						System.out.println(key);
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
						System.out.println("should be red  :: "+key);
						((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #ed492f;");;
						break;
					}
				}
			}
		}

	}
	
	
	//-----------------------green Tile
	
	
	public void GenerateGreenTiles( Scene s, Model.Color Nowplaying )  {

        // color 1 random Empty tile
     Tile greenTile = this.game.generateGreenTile(Nowplaying) ; 
     if(greenTile != null ) { 
            String possibleTile = greenTile.getX()+","+greenTile.getY();
            String check = null;
            String key = null;
            for (String ks : tilesBoardMap.keySet()) {
                check = tilesBoardMap.get(ks);
                if(check!=null) {
                    if(check.equals(possibleTile)) {
                        key = ks;
                        System.out.println("should be red  :: "+key);
                        ((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #7EB77C;");;
                        break;
                    }
                }
            }
     }

    }

	
	
	//-------------------------
	
	public void GenerateOrangeTiles( Scene s, Model.Color Nowplaying )  {
      
     // color 3 random Empty tiles
        for (Tile tile : this.game.generateOrangeTiles(Nowplaying) ) {
            String possibleTile = tile.getX()+","+tile.getY();
            String check = null;
            String key = null;
            for (String ks : tilesBoardMap.keySet()) {
                check = tilesBoardMap.get(ks);
                if(check!=null) {
                    if(check.equals(possibleTile)) {
                        key = ks;
                        System.out.println("should be orange  :: "+key);
                        ((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #e38d0b;");;
                        break;
                    }
                }
            }
        }

    }
	
	
	public void stackDeadsoldeirs() {
		
	}
	
	
	public void GenerateYellowTiles( Scene s)  {

        // color 3 random Empty tiles
        for (Tile tile : this.game.generateYellowTiles()) {
            String possibleTile = tile.getX()+","+tile.getY();
            String check = null;
            String key = null;
            for (String ks : tilesBoardMap.keySet()) {
                check = tilesBoardMap.get(ks);
                if(check!=null) {
                    if(check.equals(possibleTile)) {
                        key = ks;
                        System.out.println(key);
                        ((Button) s.lookup("#"+key)).setStyle("-fx-background-color: #c8de59;");;
                        break;
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
	      for (Tile tile :this.game.getBoard().getEmptyTiles()) {
	            String possibleTile = tile.getX()+","+tile.getY();
	            String check = null;
	            String key = null;
	            for (String ks : tilesBoardMap.keySet()) {
	                check = tilesBoardMap.get(ks);
	                if(check!=null) {
	                    if(check.equals(possibleTile)) {
	                        key = ks;
	                        System.out.println(key);
	                        toCheck.add(((Button) s.lookup("#"+key)));
	                        //break;
	                    }
	                }
	            }
	        }
		
	      
	      if(toCheck.contains(btn)) {
	    	  System.out.println("the tile/Button  is Empty :  ");
	    	  return true ; // is Empty 
	      }
		
	      
	      return false ; 
	}
	
	
	
	
	
	
}


