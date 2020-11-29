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
	private Pair[] yellowPanels;
	private int[][] board = {{-1,2,-1,2,-1,2,-1,2},
							{2,-1,2,-1,2,-1,2,-1},
							{-1,2,-1,2,-1,2,-1,2},
							{0,-1,11,-1,1,-1,0,-1},
							{-1,0,-1,0,-1,0,-1,0},
							{1,-1,0,-1,1,-1,1,-1},
							{-1,2,-1,1,-1,1,-1,1},
							{1,-1,0,-1,1,-1,1,-1}
};
	PlayerTurn turn;
	
	public Game(String whitePlayer , String blackPlayer) {
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.whitePlayerPoints = 0;
		this.blackPlayerPoints = 0;
//		matchTime = new Timer();
		this.whitePlayerSoldiers = 12;
		this.blackPlayerSoldiers = 12;
		// Time is needed here
		turn = PlayerTurn.Black;
		
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


	public int[][] getBoard() {
		return board;
	}


	public void setBoard(int[][] board) {
		this.board = board;
	}

	public PlayerTurn getTurn() {
		return this.turn;
	}
	/**
	 * 
	 * @author mutla
	 *enum for the turn of player
	 */
	public enum PlayerTurn {
		Black,White
		}
		/**
		 * 
		 * @author mutla
		 *class to get the coloured pannel x,y
		 */
		private class Pair {
			int x;// row
			int y;//col
			
			private Pair(int x,int y){
				this.x = x;
				this.y = y;
			}
			public String toString() {
				return "[x="+x+",y="+y+"]";
			}
			
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + getEnclosingInstance().hashCode();
				result = prime * result + x;
				result = prime * result + y;
				return result;
			}
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Pair other = (Pair) obj;
				if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
					return false;
				if (x != other.x)
					return false;
				if (y != other.y)
					return false;
				return true;
			}
			
			
			private Game getEnclosingInstance() {
				return Game.this;
			}
			
			
		}
	
	 /**
	  * This method changes the turn when a player hands over the turn to the other player.
	  * 
	  */
	 public void handTurn() {
		 if(turn.equals(PlayerTurn.Black)) {
			 turn = PlayerTurn.White;
			 //should calculate Points
			 return;
		 }
		 turn = PlayerTurn.Black;
	 }
	 /**
	  * helper method that returns the content in a certain position
	  * @param x row
	  * @param y column
	  * @return the content (0/1/2/11/22)
	  */
	 public int getContentWithXandY(int x , int y) {
		 return getTileContent(new Pair(x,y));
	 }
	 /**
	  * 
	  * @param x row
	  * @param y column
	  * @return generates pair object from x and y
	  */
	 public Pair getPair(int x,int y) {
		 return new Pair(x,y);
	 }
	 /***
	  * return what insided the tile (empty ? , soldier ? , queen ? )
	  * @param x
	  * @param y
	  * @return
	  */
	 public int getTileContent(Pair pair) {
		 if(pair.x >=0 && pair.x <= 7 && pair.y >=0 && pair.y <=7)
			 return this.getBoard()[pair.x][pair.y];
		 return -2;
	 }
	 
	 
	 /*****************************  Code Regarding Moves ***************************\
	 
	 /***
	  * This method gets the possible moves for the white soldier and return them in an array
	  * @param obj
	  * @param pair
	  * @return
	  */
	 public ArrayList<Pair> getPossibleMovesForBlackSoldier(int obj , Pair pair) {
		 ArrayList<Pair> pairMoves = new ArrayList<Pair>();
		 if(obj != 2)
			 return null;
		 else {
			 if(pair.y == 0 && pair.x < 7 ) { // column =0 and row 1-6
				if( getTileContent(new Pair(pair.x+1,pair.y+1)) == 0) {
					 pairMoves.add(new Pair(pair.x+1,pair.y+1));
					 //System.out.println(pairMoves);
					 return pairMoves;
				}
				else if ((getTileContent(new Pair(pair.x+1,pair.y+1)) == 1 || getTileContent(new Pair(pair.x+1,pair.y+1)) == 11) && getTileContent(new Pair(pair.x  + 2 , pair.y + 2)) == 0 && pair.x <6 ) { //if eat is possible and row 1-5
						pairMoves.add(new Pair(pair.x + 2, pair.y + 2));
						// System.out.println(pairMoves);
						return pairMoves;
				}
			 }else if(pair.y == 7 && pair.x < 7 ) { // if column =7 and row 0-6
				 if(getTileContent(new Pair(pair.x + 1,pair.y - 1)) == 0) { // move possible
					 pairMoves.add(new Pair(pair.x+1,pair.y-1));
					// System.out.println(pairMoves);

					 return pairMoves;
				 }else if ((getTileContent(new Pair(pair.x+1,pair.y-1)) == 1 || getTileContent(new Pair(pair.x+1,pair.y-1)) ==11)  && pair.x <6 && getTileContent(new Pair(pair.x + 2,pair.y - 2)) == 0) {//if eat possible and row 1-5
					 pairMoves.add(new Pair(pair.x + 2, pair.y - 2));
					// System.out.println(pairMoves);
					 return pairMoves;
				 }
			 }else if (pair.x <7 && pair.y>0 && pair.y<7 ) {  
				 if(getTileContent(new Pair(pair.x + 1, pair.y - 1)) == 0 || getTileContent(new Pair(pair.x + 1,pair.y + 1)) == 0) {// row 1-6 and column 1-6 and move possible
					 if(getTileContent(new Pair(pair.x + 1, pair.y - 1)) == 0 )
						 pairMoves.add(new Pair(pair.x + 1, pair.y -1 ));
					 if(getTileContent(new Pair(pair.x + 1,pair.y + 1)) == 0)
						 pairMoves.add(new Pair(pair.x + 1, pair.y + 1 ));
				 }
				 if (pair.x < 6 ) { // row 0-5 and column 2-5 
					 if(pair.y<6 &&(getTileContent(new Pair(pair.x+1,pair.y+1)) == 1 || getTileContent(new Pair(pair.x+1,pair.y+1)) ==11) && getTileContent(new Pair(pair.x + 2,pair.y + 2)) == 0) //if eat to the right possible
						 pairMoves.add(new Pair(pair.x + 2,pair.y + 2));
					 if(pair.y>1 && (getTileContent(new Pair(pair.x+1,pair.y-1)) == 1 || getTileContent(new Pair(pair.x+1,pair.y-1)) ==11) && getTileContent(new Pair(pair.x + 2 , pair.y - 2)) == 0) // if eat to the left possible
						pairMoves.add(new Pair(pair.x + 2,pair.y - 2));
					
			 }
				 //System.out.println(pairMoves);

				 return pairMoves;
		 }}
		 return null;
		 
	 }
	 
	 
	 /***
	  * This method gets the possible moves for the black soldier and returns then in an array
	  * @param obj
	  * @param pair
	  * @return
	  */
	 public ArrayList<Pair> getPossibleMovesForWhiteSoldier(int obj , Pair pair) {
		 ArrayList<Pair> pairMoves = new ArrayList<Pair>();
		 if(obj != 1)
			 return null;
		 else {
			 if(pair.y == 0 && pair.x > 0 ) { // row 1-7 and col 0
				if( getTileContent(new Pair(pair.x-1,pair.y+1)) == 0) { // if move possible
					 pairMoves.add(new Pair(pair.x-1,pair.y+1));
					 System.out.println(pairMoves);
					 return pairMoves;
				}
				else if ((getTileContent(new Pair(pair.x-1,pair.y+1)) == 2 || getTileContent(new Pair(pair.x-1,pair.y+1)) ==22 ) && getTileContent(new Pair(pair.x  - 2 , pair.y + 2)) == 0 && pair.x>1 ) { // if eat possible and row 2-7
						pairMoves.add(new Pair(pair.x - 2, pair.y + 2));
						 System.out.println(pairMoves);
						return pairMoves;
				}
			 }else if(pair.y == 7 && pair.x >0 ) {
				 if(getTileContent(new Pair(pair.x - 1,pair.y - 1)) == 0) {
					 pairMoves.add(new Pair(pair.x-1,pair.y-1));
					 return pairMoves;
				 }else if ((getTileContent(new Pair(pair.x-1,pair.y-1)) == 2 || getTileContent(new Pair(pair.x-1,pair.y-1)) ==22 )  && pair.x-1 != 0 && getTileContent(new Pair(pair.x - 2,pair.y - 2)) == 0) {
					 pairMoves.add(new Pair(pair.x - 2, pair.y - 2));
					 System.out.println(pairMoves);
					 return pairMoves;
				 }
			 }else if (pair.x >0  && pair.y>0 && pair.y <7 ) {
				 if(getTileContent(new Pair(pair.x - 1, pair.y - 1)) == 0 || getTileContent(new Pair(pair.x - 1,pair.y + 1)) == 0) {
					 if(getTileContent(new Pair(pair.x - 1, pair.y - 1)) == 0 )
						 pairMoves.add(new Pair(pair.x - 1, pair.y -1 ));
					 if(getTileContent(new Pair(pair.x - 1,pair.y + 1)) == 0)
						 pairMoves.add(new Pair(pair.x - 1, pair.y + 1 ));
				 }
			  if (pair.x > 1) {
				 if(pair.y>1 && (getTileContent(new Pair(pair.x-1,pair.y+1)) == 2 || getTileContent(new Pair(pair.x-1,pair.y+1)) ==22 ) && getTileContent(new Pair(pair.x - 2,pair.y + 2)) == 0) 
					 pairMoves.add(new Pair(pair.x - 2,pair.y + 2));
				 if(pair.y<6  && (getTileContent(new Pair(pair.x-1,pair.y-1)) == 2 || getTileContent(new Pair(pair.x-1,pair.y-1)) ==22 ) && getTileContent(new Pair(pair.x - 2 , pair.y - 2)) == 0) 
					pairMoves.add(new Pair(pair.x - 2,pair.y - 2));
			 }
				 return pairMoves;
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
	  */
	 public void moveWhiteSoldier(Pair currentPos , Pair nextPos , ArrayList<Pair> possibleMoves) {
		 ArrayList<Pair> allKillsAvailable = getKills(PlayerTurn.White);
		 if(possibleMoves == null || !possibleMoves.contains(nextPos)) {
			 System.out.println("Wrong input");
			 return;
		 }
		 if(ifKillExist(currentPos,possibleMoves)) {
			 ArrayList<Pair> killMoves = getKillMove(possibleMoves,currentPos);
			 if(killMoves.size() == 2) {
				 Pair midEnemySol = getMiddleEnemySoldier(1,currentPos,nextPos);
				 board[nextPos.x][nextPos.y] = 1;
				 board[midEnemySol.x][midEnemySol.y] = 0;
				 board[currentPos.x][currentPos.y] = 0;
				 System.out.println("You killed enemy soldier!");
				 this.blackPlayerSoldiers--;
				 this.whitePlayerPoints+=100;
			 }else {
				 Pair priorityMove = killMoves.get(0);
				 if(nextPos.x != priorityMove.x && nextPos.y != priorityMove.y) {
					 System.out.println("You shoud have killed , you lose a soldier!");
					 board[currentPos.x][currentPos.y] = 0;
					 this.whitePlayerSoldiers--;
				 }else {
						 Pair midEnemySol = getMiddleEnemySoldier(1,currentPos,nextPos);
						 String killed;
						 if(getTileContent(midEnemySol)==2 ) {
							 killed = "soldier!";
						 }else {
							 killed = "queen!";
						 }
						 board[nextPos.x][nextPos.y] = 1;
						 board[midEnemySol.x][midEnemySol.y] = 0;
						 board[currentPos.x][currentPos.y] = 0;
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
				 if(allKillsAvailable != null && !allKillsAvailable.isEmpty() && !allKillsAvailable.contains(nextPos)) {
					 Pair soldierHasToDie = getSoldierWithKill(PlayerTurn.White);
					 if(soldierHasToDie != null) {
						 if(nextPos.x != 7) {
							 board[currentPos.x][currentPos.y] = 0;
							 board[nextPos.x][nextPos.y]=1;
							 board[soldierHasToDie.x][soldierHasToDie.y] = 0;
							 this.whitePlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y + " , you also missed a kill , your soldier " + soldierHasToDie + " is dead.");
						 	}
						 else {
							 board[currentPos.x][currentPos.y] = 0;
							 board[nextPos.x][nextPos.y]=11;
							 board[soldierHasToDie.x][soldierHasToDie.y] = 0;
							 this.whitePlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y + "  and became a queen, you also missed a kill , your soldier " + soldierHasToDie + " is dead.");
							 this.whitePlayerQueens++;
						 }
						 }
				 }else {
			 if(possibleMoves.contains(nextPos)) {
				 if(nextPos.x == 0) {
					 board[currentPos.x][currentPos.y] = 0;
					 board[nextPos.x][nextPos.y] = 11;
					 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y + " and became a queen !");
					 this.whitePlayerQueens++;

				 }else {
				 board[currentPos.x][currentPos.y] = 0;
				 board[nextPos.x][nextPos.y]=1;
				 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y);
				 }
			 }
		 }}
		 
		 for(int i=0;i<8;i++) {
			 for(int j=0; j<8;j++)
				 System.out.print(board[i][j] + ",");
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
	 public void moveBlackSoldier(Pair currentPos , Pair nextPos , ArrayList<Pair> possibleMoves) {
		 ArrayList<Pair> allKillsAvailable = getKills(PlayerTurn.Black);
		 if(possibleMoves == null || !possibleMoves.contains(nextPos)) {
			 System.out.println("Wrong input"); 
			 return;
		 }
		 if(ifKillExist(currentPos,possibleMoves)) {
			 ArrayList<Pair> killMoves = getKillMove(possibleMoves,currentPos);
			 if(killMoves.size() == 2) {
				 Pair midEnemySol = getMiddleEnemySoldier(2,currentPos,nextPos);
				 String killed;
				 if(getTileContent(midEnemySol) == 1 ) {
					killed = "soldier!"; 
				 }else {
					 killed = "queen!";
				 }
				 board[nextPos.x][nextPos.y] = 2;
				 board[midEnemySol.x][midEnemySol.y] = 0;
				 board[currentPos.x][currentPos.y] = 0;
				 System.out.println("You killed an enemy " + killed );
				 if(killed.equals("soldier!")) {
					 this.whitePlayerSoldiers--;
				 }else {
					 this.whitePlayerQueens--;
				 }
				 this.blackPlayerPoints+=100;
			 }else {
				 Pair priorityMove = killMoves.get(0);
				 if(nextPos.x != priorityMove.x && nextPos.y != priorityMove.y) {
					 System.out.println("You missed a kill , you lose this soldier!");
					 board[currentPos.x][currentPos.y] = 0;
					 this.blackPlayerSoldiers--;
				 }else {
						 Pair midEnemySol = getMiddleEnemySoldier(2,currentPos,nextPos);
						 board[nextPos.x][nextPos.y] = 2;
						 board[midEnemySol.x][midEnemySol.y] = 0;
						 board[currentPos.x][currentPos.y] = 0;
						 System.out.println("You killed enemy soldier");
						 this.whitePlayerSoldiers--;
						 this.blackPlayerPoints+=100;
				 	}
			 	}
			 }else {
				 if(allKillsAvailable != null && !allKillsAvailable.isEmpty() && !allKillsAvailable.contains(nextPos)) {
					 Pair soldierHasToDie = getSoldierWithKill(PlayerTurn.Black);
					 if(soldierHasToDie != null) {
						 if(nextPos.x != 7) {
							 board[currentPos.x][currentPos.y] = 0;
							 board[nextPos.x][nextPos.y]=2;
							 board[soldierHasToDie.x][soldierHasToDie.y] = 0;
							 this.blackPlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y + " , you also missed a kill , your soldier " + soldierHasToDie + " is dead.");
						 	}
						 else {
							 board[currentPos.x][currentPos.y] = 0;
							 board[nextPos.x][nextPos.y]=22;
							 board[soldierHasToDie.x][soldierHasToDie.y] = 0;
							 this.blackPlayerSoldiers--;
							 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y + "  and became a queen, you also missed a kill , your soldier " + soldierHasToDie + " is dead.");
							 this.blackPlayerQueens++;
						 }
						 }
				 }else {
					 if(possibleMoves.contains(nextPos)) {
						 if(nextPos.x == 7) {
							 board[currentPos.x][currentPos.y] = 0;
							 board[nextPos.x][nextPos.y]=22;
							 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y + " and became a queen!");
							 this.blackPlayerQueens++;
						 }else {
						 board[currentPos.x][currentPos.y] = 0;
						 board[nextPos.x][nextPos.y]=2;
						 System.out.println("soldier moved to " + nextPos.x + "," + nextPos.y);
						 }
					 }
				 }
		 }
		 
		 for(int i=0;i<8;i++) {
			 for(int j=0; j<8;j++)
				 System.out.print(board[i][j] + ",");
			 System.out.println();
		 }
	 }
	 
	 
		public void moveQueen(int obj , Pair cur , Pair next , HashMap<Pair,Pair> possibleMoves) {
			if(possibleMoves == null || !possibleMoves.containsKey(next))
				System.out.println("Wrong Input");
			else if(getTileContent(next) != 0)
				System.out.println("Wrong Destination");
			else {
				if(obj == 22) {
					this.board[next.x][next.y] = 22;
					this.board[cur.x][cur.y] = 0;
					if(possibleMoves.get(next) == null) {
						System.out.println("You have moved your queen from " + cur + " to " + next);
					}else {
						Pair midKill = possibleMoves.get(next);
						this.board[midKill.x][midKill.y]= 0;
						if(getTileContent(midKill) == 1)
							this.whitePlayerSoldiers--;
						else
							this.whitePlayerQueens--;
						System.out.println("You have moved your queen from " + cur + " to " + next + ", and killed an enemy");
						this.blackPlayerPoints+=100;
					}
				}else if ( obj == 11) {
					this.board[next.x][next.y]= 11;
					this.board[cur.x][cur.y]= 0;
					if(possibleMoves.get(next) == null) {
						System.out.println("You have moved your queen from " + cur + " to " + next);
					}else {
						Pair midKill = possibleMoves.get(next);
						this.board[midKill.x][midKill.y]= 0;
						if(getTileContent(midKill) == 2)
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
//					System.out.print(board[i][j] + ",");
//				 System.out.println();
				
		//	}
		}
		/***
		 * This method returns a random soldier that has a kill move.
		 * @param turn
		 * @return
		 */
		public Pair getSoldierWithKill(PlayerTurn turn) {
			
			for(int i = 0; i < 8;i++) {
				for(int j = 0; j < 8; j++) {
					if(board[i][j] == 2 && turn.equals(PlayerTurn.Black)) {
						if(ifKillExist(getPair(i,j), getPossibleMovesForBlackSoldier(board[i][j], getPair(i,j)))) {
							return new Pair(i,j);
						}
					}else if(board[i][j] == 1 && turn.equals(PlayerTurn.White)) {
						if(ifKillExist(getPair(i,j),getPossibleMovesForWhiteSoldier(board[i][j], getPair(i,j)))) {
							return new Pair(i,j);
						}
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
		public ArrayList<Pair> getKills(PlayerTurn turn){
			ArrayList<Pair> blackKillsMoves = new ArrayList<Pair>();
			ArrayList<Pair> whiteKillsMoves = new ArrayList<Pair>();
			if(turn == null) return null;
			
				for(int i=0; i < 8;i++) {
					for(int j = 0; j < 8;j++) {
						if(board[i][j] == 2 && turn.equals(PlayerTurn.Black)) {
							ArrayList<Pair> possibleMoves = getPossibleMovesForBlackSoldier(board[i][j], getPair(i,j));
							if(ifKillExist(getPair(i,j), possibleMoves)) {
								blackKillsMoves.addAll(getKillMove(possibleMoves, getPair(i,j)));
							}
						}else if(board[i][j] == 1 && turn.equals(PlayerTurn.White)) {
							ArrayList<Pair> possibleMoves = getPossibleMovesForWhiteSoldier(board[i][j], getPair(i,j));
							if(ifKillExist(getPair(i,j), possibleMoves)) {
								whiteKillsMoves.addAll(getKillMove(possibleMoves, getPair(i,j)));
							}
						}
					}
				}
				
				if(turn.equals(PlayerTurn.Black))
					return blackKillsMoves;
				else
					return whiteKillsMoves;
			
		}
	 /***
	  * This method returns the coordinate of the enemy soldier that sits between current position and next position
	  * @param color
	  * @param current
	  * @param next
	  * @return
	  */
	 public Pair getMiddleEnemySoldier(int color ,Pair current , Pair next) {
		 if(color == 1) {
			 if(next.y - 2 == current.y)
				 return  new Pair(next.x + 1,next.y - 1);
			  if(next.y + 2 == current.y)
				 return new Pair(next.x + 1,next.y + 1);
		 }else {
			 if(next.y - 2 == current.y )
				 return new Pair(next.x - 1,next.y - 1);
			  if(next.y + 2 == current.y)
				 return new Pair(next.x - 1 , next.y + 1);
		 }
		 return null;
	 }
	 
	 /***
	  * This method take an array of possible moves and check if there is kill move among them and returns true, otherwise false
	  * @param currentPos
	  * @param possibleMoves
	  * @return
	  */
	 public boolean ifKillExist(Pair currentPos , ArrayList<Pair> possibleMoves) {
		if(possibleMoves != null) {
			for(Pair pair : possibleMoves) {
				if(pair.x - 2 == currentPos.x || pair.x + 2 == currentPos.x) {
					return true;
				}
			}
		}
		return false;
	 }
	 
	 /***
	  * This method returns the kill moves of the possible moves .
	  * @param moves
	  * @param currentPos
	  * @return
	  */
	 public ArrayList<Pair> getKillMove(ArrayList<Pair> moves ,Pair currentPos){
		 ArrayList<Pair> toReturn = new ArrayList<Pair>();
		 if(moves != null) {
			 for(Pair pair : moves) {
				 if(pair.x - 2 == currentPos.x || pair.x + 2 == currentPos.x) {
					 toReturn.add(pair);
				 }
			 }return toReturn;
		 }
		 return null;
	 }
	 
	 
	 /**
	  * method to use after a kill to check if a kill streak is possible
	  * @param obj 1 or 2 the type of the soldier
	  * @param pair is the current position of the soldier  
	  * @return list of the kills possible including backwards kills
	  */
	public ArrayList<Pair> getKillStreak(int obj , Pair pair){
		if(obj != 1 || obj !=2) return null; //if wasnt soldier then theres something wrong
		ArrayList<Pair> toReturn = new ArrayList<Pair>(); 
		int x = pair.x;
		int y = pair.y;
		if(obj ==1 ) {// if white soldier possible kill backwards
			ArrayList<Pair> possibleMoves = getPossibleMovesForWhiteSoldier(obj, pair);	//the soldiers current possible moves
			if(ifKillExist(pair, possibleMoves)) {  // if theres a kill possible in the ordinary moves add it
				for( Pair p : getKillMove(possibleMoves, pair)) 
					toReturn.add(pair);
			}
			if((y==0 || y==1) && x<6 ) {
				if( getTileContent(new Pair(x+1,y+1)) == 2 && getTileContent(new Pair(x+2,y+2))==0) 
					toReturn.add(new Pair(x+1,y+1));
			}
			if(y>1 && y<6 && x<6) {
				if(getTileContent(new Pair(x+1,y-1)) == 2 && getTileContent(new Pair(x+2,y-2)) == 0  )
					toReturn.add(new Pair(x+1, y-1));
				if(getTileContent(new Pair(x+1,y+1)) == 2 && getTileContent(new Pair(x+2,y+2)) == 0  )
					toReturn.add(new Pair(x+1, y+1));
			}
			if((y==6 || y==7) && x<6) {
				if(getTileContent(new Pair(x+1,y-1)) == 2 && getTileContent(new Pair(x+2,y-2)) == 0 )
					toReturn.add(new Pair(x-1,y-1));
			}
		}else {  // if black soldier possible kill backwards.
			ArrayList<Pair> possibleMoves = getPossibleMovesForBlackSoldier(obj, pair);	//the soldiers current possible moves
			if(ifKillExist(pair, possibleMoves)) {  // if theres a kill possible in the ordinary moves add it
				for( Pair p : getKillMove(possibleMoves, pair)) 
					toReturn.add(pair);
			}
			if( (y==0 || y==1) && x>1 ) {
				if(getTileContent(new Pair(x-1,y+1)) ==1 && getTileContent(new Pair(x-2,y+2))==0)
					toReturn.add(new Pair(x-1,y+1));
			}
			if(y>1 && y<6 && x>1) {
				if(getTileContent(new Pair(x-1,y+1)) ==1 && getTileContent(new Pair(x-2,y+2))==0)
					toReturn.add(new Pair(x-1, y+1));
				if(getTileContent(new Pair(x-1,y-1)) ==1 && getTileContent(new Pair(x-2,y-2))==0)
					toReturn.add(new Pair(x-1,y-1));
			}
			if((y==6 || y==7) && x>1) {
				if(getTileContent(new Pair(x-1,y-1)) ==1 && getTileContent(new Pair(x-2,y-2))==0)
					toReturn.add(new Pair(x-1,y-1));
			}
		}
		return toReturn;
	}
	
	
	
	
	/*********************** Code Regarding Special (Colored) Tile ************************************\
	 * 
	 *** YELLOW TILE ***
	 */
	/**
	 * method that return the empty tiles in the board
	 * @return list of pairs
	 */
	public ArrayList<Pair> getEmptyTiles(){
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
		for( int i=0 ; i< 8 ; i++) {
			for(int j=0 ; j<8; j++) {
				if(i%2==0 && j%2==1 && board[i][j]==0) toReturn.add(new Pair(i,j));
				if(i%2==1 && j%2==0 && board[i][j]==0) toReturn.add(new Pair(i,j));
			}
		}
		return toReturn;
	}
	
	/***
	 * This method generates a 3 random tiles and returns them.
	 * @return
	 */
	public ArrayList<Pair> generateYellowTiles(){
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
		int count = 0;
		for(;;) {
			int x = (int)(Math.random()*8);
			int y = (int)(Math.random()*8);
			if(getEmptyTiles().contains(new Pair(x,y))) {
				count++;
				toReturn.add(new Pair(x,y));
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
	public Pair generateGreenTile(PlayerTurn turn) {
		TreeSet<Pair> allPossibleMoves = new TreeSet<Pair>();
		ArrayList<Pair> possibleMovesArray = new ArrayList<Pair>();
		if(turn == PlayerTurn.Black) {
				for(int i=0 ; i < 8 ; i++) {
					for(int j=0; j < 8; j++) {
						if(getContentWithXandY(i, j) == 2) {
							allPossibleMoves.addAll(getPossibleMovesForBlackSoldier(getContentWithXandY(i, j), getPair(i, j)));
						}
					}
				}
		}else {
			for(int i=0 ; i < 8 ; i++) {
				for(int j=0; j < 8; j++) {
					if(getContentWithXandY(i, j) == 1) {
						allPossibleMoves.addAll(getPossibleMovesForWhiteSoldier(getContentWithXandY(i, j), getPair(i, j)));
					}
				}}			
		}
		possibleMovesArray.addAll(allPossibleMoves);
		int numberOfMoves = possibleMovesArray.size();
		int randomNumber = ThreadLocalRandom.current().nextInt(0 , numberOfMoves );
		return possibleMovesArray.get(randomNumber);
	}
	/*** BLUE TILE ****/ 
	/**
	 * method that counts the number of pieces from a certain type 
	 * @param obj white or black soldier or queen
	 * @return count of the pieces from obj type
	 */
	public int countPiece(int obj) {
		int count =0;
		for(int i=0; i<8 ;i++) {
			for(int j=0; j<8;j++) {
				if(getContentWithXandY(i, j)==obj) count++;
			}
		}
		return count;
	}
	/**
	 * @return true if player has exactly 2 soldiers and at least 1 queen, false otherwise
	 */
	public boolean checkIfBlueTile() {
		if((countPiece(1)==2 && countPiece(11)>=1) || (countPiece(2)==2 && countPiece(22)>=1)) return true;
		return false;
	}
	/**
	 * @return random pair for a tile in the board as Blue tile.
	 */
	public Pair generateBlueTile() {
		if(checkIfBlueTile()) {
			int x = (int)(Math.random()*8);
			int y = (int)(Math.random()*8);
			return new Pair(x,y);
		}
		return null;
	}
	
/**
 * 
 * @param obj the type of the soldier (white or black)
 * @param pos the position the player wants to put it in
 * @return true if legal position, false otherwise
 */
	public boolean checkIfLegalPosition(int obj,Pair pos){
		int x = pos.x;
		int y = pos.y;
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
		if(getContentWithXandY(x-2, y)==op || getContentWithXandY(x-2, y)==queOp || getContentWithXandY(x+2, y)==op ||  getContentWithXandY(x+2, y)==queOp ||
				getContentWithXandY(x, y-2)==op || getContentWithXandY(x, y-2)==queOp || getContentWithXandY(x, y+2)==op ||  getContentWithXandY(x, y+2)==queOp) 
			return false;
		
		int it = 1;
		while(it<5) {
			int i = x;
			int j = y;
			int counter =0;
			while(getContentWithXandY(i, j) != -2) {
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
				if(counter<2 && ( getContentWithXandY(i,j) == op || getContentWithXandY(i,j) == queOp )) {
					System.out.println(counter);
					return false;
				}
				if(getContentWithXandY(i, j) == 0) counter ++;//System.out.println(counter);
			}
			   it++;
		}
		return true;
	}
	
	public boolean ressurectSoldier(int obj, Pair pos) {
        if(checkIfLegalPosition(obj, pos)) {
            if(obj == 1) whitePlayerSoldiers++;
            else blackPlayerSoldiers++;
            board[pos.x][pos.y] = obj;
            return true;
        }
        return false;
    }

	/*** ORANGE TILES ***
	 * This method generates orange tiles for the player and returns them.
	 * @param turn
	 * @return
	 */
	public ArrayList<Pair> generateOrangeTiles(PlayerTurn turn){
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
		TreeSet<Pair> allPossibleMoves = new TreeSet<Pair>();
		if(turn == null)
			return null;
		if(turn == PlayerTurn.Black) {
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0 ; j < 8 ; j++) {
				if(getContentWithXandY(i, j) == 2) {
					allPossibleMoves.addAll(getPossibleMovesForBlackSoldier(2, getPair(i, j)));
				}
			}
		}
		}else {
			for(int i = 0 ; i < 8 ; i++) {
				for(int j = 0 ; j < 8 ; j++) {
					if(getContentWithXandY(i, j) == 1) {
						allPossibleMoves.addAll(getPossibleMovesForWhiteSoldier(1, getPair(i, j)));
					}
				}
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
	public Pair generateRedTile(PlayerTurn turn) {
		return null;
	}
	/***
	 * This method takes the position of the queen as parameter and returns all the possible moves in all of the 4 biases.
	 * @param obj
	 * @param pos
	 * @return
	 */
	public HashMap<Pair,Pair> getQueenBiasMoves(int obj , Pair pos,String dir,HashMap<Pair,Pair> mapMoves){
		int i = 1 ,j = 1;
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
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
					if(mapMoves.containsKey(new Pair(pos.x - (i+1),pos.y + (j+1)))) {
						if(mapMoves.get(new Pair(pos.x - (i+1),pos.y + (j+1))) == null) {
							mapMoves.put(new Pair(pos.x - (i+1),pos.y + (j+1)), new Pair(pos.x - i, pos.y + j));
						}
					}else {
						mapMoves.put(new Pair(pos.x - (i+1),pos.y + (j+1)), new Pair(pos.x - i, pos.y + j));
					}
//					toReturn.add(getPair(pos.x - (i+1), pos.y + (j+1)));
					break;
				}
//				toReturn.add(getPair(pos.x - i , pos.y + j));
				if(getContentWithXandY(pos.x - i , pos.y + j) == 0 && !mapMoves.containsKey(new Pair(pos.x - i , pos.y + j)))
					mapMoves.put(new Pair(pos.x - i , pos.y + j),null);
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
					if(mapMoves.containsKey(new Pair(pos.x - (i+1), pos.y - (j+1)))) {
						if(mapMoves.get(new Pair(pos.x - (i+1) , pos.y - (j+1))) == null) {
							mapMoves.put(new Pair(pos.x - (i+1) , pos.y - (j+1)), new Pair(pos.x - i,pos.y - j));
						}
					}else {
						mapMoves.put(new Pair(pos.x - (i+1) , pos.y - (j+1)), new Pair(pos.x - i,pos.y - j));
					}
//					toReturn.add(getPair(pos.x - (i+1), pos.y - (j+1)));
					break;
				}
				if(getContentWithXandY(pos.x - i, pos.y - j) == 0 && !mapMoves.containsKey(new Pair(pos.x - i, pos.y - j)))
					mapMoves.put(new Pair(pos.x - i,pos.y - j), null);
//				toReturn.add(getPair(pos.x - i, pos.y - j));
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
					if(mapMoves.containsKey(new Pair(pos.x + (i+1), pos.y + (j+1)))) {
						if(mapMoves.get(new Pair(pos.x + (i+1), pos.y + (j+1))) == null) {
							mapMoves.put(new Pair(pos.x + (i+1), pos.y + (j+1)), new Pair(pos.x + i, pos.y + j));
						}
					}else {
						mapMoves.put(new Pair(pos.x + (i+1), pos.y + (j+1)), new Pair(pos.x + i, pos.y + j));
					}
//					toReturn.add(getPair(pos.x + (i+1),pos.y + (j+1)));
					break;
					}
				if(getContentWithXandY(pos.x + i, pos.y + j) == 0 && !mapMoves.containsKey(new Pair(pos.x + i, pos.y + j)))
					mapMoves.put(new Pair(pos.x + i, pos.y + j), null);

//				toReturn.add(getPair(pos.x + i,pos.y + j));
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
					if(mapMoves.containsKey(new Pair(pos.x + (i+1), pos.y - (j+1)))) {
						if(mapMoves.get(new Pair(pos.x + (i+1), pos.y - (j+1))) == null) {
							mapMoves.put(new Pair(pos.x + (i+1), pos.y - (j+1)), new Pair(pos.x + i, pos.y - j));
						}
					}else {
						mapMoves.put(new Pair(pos.x + (i+1), pos.y - (j+1)), new Pair(pos.x + i, pos.y - j));
					}
//					toReturn.add(getPair(pos.x + (i+1),pos.y - (j+1)));
					break;
				}
				if(getContentWithXandY(pos.x + i, pos.y - j) == 0 && !mapMoves.containsKey(new Pair(pos.x + i, pos.y - j))){
					mapMoves.put(new Pair(pos.x + i, pos.y - j), null);

				}
//				toReturn.add(getPair(pos.x + i,pos.y - j));
				i++;
				j++;
			}}
		return mapMoves;
	}
	
	public HashMap<Pair,Pair> getQueenMoves(int obj,Pair pos){
		HashMap<Pair,Pair> myMoves = new HashMap<Pair,Pair>();
		myMoves = getQueenBiasMoves(obj, pos, "TOP-LEFT", myMoves);
		myMoves = getQueenBiasMoves(obj, pos, "TOP-RIGHT", myMoves);
		myMoves = getQueenBiasMoves(obj, pos, "BOTTOM-LEFT", myMoves);
		myMoves = getQueenBiasMoves(obj, pos, "BOTTOM-RIGHT", myMoves);
		myMoves = getQueenCrossBoardMoves(obj, pos, myMoves);
//		for(Map.Entry<Pair, Pair> temp : myMoves.entrySet()) {
//			System.out.println(temp.getKey() + "  , " + temp.getValue());
//		}
		return myMoves;
	}
	
	/***
	 * This method gets the possible moves crossing the board (NOT FINISHED - may have bugs).
	 * @param obj
	 * @param pos
	 * @param map
	 * @return
	 */
	public HashMap<Pair,Pair> getQueenCrossBoardMoves(int obj , Pair pos,HashMap<Pair,Pair> map){
		ArrayList<Pair> boardEdgesMoves = getLastPossibleMoveInBias(obj, pos,map);
		HashSet<Pair> treeMoves = new HashSet<>();
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
			for(Pair pair : boardEdgesMoves) {
				if((pos.x < pair.x && pos.y > pair.y)) {
					dir = "BOTTOM-LEFT";
				} else if (pos.x > pair.x && pos.y > pair.y) {
					dir = "TOP-LEFT";
				}else if((pos.x > pair.x && pos.y < pair.y )) {
					dir = "TOP-RIGHT";
				}else if (pos.x < pair.x && pos.y < pair.y) {
					dir = "BOTTOM-RIGHT";
				}
				if(pair.y == 0 && pair.x == 7 && dir.equals("BOTTOM-LEFT") ){
					if(getContentWithXandY(pair.x-7, pair.y+7) == 0) {
						treeMoves.add(new Pair(pair.x - 7,pair.y + 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x - 7,pair.y + 7), "BOTTOM-LEFT", map);
					}
				}else if(pair.y == 0 && dir.equals("BOTTOM-LEFT")) {
					if(getContentWithXandY(pair.x + 1, pair.y + 7) == 0) {
						treeMoves.add(new Pair(pair.x + 1,pair.y + 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x + 1,pair.y + 7), "BOTTOM-LEFT", map);
					}
				}else if(pair.y == 0 && dir.equals("TOP-LEFT")) {
					if(getContentWithXandY(pair.x - 1, pair.y + 7) == 0) {
						treeMoves.add(new Pair(pair.x - 1,pair.y + 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x - 1,pair.y + 7), "TOP-LEFT", map);
					}
				}else if(pair.y == 7 && pair.x == 0 && dir.equals("TOP-RIGHT")) {
					if(getContentWithXandY(pair.x + 7, pair.y - 7) == 0) {
						treeMoves.add(new Pair(pair.x + 7,pair.y - 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x + 7,pair.y - 7), "TOP-RIGHT", map);
					}
				}else if(pair.y == 7 && dir.equals("TOP-RIGHT")) {
					if(getContentWithXandY(pair.x - 1, pair.y - 7) == 0) {
						treeMoves.add(new Pair(pair.x - 1,pair.y - 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x - 1,pair.y - 7), "TOP-RIGHT", map);

					}
				}else if(pair.y == 7 && dir.equals("BOTTOM-RIGHT")) {
					if(getContentWithXandY(pair.x + 1, pair.y - 7) == 0) {
						treeMoves.add(new Pair(pair.x + 1,pair.y - 7));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x + 1,pair.y - 7), "BOTTOM-RIGHT", map);
					}
				}else if(pair.x == 0 && dir.equals("TOP-RIGHT")) {
					if(getContentWithXandY(pair.x + 7, pair.y + 1) == 0) {
						treeMoves.add(new Pair(pair.x + 7,pair.y + 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x + 7,pair.y + 1), "TOP-RIGHT", map);
					}
				}else if(pair.x == 0 && dir.equals("TOP-LEFT")) {
					if(getContentWithXandY(pair.x + 7, pair.y - 1) == 0) {
						treeMoves.add(new Pair(pair.x + 7,pair.y - 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x + 7,pair.y - 1), "TOP-LEFT", map);
					}
				}else if(pair.x == 7 && dir.equals("BOTTOM-LEFT")) {
					if(getContentWithXandY(pair.x - 7, pair.y -1 ) == 0) {
						treeMoves.add(new Pair(pair.x - 7,pair.y - 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x - 7,pair.y - 1), "BOTTOM-RIGHT", map);
					}
				}else if(pair.x == 7 && dir.equals("BOTTOM-RIGHT")) {
					if(getContentWithXandY(pair.x - 7, pair.y + 1) == 0) {
						treeMoves.add(new Pair(pair.x - 7,pair.y + 1));
						//FUNCTION CALL
						map = getQueenBiasMoves(getTileContent(pos), new Pair(pair.x - 7,pair.y + 1), "TOP-RIGHT", map);
					}
				}
			}
		
		if(!treeMoves.isEmpty())
			for(Pair temp : treeMoves) {
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
	 */
	private ArrayList<Pair> getLastPossibleMoveInBias(int obj ,Pair pos,HashMap<Pair,Pair> queenMoves){
		ArrayList<Pair> movesToReturn = new ArrayList<Pair>();
		
		if(pos.x == 0 || pos.x == 7 || pos.y == 0 || pos.y == 7) 
			movesToReturn.add(pos);
		for(Map.Entry<Pair,Pair> pair : queenMoves.entrySet()) {
			if(pair.getKey().x == 0 || pair.getKey().x == 7 || pair.getKey().y == 0 || pair.getKey().y == 7) {
				if(pair.getValue() == null)
					movesToReturn.add(pair.getKey());
			}
		}
		return movesToReturn;
	}
	
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
		if(this.whitePlayerQueens == 0 && this.whitePlayerSoldiers == 0) 
			return this.blackPlayer;
		else 
			return this.whitePlayer;
	}

	public void printBoard() {
		for(int i = 0 ; i < 8; i++) {
			for(int j = 0; j < 8; j++) 
				System.out.print(board[i][j] + ",");
			 System.out.println();
			
		}
	}
	
	public ArrayList<Pair> getPossibleMovesForSoldiers(int obj, Pair pair){
        if(obj == 1 ) {
            return getPossibleMovesForSoldiers(obj, pair);
        }
        return getPossibleMovesForBlackSoldier(obj, pair);
     }
}
