package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Timer;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
	private static int gameID;
	private String whitePlayer;
	private String blackPlayer;
	private int whitePlayerPoints;
	private int blackPlayerPoints;
	private Timer turnTime;
	private Timer matchTime;
	private int whitePlayerSoldiers;
	private int blackPlayerSoldiers;
	private int whitePlayerQueens;
	private int blackPlayerQueens;
	private String date;
	public int seconds = 0;
	private Tile[] yellowPanels;
	private Board board;
//	private int[][] gameBoard = board.getBoard();
	/*private int[][] board = {{-1,2,-1,2,-1,2,-1,2},
							{2,-1,2,-1,2,-1,2,-1},
							{-1,2,-1,2,-1,2,-1,2},
							{0,-1,0,-1,0,-1,0,-1},
							{-1,0,-1,0,-1,0,-1,0},
							{1,-1,1,-1,1,-1,1,-1},
							{-1,1,-1,1,-1,1,-1,1},
							{1,-1,1,-1,1,-1,1,-1}
};*/
	Color turn;
	
	public Game(String whitePlayer , String blackPlayer, int[][] gameBoard) {
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.whitePlayerPoints = 0;
		this.blackPlayerPoints = 0;
//		matchTime = new Timer();
		this.whitePlayerSoldiers = 12;
		this.blackPlayerSoldiers = 12;
		// Time is needed here
		turn = Color.Black;
		
		board = new Board(gameBoard);
		
	}


	public String getDate() {
		return date;
	}


	public int getblackPlayerPoints() {
		return blackPlayerPoints;
	}


	public void setblackPlayerPoints(int blackPlayerPoints) {
		this.blackPlayerPoints = blackPlayerPoints;
	}


	public int getwhitePlayerSoldiers() {
		return whitePlayerSoldiers;
	}


	public void setwhitePlayerSoldiers(int whitePlayerSoldiers) {
		this.whitePlayerSoldiers = whitePlayerSoldiers;
	}


	public int getblackPlayerSoldiers() {
		return blackPlayerSoldiers;
	}


	public void setblackPlayerSoldiers(int blackPlayerSoldiers) {
		this.blackPlayerSoldiers = blackPlayerSoldiers;
	}


	public int getwhitePlayerQueens() {
		return whitePlayerQueens;
	}


	public void setwhitePlayerQueens(int whitePlayerQueens) {
		this.whitePlayerQueens = whitePlayerQueens;
	}


	public int getblackPlayerQueens() {
		return blackPlayerQueens;
	}


	public void setblackPlayerQueens(int blackPlayerQueens) {
		this.blackPlayerQueens = blackPlayerQueens;
	}


	public static int getGameID() {
		return gameID;
	}


	public String getwhitePlayer() {
		return whitePlayer;
	}


	public String getblackPlayer() {
		return blackPlayer;
	}


	public int getwhitePlayerPoints() {
		return whitePlayerPoints;
	}
	  
	
	public Timer getTurnTime() {
		return turnTime;
	}


	public void setTurnTime(Timer turnTime) {
		this.turnTime = turnTime;
	}


	public Timer getMatchTime() {
		return matchTime;
	}


	public void setMatchTime(Timer matchTime) {
		this.matchTime = matchTime;
	}




	public Color getTurn() {
		return this.turn;
	}
	
	
		
	 public Tile getTile(int x,int y) {
		 return new Tile(x,y);
	 }
	 /**
	  * This method changes the turn when a player hands over the turn to the other player.
	  * 
	  */
	 public void handTurn() {
		 if(turn.equals(Color.Black)) {
			 turn = Color.White;
			 //should calculate Points
			 return;
		 }
		 turn = Color.Black;
	 }
	 /**
	  * helper method that returns the content in a certain position
	  * @param x row
	  * @param y column
	  * @return the content (0/1/2/11/22)
	  */

	 /***
	  * return what soldier
	  * @param x
	  * @param y
	  * @return
	  */
	 public Soldier getTileContent(Tile tile) {
		 if(board.getSoldier(tile)== null) {
			 return null;
		 }
		 return board.getSoldier(tile);
	 }
	 
	 
	 /*****************************  Code Regarding Moves ***************************\
	 
	 
	 
	  /***
	  * This method gets the possible moves for the white soldier and return them in an array
	  * @param obj
	  * @param Tile
	  * @return
	  */
	 public ArrayList<Tile> getPossibleMovesForBlackSoldier(Soldier s) {
		 ArrayList<Tile> possibleMoves = new ArrayList<Tile>();
		 if(s.getColor() != Color.Black && s.getSoldierNumber()!=1 || board.getTileOfSoldier(s)==null)
			 return null;
		 else {
			 Tile t = s.getPosition();
			/* if(s.getSoldierNumber()!=2) {
				 System.out.println("the number of soldier"+"["+t.getX()+","+t.getY() + "] is: " + s.getSoldierNumber());
				 return null;
			 }*/
			 int x = t.getX();
			 int y = t.getY();
			 if(y == 0 && x < 7 ) { // column =0 and row 1-6
				if( board.getSoldier(new Tile(x+1,y+1)) == null) {
					possibleMoves.add(new Tile(x+1,y+1));
					 //System.out.println(TileMoves);
					 return possibleMoves;
				}
				else if ((board.getSoldier(new Tile(x+1,y+1)).getSoldierNumber() == 1 || board.getSoldier(new Tile(x+1,y+1)).getSoldierNumber()== 11) && isTileInFrame(new Tile(x+2,y+2)) && board.getSoldier(new Tile(x  + 2 , y + 2)) == null && x <6 ) { //if eat is possible and row 1-5
					possibleMoves.add(new Tile(x + 2, y + 2));
						// System.out.println(TileMoves);
						return possibleMoves;
				}
			 }else if(y == 7 && x < 7 ) { // if column =7 and row 0-6
				 if(board.getSoldier(new Tile(x + 1,y - 1)) == null) { // move possible
					 possibleMoves.add(new Tile(x+1,y-1));
					// System.out.println(TileMoves);

					 return possibleMoves;
				 }else if ((board.getSoldier(new Tile(x+1,y-1)).getSoldierNumber() == 1 || board.getSoldier(new Tile(x+1,y-1)).getSoldierNumber() ==11)  && x <6 && isTileInFrame(new Tile(x+2,y-2))&& board.getSoldier(new Tile(x + 2,y - 2)) == null) {//if eat possible and row 1-5
					 possibleMoves.add(new Tile(x + 2, y - 2));
					// System.out.println(TileMoves);
					 return possibleMoves;
				 }
			 }else if (x <7 && y>0 && y<7 ) {  
				 if(board.getSoldier(new Tile(x + 1, y - 1)) == null || board.getSoldier(new Tile(x + 1,y + 1)) == null) {// row 1-6 and column 1-6 and move possible
					 if(board.getSoldier(new Tile(x + 1, y - 1)) == null )
						 possibleMoves.add(new Tile(x + 1, y -1 ));
					 if(board.getSoldier(new Tile(x + 1,y + 1)) == null)
						 possibleMoves.add(new Tile(x + 1, y + 1 ));
				 }
				 if (x < 6 ) { // row 0-5 and column 2-5 
					 if(board.getSoldier(new Tile(x+1,y+1)) != null) {
						 if(y<6  &&(board.getSoldier(new Tile(x+1,y+1)).getSoldierNumber() == 1 || board.getSoldier(new Tile(x+1,y+1)).getSoldierNumber() ==11)&& isTileInFrame(new Tile(x+2,y+2)) && board.getSoldier(new Tile(x + 2,y + 2)) == null) //if eat to the right possible
							 possibleMoves.add(new Tile(x + 2,y + 2));
					 }
					if(getTileContent(new Tile(x+1,y-1))!=null) {
						 if(y>1 && (getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == 1 || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() ==11) && isTileInFrame(new Tile(x+2,y-2)) && board.getSoldier(new Tile(x + 2 , y - 2)) == null) // if eat to the left possible
							 possibleMoves.add(new Tile(x + 2,y - 2));
					}		
			 }
				 //System.out.println(TileMoves);

				 return possibleMoves;
		 }}
		 return null;
		 
	 }
	 
	 
	 public boolean isTileInFrame(Tile tile) {
		 if(tile.getX() > 7 ||tile.getX()<0 || tile.getY()>7 || tile.getY()<0) {
			 return false;
		 }
		return true;
	 }
	 
	 /***
	  * This method gets the possible moves for the white soldier and return them in an array
	  * @param obj
	  * @param Tile
	  * @return
	  
	 public ArrayList<Tile> getPossibleMovesForBlackSoldier( int obj , Tile Tile) {
		 ArrayList<Tile> TileMoves = new ArrayList<Tile>();
		 if(obj != 2)
			 return null;
		 else {
			 if(Tile.y == 0 && Tile.x < 7 ) { // column =0 and row 1-6
				if( getTileContent(new Tile(Tile.x+1,Tile.y+1)) == 0) {
					 TileMoves.add(new Tile(Tile.x+1,Tile.y+1));
					 //System.out.println(TileMoves);
					 return TileMoves;
				}
				else if ((getTileContent(new Tile(Tile.x+1,Tile.y+1)) == 1 || getTileContent(new Tile(Tile.x+1,Tile.y+1)) == 11) && getTileContent(new Tile(Tile.x  + 2 , Tile.y + 2)) == 0 && Tile.x <6 ) { //if eat is possible and row 1-5
						TileMoves.add(new Tile(Tile.x + 2, Tile.y + 2));
						// System.out.println(TileMoves);
						return TileMoves;
				}
			 }else if(Tile.y == 7 && Tile.x < 7 ) { // if column =7 and row 0-6
				 if(getTileContent(new Tile(Tile.x + 1,Tile.y - 1)) == 0) { // move possible
					 TileMoves.add(new Tile(Tile.x+1,Tile.y-1));
					// System.out.println(TileMoves);

					 return TileMoves;
				 }else if ((getTileContent(new Tile(Tile.x+1,Tile.y-1)) == 1 || getTileContent(new Tile(Tile.x+1,Tile.y-1)) ==11)  && Tile.x <6 && getTileContent(new Tile(Tile.x + 2,Tile.y - 2)) == 0) {//if eat possible and row 1-5
					 TileMoves.add(new Tile(Tile.x + 2, Tile.y - 2));
					// System.out.println(TileMoves);
					 return TileMoves;
				 }
			 }else if (Tile.x <7 && Tile.y>0 && Tile.y<7 ) {  
				 if(getTileContent(new Tile(Tile.x + 1, Tile.y - 1)) == 0 || getTileContent(new Tile(Tile.x + 1,Tile.y + 1)) == 0) {// row 1-6 and column 1-6 and move possible
					 if(getTileContent(new Tile(Tile.x + 1, Tile.y - 1)) == 0 )
						 TileMoves.add(new Tile(Tile.x + 1, Tile.y -1 ));
					 if(getTileContent(new Tile(Tile.x + 1,Tile.y + 1)) == 0)
						 TileMoves.add(new Tile(Tile.x + 1, Tile.y + 1 ));
				 }
				 if (Tile.x < 6 ) { // row 0-5 and column 2-5 
					 if(Tile.y<6 &&(getTileContent(new Tile(Tile.x+1,Tile.y+1)) == 1 || getTileContent(new Tile(Tile.x+1,Tile.y+1)) ==11) && getTileContent(new Tile(Tile.x + 2,Tile.y + 2)) == 0) //if eat to the right possible
						 TileMoves.add(new Tile(Tile.x + 2,Tile.y + 2));
					 if(Tile.y>1 && (getTileContent(new Tile(Tile.x+1,Tile.y-1)) == 1 || getTileContent(new Tile(Tile.x+1,Tile.y-1)) ==11) && getTileContent(new Tile(Tile.x + 2 , Tile.y - 2)) == 0) // if eat to the left possible
						TileMoves.add(new Tile(Tile.x + 2,Tile.y - 2));
					
			 }
				 //System.out.println(TileMoves);

				 return TileMoves;
		 }}
		 return null;
		 
	 }*/
	 
	 
	 /***
	  * This method gets the possible moves for the black soldier and returns then in an array
	  * @param obj
	  * @param Tile
	  * @return
	  */
	 public ArrayList<Tile> getPossibleMovesForWhiteSoldier(Soldier s) {
		 ArrayList<Tile> possibleMoves = new ArrayList<Tile>();
		 if(s.getColor()!=Color.White && s.getSoldierNumber() != 1)
			 return null;
		 else {
			 Tile t = s.getPosition();
			 int x = t.getX();
			 int y = t.getY();
			 if(y == 0 && x > 0 ) { // row 1-7 and col 0
				if( getTileContent(new Tile(x-1,y+1)) == null) { // if move possible
					possibleMoves.add(new Tile(x-1,y+1));
					 System.out.println(possibleMoves);
					 return possibleMoves;
				}
				else if ((getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == 2 || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() ==22 ) && isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x  - 2 , y + 2)) == null && x>1 ) { // if eat possible and row 2-7
					possibleMoves.add(new Tile(x - 2, y + 2));
						 System.out.println(possibleMoves);
						return possibleMoves;
				}
			 }else if(y == 7 && x >0 ) {
				 if(getTileContent(new Tile(x - 1,y - 1)) == null) {
					 possibleMoves.add(new Tile(x-1,y-1));
					 return possibleMoves;
				 }else if ((getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == 2 || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() ==22 )  && x-1 != 0 && isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x - 2,y - 2)) == null) {
					 possibleMoves.add(new Tile(x - 2, y - 2));
					 System.out.println(possibleMoves);
					 return possibleMoves;
				 }
			 }else if (x >0  && y>0 && y <7 ) {
				 if(getTileContent(new Tile(x - 1, y - 1)) == null || getTileContent(new Tile(x - 1,y + 1)) == null) {
					 if(getTileContent(new Tile(x - 1, y - 1)) == null )
						 possibleMoves.add(new Tile(x - 1, y -1 ));
					 if(getTileContent(new Tile(x - 1,y + 1)) == null)
						 possibleMoves.add(new Tile(x - 1, y + 1 ));
				 }
			  if (x > 1) {
				  if(getTileContent(new Tile(x-1,y+1))!=null) {
					   if(y>1 && (getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == 2 || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() ==22 ) && isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x - 2,y + 2)) == null) 
						   possibleMoves.add(new Tile(x - 2,y + 2)); 
				  }
				  if(getTileContent(new Tile(x-1,y-1))!=null) {
					   if(y<7  && (getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == 2 || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() ==22 ) && isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x - 2 , y - 2)) == null) 
						   possibleMoves.add(new Tile(x - 2,y - 2));
				  }
			 }
				 return possibleMoves;
		 }
		 }		 
		 return null;
	 }
	 
	 /***
	  * This method is responsible for moving white soldiers around the board by providing the currect next position and the possible moves
	  * it is also responsible for killing enemy soldiers.
	  * @param currentPos
	  * @param nextPos
	  * @param possibleMoves
	  *///Tile currentPos 
	 public void moveWhiteSoldier(Soldier s, Tile nextPos , ArrayList<Tile> possibleMoves) {
		 ArrayList<Tile> allKillsAvailable = getKills(Color.White);
		 if(possibleMoves == null || !possibleMoves.contains(nextPos) || s.getSoldierNumber()!=1) {
			 System.out.println("Wrong input");
			 return;
		 }
		 Tile currentPos = board.getTileOfSoldier(s);
		 if(ifKillExist(currentPos,possibleMoves)) {
			 ArrayList<Tile> killMoves = getKillMove(possibleMoves,currentPos);
			 if(killMoves.size() == 2) {
				 Tile midEnemyTile = getMiddleEnemySoldier(s,nextPos);
				 Soldier midEnemySol = board.getSoldier(midEnemyTile);
				 board.removeSoldier(s, currentPos);
				 board.setSoldier(s, nextPos);
				 board.removeSoldier(midEnemySol, midEnemyTile);
				 System.out.println("You killed enemy soldier!");
				 this.blackPlayerSoldiers--;
				 this.whitePlayerPoints+=100;
			 }else {
				 Tile priorityMove = killMoves.get(0);
				 if(nextPos.getX() != priorityMove.getX()  && nextPos.getY() != priorityMove.getY()) {
					 System.out.println("You shoud have killed , you lose a soldier!");
					 board.removeSoldier(s, currentPos);
					 this.whitePlayerSoldiers--;
				 }else {
						 Tile midEnemyTile = getMiddleEnemySoldier(s,nextPos);
						 Soldier midEnemySol = board.getSoldier(midEnemyTile);
						 String killed;
						 if(midEnemySol.getSoldierNumber()==2 ) {
							 killed = "soldier!";
						 }else {
							 killed = "queen!";
						 }
						 board.removeSoldier(s, currentPos);
						 board.setSoldier(s, nextPos);
						 board.removeSoldier(midEnemySol, midEnemyTile);
						 System.out.println("You killed an enemy " + killed );
						 if(killed.equals("soldier!")) {
							 this.whitePlayerSoldiers--;
						 }else {
							 this.whitePlayerQueens--;
						 }
						 this.whitePlayerPoints+=100;

				 		}
			 		}
			 }else {
				 if(allKillsAvailable != null && !allKillsAvailable.isEmpty() && (!allKillsAvailable.contains(nextPos) || !isThisMoveAKill(s, nextPos))) {
					 Tile deadSoldierTile = getSoldierWithKill(Color.White);
					 Soldier deadSoldier = board.getSoldier(deadSoldierTile);
					 if(deadSoldierTile != null) {
						 if(nextPos.getX() != 7) {
							 board.removeSoldier(s, currentPos);
							 board.setSoldier(s, nextPos);
							 board.removeSoldier(deadSoldier, deadSoldierTile);
							 this.whitePlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " , you also missed a kill , your soldier " + deadSoldierTile + " is dead.");
						 	}
						 else {
							 board.removeSoldier(s, currentPos);
							 board.setSoldier(new Queen(11), nextPos);
							 board.removeSoldier(deadSoldier, deadSoldierTile);
		
							 this.whitePlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + "  and became a queen, you also missed a kill , your soldier " + deadSoldierTile + " is dead.");
							 this.whitePlayerQueens++;
						 }
						 }
				 }else {
			 if(possibleMoves.contains(nextPos)) {
				 if(nextPos.getX() == 0) {
					 board.removeSoldier(s,currentPos);
					 board.setSoldier(new Queen(11), nextPos);
					 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " and became a queen !");
					 this.whitePlayerQueens++;

				 }else {
					 board.removeSoldier(s, currentPos);
					 board.setSoldier(s, nextPos);
					 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY());
				 }
			 }
		 }}
		 
		 for(int i=0;i<8;i++) {
			 for(int j=0; j<8;j++)
				 System.out.print(board.board[i][j] + ",");
			 System.out.println();
		 }
	 }
	 
	 /***
	  * This method is responsible for moving black soldiers around the board by providing the currect next position and the possible moves
	  * it is also responsible for killing enemy soldiers.
	  * @param currentPos
	  * @param nextPos
	  * @param possibleMoves
	  */
	 public void moveBlackSoldier(Soldier s , Tile nextPos , ArrayList<Tile> possibleMoves) {
		 ArrayList<Tile> allKillsAvailable = getKills(Color.Black);
		 if(possibleMoves == null || !possibleMoves.contains(nextPos) || s.getSoldierNumber()!=2) {
			 System.out.println("Wrong input"); 
			 return;
		 }
		 Tile currentPos = board.getTileOfSoldier(s);
		 if(ifKillExist(currentPos,possibleMoves)) {
			 ArrayList<Tile> killMoves = getKillMove(possibleMoves,currentPos);
			 if(killMoves.size() == 2) {
				 Tile midEnemyTile = getMiddleEnemySoldier(s,nextPos);
				 Soldier midEnemySol = board.getSoldier(midEnemyTile);
				 String killed;
				 if(midEnemySol.getSoldierNumber() == 1 ) {
					killed = "soldier!"; 
				 }else {
					 killed = "queen!";
				 }
				 board.removeSoldier(s, currentPos);
				 board.setSoldier(s,nextPos);
				 board.removeSoldier(midEnemySol, midEnemyTile);
				 System.out.println("You killed an enemy " + killed );
				 if(killed.equals("soldier!")) {
					 this.whitePlayerSoldiers--;
				 }else {
					 this.whitePlayerQueens--;
				 }
				 this.blackPlayerPoints+=100;
			 }else {
				 Tile priorityMove = killMoves.get(0);
				 if(nextPos.getX() != priorityMove.getX() && nextPos.getY() != priorityMove.getY()) {
					 System.out.println("You missed a kill , you lose this soldier!");
					 board.removeSoldier(s,currentPos);
					 this.blackPlayerSoldiers--;
				 }else {
						 Tile midEnemyTile = getMiddleEnemySoldier(s,nextPos);
						 Soldier midEnemySol = board.getSoldier(midEnemyTile);
						 board.removeSoldier(s, currentPos);
						 board.removeSoldier(midEnemySol,midEnemyTile);
						 board.setSoldier(s, nextPos);
						 System.out.println("You killed enemy soldier");
						 this.whitePlayerSoldiers--;
						 this.blackPlayerPoints+=100;
				 	}
			 	}
			 }else {
				 System.out.println(allKillsAvailable);
				 if(allKillsAvailable != null && !allKillsAvailable.isEmpty() && (!allKillsAvailable.contains(nextPos) || !isThisMoveAKill(s, nextPos)) ) {
					 Tile soldierHasToDieTile = getSoldierWithKill(Color.Black);
					
					 Soldier soldierHasToDie = board.getSoldier(soldierHasToDieTile);
					 if(soldierHasToDie != null) {
						 if(nextPos.getX() != 7) {
							 board.removeSoldier(s, currentPos);
							 board.setSoldier(s, nextPos);
							 board.removeSoldier(soldierHasToDie, soldierHasToDieTile);
							 this.blackPlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " , you also missed a kill , your soldier " + soldierHasToDieTile + " is dead.");
						 	}
						 else {
							 board.removeSoldier(s, currentPos);
							 board.setSoldier(new Queen(22), nextPos);
							 board.removeSoldier(soldierHasToDie, soldierHasToDieTile);
							 this.blackPlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + "  and became a queen, you also missed a kill , your soldier " + soldierHasToDieTile + " is dead.");
							 this.blackPlayerQueens++;
						 }
						 }
				 }else {
					 if(possibleMoves.contains(nextPos)) {
						 if(nextPos.getX() == 7) {
							 board.removeSoldier(s, currentPos);
							 board.setSoldier(new Queen(22), nextPos);
							 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " and became a queen!");
							 this.blackPlayerQueens++;
						 }else {
						 board.removeSoldier(s, currentPos);
						 board.setSoldier(s, nextPos);
						 System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY());
						 }
					 }
				 }
		 }
		 
		 for(int i=0;i<8;i++) {
			 for(int j=0; j<8;j++)
				 System.out.print(board.board[i][j] + ",");
			 System.out.println();
		 }
	 }
	 
	 public boolean isThisMoveAKill(Soldier s, Tile next) {
		 if(s.getPosition().getX()-1 == next.getX() || s.getPosition().getX()+1 == next.getX()) {
			 return false;
		 }
		 return true;
	 }
		public void moveQueen(Soldier s , Tile next , HashMap<Tile,Tile> possibleMoves) {
			if(possibleMoves == null || !possibleMoves.containsKey(next)) {
				System.out.println("Wrong Input");
				return;
			}
			else if(board.getSoldier(next).getSoldierNumber() != 0) {
				System.out.println("Wrong Destination");
				return;
			}
			else {
				Tile cur = board.getTileOfSoldier(s);
				if(s.getSoldierNumber() == 22) {
					board.removeSoldier(s, cur);
					board.setSoldier(s, next);
					if(possibleMoves.get(next) == null) {
						System.out.println("You have moved your queen from " + cur + " to " + next);
					}else {
						Tile midKill = possibleMoves.get(next);
						Soldier midKillSol = board.getSoldier(midKill);
						board.removeSoldier(midKillSol,midKill);
						if(getTileContent(midKill).getSoldierNumber() == 1)
							this.whitePlayerSoldiers--;
						else
							this.whitePlayerQueens--;
						System.out.println("You have moved your queen from " + cur + " to " + next + ", and killed an enemy");
						this.blackPlayerPoints+=100;
					}
				}else if ( s.getSoldierNumber() == 11) {
					board.removeSoldier(s, cur);
					board.setSoldier(s, next);
					if(possibleMoves.get(next) == null) {
						System.out.println("You have moved your queen from " + cur + " to " + next);
					}else {
						Tile midKill = possibleMoves.get(next);
						Soldier midKillSol = board.getSoldier(midKill);
						board.removeSoldier(midKillSol, midKill);
						if(getTileContent(midKill).getSoldierNumber() == 2)
							this.blackPlayerSoldiers--;
						else
							this.blackPlayerQueens--;
						System.out.println("You have moved your queen from " + cur + " to " + next + ", and killed an enemy");
						this.whitePlayerPoints+=100;
					}
				}
			}
			
//			for(int i = 0 ; i < 8; i++) {
//				for(int j = 0; j < 8; j++) 
//					System.out.print(gameBoard[i][j] + ",");
//				 System.out.println();
				
		//	}
		}
		
		public void moveStreak(Soldier solWithKillStreak,Soldier currentSol , Tile nextMove) {
			if(solWithKillStreak == null || currentSol == null || !isTileInFrame(nextMove)) {
				return;
			}
			int solNum = solWithKillStreak.getSoldierNumber();
			if(!solWithKillStreak.equals(currentSol)) {
				board.removeSoldier(solWithKillStreak, solWithKillStreak.getPosition());
				System.out.println("The kill streak was supposed to be for another soldier , therefore you lose it.");
			}else {
				ArrayList<Tile> moves = getKillStreak(solWithKillStreak);
				int op, queenOp;
				if(solNum == 2 || solNum == 22) {
					op = 1;
					queenOp=11;
				}else {
					op=2;
					queenOp=22;
				}
				if(!moves.contains(nextMove)) {
					board.removeSoldier(solWithKillStreak, solWithKillStreak.getPosition());
					System.out.println("You didn't go for the kill , therefore you lose the chosen soldier.");
				}else {
					Tile middleEnemySoldierTile = getMiddleEnemySoldier(solWithKillStreak, nextMove);
					Soldier middleEnemySoldier = board.getSoldier(middleEnemySoldierTile);
					if(isTileInFrame(nextMove) && middleEnemySoldier!=null && getTileContent(nextMove)==null && (middleEnemySoldier.getSoldierNumber()==op || middleEnemySoldier.getSoldierNumber()==queenOp )) {
						board.removeSoldier(middleEnemySoldier, middleEnemySoldierTile);
						board.removeSoldier(solWithKillStreak, solWithKillStreak.getPosition());
						board.setSoldier(solWithKillStreak, nextMove);
						System.out.println("Soldier in [" + middleEnemySoldierTile.getX()+","+middleEnemySoldierTile.getY()+"] was killed!");
					}
				}
			}
		}
		/***
		 * This method returns a random soldier that has a kill move.
		 * @param turn
		 * @return
		 */
		public Tile getSoldierWithKill(Color turn) {
			HashMap<Tile, Soldier> soldiers ;
			if(turn.equals(Color.Black)) {
				soldiers = board.getSameColorSoldiers(2);
			}else {
				soldiers = board.getSameColorSoldiers(1);
			}
			for(Map.Entry<Tile, Soldier> entry : soldiers.entrySet()) {
				Tile t = entry.getKey();
				Soldier s = entry.getValue();
					if(turn.equals(Color.Black)) {
						if(ifKillExist(t, getPossibleMovesForBlackSoldier(s))) {
							return t;
						}
					}else {
						if(ifKillExist(t, getPossibleMovesForWhiteSoldier(s))) {
							return t;
					    }
					}
			}
			return null;
		}
		/***
		 * This method gets all the possible kills for the player.
		 * @param turn
		 * @return
		 */
		public ArrayList<Tile> getKills(Color turn){
			if(turn == null) return null;
			ArrayList<Tile> blackKillsMoves = new ArrayList<Tile>();
			ArrayList<Tile> whiteKillsMoves = new ArrayList<Tile>();
			ArrayList<Tile> tiles = board.getPlayerPositions(turn);
			for(Tile t : tiles) {
				if( getTileContent(t).getSoldierNumber()==2 &&  turn.equals(Color.Black)) {
					ArrayList<Tile> possibleMoves = getPossibleMovesForBlackSoldier(board.getSoldier(t));
					if(ifKillExist(t, possibleMoves)) {
						blackKillsMoves.addAll(getKillMove(possibleMoves, t));
					}
				}else if(getTileContent(t).getSoldierNumber()==1 && turn.equals(Color.White)) {
					ArrayList<Tile> possibleMoves = getPossibleMovesForWhiteSoldier(board.getSoldier(t));
					if(ifKillExist(t, possibleMoves)) {
						whiteKillsMoves.addAll(getKillMove(possibleMoves, t));
					}
				}
			}
				if(turn.equals(Color.Black)) {
					return blackKillsMoves;
				}
					return whiteKillsMoves;
		}
	 /***
	  * This method returns the coordinate of the enemy soldier that sits between current position and next position
	  * @param color
	  * @param current
	  * @param next
	  * @return
	  *///int color ,Tile current 
	 public Tile getMiddleEnemySoldier(Soldier s , Tile next) {
		 Tile current=board.getTileOfSoldier(s);
		 if(current.getX()+2 == next.getX() && current.getY()+2 ==next.getY()) 
             return new Tile(current.getX()+1,current.getY()+1);
         if(current.getX()+2==next.getX() && current.getY()-2 == next.getY())
             return new Tile(current.getX()+1,current.getY()-1);
         if(current.getX()-2==next.getX() && current.getY()+2 == next.getY())
             return new Tile(current.getX()-1,current.getY()+1);
         if(current.getX()-2==next.getX() && current.getY()-2 == next.getY())
             return new Tile(current.getX()-1,current.getY()-1);	 	
		 return null;
	 }
	 
	 /***
	  * This method take an array of possible moves and check if there is kill move among them and returns true, otherwise false
	  * @param currentPos
	  * @param possibleMoves
	  * @return
	  */
	 public boolean ifKillExist(Tile currentPos , ArrayList<Tile> possibleMoves) {
		if(possibleMoves != null) {
			for(Tile Tile : possibleMoves) {
				if(Tile.getX() - 2 == currentPos.getX() || Tile.getX() + 2 == currentPos.getX()) {
					return true;
				}
			}
		}
		return false;
	 }
	 
	 /***
	  * This method returns the kill moves out of the possible moves .
	  * @param moves
	  * @param currentPos
	  * @return
	  */
	 public ArrayList<Tile> getKillMove(ArrayList<Tile> moves ,Tile currentPos){
		 ArrayList<Tile> toReturn = new ArrayList<Tile>();
		 if(moves != null) {
			 for(Tile Tile : moves) {
				 if(Tile.getX() - 2 == currentPos.getX() || Tile.getX() + 2 == currentPos.getX()) {
					 toReturn.add(Tile);
				 }
			 }return toReturn;
		 }
		 return null;
	 }
	 
	 /**
	  * method to use after a kill to check if a kill streak is possible
	  * @param obj 1 or 2 the type of the soldier
	  * @param Tile is the current position of the soldier  
	  * @return list of the kills possible including backwards kills
	  */
	public ArrayList<Tile> getKillStreak(Soldier s){
		if(s.getSoldierNumber() != 1 && s.getSoldierNumber()  !=2) return null; //if wasnt soldier then theres something wrong
		ArrayList<Tile> toReturn = new ArrayList<Tile>(); 
		Tile Tile =board.getTileOfSoldier(s);
		int x = board.getTileOfSoldier(s).getX();
		int y =  board.getTileOfSoldier(s).getY();
		int obj = s.getSoldierNumber();
		
		if(obj ==1 || obj == 11) {// if white soldier possible kill backwards
			ArrayList<Tile> possibleMoves = getPossibleMovesForWhiteSoldier(s);	//the soldiers current possible moves
			if(ifKillExist(Tile, possibleMoves)) {  // if theres a kill possible in the ordinary moves add it
				for( Tile p : getKillMove(possibleMoves, Tile)) 
					toReturn.add(p);
			}
			if((y==0 || y==1) && x<6 ) {
				if(getTileContent(new Tile(x+1,y+1))!= null && (getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == 2 || getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == 22) && isTileInFrame(new Tile(x+2,y+2)) && getTileContent(new Tile(x+2,y+2))==null ) 
					toReturn.add(new Tile(x+2,y+2));
			}
			if(y>1 && y<6 && x<6) {
				if(getTileContent(new Tile(x+1,y-1))!= null && (getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == 2 || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == 22) && isTileInFrame(new Tile(x+2,y-2)) && getTileContent(new Tile(x+2,y-2)) == null  )
					toReturn.add(new Tile(x+2, y-2));
				if(getTileContent(new Tile(x+1,y+1))!= null && (getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == 2 || getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == 22) && isTileInFrame(new Tile(x+2,y+2)) && getTileContent(new Tile(x+2,y+2)) == null )
					toReturn.add(new Tile(x+2, y+2));
			}
			if((y==6 || y==7) && x<6) {
				if(getTileContent(new Tile(x+1,y-1))!= null && (getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == 2 || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == 22) && isTileInFrame(new Tile(x+2,y-2)) && getTileContent(new Tile(x+2,y-2)) == null)
					toReturn.add(new Tile(x+2,y-2));
			}
		}else {  // if black soldier possible kill backwards.
			ArrayList<Tile> possibleMoves = getPossibleMovesForBlackSoldier(s);	//the soldiers current possible moves
			if(ifKillExist(Tile, possibleMoves)) {  // if theres a kill possible in the ordinary moves add it
				for( Tile p : getKillMove(possibleMoves, Tile)) 
					toReturn.add(p);
			}
			if( (y==0 || y==1) && x>1 ) {
				if(getTileContent(new Tile(x-1,y+1))!= null && (getTileContent(new Tile(x-1,y+1)).getSoldierNumber() ==1 || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == 11) && isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x-2,y+2))==null)
					toReturn.add(new Tile(x-2,y+2));
			}
			if(y>1 && y<6 && x>1) {
				if(getTileContent(new Tile(x-1,y+1))!= null && (getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == 1 || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == 11) && isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x-2,y+2))==null)
					toReturn.add(new Tile(x-2, y+2));
				if(getTileContent(new Tile(x-1,y-1))!= null && (getTileContent(new Tile(x-1,y-1)).getSoldierNumber() ==1 || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == 11) && isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x-2,y-2))==null)
					toReturn.add(new Tile(x-2,y-2));
			}
			if((y==6 || y==7) && x>1) {
				if(getTileContent(new Tile(x-1,y-1))!= null && (getTileContent(new Tile(x-1,y-1)).getSoldierNumber() ==1 || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == 11) && isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x-2,y-2))==null)
					toReturn.add(new Tile(x-2,y-2));
			}
		}
		return toReturn;
	}
	
	/*********************** Code Regarding Special (Colored) Tile ************************************\
	 * 
	 *** YELLOW TILE ***
	 */
	/***
	 * This method generates a 3 random tiles and returns them.
	 * @return
	 */
	public ArrayList<Tile> generateYellowTiles(){
		ArrayList<Tile> toReturn = new ArrayList<Tile>();
		int count = 0;
		for(;;) {
			int x = (int)(Math.random()*8);
			int y = (int)(Math.random()*8);
			if(board.getEmptyTiles().contains(new Tile(x,y))) {
				count++;
				toReturn.add(new Tile(x,y));
			}
				if(count ==3) break;	
			}
		return toReturn;
	}
	

	/***GREEN TILE ***
	 * This method generates a green tile of all the possible tiles for the player.
	 * @param turn
	 * @return
	 */
	public Tile generateGreenTile(Color turn) {
		TreeSet<Tile> allPossibleMoves = new TreeSet<Tile>();
		ArrayList<Tile> possibleMovesArray = new ArrayList<Tile>();
		if(turn == Color.Black) {
			HashMap<Tile, Soldier> blackSoldiers = board.getSameColorSoldiers(2);
			for(Soldier s : blackSoldiers.values()) {
				allPossibleMoves.addAll(getPossibleMovesForBlackSoldier(s));
			}
		}		
		else {
			
			HashMap<Tile, Soldier> whiteSoldiers = board.getSameColorSoldiers(1);
			for(Soldier s : whiteSoldiers.values()) {
				allPossibleMoves.addAll(getPossibleMovesForWhiteSoldier(s));
			}			
		}
		possibleMovesArray.addAll(allPossibleMoves);
		int numberOfMoves = possibleMovesArray.size();
		int randomNumber = ThreadLocalRandom.current().nextInt(0 , numberOfMoves );
		return possibleMovesArray.get(randomNumber);
	}

	/**
	 * @return true if player has exactly 2 soldiers and at least 1 queen, false otherwise
	 */
	public boolean checkIfBlueTile() {
		if((board.countPiece(1)==2 && board.countPiece(11)>=1) || (board.countPiece(2)==2 && board.countPiece(22)>=1)) return true;
		return false;
	}
	/**
	 * @return random Tile for a tile in the board as Blue tile.
	 */
	public Tile generateBlueTile() {
		if(checkIfBlueTile()) {
			int x = (int)(Math.random()*8);
			int y = (int)(Math.random()*8);
			return new Tile(x,y);
		}
		return null;
	}
	
/**
 * 
 * @param obj the type of the soldier (white or black)
 * @param pos the position the player wants to put it in
 * @return true if legal position, false otherwise
 */
	public boolean checkIfLegalPosition(int obj,Tile pos){
		int x = pos.getX();
		int y = pos.getY();
		int op;
		int queOp;
		if(obj == 1 ) {
			op = 2; 
			queOp=22;
		}
		else {
			op = 1;
			queOp=11;
		}
		if(getTileContent(new Tile(x-2, y)).getSoldierNumber()==op || getTileContent(new Tile(x-2, y)).getSoldierNumber()==queOp || getTileContent(new Tile(x+2, y)).getSoldierNumber()==op ||  getTileContent(new Tile(x+2, y)).getSoldierNumber()==queOp ||
				getTileContent(new Tile(x, y-2)).getSoldierNumber()==op || getTileContent(new Tile(x, y-2)).getSoldierNumber()==queOp || getTileContent(new Tile(x, y+2)).getSoldierNumber()==op ||  getTileContent(new Tile(x, y+2)).getSoldierNumber()==queOp) 
			return false;
		
		int it = 1;
		while(it<5) {
			int i = x;
			int j = y;
			int counter =0;
			while(i>=0 && i<8 && j<8 && j>=0) {
				if(it == 1) {
					i--;
					j--;
				}
				else if(it == 2) {
					i--;
					j++;
				}
				else if(it ==3) {
					i++;
					j--;
				}else {
					i++;
					j++;
				}
				if(counter ==2 ) break;		
				if(counter<2 && ( getTileContent(new Tile(i,j)).getSoldierNumber() == op || getTileContent(new Tile(i,j)).getSoldierNumber() == queOp )) {
					System.out.println(counter);
					return false;
				}
				if(getTileContent(new Tile(i, j)) == null) counter ++;//System.out.println(counter);
			}
			   it++;
		}
		return true;
	}
	
	public boolean ressurectSoldier(int obj, Tile pos) {
        if(checkIfLegalPosition(obj, pos)) {
            if(obj == 1) whitePlayerSoldiers++;
            else blackPlayerSoldiers++;
            board.setSoldier(new Soldier(obj), pos);
            return true;
        }
        return false;
    }

	/*** ORANGE TILES ***
	 * This method generates orange tiles for the player and returns them.
	 * @param turn
	 * @return
	 */
	public ArrayList<Tile> generateOrangeTiles(Color turn){
		ArrayList<Tile> toReturn = new ArrayList<Tile>();
		TreeSet<Tile> allPossibleMoves = new TreeSet<Tile>();
		if(turn == null)
			return null;
		if(turn == Color.Black) {
			for(Soldier s : board.getSameColorSoldiers(2).values()) {
				allPossibleMoves.addAll(getPossibleMovesForBlackSoldier(s));
			}
		}	
		else {
			for(Soldier s : board.getSameColorSoldiers(1).values()) {
				allPossibleMoves.addAll(getPossibleMovesForWhiteSoldier(s));
			}
		}
		toReturn.addAll(allPossibleMoves);
		return toReturn;
	}
	
	
	/*** RED TILE ***
	 * This method generates a random red tile from the player's possible moves and returns it.
	 * @param turn
	 * @return
	 */
	public Tile generateRedTile(Color turn) {
        if(turn == null) return null;
        ArrayList<Tile> candidates = new ArrayList<Tile>();
       if(getKills(turn)!= null && getKills(turn).size()>0) {
           for(Tile tile : board.getPlayerPositions(turn)) {

               if(turn.equals(Color.Black) && getPossibleMovesForBlackSoldier(getTileContent(tile))!=null) {
                   candidates.addAll(getPossibleMovesForBlackSoldier(getTileContent(tile)));
               }else if (getPossibleMovesForWhiteSoldier(getTileContent(tile))!=null){
                   candidates.addAll(getPossibleMovesForWhiteSoldier(getTileContent(tile)));
               }
           }
       }
       int max = candidates.size();
     //  System.out.println(candidates);
       int random = (int)(Math.random()*max);
        return candidates.get(random);
    }
	
	
	public HashMap<Tile,Soldier> getQueenBiasMoves(Queen queen , String direction){
		HashMap<Tile,Soldier> movesMap = new HashMap<>();
		if(queen == null || queen.getSoldierNumber() == 1 || queen.getSoldierNumber() == 2 || direction.equals(""))
			return null;
		int opSol,opQue;
		int x = queen.getPosition().getX();
		int y = queen.getPosition().getY();
		if(queen.getSoldierNumber() == 11) {
			opSol = 2;
			opQue = 22;
		}else {
			opSol = 1;
			opQue = 11;
		}
		if(direction.equals("TR")) {// if top right
			while((isTileInFrame(new Tile(x-1,y+1)) && getTileContent(new Tile(x-1,y+1)) == null) || ((getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x-2,y+2)) == null))) {
				if(getTileContent(new Tile(x-1,y+1)) == null){
					queen.getQueenMoves().put(new Tile(x-1,y+1), null);
					movesMap.put(new Tile(x-1,y+1), null);
				}else if ((getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x-2,y+2)) == null)) {
					queen.getQueenMoves().put(new Tile(x-2,y+2),getTileContent(new Tile(x-1,y+1)));
					movesMap.put(new Tile(x-2,y+2),getTileContent(new Tile(x-1,y+1)));
				}
				x--;
				y++;
			}
		}else if(direction.equals("TL")) {//if top left
			while((isTileInFrame(new Tile(x-1,y-1)) && getTileContent(new Tile(x-1,y-1)) == null) || ((getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x-2,y-2)) == null))) {
				if(getTileContent(new Tile(x-1,y-1)) == null){
					queen.getQueenMoves().put(new Tile(x-1,y-1), null);
					movesMap.put(new Tile(x-1,y-1), null);
				}else if ((getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x-2,y-2)) == null)) {
					queen.getQueenMoves().put(new Tile(x-2,y-2),getTileContent(new Tile(x-1,y-1)));
					movesMap.put(new Tile(x-2,y-2),getTileContent(new Tile(x-1,y-1)));
				}
				x--;
				y--;
			}
		}else if(direction.equals("BR")) { //if bottom right
			while((isTileInFrame(new Tile(x+1,y+1)) && getTileContent(new Tile(x+1,y+1)) == null) || ((getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y+2)) && getTileContent(new Tile(x+2,y+2)) == null))) {
				if(getTileContent(new Tile(x+1,y+1)) == null){
					queen.getQueenMoves().put(new Tile(x+1,y+1), null);
					movesMap.put(new Tile(x+1,y+1), null);
				}else if ((getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y+2)) && getTileContent(new Tile(x+2,y+2)) == null)) {
					queen.getQueenMoves().put(new Tile(x+2,y+2),getTileContent(new Tile(x+1,y+1)));
					movesMap.put(new Tile(x+2,y+2),getTileContent(new Tile(x+1,y+1)));
				}
				x++;
				y++;
			}
		}else if(direction.equals("BL")) { //if Bottom left
			while((isTileInFrame(new Tile(x+1,y-1)) && getTileContent(new Tile(x+1,y-1)) == null) || ((getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y-2)) && getTileContent(new Tile(x+2,y-2)) == null))) {
				if(getTileContent(new Tile(x+1,y-1)) == null){
					queen.getQueenMoves().put(new Tile(x+1,y-1), null);
					movesMap.put(new Tile(x+1,y-1), null);
				}else if ((getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y-2)) && getTileContent(new Tile(x+2,y-2)) == null)) {
					queen.getQueenMoves().put(new Tile(x+2,y-2),getTileContent(new Tile(x+1,y-1)));
					movesMap.put(new Tile(x+2,y-2),getTileContent(new Tile(x+1,y-1)));
				}
				x++;
				y--;
			}
			
		}
		return movesMap;
	}
	/***
	 * This method takes the position of the queen as parameter and returns all the possible moves in all of the 4 biases.
	 * @param obj
	 * @param pos
	 * @return
	 *//*
	public HashMap<Tile,Tile> getQueenBiasMoves(int obj , Tile pos,String dir,HashMap<Tile,Tile> mapMoves){
		int i = 1 ,j = 1;
		ArrayList<Tile> toReturn = new ArrayList<Tile>();
		int opSol,opQue;
		if(obj == 11) { 
			opSol= 2;
			opQue = 22;
		}
		else { opSol =1;
				opQue = 11;
		}
		// get the possible moves in the right-top bias
		if(dir.equals("TOP-RIGHT")) {
			while(getContentWithXandY(pos.x - i , pos.y + j) == 0 || ((getContentWithXandY(pos.x - i, pos.y + j) == opSol || getContentWithXandY(pos.x - i,pos.y + j) == opQue)
					&& getContentWithXandY(pos.x - (i+1), pos.y + (j+1))== 0)) {
				if((getContentWithXandY(pos.x - i, pos.y + j) == opSol || getContentWithXandY(pos.x - i,pos.y + j) == opQue) && getContentWithXandY(pos.x - (i+1), pos.y + (j+1) )== 0) {
					if(mapMoves.containsKey(new Tile(pos.x - (i+1),pos.y + (j+1)))) {
						if(mapMoves.get(new Tile(pos.x - (i+1),pos.y + (j+1))) == null) {
							mapMoves.put(new Tile(pos.x - (i+1),pos.y + (j+1)), new Tile(pos.x - i, pos.y + j));
						}
					}else {
						mapMoves.put(new Tile(pos.x - (i+1),pos.y + (j+1)), new Tile(pos.x - i, pos.y + j));
					}
//					toReturn.add(getTile(pos.x - (i+1), pos.y + (j+1)));
					break;
				}
//				toReturn.add(getTile(pos.x - i , pos.y + j));
				if(getContentWithXandY(pos.x - i , pos.y + j) == 0 && !mapMoves.containsKey(new Tile(pos.x - i , pos.y + j)))
					mapMoves.put(new Tile(pos.x - i , pos.y + j),null);
				i++;
				j++;
			}}
//			i =1 ;
//			j = 1;
			// get the possible moves in the left-top bias
			else if(dir.equals("TOP-LEFT")) {
			while(getContentWithXandY(pos.x - i, pos.y - j) == 0 || ((getContentWithXandY(pos.x - i, pos.y - j) == opSol || getContentWithXandY(pos.x - i,pos.y - j) == opQue)
					&& getContentWithXandY(pos.x - (i+1), pos.y - (j+1))== 0)) {
				if((getContentWithXandY(pos.x - i, pos.y - j) == opSol || getContentWithXandY(pos.x - i,pos.y - j) == opQue) && getContentWithXandY(pos.x - (i+1), pos.y - (j+1) )== 0) {
					if(mapMoves.containsKey(new Tile(pos.x - (i+1), pos.y - (j+1)))) {
						if(mapMoves.get(new Tile(pos.x - (i+1) , pos.y - (j+1))) == null) {
							mapMoves.put(new Tile(pos.x - (i+1) , pos.y - (j+1)), new Tile(pos.x - i,pos.y - j));
						}
					}else {
						mapMoves.put(new Tile(pos.x - (i+1) , pos.y - (j+1)), new Tile(pos.x - i,pos.y - j));
					}
//					toReturn.add(getTile(pos.x - (i+1), pos.y - (j+1)));
					break;
				}
				if(getContentWithXandY(pos.x - i, pos.y - j) == 0 && !mapMoves.containsKey(new Tile(pos.x - i, pos.y - j)))
					mapMoves.put(new Tile(pos.x - i,pos.y - j), null);
//				toReturn.add(getTile(pos.x - i, pos.y - j));
				i++;
				j++;
			}}
//				i=1;
//				j=1;
			else if(dir.equals("BOTTOM-RIGHT")) {
			// get the possible moves in the right-bottom bias
			while(getContentWithXandY(pos.x + i, pos.y + j) == 0 || ((getContentWithXandY(pos.x + i, pos.y + j) == opSol || getContentWithXandY(pos.x + i,pos.y + j) == opQue)
					&& getContentWithXandY(pos.x + (i+1), pos.y + (j+1))== 0)) {
				if((getContentWithXandY(pos.x + i, pos.y + j) == opSol || getContentWithXandY(pos.x + i,pos.y + j) == opQue) && getContentWithXandY(pos.x + (i+1), pos.y + (j+1))== 0) {
					if(mapMoves.containsKey(new Tile(pos.x + (i+1), pos.y + (j+1)))) {
						if(mapMoves.get(new Tile(pos.x + (i+1), pos.y + (j+1))) == null) {
							mapMoves.put(new Tile(pos.x + (i+1), pos.y + (j+1)), new Tile(pos.x + i, pos.y + j));
						}
					}else {
						mapMoves.put(new Tile(pos.x + (i+1), pos.y + (j+1)), new Tile(pos.x + i, pos.y + j));
					}
//					toReturn.add(getTile(pos.x + (i+1),pos.y + (j+1)));
					break;
					}
				if(getContentWithXandY(pos.x + i, pos.y + j) == 0 && !mapMoves.containsKey(new Tile(pos.x + i, pos.y + j)))
					mapMoves.put(new Tile(pos.x + i, pos.y + j), null);

//				toReturn.add(getTile(pos.x + i,pos.y + j));
				i++;
				j++;
				}}
			
//				i=1;
//				j=1;
			else if(dir.equals("BOTTOM-LEFT")) {
				// get the possible moves in the left-bottom bias
			while(getContentWithXandY(pos.x + i, pos.y - j) == 0 || ((getContentWithXandY(pos.x + i, pos.y - j) == opSol || getContentWithXandY(pos.x + i,pos.y - j) == opQue)
					&& getContentWithXandY(pos.x + (i+1), pos.y - (j+1))== 0)) {
				if((getContentWithXandY(pos.x + i, pos.y - j) == opSol || getContentWithXandY(pos.x + i,pos.y - j) == opQue) && getContentWithXandY(pos.x + (i+1), pos.y - (j+1))== 0)
				{
					if(mapMoves.containsKey(new Tile(pos.x + (i+1), pos.y - (j+1)))) {
						if(mapMoves.get(new Tile(pos.x + (i+1), pos.y - (j+1))) == null) {
							mapMoves.put(new Tile(pos.x + (i+1), pos.y - (j+1)), new Tile(pos.x + i, pos.y - j));
						}
					}else {
						mapMoves.put(new Tile(pos.x + (i+1), pos.y - (j+1)), new Tile(pos.x + i, pos.y - j));
					}
//					toReturn.add(getTile(pos.x + (i+1),pos.y - (j+1)));
					break;
				}
				if(getContentWithXandY(pos.x + i, pos.y - j) == 0 && !mapMoves.containsKey(new Tile(pos.x + i, pos.y - j))){
					mapMoves.put(new Tile(pos.x + i, pos.y - j), null);

				}
//				toReturn.add(getTile(pos.x + i,pos.y - j));
				i++;
				j++;
			}}
		return mapMoves;
	}
	
	public HashMap<Tile,Tile> getQueenMoves(int obj,Tile pos){
		HashMap<Tile,Tile> myMoves = new HashMap<Tile,Tile>();
		myMoves = getQueenBiasMoves(obj, pos, "TOP-LEFT", myMoves);
		myMoves = getQueenBiasMoves(obj, pos, "TOP-RIGHT", myMoves);
		myMoves = getQueenBiasMoves(obj, pos, "BOTTOM-LEFT", myMoves);
		myMoves = getQueenBiasMoves(obj, pos, "BOTTOM-RIGHT", myMoves);
		myMoves = getQueenCrossBoardMoves(obj, pos, myMoves);
//		for(Map.Entry<Tile, Tile> temp : myMoves.entrySet()) {
//			System.out.println(temp.getKey() + "  , " + temp.getValue());
//		}
		return myMoves;
	}
	*/
	/***
	 * This method gets the possible moves crossing the board (NOT FINISHED - may have bugs).
	 * @param obj
	 * @param pos
	 * @param map
	 * @return
	 *//*
	public HashMap<Tile,Tile> getQueenCrossBoardMoves(int obj , Tile pos,HashMap<Tile,Tile> map){
		ArrayList<Tile> boardEdgesMoves = getLastPossibleMoveInBias(obj, pos,map);
		HashSet<Tile> treeMoves = new HashSet<>();
		String dir = "";
		int opSol,opQue;
		if(obj == 11) {
			opSol = 2;
			opQue = 22;
		}else if(obj == 22) {
			opSol = 1;
			opQue = 11;
		}
		if(boardEdgesMoves != null && !boardEdgesMoves.isEmpty())
			for(Tile Tile : boardEdgesMoves) {
				if((pos.x < Tile.x && pos.y > Tile.y)) {
					dir = "BOTTOM-LEFT";
				} else if (pos.x > Tile.x && pos.y > Tile.y) {
					dir = "TOP-LEFT";
				}else if((pos.x > Tile.x && pos.y < Tile.y )) {
					dir = "TOP-RIGHT";
				}else if (pos.x < Tile.x && pos.y < Tile.y) {
					dir = "BOTTOM-RIGHT";
				}
				if(Tile.y == 0 && Tile.x == 7 && dir.equals("BOTTOM-LEFT") ){
					if(getContentWithXandY(Tile.x-7, Tile.y+7) == 0) {
						treeMoves.add(new Tile(Tile.x - 7,Tile.y + 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x - 7,Tile.y + 7), "BOTTOM-LEFT", map);
					}
				}else if(Tile.y == 0 && dir.equals("BOTTOM-LEFT")) {
					if(getContentWithXandY(Tile.x + 1, Tile.y + 7) == 0) {
						treeMoves.add(new Tile(Tile.x + 1,Tile.y + 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x + 1,Tile.y + 7), "BOTTOM-LEFT", map);
					}
				}else if(Tile.y == 0 && dir.equals("TOP-LEFT")) {
					if(getContentWithXandY(Tile.x - 1, Tile.y + 7) == 0) {
						treeMoves.add(new Tile(Tile.x - 1,Tile.y + 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x - 1,Tile.y + 7), "TOP-LEFT", map);
					}
				}else if(Tile.y == 7 && Tile.x == 0 && dir.equals("TOP-RIGHT")) {
					if(getContentWithXandY(Tile.x + 7, Tile.y - 7) == 0) {
						treeMoves.add(new Tile(Tile.x + 7,Tile.y - 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x + 7,Tile.y - 7), "TOP-RIGHT", map);
					}
				}else if(Tile.y == 7 && dir.equals("TOP-RIGHT")) {
					if(getContentWithXandY(Tile.x - 1, Tile.y - 7) == 0) {
						treeMoves.add(new Tile(Tile.x - 1,Tile.y - 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x - 1,Tile.y - 7), "TOP-RIGHT", map);

					}
				}else if(Tile.y == 7 && dir.equals("BOTTOM-RIGHT")) {
					if(getContentWithXandY(Tile.x + 1, Tile.y - 7) == 0) {
						treeMoves.add(new Tile(Tile.x + 1,Tile.y - 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x + 1,Tile.y - 7), "BOTTOM-RIGHT", map);
					}
				}else if(Tile.x == 0 && dir.equals("TOP-RIGHT")) {
					if(getContentWithXandY(Tile.x + 7, Tile.y + 1) == 0) {
						treeMoves.add(new Tile(Tile.x + 7,Tile.y + 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x + 7,Tile.y + 1), "TOP-RIGHT", map);
					}
				}else if(Tile.x == 0 && dir.equals("TOP-LEFT")) {
					if(getContentWithXandY(Tile.x + 7, Tile.y - 1) == 0) {
						treeMoves.add(new Tile(Tile.x + 7,Tile.y - 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x + 7,Tile.y - 1), "TOP-LEFT", map);
					}
				}else if(Tile.x == 7 && dir.equals("BOTTOM-LEFT")) {
					if(getContentWithXandY(Tile.x - 7, Tile.y -1 ) == 0) {
						treeMoves.add(new Tile(Tile.x - 7,Tile.y - 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x - 7,Tile.y - 1), "BOTTOM-RIGHT", map);
					}
				}else if(Tile.x == 7 && dir.equals("BOTTOM-RIGHT")) {
					if(getContentWithXandY(Tile.x - 7, Tile.y + 1) == 0) {
						treeMoves.add(new Tile(Tile.x - 7,Tile.y + 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Tile(Tile.x - 7,Tile.y + 1), "TOP-RIGHT", map);
					}
				}
			}
		
		if(!treeMoves.isEmpty())
			for(Tile temp : treeMoves) {
				if(!map.containsKey(temp)) {
					map.put(temp, null);
				}
			}
		
		return map;
	}
	
	
	/***
	 * This private method takes the queen possible moves and returns the ones on the edges of the board .
	 * @param obj
	 * @param pos
	 * @param queenMoves
	 * @return
	 *//*
	private ArrayList<Tile> getLastPossibleMoveInBias(int obj ,Tile pos,HashMap<Tile,Tile> queenMoves){
		ArrayList<Tile> movesToReturn = new ArrayList<Tile>();
		
		if(pos.x == 0 || pos.x == 7 || pos.y == 0 || pos.y == 7) 
			movesToReturn.add(pos);
		for(Map.Entry<Tile,Tile> Tile : queenMoves.entrySet()) {
			if(Tile.getKey().x == 0 || Tile.getKey().x == 7 || Tile.getKey().y == 0 || Tile.getKey().y == 7) {
				if(Tile.getValue() == null)
					movesToReturn.add(Tile.getKey());
			}
		}
		return movesToReturn;
	}
	*/
	/***
	 * Checks if the game is over or not.
	 * @return
	 */
	public boolean isGameOver() {
		if((this.whitePlayerSoldiers == 0 && this.whitePlayerQueens == 0 ) || (this.blackPlayerQueens == 0 && this.blackPlayerSoldiers == 0))
			return true;
		else 
			return false;
	}
	/***
	 * Returns the winner name
	 * @return
	 */
	public String winner() {
		if(this.whitePlayerPoints < this.blackPlayerPoints) 
			return this.blackPlayer;
		else  if(this.whitePlayerPoints > this.blackPlayerPoints)
			return this.whitePlayer;
		else {
			if(this.whitePlayerSoldiers==0 && this.whitePlayerQueens==0 )
				return this.blackPlayer;
			else
				return this.whitePlayer;
		}
	}

	public void printBoard() {
		for(int i = 0 ; i < 8; i++) {
			for(int j = 0; j < 8; j++) 
				System.out.print(board.board[i][j] + ",");
			 System.out.println();
			
		}
	}
	

	
	
}
