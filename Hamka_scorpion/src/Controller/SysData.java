package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import Model.Board;
import Model.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Model.Game;
import Model.Level;
import Model.Question;
import Model.Winner;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SysData {

	private static SysData instance = new SysData();
	public static int count = 0;
	

	private SysData() {}
	
	public static SysData getInstance() {
		return instance;
	}
	
	//
	//----------------------------------------------------------------//
	//COMPARATOR
	Comparator<Winner> comp = (Winner w1,Winner w2)->w1.compareTo(w2);
	
	//----------------------------------------------------------------//
	// DATA STRUCTURES 
	ArrayList<Game> gameHistory = new ArrayList<Game>();
	ArrayList<Question> questionList = new ArrayList<Question>();
	TreeSet<Winner> leaderboard = new TreeSet<>(comp);
	ArrayList<Question> alreadyShownQuestions = new ArrayList<>();
	//----------------------------------------------------------------//
	
	//METHODS
	
	public boolean addQuestion(Question question) {
		return questionList.add(question);
	}
	
	public boolean removeQuestion(Question question) {
		 questionList.remove(question);
		 writeQuestionsToJson();
		 return true;
	}
	
	public ArrayList<Question> getQuestions(){
		questionList.clear();
		readQuestionsFromJson();
		return questionList;
	}
	
	public ArrayList<Game> getGames(){
		return gameHistory;
	}
	
	public TreeSet<Winner> getLeaderboard(){
		leaderboard.clear();
		readLeaderboardFile();
		return leaderboard;
	}
	
	public ArrayList<Question> getQuestionList(){
		return questionList;
	}
	
	public ArrayList<Question> getAlreadyShownQuestion(){
		return alreadyShownQuestions;
	}
	
	/***
	 * When a question is shown to the user , it should be removed from the main question list and added to the second data structure
	 * @param question
	 */
	public void questionIsShown(Question question) {
		questionList.remove(question);
		alreadyShownQuestions.add(question);
	}
	
	/***
	 * This method will be called once all the questions have been shown to the players.
	 */
	public void refillQuestionList() {
		if(questionList.isEmpty()) {
			questionList.addAll(alreadyShownQuestions);
			alreadyShownQuestions.clear();
		}
	}
	/***
	 * 
	 * @param question
	 * @param content
	 * @return true if the question has been changed successfully , otherwise returns false
	 */
	public boolean editQuestion(Question question , String content,ArrayList<String> answers,String level,String rightAnswer) {
		if(question == null || content.equals("") || !questionList.contains(question))
			return false;
		else {
			for(Question que : questionList) {
				if(que.equals(question)) {
					que.setContent(content);
					que.setAnswers(answers);
					que.setLevel(level);
					que.setRightAnswer(rightAnswer);
					writeQuestionsToJson();
					return true;
				}
			}
			return false;
		}
	}
	
	/***
	 * 
	 * @param game
	 * @return true if the game was added in the data structure of game histories , otherwise returns false
	 */
	public boolean addGame(Game game) {
		return game ==null ? false : gameHistory.add(game);
	}
	
	
	/***
	 * 
	 * @param winner
	 * @return true if the winner was added to the leaderboard's data structure , otherwise false
	 */
	public boolean addWinnerToLeaderboard(Winner winner) {
		return winner == null ? false : leaderboard.add(winner);
	}
	
	/***
	 * 
	 * @return a random question.
	 */
	public Question randomQuestion() {
		int numberOfQuestions = questionList.size();
		int randomNumber = ThreadLocalRandom.current().nextInt(0 , numberOfQuestions );
		return questionList.get(randomNumber );
	}
	
	
	/***
	 * This method accepts a Game object , it determine who is the winner in this game and add it to the leaderboard.
	 * @param game
	 * @return
	 */
	public boolean extractWinner(Game game) {
		if(game == null)
			return false;
		else {
			Winner winner = null;
			if(game.getwhitePlayerPoints() > game.getblackPlayerPoints())
				winner = new Winner(game.getwhitePlayer(),game.getwhitePlayerPoints(),game.getDate());
			else{
				winner = new Winner(game.getblackPlayer(),game.getblackPlayerPoints(),game.getDate());
			}
			return addWinnerToLeaderboard(winner);
		}
	}
	/***
	 * this method accepts a boolean , if it's true music will play , if false music will stop.
	 * @param status
	 */

	public void soundtrackOn(boolean status) {
		
		String fileName = new File("Hamka_scorpion/Music/soundtrack.mp3").toURI().toString();
		JFXPanel j = new JFXPanel();
		Media media = new Media(fileName);
		MediaPlayer mp = new MediaPlayer(media);
		
		try {
			if(status) {
				count = 1;
				mp.setVolume(0.1);
				mp.play();
			}else {
				mp.stop();
				count=0;
			}
		}catch(Exception e) {
			e.printStackTrace();

		}
	}
	
	/***
	 * This method checks if the player's answer is correct or not.
	 * @param q
	 * @param playerAnswer
	 * @return
	 */
	public boolean isQuestionAnsweredCorrectly(Question q , String playerAnswer) {
		if(q == null)
			return false;
		if(q.getRightAnswer().equals(playerAnswer))
			return true;
		 return false;
	}
	/***
	 * This method is responsible for reading questions from JSON FILES and add them to the question's data structure
	 */
	public void readQuestionsFromJson() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Questions.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray questionsList = (JSONArray) jsonObject.get("questions");
			
			Iterator<JSONObject> iterator = questionsList.iterator();
			ArrayList<JSONObject> allQuestions = new ArrayList<JSONObject>();
			
			while(iterator.hasNext()) {
				allQuestions.add(iterator.next());
			}
			
			for(JSONObject temp : allQuestions) {
				String quesContent = temp.get("question").toString();
				String level = temp.get("level").toString();
				int correctAns = Integer.parseInt(temp.get("correct_ans").toString());
				ArrayList<String> answers =(ArrayList<String>) temp.get("answers");
				Question question = new Question(quesContent,level,answers,answers.get(correctAns-1));
				this.questionList.add(question);
			}
		}catch(ParseException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * This method is responsible for writing questions and it's contents in JSON File.
	 */
	@SuppressWarnings("unchecked")
	public void writeQuestionsToJson() {
		try {
		JSONArray questionsList = new JSONArray();
		for(Question que : this.questionList) {
			JSONObject questionDetails = new JSONObject();
			questionDetails.put("question", que.getContent());
			questionDetails.put("answers", que.getAnswers());
			questionDetails.put("correct_ans",que.getAnswers().indexOf(que.getRightAnswer())+1);
			
			if(que.getLevel() == Level.easy)
				questionDetails.put("level", "1");
			else if (que.getLevel() == Level.medium)
				questionDetails.put("level", "2");
			else
				questionDetails.put("level", "3");
			
			questionDetails.put("team", "animal");
			questionsList.add(questionDetails);

		}
		JSONObject obj = new JSONObject();
		obj.put("questions", questionsList);
		FileWriter file = new FileWriter("Questions.json");
		file.write(obj.toJSONString());
		file.flush();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/***
	 * This method read the leaderboard JSON file and adds the winners to the right data structure
	 */
	public void readLeaderboardFile() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Leaderboard.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray winnersList = (JSONArray) jsonObject.get("Winners");
			
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = winnersList.iterator();
			ArrayList<JSONObject> winners = new ArrayList<JSONObject>();
			
			while(iterator.hasNext()) {
				winners.add(iterator.next());
			}
			
			for(JSONObject temp : winners) {
				String winnerName = temp.get("Name").toString();
				int winnerPoints = Integer.parseInt(temp.get("Points").toString());
				String date = temp.get("Date").toString();
				Winner winner = new Winner(winnerName,winnerPoints,date);
				this.leaderboard.add(winner);
			}
		}catch(ParseException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * This method write the top winners in the leaderboard and save them into json file.
	 */
	@SuppressWarnings("unchecked")
	public void writeWinnersIntoFile() {
		try {
		JSONArray winnersList = new JSONArray();
		for(Winner win : this.leaderboard) {
			JSONObject winnerDetails = new JSONObject();
			winnerDetails.put("Name", win.getWinnerName());
			winnerDetails.put("Date",win.getDate());
			winnerDetails.put("Points",win.getWinnerPoints());
			winnersList.add(winnerDetails);

		}
		JSONObject obj = new JSONObject();
		obj.put("Winners", winnersList);
		FileWriter file = new FileWriter("Leaderboard.json");
		file.write(obj.toJSONString());
		file.flush();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * load the saved game from the TXT file
	 * @return Array of strings
	 */
	
	private static String[] loadBoardText(String path) {
		if(path == "" || path == null) {
			System.out.println("Invalid Path!");
			return null;
		}
		File file = new File(path);
		Scanner scanner = null;
		String str = "";
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				str+= scanner.nextLine();
			}
		}catch (FileNotFoundException e) {
             e.getStackTrace();
        }finally {
        	if(scanner != null) {
        		 scanner.close();
        	}
        }
		String[] toReturn =  str.split(",");
		return toReturn;		
	}
	
	/**
	 * method that gets a matrix of the board 
	 * @return matrix of numbers
	 */
	public  int[][] getBoard(String chosenFilePath){
		String arr[] = loadBoardText(chosenFilePath);
		int[][] board = {{-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1}
};
		int k=0;
		for(int i=0; i<8;i++) {
			for(int j=0 ; j<8 ; j++) {
				if(i%2==0 && j%2==1) {
					board[i][j]=Integer.parseInt(arr[k++]);
				}else if(i%2==1 && j%2==0){
					board[i][j]=Integer.parseInt(arr[k++]);
				}
			}
		}
		return board;
	}
	
	/**
	 * 
	 * @return which player in turn in saved game.
	 */
	public Color getTurn(String chosenFilePath) {
		String arr[] = loadBoardText(chosenFilePath);
		if(arr[arr.length-1].equals("B")) {
			return Color.Black;
		}
		return Color.White;
	}
	
	/***
	 * This method responsible for saving games , i.e writes the board and turn to an external file in order to load them when needed.
	 * @param board
	 * @param turn
	 */
	public void saveGame(Board board , Color turn , String fileName) {
		int board2D[][] = board.getBoard();
		try {
			FileWriter myWriter = new FileWriter("savedGames/" +fileName +".txt");
			for(int i = 0 ;i < 8; i++) {
				for(int j = 0; j < 8 ; j++) {
					if(board2D[i][j] != -1)
						myWriter.write(board2D[i][j] + ",");
				}
				myWriter.write('\n');
			}
			if(turn.equals(Color.Black))
				myWriter.write('B');
			else
				myWriter.write('W');
			myWriter.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
