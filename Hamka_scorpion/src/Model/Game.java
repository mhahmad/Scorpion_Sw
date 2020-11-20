package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
	private static int gameID;
	private String player1;
	private String player2;
	private int player1Points;
	private int player2Points;
	private Timer turnTime;
	private Timer matchTime;
	private int player1Soldiers;
	private int player2Soldiers;
	private int player1Queens;
	private int player2Queens;
	private String date;
	public int seconds = 0;
	private Pair[] yellowPanels;
	private int[][] board = {{-1,1,-1,1,-1,1,-1,1},
			                 {1,-1,1,-1,1,-1,1,-1},
			                 {-1,1,-1,1,-1,1,-1,1},
			                 {0,-1,0,-1,0,-1,0,-1},
			                 {-1,0,-1,0,-1,0,-1,0},
			                 {2,-1,2,-1,2,-1,2,-1},
			                 {-1,2,-1,2,-1,2,-1,2},
		                     {2,-1,2,-1,2,-1,2,-1}
	};
	PlayerTurn turn;
	
	public Game(String player1 , String player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.player1Points = 0;
		this.player2Points = 0;
		matchTime = new Timer();
		this.player1Soldiers = 12;
		this.player2Soldiers = 12;
		// Time is needed here
		turn = PlayerTurn.Black;
	}


	public String getDate() {
		return date;
	}


	public int getPlayer2Points() {
		return player2Points;
	}


	public void setPlayer2Points(int player2Points) {
		this.player2Points = player2Points;
	}


	public int getPlayer1Soldiers() {
		return player1Soldiers;
	}


	public void setPlayer1Soldiers(int player1Soldiers) {
		this.player1Soldiers = player1Soldiers;
	}


	public int getPlayer2Soldiers() {
		return player2Soldiers;
	}


	public void setPlayer2Soldiers(int player2Soldiers) {
		this.player2Soldiers = player2Soldiers;
	}


	public int getPlayer1Queens() {
		return player1Queens;
	}


	public void setPlayer1Queens(int player1Queens) {
		this.player1Queens = player1Queens;
	}


	public int getPlayer2Queens() {
		return player2Queens;
	}


	public void setPlayer2Queens(int player2Queens) {
		this.player2Queens = player2Queens;
	}


	public static int getGameID() {
		return gameID;
	}


	public String getPlayer1() {
		return player1;
	}


	public String getPlayer2() {
		return player2;
	}


	public int getPlayer1Points() {
		return player1Points;
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
			int x;
			int y;
			
			Pair(int x,int y){
				this.x = x;
				this.y = y;
			}
		}
	/***
	 * method that decides random places to the yellow tiles
	 * @return array of pairs of x and y
	 */
	 private Pair[] randomYellowTiles() {
		 Pair[] pairs = new Pair[3];
		 
		return pairs;
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
//		 TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				seconds++;
//			}
//			 
//		 };
//			matchTime.scheduleAtFixedRate(task, 1000, 1000);
		// turnTime.
		 turn = PlayerTurn.Black;
	 }
	 
	 /***
	  * return what insided the tile (empty ? , soldier ? , queen ? )
	  * @param x
	  * @param y
	  * @return
	  */
	 public int getTileContent(Pair pair) {
		 return this.getBoard()[pair.x][pair.y];
	 }
	 
	 
	 
	 public ArrayList<Pair> getPossibleMovesForWhiteSoldier(int obj , Pair pair) {
		 ArrayList<Pair> pairMoves = new ArrayList<Pair>();
		 if(obj != 1)
			 return null;
		 else {
			 if(pair.y == 0 && pair.x != 7 ) {
				if( getTileContent(new Pair(pair.x+1,pair.y+1)) == 0) {
					 pairMoves.add(new Pair(pair.x+1,pair.y+1));
					 return pairMoves;
				}
				else if (getTileContent(new Pair(pair.x+1,pair.y+1)) == 2 && getTileContent(new Pair(pair.x  + 2 , pair.y + 2)) == 0) {
						pairMoves.add(new Pair(pair.x + 2, pair.y + 2));
						return pairMoves;
				}
			 }else if(pair.y == 7 && pair.x != 7 ) {
				 if(getTileContent(new Pair(pair.x + 1,pair.y - 1)) == 0) {
					 pairMoves.add(new Pair(pair.x+1,pair.y-1));
					 return pairMoves;
				 }else if (getTileContent(new Pair(pair.x+1,pair.y -1 )) == 2  && pair.x+1 != 7 && getTileContent(new Pair(pair.x + 2,pair.y - 2)) == 0) {
					 pairMoves.add(new Pair(pair.x + 2, pair.y - 2));
					 return pairMoves;
				 }
			 }else if (pair.x !=7 && getTileContent(new Pair(pair.x + 1, pair.y - 1)) == 0 || getTileContent(new Pair(pair.x + 1,pair.y + 1)) == 0) {
				 if(getTileContent(new Pair(pair.x + 1, pair.y - 1)) == 0 )
					 pairMoves.add(new Pair(pair.x + 1, pair.y -1 ));
				 if(getTileContent(new Pair(pair.x + 1,pair.y + 1)) == 0)
					 pairMoves.add(new Pair(pair.x + 1, pair.y + 1 ));
				 return pairMoves;
			 }else if (pair.x != 6 ) {
				 if(getTileContent(new Pair(pair.x + 1,pair.y + 1)) == 2 && getTileContent(new Pair(pair.x + 2,pair.y + 2)) == 0) 
					 pairMoves.add(new Pair(pair.x + 2,pair.y + 2));
				 if(getTileContent(new Pair(pair.x + 1,pair.y - 1)) == 2 && getTileContent(new Pair(pair.x + 2 , pair.y - 2)) == 0) 
					pairMoves.add(new Pair(pair.x + 2,pair.y = 2));
				 return pairMoves;
			 }
		 }
		 return null;
	 }
}
