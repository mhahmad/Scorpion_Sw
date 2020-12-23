package Controller;

import java.util.ArrayList;

import Model.Board;
import Model.Color;
import Model.Game;
import Model.Queen;
import Model.Soldier;
import Model.StopWatch;
import Model.Tile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class gameController {

	public static int count = 0;
	@FXML
	public  Button tile1;

	/* path to file chosen in LoadGameScreen   */
	public String chosenFilePath =""; 

	private static int[][] startBoard = {
			{-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,1,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{1,-1,1,-1,1,-1,1,-1}
	};

	public static Game game = new Game(gameplayScreenController.p1Name, gameplayScreenController.p2Name, startBoard); //Singletone changes to be in every method.







	//	public static String  moveBlackSoldier(Soldier prevS,Tile t) {
	//		
	//		System.out.println("IN CONTROLLERRRRR" + game.toString());
	//		
	//		game.moveBlackSoldier(prevS, t, gameplayScreenController.possible);
	//		
	//		if(game.isGameOver()) {
	//			return game.winner();
	//		}
	//		
	//		return "Not over";
	//	}
	//	
	//	public static  Soldier returnTileContent(Tile t) {
	//		return game.getTileContent(t);
	//	}
	//	




	public static int[][] getBoard(){
		return game.getBoard().getBoard();
	}
	
	public static ArrayList<Tile> getPossibleMoves(Soldier s){
		ArrayList<Tile> p = null;
		
		return p = (s.getColor().equals(Color.Black)) ? game.getPossibleMovesForBlackSoldier(s) :  game.getPossibleMovesForWhiteSoldier(s);
	}


	public static String move(Integer i,Integer j) {

		Tile current = new Tile(i, j);
		Soldier s = game.getTileContent(current);
		Color color = game.getTurn();

		if(color==Color.Black) { //Black's turn
			//StopWatch turnTimer = new StopWatch();
			//turnTimer.start();
			System.out.println();

			System.out.println("switching to Black  !!");
			moveBlackSoldier(i,j, s);

			//SwitchTurntoBlack(i , j , s , currentTile , blackSoldier, chosenBlackSoldier, blackQueen,  chosenBlackQueen) ; 

		}
		else if(color==Color.White) { //turn.color = Color.White
			System.out.println("switching to White !!");
			//	SwitchTurntoWhite(s, i, j, currentTile, whiteSoldier, chosenWhiteSoldier, whiteQueen, chosenWhiteQueen);

		}

		return null;

	}


	public static String moveBlackSoldier(Integer i, Integer j, Soldier s) {

		if(s==null) { //IF current Tile doesnt have a soldier.
			if(gameplayScreenController.possible==null && gameplayScreenController.possibleQueen == null)   //If possible is null then no soldier was selected before.
				System.out.println("Please click a black Soldier!");
			else if(gameplayScreenController.clickedSoldier!=null){

				String prev = gameplayScreenController.tilesBoardMap.get(gameplayScreenController.clickedSoldier);
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
					//System.out.println("99999999999999999999999999");
					for (Tile t : gameplayScreenController.possible) {  //a tile was selected before, and current tile is used to make the move.
						int coordinateI = t.getX();
						int coordinateJ = t.getY();
						if(i==coordinateI && j == coordinateJ){


							System.out.println("SOLDIER");
							//	Button to = getButtonById(currentTile.getId());
							//to.setGraphic(blackSoldier);
							//Button from = getButtonById(clickedSoldier);
							//	from.setGraphic(null);
							//System.out.println(prevS);
							game.moveBlackSoldier(prevS, t, gameplayScreenController.possible);
							game.handTurn();
							//GenerateGreenTiles(scene, Color.White);
							System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
							if(game.isGameOver()) {
								return game.winner() + "Wins!";
							}
							gameplayScreenController.clickedSoldier=null;
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
					if(gameplayScreenController.possibleQueen!=null) {
						for (Tile t2 : gameplayScreenController.possibleQueen.keySet()) {  //a tile was selected before, and current tile is used to make the move.
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
								game.queenMove(prevSs, t2, gameplayScreenController.possibleQueen);
								//GenerateGreenTiles(scene, Color.White);
								System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
								if(game.isGameOver()) {
									return game.winner() + "Wins!";
								}
								System.out.println("Now It's White's turn");
								gameplayScreenController.clickedSoldier=null;
								break;

							}
						}
					}
				}

			}



		}else if( s.getColor().equals(Color.Black)) { //Tile does have a Soldier/Queen

		}else if(s.getColor().equals(Color.White))
			System.out.println("White Soldier clicked!");

		return null;

	}



}
