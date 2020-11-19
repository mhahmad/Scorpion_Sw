package Model;

import java.util.Timer;


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
			// turnTime.
			 return;
		 }
		 turn = PlayerTurn.Black;
	 }
	 
	 public int getTileContent(int x , int y) {
		 return this.getBoard()[x][y];
	 }
	 
	 
	 /***
	  * commit this
	  */
	 public void newFun() {
		 System.out.println("fun does not fun");
	 }
}
