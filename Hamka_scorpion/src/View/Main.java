package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import Model.Game;
import Model.Queen;
import Model.Soldier;
import Model.StopWatch;
import Model.Tile;
import Model.Color;
import Model.Game;


public class Main  {

	public static void main(String[] args) {
//		Winner w1 = new Winner("Mohamed",720,"20/07/2019");
//		Winner w2 = new Winner("Lool",400,"16/11/2020");
//		SysData.getInstance().addWinnerToLeaderboard(w1);
//		Winner w2 = new Winner("Lool",400,"16/11/2020");
//		SysData.getInstance().addWinnerToLeaderboard(w2);
//		SysData.getInstance().writeWinnersIntoFile();
//		SysData.getInstance().addWinnerToLeaderboard(w1);
//		Game game = new Game("PL","SS");
//		game.getPossibleMovesForWhiteSoldier(1, game.getTile(2, 1));
//		game.moveBlackSoldier(game.getTile(4, 3), game.getTile(6, 1), game.getPossibleMovesForBlackSoldier(game.getContentWithXandY(4,3), game.getTile(4,3)));
//		System.out.println(game.getblackPlayerSoldiers());
//		System.out.println(game.generateYellowTiles());
//		game.getQueenBiasMoves(game.getContentWithXandY(4, 3), game.getTile(4, 3));
//		boolean leg = game.checkIfLegalPosition(1, game.getTile(4, 3));
//		System.out.println(leg);
//		game.getBiasMovesQueen(game.getContentWithXandY(2, 5), game.getTile(2, 5));
//		game.getQueenMoves(game.getContentWithXandY(2, 5), game.getTile(2, 5));
		/*Scanner scan = new Scanner(System.in);
		System.out.println("Enter first name : ");
		String firstP = scan.nextLine();
		System.out.println("Enter second name : ");
		String secondP = scan.nextLine();
		Game game = new Game(firstP,secondP);
		StopWatch sw = new StopWatch();
		StopWatch sw2 = new StopWatch();
		int x , y ,z,w;
		System.out.println("Game started , " + firstP + " has the color black , "+ secondP + " has the color white");
		System.out.println(" -1 is a white Tile .");
		while(!game.isGameOver()) {
			game.printBoard();
			if(game.getTurn().equals(Color.Black)) {
				sw.start();
				sw2.start();
				System.out.println("It's black turn");
				System.out.print("Enter number x for the soldier/queen you want to move : ");
				x = scan.nextInt();
				System.out.print("Enter number y: ");
				y = scan.nextInt();
				while(game.getContentWithXandY(x, y) != 2 && game.getContentWithXandY(x, y) != 22) {
					System.out.println("WRONG INPUT , enter x : ");
					x = scan.nextInt();
					System.out.println("Enter y : ");
					y = scan.nextInt();
				}
				System.out.print("Enter number z for the place you want to go to : ");
				z = scan.nextInt();
				System.out.print("Enter number w: ");
				w = scan.nextInt();
				while(game.getContentWithXandY(z, w) != 0) {
					System.out.println("Wrong tile , enter z : ");
					z = scan.nextInt();
					System.out.println("Enter w : ");
					w = scan.nextInt();
				}
				if(game.getContentWithXandY(x, y) == 2) {
					game.moveBlackSoldier(game.getTile(x, y), game.getTile(z, w), game.getPossibleMovesForBlackSoldier(2, game.getTile(x, y)));
				}else {
					game.moveQueen(22, game.getTile(x, y), game.getTile(z, w), game.getQueenMoves(22, game.getTile(x, y)));
				}
				sw.stop();
				System.out.println("Your turn took: " + (System.currentTimeMillis() - sw.startTime) / 1000  + " Seconds");
				System.out.println();
			}else {
				sw.start();
				System.out.println("It's white turn");
				System.out.print("Enter number x for the soldier/queen you want to move : ");
				x = scan.nextInt();
				System.out.print("Enter number y: ");
				y = scan.nextInt();
				while(game.getContentWithXandY(x, y) != 1 && game.getContentWithXandY(x, y) != 11) {
					System.out.println("WRONG INPUT , enter x : ");
					x = scan.nextInt();
					System.out.println("Enter y : ");
					y = scan.nextInt();
				}
				System.out.print("Enter number Z for the place you want to go to : ");
				z = scan.nextInt();
				System.out.print("Enter number W: ");
				w = scan.nextInt();
				while(game.getContentWithXandY(z, w) != 0) {
					System.out.println("Wrong tile , enter Z : ");
					z = scan.nextInt();
					System.out.println("Enter W : ");
					w = scan.nextInt();
				}
				if(game.getContentWithXandY(x, y) == 1) {
					game.moveWhiteSoldier(game.getTile(x, y), game.getTile(z, w), game.getPossibleMovesForWhiteSoldier(1, game.getTile(x, y)));
				}else {
					game.moveQueen(11, game.getTile(x, y), game.getTile(z, w), game.getQueenMoves(11, game.getTile(x, y)));
				}
				sw.stop();
				System.out.println("Your turn took: " + (System.currentTimeMillis() - sw.startTime) / 1000 + " Seconds");
				System.out.println();
			}
			game.handTurn();
		}
		sw.stop();
		System.out.println("The Game took: " + (System.currentTimeMillis() - sw2.startTime) / 1000 + " Seconds");
		sw2.stop();
		System.out.println("The winner is : " + game.winner());*/
		int[][] board = {{-1,1,-1,2,-1,0,-1,1},
			          	 {2,-1,0,-1,0,-1,0,-1},
			        	 {-1,0,-1,2,-1,22,-1,0},
		          		 {0,-1,1,-1,0,-1,0,-1},
				         {-1,0,-1,0,-1,0,-1,0},
				         {0,-1,0,-1,1,-1,1,-1},
				         {-1,0,-1,1,-1,1,-1,0},
				         {0,-1,0,-1,1,-1,0,-1}
};
	
	Game g = new Game("M","A",board);
	System.out.println(g.getQueenBiasMoves((Queen)g.getTileContent(new Tile(2,5)), "BL"));
	

//	System.out.println(g.board.getPlayerPositions(Color.Black));

	//System.out.println(g.ifKillExist(new Tile(5,0), g.getPossibleMovesForWhiteSoldier(g.getTileContent(new Tile(5,0)))));
	/*ArrayList<Tile> moves = g.getPossibleMovesForBlackSoldier(g.getTileContent(new Tile(2,5)));

	Soldier s = g.getTileContent(new Tile(2,5));
	Tile next = new Tile(4,3);
	g.moveBlackSoldier(s, next ,moves);
		System.out.println(g.getKillStreak(s));
	g.moveStreak(s, g.getTileContent(new Tile(4,3)), new Tile(2,1));*/
	}
}
