package View;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.management.openmbean.OpenType;

import Model.Board;
import Model.Color;
import Model.Game;
import Model.Soldier;
import Model.Tile;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.Control;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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



	public static HashMap<String, String> tilesBoardMap;
	public static HashMap<String, Button> tilesButtonMap;
	public static String clickedSoldier = null;

	private int[][] board = {
			{-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,1,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{1,-1,1,-1,1,-1,1,-1}
	};
	
	private Game game = new Game("White", "Black", board);

	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent  root = FXMLLoader.load(getClass().getResource("gameplayScreen.fxml"));
		Scene  scene = new Scene(root);
		//FillBoard() ;

		stage.setScene(scene);
		//java.io.FileInputStream fis = new FileInputStream("/System/Library/CoreServices/loginwindow.app/Contents/Resources/LogOut.png");
		buildTilesBoardMap();

		try {
			for(int i = 0; i<=7; i++) {
				for(int j = 0; j<=7; j++) {
					//for(int j = 2; j<8; j+=2) {

					ImageView blackSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
					blackSoldier.setFitHeight(45);
					blackSoldier.setFitWidth(45);
					ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
					whiteSoldier.setFitHeight(45);
					whiteSoldier.setFitWidth(45);

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
				//	System.out.println("Over herererere");
					//System.out.println(key);

					if(board[i][j]==1) {
						((Button) scene.lookup("#"+key)).setGraphic(whiteSoldier);
					}
					if(board[i][j]==2) {
						((Button) scene.lookup("#"+key)).setGraphic(blackSoldier);
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

			//	((Button) scene.lookup("#tile12")).setGraphic(img);
		}catch (Exception e) {
			System.out.println("hello");
			e.getCause();
			e.printStackTrace();
		}






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

		//	FillBoard();



		//ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));
		//	tile1.setGraphic( img);

		// ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/Resources/blackSoldier.png")));

		//tile1.setGraphic( img);

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
		Button currentTile;
		Board board = game.getBoard();
		
		String dest = tilesBoardMap.get((String)((Control)event.getSource()).getId());
		Parent  root = FXMLLoader.load(getClass().getResource("gameplayScreen.fxml"));
		Scene  scene = new Scene(root);
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
		ImageView whiteSoldier = new ImageView(new Image(getClass().getResourceAsStream("/Resources/whiteSoldier.png")));
		whiteSoldier.setFitHeight(45);
		whiteSoldier.setFitWidth(45);

		
		System.out.println();
//		System.out.println(currentTile);
		System.out.println("i is:" + i + ", j  is: "+ j);
		//System.out.println(currentTile.getId().equals("tile1"));
		//((Button) scene.lookup("#"+"tile1")).setGraphic(null);
//		Button b = getButtonById(currentTile.getId());
//		System.out.println("Here");
//		System.out.println(b);
//		System.out.println("End");
//		b.setGraphic(chosenBlackSoldier);
		

		if(board.board[i][j]!=0 && board.board[i][j] !=-1)
		System.out.println("Possible");
	else
		System.out.println("Nope");
		
	Tile current = new Tile(i, j);
	
	ArrayList<Tile> possible = null;
	
	Soldier s = game.getTileContent(current);
	Color color = game.getTurn();
	if(color==Color.Black) {
		if(s.getColor().equals(Color.Black)) {
		Button b = getButtonById(currentTile.getId());
		if(clickedSoldier==null) {
			System.out.println("Here");
			System.out.println(b);
			System.out.println("End");
			b.setGraphic(chosenBlackSoldier);
			clickedSoldier = b.getId();
		}else {
			Button last = getButtonById(clickedSoldier);
			last.setGraphic(blackSoldier);
			System.out.println("Here");
			System.out.println(b);
			System.out.println("End");
			b.setGraphic(chosenBlackSoldier);
			clickedSoldier = b.getId();
		}
		possible = game.getPossibleMovesForBlackSoldier(s);
		System.out.println("Here are the possible moves: " + possible);
		}else {
			System.out.println("White Soldier clicked!");
		}
		
	}
	else { //Color.White
		System.out.println("Call white method");
	}
		

		//((Button) scene.lookup("#"+currentTile.getId())).setGraphic(chosenBlackSoldier);
		
		
		
//		if(board.board[i][j]!=0 && board.board[i][j] !=-1)
//			System.out.println("Possible");
//		else
//			System.out.println("Nope");
//		Tile current = new Tile(i, j);
//		ArrayList
//		
//		Soldier s = game.getTileContent(current);
//		Color color = game.getTurn();
//		if(color==Color.Black)
//			game.getPossibleMovesForBlackSoldier(s);
//		else
//			System.out.println("Call white method");
		
//		String cord = i+","+j;
//		String check = null;
//		String key = null;
//		for (String ks : tilesBoardMap.keySet()) {
//			check = tilesBoardMap.get(ks);
//			if(check!=null) {
//				if(check.equals(dest)) {
//					key = ks;
//					break;
//				}
//			}
//		}
		
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
			case "tile25" : return tile26;
			case "tile27" : return tile27;
			case "tile28" : return tile28;
			case "tile29" : return tile29;
			case "tile30" : return tile30;
			case "tile31" : return tile31;
			case "tile32" : return tile32;
		}
		return null;
	}

}


