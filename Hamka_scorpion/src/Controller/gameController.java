package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Board;
import Model.Color;
import Model.Game;
import Model.Queen;
import Model.Soldier;
import Model.StopWatch;
import Model.Tile;
import View.gameplayScreenController;
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

	public static Board loadedBoard = null;

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


	public static gameplayScreenController gs = gameplayScreenController.getInstance();
	public static Game game = new Game(gs.p1Name, gs.p2Name, startBoard); 







	public static int[][] getBoard(){
		if(game==null) return null;
		return game.getBoard().getBoard();
	}
	
	public static Board getBoardObj(){
		if(game==null) return null;
		return game.getBoard();
	}


	public static int getSoldiers ( ) {
		int toReturn;
		return toReturn = (getTurn().equals(Color.Black)) ? game.getblackPlayerSoldiers():  game.getwhitePlayerSoldiers();
	}

	public static int getQueens ( ) {
		int toReturn;
		return toReturn = (getTurn().equals(Color.Black)) ? game.getblackPlayerQueens():  game.getwhitePlayerQueens();
	}
	
	public static int getPlayerPoints(Color c) {
		int toReturn;
		return toReturn = (c.equals(Color.Black)) ? game.getblackPlayerPoints():  game.getWhitePlayerPoints();
	}
	
	public static String getPlayer(Color c) {
		String toReturn;
		return toReturn = (c.equals(Color.Black)) ? game.getblackPlayer():  game.getwhitePlayer();
	}
	
	public static ArrayList<Tile> getPossibleMovesForSoldier(Soldier s, Color c){
		if (s==null) return null;
		ArrayList<Tile> toReturn;
		return toReturn = (c.equals(Color.Black)) ? game.getPossibleMovesForBlackSoldier(s):  game.getPossibleMovesForWhiteSoldier(s);
	}


	public static Color getTurn() {
		if(game==null) return null;
		return game.getTurn();
	}
	
	public static void swapTurn() {
		if(game!=null)
			game.handTurn();
	}
	public static String winner() {
		if(game==null) return null;
		return game.winner();
	}
	

	/*
	 * Return soldier number from Soldier object.
	 */
	public static Integer getSoldierNumber (Soldier soldier) {

		return soldier.getSoldierNumber();
	}

	/*
	 * return soldier number
	 * Input tile string, e.g (1,2)
	 * output the soldier number (1,2,11,22)
	 * --> In tile (1,2) a black Soldier exists (number 2)
	 */
	public static Integer getSoldierNumber (String soldier) {

		//Convert tile to i,j
		String[] parts= soldier.split(",");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		//Tile converted to i,j format to be used with the board 2d arary.
		Integer desti = Integer.parseInt(part1);
		Integer destj = Integer.parseInt(part2);
		Tile prevT = new Tile(desti, destj);
		Soldier prevS = gameController.game.getTileContent(prevT);

		return prevS.getSoldierNumber();
	}

	/*
	 * Return Tile object from (x,y) string format.
	 */
	public static Tile getTile (String soldier) {
		//Convert tile to i,j
		String[] parts= soldier.split(",");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		//Tile converted to i,j format to be used with the board 2d arary.
		Integer desti = Integer.parseInt(part1);
		Integer destj = Integer.parseInt(part2);
		Tile prevT = new Tile(desti, destj);

		return prevT;
	}

	/*
	 * return Soldier object from (x,y) tile as string format.
	 */
	public static Soldier getSoldier (String soldier) {

		//Convert tile to i,j
		String[] parts= soldier.split(",");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		//Tile converted to i,j format to be used with the board 2d arary.
		Integer desti = Integer.parseInt(part1);
		Integer destj = Integer.parseInt(part2);
		Tile prevT = new Tile(desti, destj);
		Soldier prevS = gameController.game.getTileContent(prevT);
		return prevS;
	}

	/*
	 * return tile's X
	 */
	public static int tileGetX(Tile t) {
		if(t==null) return -1;
		return t.getX();
	}

	/*
	 * return tile's Y
	 */
	public static int tileGetY(Tile t) {
		if(t==null) return -1;
		return t.getY();
	}

	
	
	public static boolean isGameOver() {
		if(game!=null)
			return game.isGameOver();
		else return false;
	}
	
	public static ArrayList<Tile> getKills(ArrayList<Tile> possible, Tile t){
		
		if (possible==null || possible.isEmpty() || t==null) return null;
		else return game.getKillMove(possible, t);
		
	}
	
	/*
	 * Input: Tile object and returns Soldier (Soldier in that tile)
	 */
	public static Soldier getTileContent(Tile t){
		
		if ( t==null) return null;
		else return game.getTileContent(t);
		
	}
	
	/*
	 * Return an ArrayList of the KillStreak  for Soldier 's', killStreak logic from Model.
	 */
	public static ArrayList<Tile> getKilStreakForSoldier(Soldier s){
		if(s==null) return null;
		else return game.getKillStreak(s);
	}
	
	/*
	 * Update player points, add input. (add)
	 */
	public static void updatePlayerPoints(int toAdd, Color c) {
		if( c!=null) {
			if(c.equals(Color.Black))
				game.setblackPlayerPoints(game.getblackPlayerPoints() + toAdd);
			else
				game.setWhitePlayerPoints(game.getwhitePlayerPoints() + toAdd);
		}
	}
	
//	/*
//	 * Update player points, add input. (delete)
//	 */
//	public static void deletePlayerPoints(int toDelete, Color c) {
//		if( c!=null) {
//			if(c.equals(Color.Black))
//				game.setblackPlayerPoints(game.getblackPlayerPoints() + toDelete);
//			else
//				game.setWhitePlayerPoints(game.getwhitePlayerPoints() + toDelete);
//		}
//	}
	

	public static void moveSoldier (Soldier toMove, Tile t, ArrayList<Tile> possible) {
		if(toMove!=null && t!=null && possible!=null && !possible.isEmpty()) {

			if(gameController.getTurn()==Color.Black)
				gameController.game.moveBlackSoldier(toMove, t, possible);
			else {
				gameController.game.moveWhiteSoldier(toMove, t, possible);
			}
		}
	}
	
	public static void moveQueen (Queen toMove, Tile t, HashMap<Tile, Soldier> possibleQueen) {
		if(toMove!=null && t!=null && possibleQueen!=null && !possibleQueen.isEmpty()) {
			game.queenMove(toMove, t, possibleQueen);
		}
	}
	
	public static HashMap<Tile,Soldier> getPriorityKills(Queen q){
		if(q==null) return null;
		return game.priorityKill(q);
	}
	
	public static HashMap<Tile,Soldier> getQueenBiasMoves(Queen q, String dir){
		if(q==null || dir==null) return null;
		return game.getQueenBiasMoves(q, dir);
	}
	
	public static boolean ressurectSoldier(int n, Tile t) {
		if(n==-1 || t==null) return false;
		return game.ressurectSoldier(n, t);
	}
	
	public static Tile generateRedTile (Color c, ArrayList<Tile> pos) {
		if(c==null || pos==null || pos.isEmpty()) return null;
		return game.generateRedTile(c, pos);
	}
	
	public static Tile generateGreenTile (Color c, ArrayList<Tile> pos) {
		if(c==null || pos==null || pos.isEmpty()) return null;
		return game.generateGreenTile(c, pos);
	}
	
	public static Tile generateBlueTile (Color c, ArrayList<Tile> pos) {
		if(c==null || pos==null || pos.isEmpty()) return null;
		return game.generateBlueTile(c, pos);
	}
	
	public static ArrayList<Tile> generateOrangeTiles(Color c){
		if(c==null) return null;
		return game.generateOrangeTiles(c);
	}
	
	public static ArrayList<Tile> generateYellowTiles(ArrayList<Tile> pos){
		if(pos==null || pos.isEmpty()) return null;
		return game.generateYellowTiles(pos);
	}
	
	public static boolean checkIfLegalPosition (int o, Tile t) {
		if(o==-1 || t==null) return false;
		return game.checkIfLegalPosition(o, t);
	}
	
	public static void setBoard(Board b) {
		if(b!=null)
			game.setBoard(b);
	}
	
	public static void setTurn(Color c) {
		if(c!=null)
			game.setTurn(c);
	}





}


//	public static String move(Integer i,Integer j) {
//
//		Tile current = new Tile(i, j);
//		Soldier s = game.getTileContent(current);
//		Color color = game.getTurn();
//
//		if(color==Color.Black) { //Black's turn
//			//StopWatch turnTimer = new StopWatch();
//			//turnTimer.start();
//			System.out.println();
//
//			System.out.println("switching to Black  !!");
//			moveBlackSoldier(i,j, s);
//
//			//SwitchTurntoBlack(i , j , s , currentTile , blackSoldier, chosenBlackSoldier, blackQueen,  chosenBlackQueen) ; 
//
//		}
//		else if(color==Color.White) { //turn.color = Color.White
//			System.out.println("switching to White !!");
//			//	SwitchTurntoWhite(s, i, j, currentTile, whiteSoldier, chosenWhiteSoldier, whiteQueen, chosenWhiteQueen);
//
//		}
//
//		return null;
//
//	}
//	
//	public static String moveBlackSoldier(Integer i, Integer j, Soldier s, Soldier prevS, Tile prevT) {
//
//		String toReturn = "Nothing";
//
//		for (Tile t : gs.possible) {  //a tile was selected before, and current tile is used to make the move.
//			int coordinateI = t.getX();
//			int coordinateJ = t.getY();
//			if(i==coordinateI && j == coordinateJ){
//				gs.InvalidMove = false;
//
//				game.moveBlackSoldier(prevS, t, gs.possible);
//				Soldier afterKill;
//				if(game.isGameOver())
//					gs.isGameOver = true;
//				//check if a killstreak is available.
//				boolean killed = false;
//				ArrayList<Tile> kills = game.getKillMove(gs.possible, prevT);
//				if(kills!=null && !kills.isEmpty() ) { //if this move was a kill, then we need to check for a killstreak.
//					killed = true;
//					afterKill = game.getTileContent(t);
//					if(afterKill != null)
//						gs.possible = game.getKillStreak(afterKill);
//					else {
//						gs.possible.clear();
//						gs.possible = null;
//					}
//
//				}
//
//				if(gs.possible!=null && !gs.possible.isEmpty() && killed)  //There is a streak
//					gs.lockedForStreak = true;
//				else 
//					gs.lockedForStreak = false;
//
//				if(gs.greenTile != null && gs.greenTile.equals(t)) {
//					game.setblackPlayerPoints(game.getblackPlayerPoints() + 50);
//					gs.greenTile = null;
//				}
//				else if(gs.redTile!=null && gs.redTile.equals(t)) {
//					gs.lockedForRedTile = true;
//					gs.lockedForStreak = false;
//					afterKill = game.getTileContent(t);
//					if(afterKill!=null)
//						gs.possible = game.getPossibleMovesForBlackSoldier(afterKill);
//
//					if(gs.possible!=null && !gs.possible.isEmpty()) { //if there are no possible moves dont lock.
//						gs.lockedForRedTile = true;
//					}
//					else {
//						gs.lockedForRedTile = false;
//					}
//				}else if(gs.blueTile != null && gs.blueTile.equals(t)) {
//					gs.flag++;
//					gs.lockedForBlue = true;
//				}
////				else if(gs.yellowTiles.contains(t) && !gs.isGameOver) {
////					//					View.gs.quesPop.question = SysData.getInstance().randomQuestion();
////					//					quesPop.display();
////					//					SysData.getInstance().questionIsShown(quesPop.question);
////				}
//				if(gs.redTile==null || !gs.redTile.equals(t))
//					gs.lockedForRedTile = false;
//
//				if(!gs.lockedForStreak && !gs.lockedForRedTile && !gs.yellowTiles.contains(t) && !gs.lockedForBlue) 
//					gs.SwapTurn();
//
//
//
//				//				gs.p1Points.setText(String.valueOf(this.game.getblackPlayerPoints()) ); 
//				//				gs.p2Points.setText(String.valueOf(this.game.getwhitePlayerPoints()) ); 
//				gs.flag=0;
//				//Timer Related - 
//				gs.occupiedTilesOriginalColor(gs.scene) ; 
//				gs.ClearColoredTiles(gs.scene);
//				gs.blueTile = null;
//				//GenerateGreenTiles(scene, Color.White);
//				System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
//				//				if(game.isGameOver()) {
//				//					winnerLabel.setVisible(true);
//				//					boardOFF();
//				//					winnerLabel.setText(game.winner() + " Wins!");
//				//					winnerWin.winnerLabel.setText(game.winner() + "  Wins .");
//				//					winnerWin.display();
//				//					
//			}
//
//			System.out.println("Now It's White's turn");
//			//clickedSoldier=null;
//			break;
//		}
//		try {
//			gs.refreshBoard(game, gs.scene, gs.root);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return toReturn;
//	}
//	
//	
//}



//
//	public static String moveBlackSoldier(Integer i, Integer j, Soldier s) {
//
//		if(s==null) { //IF current Tile doesnt have a soldier.
//			if(gs.possible==null && gs.possibleQueen == null)   //If possible is null then no soldier was selected before.
//				System.out.println("Please click a black Soldier!");
//			else if(gs.clickedSoldier!=null){
//
//				String prev = gs.tilesBoardMap.get(gs.clickedSoldier);
//				//System.out.println("sdsds" + prev);
//				//Convert tile to i,j
//				String[] parts2= prev.split(",");
//				String part21 = parts2[0]; 
//				String part22 = parts2[1]; 
//				//Tile converted to i,j format to be used with the board 2d arary.
//				Integer desti = Integer.parseInt(part21);
//				Integer destj = Integer.parseInt(part22);
//				Tile prevT = new Tile(desti, destj);
//
//				//System.out.println("Prev Tile: " + prevT);
//				Soldier prevS = game.getTileContent(prevT);
//
//				if(prevS.getSoldierNumber()==2) {
//					//System.out.println("99999999999999999999999999");
//					for (Tile t : gs.possible) {  //a tile was selected before, and current tile is used to make the move.
//						int coordinateI = t.getX();
//						int coordinateJ = t.getY();
//						if(i==coordinateI && j == coordinateJ){
//
//
//							System.out.println("SOLDIER");
//							//	Button to = getButtonById(currentTile.getId());
//							//to.setGraphic(blackSoldier);
//							//Button from = getButtonById(clickedSoldier);
//							//	from.setGraphic(null);
//							//System.out.println(prevS);
//							game.moveBlackSoldier(prevS, t, gs.possible);
//							game.handTurn();
//							//GenerateGreenTiles(scene, Color.White);
//							System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
//							if(game.isGameOver()) {
//								return game.winner() + "Wins!";
//							}
//							gs.clickedSoldier=null;
//							break;
//
//							//							System.out.println("Yes" + currentTile.getId());
//							//							System.out.println();
//							//							System.out.println();
//							//	board = game.getBoard();
//							//	System.out.println("Game id: " + game.getGameID());
//						}
//					}
//				}
//
//				else if(prevS.getSoldierNumber()==22) {
//					if(gs.possibleQueen!=null) {
//						for (Tile t2 : gs.possibleQueen.keySet()) {  //a tile was selected before, and current tile is used to make the move.
//							int coordinateI2 = t2.getX();
//							int coordinateJ2 = t2.getY();
//							if(i==coordinateI2 && j == coordinateJ2){
//
//								System.out.println("QUEEN");
//								//	Button to = getButtonById(currentTile.getId());
//								//to.setGraphic(blackSoldier);
//								//Button from = getButtonById(clickedSoldier);
//								//	from.setGraphic(null);
//								//System.out.println("Prev Tile: " + prevT);
//								Queen prevSs =(Queen) prevS;
//								//System.out.println(prevS);
//								game.queenMove(prevSs, t2, gs.possibleQueen);
//								//GenerateGreenTiles(scene, Color.White);
//								System.out.println(game.isGameOver() + " IS GAME OVER ??" + game.getwhitePlayerSoldiers() + " , queens = " + game.getwhitePlayerQueens());
//								if(game.isGameOver()) {
//									return game.winner() + "Wins!";
//								}
//								System.out.println("Now It's White's turn");
//								gs.clickedSoldier=null;
//								break;
//
//							}
//						}
//					}
//				}
//
//			}
//
//
//
//		}else if( s.getColor().equals(Color.Black)) { //Tile does have a Soldier/Queen
//
//		}else if(s.getColor().equals(Color.White))
//			System.out.println("White Soldier clicked!");
//
//		return null;
//
//	}




