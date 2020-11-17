package View;
import java.util.concurrent.ThreadLocalRandom;

import Controller.SysData;
import Model.Winner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class Main  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Winner w1 = new Winner("Mohamed",720,"20/07/2019");
//		Winner w2 = new Winner("Lool",400,"16/11/2020");
//		SysData.getInstance().addWinnerToLeaderboard(w1);
//		SysData.getInstance().addWinnerToLeaderboard(w2);
//		SysData.getInstance().writeWinnersIntoFile();
		SysData.getInstance().readLeaderboardFile();
		for(Winner win : SysData.getInstance().getLeaderboard()) 
			System.out.println(win);
	}


}
