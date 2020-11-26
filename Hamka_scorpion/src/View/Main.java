package View;
import java.util.concurrent.ThreadLocalRandom;

import Controller.SysData;
import Model.Game;
import Model.Winner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main  {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Winner w1 = new Winner("Mohamed",720,"20/07/2019");
//		Winner w2 = new Winner("Lool",400,"16/11/2020");
//		SysData.getInstance().addWinnerToLeaderboard(w1);
//		SysData.getInstance().addWinnerToLeaderboard(w2);
//		SysData.getInstance().writeWinnersIntoFile();
		
		Game game = new Game("PL","SS");
//		game.getPossibleMovesForWhiteSoldier(1, game.getPair(2, 1));
		//game.moveBlackSoldier(game.getPair(4, 3), game.getPair(6, 1), game.getPossibleMovesForBlackSoldier(game.getContentWithXandY(4,3), game.getPair(4,3)));
//		System.out.println(game.getblackPlayerSoldiers());
//		System.out.println(game.generateYellowTiles());
//		game.getQueenBiasMoves(game.getContentWithXandY(4, 3), game.getPair(4, 3));
		
		stopWatch sw = new stopWatch(5);
		sw.startTiming();
		while(true)
			System.out.println(stopWatch.time);
		
	}

}

class stopWatch{
	static int time ;
	static Timer timer;
	int currentTime ;
	
	public stopWatch(int time) {
		stopWatch.time = time;
	}
	public void startTiming() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(stopTime());
			}
			
		}, 1000, 1000);
	}
	
	
	public void setTime(int time) {
		currentTime = stopWatch.time;
		stopWatch.time = time;
	}
	private static final int stopTime() {
		if(time == 0) {
			timer.cancel();
		} 
			return time--;
	}
}