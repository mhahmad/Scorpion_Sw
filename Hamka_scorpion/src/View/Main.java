package View;

import java.util.HashMap;
import java.util.Scanner;
import Model.Game;
import Model.Game.PlayerTurn;
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
		System.out.println("SDSD");
//		game.getPossibleMovesForWhiteSoldier(1, game.getPair(2, 1));
		//game.moveBlackSoldier(game.getPair(4, 3), game.getPair(6, 1), game.getPossibleMovesForBlackSoldier(game.getContentWithXandY(4,3), game.getPair(4,3)));
//		System.out.println(game.getblackPlayerSoldiers());
//		System.out.println(game.generateYellowTiles());
//		game.getQueenBiasMoves(game.getContentWithXandY(4, 3), game.getPair(4, 3));
//		boolean leg = game.checkIfLegalPosition(1, game.getPair(4, 3));
//		System.out.println(leg);
//		game.getBiasMovesQueen(game.getContentWithXandY(2, 5), game.getPair(2, 5));
//		game.getQueenMoves(game.getContentWithXandY(2, 5), game.getPair(2, 5));
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter first name : ");
		String firstP = scan.nextLine();
		System.out.println("Enter second name : ");
		String secondP = scan.nextLine();
		Game game = new Game(firstP,secondP);
		int x , y ,z,w;
		System.out.println("Game started , " + firstP + " has the color black , "+ secondP + " has the color white");
		System.out.println(" -1 is a white Tile .");
		while(!game.isGameOver()) {
			game.printBoard();
			if(game.getTurn().equals(PlayerTurn.Black)) {
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
					game.moveBlackSoldier(game.getPair(x, y), game.getPair(z, w), game.getPossibleMovesForBlackSoldier(2, game.getPair(x, y)));
				}else {
					game.moveQueen(22, game.getPair(x, y), game.getPair(z, w), game.getQueenMoves(22, game.getPair(x, y)));
				}
			}else {
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
					game.moveWhiteSoldier(game.getPair(x, y), game.getPair(z, w), game.getPossibleMovesForWhiteSoldier(1, game.getPair(x, y)));
				}else {
					game.moveQueen(11, game.getPair(x, y), game.getPair(z, w), game.getQueenMoves(11, game.getPair(x, y)));
				}
			}
			game.handTurn();
		}
		System.out.println("The winner is : " + game.winner());		
	}
}
