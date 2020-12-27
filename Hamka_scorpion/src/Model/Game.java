package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Timer;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
	private static Game game_single = null;
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

	Color turn;

	public Game(String whitePlayer , String blackPlayer, int[][] gameBoard) {
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.whitePlayerPoints = 0;
		this.blackPlayerPoints = 0;
		turn = Color.Black;

		board = new Board(gameBoard);
		setSoldiersAndQueenNumbers();

	}


	// static method to create instance of Singleton class 
	public static Game getInstance(String whitePlayer , String blackPlayer, int[][] gameBoard) 
	{ 
		if (game_single == null) 
			game_single = new Game(blackPlayer, whitePlayer, gameBoard); 

		return game_single; 
	}


	public String getDate() {
		return date;
	}


	public int getblackPlayerPoints() {
		return blackPlayerPoints;
	}


	public int getWhitePlayerPoints() {
		return whitePlayerPoints;
	}


	public void setWhitePlayerPoints(int whitePlayerPoints) {
		this.whitePlayerPoints = whitePlayerPoints;
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

	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}

	public void setSoldiersAndQueenNumbers() {
		int whiteSolCount = 0, blackSolCount = 0, whiteQueCount = 0 , blackQueCount = 0;
		System.out.println(board);
		for(int i = 0 ; i < 8 ;i++) {
			for(int j = 0 ; j < 8 ; j++) {
				if(board.getBoard()[i][j] == 1)
					whiteSolCount++;
				else if(board.getBoard()[i][j] == 11)
					whiteQueCount++;
				else if(board.getBoard()[i][j] == 2)
					blackSolCount++;
				else if(board.getBoard()[i][j] == 22)
					blackQueCount++;
			}
		}
		this.blackPlayerQueens = blackQueCount;
		this.blackPlayerSoldiers = blackSolCount;
		this.whitePlayerQueens = whiteQueCount;
		this.whitePlayerSoldiers = whiteSolCount;
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
		if(s.getColor() != Color.Black && s.getSoldierNumber()!=2 || board.getTileOfSoldier(s)==null)
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
						if(y>0 && (getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == 2 || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() ==22 ) && isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x - 2,y + 2)) == null) 
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
				String killed;
				if(midEnemySol.getSoldierNumber()==2 ) {
					killed = "soldier!";
				}else {
					killed = "queen!";
				}
				board.removeSoldier(s, currentPos);
				if(nextPos.getX() == 0)
					board.setSoldier(new Queen(11), nextPos);
				else
					board.setSoldier(s, nextPos);
				board.removeSoldier(midEnemySol, midEnemyTile);
				if(killed.equals("queen")) {
					System.out.println("You killed enemy queen!");
					this.blackPlayerQueens--;
				}else {
					System.out.println("You killed enemy soldier!");
					this.blackPlayerSoldiers--;
				}
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
					if(nextPos.getX() == 0)
						board.setSoldier(new Queen(11), nextPos);
					else
						board.setSoldier(s, nextPos);
					board.removeSoldier(midEnemySol, midEnemyTile);
					System.out.println("You killed an enemy " + killed );
					if(killed.equals("soldier!")) {
						this.blackPlayerSoldiers--;
					}else {
						this.blackPlayerQueens--;
					}
					this.whitePlayerPoints+=100;

				}
			}
		}else {
			if(((allKillsAvailable != null && !allKillsAvailable.isEmpty()) || (getAllQueensKills(turn) != null &&!getAllQueensKills(turn).isEmpty())) && (!allKillsAvailable.contains(nextPos) || !isThisMoveAKill(s, nextPos))) {
				if(!allKillsAvailable.isEmpty()) {
					Tile deadSoldierTile = getSoldierWithKill(Color.White);
					Soldier deadSoldier = board.getSoldier(deadSoldierTile);
					if(deadSoldierTile != null) {
						if(nextPos.getX() != 0) {
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

							this.whitePlayerSoldiers-=2;
							System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + "  and became a queen, you also missed a kill , your soldier " + deadSoldierTile + " is dead.");
							this.whitePlayerQueens++;
						}
					}
				}else if(!getAllQueensKills(turn).isEmpty()) {
					Queen queenToBurn = getQueenWithKill(turn);
					if(nextPos.getX() != 0) {
						board.removeSoldier(s, currentPos);
						board.setSoldier(s, nextPos);
						board.removeSoldier(queenToBurn, queenToBurn.getPosition());
						this.whitePlayerQueens--;
						System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " , you also missed a kill , your queen " + queenToBurn.getPosition() + " is dead.");
					}else {
						board.removeSoldier(s, currentPos);
						board.setSoldier(new Queen(11), nextPos);
						board.removeSoldier(queenToBurn, queenToBurn.getPosition());
						this.whitePlayerSoldiers--;
						System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " and became a queen, you also missed a kill , your queen " + queenToBurn.getPosition() + " is dead.");
					}
				}
			}else {
				if(possibleMoves.contains(nextPos)) {
					if(nextPos.getX() == 0) {
						board.removeSoldier(s,currentPos);
						board.setSoldier(new Queen(11), nextPos);
						System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " and became a queen !");
						this.whitePlayerQueens++;
						this.whitePlayerSoldiers--;

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
				if(nextPos.getX() == 7)
					board.setSoldier(new Queen(22), nextPos);
				else
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
					String killed;
					if(midEnemySol.getSoldierNumber() == 1)
						killed = "soldier";
					else
						killed = "queen";
					board.removeSoldier(s, currentPos);
					board.removeSoldier(midEnemySol,midEnemyTile);
					if(nextPos.getX() == 7)
						board.setSoldier(new Queen(22), nextPos);
					else
						board.setSoldier(s,nextPos);
					board.removeSoldier(midEnemySol, midEnemyTile);
					if(killed.equals("queen")) {
						System.out.println("You killed enemy Queen");
						this.whitePlayerQueens--;
					}else {
						System.out.println("You killed enemy soldier");
						this.whitePlayerSoldiers--;
					}
					this.blackPlayerPoints+=100;
				}
			}
		}else {
			System.out.println(allKillsAvailable);
			if(((allKillsAvailable != null && !allKillsAvailable.isEmpty()) || (getAllQueensKills(turn) != null &&!getAllQueensKills(turn).isEmpty())) && (!allKillsAvailable.contains(nextPos) || !isThisMoveAKill(s, nextPos)) ) {
				if(!allKillsAvailable.isEmpty()) {
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
							this.blackPlayerSoldiers-=2;
							System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + "  and became a queen, you also missed a kill , your soldier " + soldierHasToDieTile + " is dead.");
							this.blackPlayerQueens++;
						}
					}
				}else if(!getAllQueensKills(turn).isEmpty()) {
					Queen queenToBurn = getQueenWithKill(turn);
					if(nextPos.getX() != 7) {
						board.removeSoldier(s, currentPos);
						board.setSoldier(s, nextPos);
						board.removeSoldier(queenToBurn, queenToBurn.getPosition());
						this.blackPlayerQueens--;
						System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " , you also missed a kill , your queen " + queenToBurn.getPosition() + " is dead.");
					}else {
						board.removeSoldier(s, currentPos);
						board.setSoldier(new Queen(22), nextPos);
						board.removeSoldier(queenToBurn, queenToBurn.getPosition());
						this.blackPlayerSoldiers--;
						System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " and became a queen, you also missed a kill , your queen " + queenToBurn.getPosition() + " is dead.");
					}
				}
			}else {
				if(possibleMoves.contains(nextPos)) {
					if(nextPos.getX() == 7) {
						board.removeSoldier(s, currentPos);
						board.setSoldier(new Queen(22), nextPos);
						System.out.println("soldier moved to " + nextPos.getX() + "," + nextPos.getY() + " and became a queen!");
						this.blackPlayerQueens++;
						this.blackPlayerSoldiers--;
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

	/***
	 * This method returns a hashmap of priority moves(kills) for queen.
	 * @param queen
	 * @return
	 */
	public HashMap<Tile,Soldier> priorityKill(Queen queen){
		HashMap<Tile,Soldier> allMoves = new HashMap<Tile,Soldier>();
		HashMap<Tile,Soldier> killsMoves  = new HashMap<>();
		HashMap<Tile,Soldier> priorKills = new HashMap<>();
		allMoves.putAll(getQueenBiasMoves(queen, "TR"));
		allMoves.putAll(getQueenBiasMoves(queen, "TL"));
		allMoves.putAll(getQueenBiasMoves(queen, "BR"));
		allMoves.putAll(getQueenBiasMoves(queen, "BL"));

		for(Map.Entry<Tile, Soldier> temp : allMoves.entrySet()) {
			if(temp.getValue() != null) {
				killsMoves.put(temp.getKey(), temp.getValue());
			}
		}
		for(Map.Entry<Tile, Soldier> temp : killsMoves.entrySet()) {
			if(temp.getValue().getPosition().getX() == 0 && temp.getValue().getPosition().getY() == 7 && ((queen.getPosition().getX() == 7 && queen.getPosition().getY() == 0) 
					|| (queen.getPosition().getX() == 1 && queen.getPosition().getY() == 6))) {
				priorKills.put(temp.getKey(), temp.getValue());
			}else if (temp.getValue().getPosition().getX() == 7 && temp.getValue().getPosition().getY() == 0 && ((queen.getPosition().getX() == 0 && queen.getPosition().getY()==7) 
					|| (queen.getPosition().getX() == 6 && queen.getPosition().getY() == 1))) {
				priorKills.put(temp.getKey(), temp.getValue());
			}else if(temp.getValue().getPosition().getX() - 1 == queen.getPosition().getX() || temp.getValue().getPosition().getX() + 1 == queen.getPosition().getX() ||
					temp.getValue().getPosition().getY() + 1 == queen.getPosition().getY() || temp.getValue().getPosition().getY() - 1 == queen.getPosition().getY()) {
				priorKills.put(temp.getKey(), temp.getValue());
			}
		}
		return  priorKills;
	}


	/***
	 * This method is responsible for moving queen accross the board :DD:D:D:D
	 * @param queen
	 * @param next
	 * @param possibleMoves
	 */
	public void queenMove(Queen queen, Tile next , HashMap<Tile,Soldier> possibleMoves) {
		Color turn = getTurn();
		if(queen == null || !isTileInFrame(next) || !possibleMoves.containsKey(next)) {
			System.out.println("Wrong Input");
			return;
		}
		else if(board.getSoldier(next) != null) {
			System.out.println("Cant go there, wrong destination");
			return;
		}
		else {
			int opSol,opQue;
			if(queen.getSoldierNumber() == 22) {
				opSol = 1;
				opQue = 11;
			}else {
				opSol = 2;
				opQue = 22;
			}
			if(!priorityKill(queen).isEmpty()) {
				if(priorityKill(queen).containsKey(next)) {
					Soldier middleKill = priorityKill(queen).get(next);
					String type = "";
					if(middleKill.getSoldierNumber() == opSol) {
						type = "soldier";
					}else {
						type = "queen";
					}
					board.removeSoldier(middleKill, middleKill.getPosition());
					board.removeSoldier(queen, queen.getPosition());
					board.setSoldier(queen, next);
					System.out.println("Queen moved to " + next.getX() + " , " + next.getY() + " and killed an enemy " + type);
					if(type.equals("soldier")) {
						if(opSol == 1) {
							this.whitePlayerSoldiers--;
							this.blackPlayerPoints+=100;
						}
						else {
							this.blackPlayerSoldiers--;
							this.whitePlayerPoints+=100;
						}
					}else if(type.equals("queen")) {
						if(opSol == 1) {
							this.whitePlayerQueens--;
							this.blackPlayerPoints+=100;
						}
						else {
							this.blackPlayerQueens--;
							this.whitePlayerPoints+=100;
						}
					}
				}else {
					System.out.println("You missed a prior kill , therefore your queen is burned");
					board.removeSoldier(queen, queen.getPosition());
				}
			}else if(((getKills(turn) != null && !getKills(turn).isEmpty()) || (getAllQueensKills(turn) != null &&!getAllQueensKills(turn).isEmpty())) && possibleMoves.get(next) == null) {
				if(!getKills(turn).isEmpty()) {
					System.out.println(getKills(turn).isEmpty());
					Soldier soldier = getTileContent(getSoldierWithKill(turn));
					board.removeSoldier(soldier, soldier.getPosition());
					board.removeSoldier(queen, queen.getPosition());
					board.setSoldier(queen, next);
					System.out.println("Queen moved to " + next + ", but your soldier in "+ soldier.getPosition()+" is burned");
					if(soldier.getSoldierNumber() == 2) 
						this.blackPlayerSoldiers--;
					else
						this.whitePlayerSoldiers--;
				}else if(!getAllQueensKills(turn).isEmpty()) {
					Queen queenToBurn = getQueenWithKill(turn);
					board.removeSoldier(queenToBurn, queenToBurn.getPosition());
					board.removeSoldier(queen, queen.getPosition());
					board.setSoldier(queen, next);
					System.out.println("Queen moved to " + next + ", but your queen in "+ queenToBurn.getPosition()+" is burned");
					if(queenToBurn.getSoldierNumber() == 22)
						this.blackPlayerQueens--;
					else
						this.whitePlayerQueens--;
				}
			}else {
				if(possibleMoves.get(next) != null) {
					Soldier sol = possibleMoves.get(next);
					if(sol.getSoldierNumber() == opSol) {
						System.out.println("Queen moved to " +next +" and killed enemy soldier in " + sol.getPosition());
						if(opSol == 2) {
							this.blackPlayerSoldiers--;
							this.whitePlayerPoints+=100;
						}
						else {
							this.blackPlayerPoints+=100;
							this.whitePlayerSoldiers--;
						}
					}else if (sol.getSoldierNumber() == opQue) {
						System.out.println("Queen moved to " +next +" and killed enemy queen in " + sol.getPosition());
						if(opQue == 22) {
							this.blackPlayerQueens--;
							this.whitePlayerPoints+=100;
						}
						else {
							this.blackPlayerPoints+=100;
							this.whitePlayerQueens--;
						}
					}
					board.removeSoldier(sol, sol.getPosition());
					board.removeSoldier(queen, queen.getPosition());
					board.setSoldier(queen, next);
				}else {
					System.out.println("Queen moved to " + next);
					board.removeSoldier(queen, queen.getPosition());
					board.setSoldier(queen, next);
				}
			}
		}
	}

	public Tile getQueenKill(Queen queen) {
	        HashMap<Tile,Soldier> allMoves  = new HashMap<>();
	        allMoves.putAll(getQueenBiasMoves(queen, "TR"));
	        allMoves.putAll(getQueenBiasMoves(queen, "TL"));
	        allMoves.putAll(getQueenBiasMoves(queen, "BR"));
	        allMoves.putAll(getQueenBiasMoves(queen, "BL"));

	        for(Map.Entry<Tile, Soldier> temp : allMoves.entrySet()) {
	            if(temp.getValue() != null)
	                return temp.getKey();
	        }
	        return null;
	    }
	    public ArrayList<Tile> getBiasQueenKills(Color turn){
	        ArrayList<Tile> queenKills = new ArrayList<>();

	        for(Tile t : board.getPlayerPositions(turn)) {
	            if(getTileContent(t).getSoldierNumber() == 22 || getTileContent(t).getSoldierNumber() == 11) {
	                Queen queen = (Queen)getTileContent(t);
	                if(getQueenKill(queen) != null)
	                    queenKills.add(getQueenKill(queen));
	            }
	        }
	        return queenKills;
	    }
	    
	    
	public ArrayList<Tile> getAllQueensKills(Color turn){
		ArrayList<Tile> allKills = new ArrayList<>();
		for(Tile t: board.getPlayerPositions(turn)) {
			if(getTileContent(t).getSoldierNumber() == 22 || getTileContent(t).getSoldierNumber()==11)
				if(priorityKill((Queen)getTileContent(t)) != null && !priorityKill((Queen)getTileContent(t)).isEmpty()) {
					allKills.addAll(priorityKill((Queen)getTileContent(t)).keySet());
				}
		}
		return allKills;
	}

	public Queen getQueenWithKill(Color turn) {
		for(Tile t: board.getPlayerPositions(turn)) {
			if((getTileContent(t).getSoldierNumber() == 22 && turn.equals(Color.Black)) || (getTileContent(t).getSoldierNumber()==11 && turn.equals(Color.White)))
				if(priorityKill((Queen)getTileContent(t)) != null && !priorityKill((Queen)getTileContent(t)).isEmpty()) {
					return (Queen)getTileContent(t);
				}
		}
		return null;
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
					if(solNum == 2 || solNum == 22) {
						this.blackPlayerPoints+=100;
						if(middleEnemySoldier.getSoldierNumber() == op)
							this.whitePlayerSoldiers--;
						else if(middleEnemySoldier.getSoldierNumber() == queenOp)
							this.whitePlayerQueens--;
					}
					else {
						this.whitePlayerPoints+=100;
						if(middleEnemySoldier.getSoldierNumber() == op)
							this.blackPlayerSoldiers--;
						else if(middleEnemySoldier.getSoldierNumber() == queenOp)
							this.blackPlayerQueens--;
					}
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
		System.out.println("- get kill moves Method - returning Null value ! ");
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

				if(!toReturn.contains(new Tile(x,y))) {
					toReturn.add(new Tile(x,y));
					count++;
				}
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
		HashSet<Tile> allPossibleMoves = new HashSet<Tile>();
		ArrayList<Tile> possibleMovesArray = new ArrayList<Tile>();
		if(turn == Color.Black) {
			HashMap<Tile, Soldier> blackSoldiers = board.getSameColorSoldiers(2);
			for(Soldier s : blackSoldiers.values()) {
				if(getPossibleMovesForBlackSoldier(s) != null)
					allPossibleMoves.addAll(getPossibleMovesForBlackSoldier(s));
			}
		}		
		else {

			HashMap<Tile, Soldier> whiteSoldiers = board.getSameColorSoldiers(1);
			for(Soldier s : whiteSoldiers.values()) {
				if( getPossibleMovesForWhiteSoldier(s) !=null)
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
	public boolean checkIfBlueTile(Color turn) {
		if(turn.equals(Color.White)) {
			if(board.countPiece(1)==2 && board.countPiece(11)>=1) {
				return true;
			}
		}else {
			if((board.countPiece(2)==2 && board.countPiece(22)>=1)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @return random Tile for a tile in the board as Blue tile.
	 */
	public Tile generateBlueTile(Color turn) {
		if(checkIfBlueTile(turn)) {
			while(true) {
				int x = (int)(Math.random()*8);
				int y = (int)(Math.random()*8);
				Tile tile = new Tile(x,y);
				if(board.getEmptyTiles().contains(tile)) {
					return tile;
				}
				
			}
			}
		
		System.out.println("Blue Til Gene- returninng Null ");
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
		if((getTileContent(getTile(x-2, y))!=null && (getTileContent(new Tile(x-2, y)).getSoldierNumber()==op || getTileContent(new Tile(x-2, y)).getSoldierNumber()==queOp)) ||
		   (getTileContent(getTile(x+2, y))!=null && (getTileContent(new Tile(x+2, y)).getSoldierNumber()==op ||  getTileContent(new Tile(x+2, y)).getSoldierNumber()==queOp))||
		   (getTileContent(getTile(x, y-2))!=null  && (getTileContent(new Tile(x, y-2)).getSoldierNumber()==op || getTileContent(new Tile(x, y-2)).getSoldierNumber()==queOp)) ||
		   (getTileContent(getTile(x, y+2))!=null && (getTileContent(new Tile(x, y+2)).getSoldierNumber()==op ||  getTileContent(new Tile(x, y+2)).getSoldierNumber()==queOp))) 
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
				if(counter<2 && (getTileContent(new Tile(i,j))!=null && (getTileContent(new Tile(i,j)).getSoldierNumber() == op || getTileContent(new Tile(i,j)).getSoldierNumber() == queOp ))) {
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
		HashSet<Tile> allPossibleMoves = new HashSet<Tile>();
		if(turn == null)
			return null;
		if(turn == Color.Black) {
			for(Soldier s : board.getSameColorSoldiers(2).values()) {
				if(getPossibleMovesForBlackSoldier(s) != null )
					allPossibleMoves.addAll(getPossibleMovesForBlackSoldier(s));
			}
		}
		else {
			for(Soldier s : board.getSameColorSoldiers(1).values()) {
				if(getPossibleMovesForWhiteSoldier(s)!=null)
					allPossibleMoves.addAll(getPossibleMovesForWhiteSoldier(s));
			}
		}
		toReturn.addAll(allPossibleMoves);
		//	        System.out.println("orange tiles : "+toReturn);
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
		if(getKills(turn)== null || getKills(turn).size()< 1) {
			for(Tile tile : board.getPlayerPositions(turn)) {

				if(turn.equals(Color.Black) && getPossibleMovesForBlackSoldier(getTileContent(tile))!=null && getBiasQueenKills(Color.Black).isEmpty()) {
					candidates.addAll(getPossibleMovesForBlackSoldier(getTileContent(tile)));
				}else if (getPossibleMovesForWhiteSoldier(getTileContent(tile))!=null && getBiasQueenKills(Color.White).isEmpty()){
					candidates.addAll(getPossibleMovesForWhiteSoldier(getTileContent(tile)));
				}
			}
		}
		int max = candidates.size();
		//  System.out.println(candidates);
		int random = (int)(Math.random()*max);
		//       System.out.println(" red Tile Feild random : "+ random);
		//       System.out.println(candidates +" this is candidates ! ");
		if(candidates.size() > random)
			return candidates.get(random);

		//       System.out.println("Returning  NULLL ! ");
		return null ; 
	}


	public HashMap<Tile,Soldier> getQueenBiasMoves(Queen queen , String direction){
		HashMap<Tile,Soldier> movesMap = new HashMap<>();
		Tile lastTileInBias = null;
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
			while(isTileInFrame(new Tile(x-1,y+1)) && (getTileContent(new Tile(x-1,y+1)) == null || ((getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x-2,y+2)) == null)))) {
				if(getTileContent(new Tile(x-1,y+1)) == null){
					queen.getQueenMoves().put(new Tile(x-1,y+1), null);
					movesMap.put(new Tile(x-1,y+1), null);
					lastTileInBias = new Tile(x-1,y+1);
				}else if (isTileInFrame(new Tile(x-1,y+1)) && (getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y+2)) && getTileContent(new Tile(x-2,y+2)) == null)) {
					queen.getQueenMoves().put(new Tile(x-2,y+2),getTileContent(new Tile(x-1,y+1)));
					movesMap.put(new Tile(x-2,y+2),getTileContent(new Tile(x-1,y+1)));
					lastTileInBias = new Tile(x-2,y+2);
					break;
				}
				x--;
				y++;
			}
		}else if(direction.equals("TL")) {//if top left
			while(isTileInFrame(new Tile(x-1,y-1)) && (getTileContent(new Tile(x-1,y-1)) == null || ((getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x-2,y-2)) == null)))) {
				if(getTileContent(new Tile(x-1,y-1)) == null){
					queen.getQueenMoves().put(new Tile(x-1,y-1), null);
					movesMap.put(new Tile(x-1,y-1), null);
					lastTileInBias = new Tile(x-1,y-1);
				}else if ((getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x-1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x-2,y-2)) && getTileContent(new Tile(x-2,y-2)) == null)) {
					queen.getQueenMoves().put(new Tile(x-2,y-2),getTileContent(new Tile(x-1,y-1)));
					movesMap.put(new Tile(x-2,y-2),getTileContent(new Tile(x-1,y-1)));
					lastTileInBias = new Tile(x-2,y-2);
					break;
				}
				x--;
				y--;
			}
		}else if(direction.equals("BR")) { //if bottom right
			while(isTileInFrame(new Tile(x+1,y+1)) && (getTileContent(new Tile(x+1,y+1)) == null || ((getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y+2)) && getTileContent(new Tile(x+2,y+2)) == null)))) {
				if(getTileContent(new Tile(x+1,y+1)) == null){
					queen.getQueenMoves().put(new Tile(x+1,y+1), null);
					movesMap.put(new Tile(x+1,y+1), null);
					lastTileInBias = new Tile(x+1,y+1);
				}else if ((getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y+1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y+2)) && getTileContent(new Tile(x+2,y+2)) == null)) {
					queen.getQueenMoves().put(new Tile(x+2,y+2),getTileContent(new Tile(x+1,y+1)));
					movesMap.put(new Tile(x+2,y+2),getTileContent(new Tile(x+1,y+1)));
					lastTileInBias = new Tile(x+2,y+2);
					break;
				}
				x++;
				y++;
			}
		}else if(direction.equals("BL")) { //if Bottom left
			while(isTileInFrame(new Tile(x+1,y-1)) && (getTileContent(new Tile(x+1,y-1)) == null || ((getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y-2)) && getTileContent(new Tile(x+2,y-2)) == null)))) {
				if(getTileContent(new Tile(x+1,y-1)) == null){
					queen.getQueenMoves().put(new Tile(x+1,y-1), null);
					movesMap.put(new Tile(x+1,y-1), null);
					lastTileInBias = new Tile(x+1,y-1);

				}else if ((getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opSol || getTileContent(new Tile(x+1,y-1)).getSoldierNumber() == opQue) && (isTileInFrame(new Tile(x+2,y-2)) && getTileContent(new Tile(x+2,y-2)) == null)) {
					queen.getQueenMoves().put(new Tile(x+2,y-2),getTileContent(new Tile(x+1,y-1)));
					movesMap.put(new Tile(x+2,y-2),getTileContent(new Tile(x+1,y-1)));
					lastTileInBias = new Tile(x+2,y-2);
					break;
				}
				x++;
				y--;
			}

		}
		// if there is a kill in the queen moves , then dont search for cross board movements .
		boolean flag = true;
		for(Map.Entry<Tile, Soldier> searchForKill : movesMap.entrySet()) {
			if(searchForKill.getValue() != null){
				flag= false;
			}
		}

		if(lastTileInBias == null ) {
			if(direction.equals("TR")) {
				if(queen.getPosition().getX() - 1 == 0 && queen.getPosition().getY() + 1 == 7 && (getTileContent(new Tile(0,7)) != null && (getTileContent(new Tile(0,7)).getSoldierNumber() == opSol ||
						getTileContent(new Tile(0,7)).getSoldierNumber() == opQue))) {
					if(getTileContent(new Tile(7,0)) == null) {
						queen.getQueenMoves().put(new Tile(7,0),getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() + 1)));
						movesMap.put(new Tile(7,0),getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() + 1)));
					}
				}else if(queen.getPosition().getX() - 1 == 0 && queen.getPosition().getY() + 1 != 7 &&getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)) != null && 
						(getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)).getSoldierNumber() == opSol || getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)).getSoldierNumber() == opQue)){
					if(getTileContent(new Tile(7,queen.getPosition().getY() + 2)) == null) {
						movesMap.put(new Tile(7,queen.getPosition().getY()+2),getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)));
						queen.getQueenMoves().put(new Tile(7,queen.getPosition().getY()+2),getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)));
					}
				}else if(queen.getPosition().getY() + 1 == 7 && queen.getPosition().getX() - 1 != 0 && getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() + 1)) != null
						&& (getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() + 1)).getSoldierNumber() == opSol || getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() + 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(queen.getPosition().getX() - 2, 0)) == null) {
						movesMap.put(new Tile(queen.getPosition().getX() - 2,0),getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)));
						queen.getQueenMoves().put(new Tile(queen.getPosition().getX() - 2,0),getTileContent(new Tile(queen.getPosition().getX()-1,queen.getPosition().getY()+1)));
					}
				}
			}else if(direction.equals("BR")) {
				if(queen.getPosition().getX() + 1 ==7 && getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1))!=null &&
						(getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)).getSoldierNumber() == opSol ||
						getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(0,queen.getPosition().getY() + 2)) == null && isTileInFrame(new Tile(0,queen.getPosition().getY() + 2))) {
						queen.getQueenMoves().put(new Tile(0,queen.getPosition().getY() + 2),getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)));
						movesMap.put(new Tile(0,queen.getPosition().getY() + 2),getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)));
					}
				}else if(queen.getPosition().getY() + 1 == 7 && getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1))!=null
						&& (getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)).getSoldierNumber() == opSol ||
						getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(queen.getPosition().getX() + 2,0)) == null) {
						queen.getQueenMoves().put(new Tile(queen.getPosition().getX() + 2,0), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)));
						movesMap.put(new Tile(queen.getPosition().getX() + 2,0), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() + 1)));
					}
				}
			}else if(direction.equals("TL")) {
				if(queen.getPosition().getX() - 1 == 0 && getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)) != null 
						&& (getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opSol || 
						getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(7,queen.getPosition().getY() - 2)) == null && isTileInFrame(new Tile(7,queen.getPosition().getY() - 2))) {
						movesMap.put(new Tile(7,queen.getPosition().getY() - 2), getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)));
						queen.getQueenMoves().put(new Tile(7,queen.getPosition().getY() - 2), getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)));
					}
				}else if(queen.getPosition().getY() - 1 == 0 && getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)) != null 
						&& (getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opSol || 
						getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(queen.getPosition().getX() - 2,7)) == null && isTileInFrame(new Tile(queen.getPosition().getX() - 2,7))) {
						movesMap.put(new Tile(queen.getPosition().getX() - 2,7), getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY()-1)));
						queen.getQueenMoves().put(new Tile(queen.getPosition().getX() - 2,7), getTileContent(new Tile(queen.getPosition().getX() - 1,queen.getPosition().getY()-1)));
					}
				}
			}else if(direction.equals("BL")) {
				if(queen.getPosition().getX() + 1 == 7 && queen.getPosition().getY() - 1 == 0 && getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)) != null
						&& (getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opSol || 
						getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(0,7)) == null) {
						movesMap.put(new Tile(0,7), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)));
						queen.getQueenMoves().put(new Tile(0,7), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)));
					}
				}else if(queen.getPosition().getX()+ 1 == 7 && queen.getPosition().getY() - 1!= 0 && getTileContent(new Tile(queen.getPosition().getX()+1,queen.getPosition().getY() -1)) != null
						&& (getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opSol || 
						getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(0,queen.getPosition().getY() - 2)) == null && isTileInFrame(new Tile(0,queen.getPosition().getY() -2))) {
						movesMap.put(new Tile(0,queen.getPosition().getY() - 2), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)));
						queen.getQueenMoves().put(new Tile(0,queen.getPosition().getY() - 2), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)));
					}
				}else if(queen.getPosition().getY() -1 == 0 && queen.getPosition().getX()+ 1 != 7 && getTileContent(new Tile(queen.getPosition().getX()+1,queen.getPosition().getY()-1))!= null && 
						(getTileContent(new Tile(queen.getPosition().getX()+1,queen.getPosition().getY() - 1)).getSoldierNumber() == opSol || 
						getTileContent(new Tile(queen.getPosition().getX()+1,queen.getPosition().getY() - 1)).getSoldierNumber() == opQue)) {
					if(getTileContent(new Tile(queen.getPosition().getX() + 2, 7)) == null && isTileInFrame(new Tile(queen.getPosition().getX() + 2,7))) {
						movesMap.put(new Tile(queen.getPosition().getX() + 2,7), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)));
						queen.getQueenMoves().put(new Tile(queen.getPosition().getX() + 2,7), getTileContent(new Tile(queen.getPosition().getX() + 1,queen.getPosition().getY() - 1)));
					}
				}
			}
		}
		if( direction.equals("TR") && flag) {
			for(Map.Entry<Tile, Soldier> temp : getCrossBoardMoves(queen, lastTileInBias, direction).entrySet()) {
				movesMap.put(temp.getKey(), temp.getValue());
			}
		}else if( direction.equals("TL") && flag) {
			for(Map.Entry<Tile, Soldier> temp : getCrossBoardMoves(queen, lastTileInBias, direction).entrySet()) {
				movesMap.put(temp.getKey(), temp.getValue());
			}
		}else if( direction.equals("BL") && flag) {
			for(Map.Entry<Tile, Soldier> temp : getCrossBoardMoves(queen, lastTileInBias, direction).entrySet()) {
				movesMap.put(temp.getKey(), temp.getValue());
			}
		}else if(direction.equals("BR") && flag) {
			for(Map.Entry<Tile, Soldier> temp : getCrossBoardMoves(queen, lastTileInBias, direction).entrySet()) {
				movesMap.put(temp.getKey(), temp.getValue());
			}
		}
		return movesMap;
	}

	/***
	 * This method returns the cross board movements if there were any, of course it returns only the cross board movement for the given direction
	 * @param queen
	 * @param lastTileInBias
	 * @param direction
	 * @return
	 */
	public HashMap<Tile,Soldier> getCrossBoardMoves(Queen queen , Tile lastTileInBias , String direction){
		HashMap<Tile,Soldier> extraMoves = new HashMap<Tile,Soldier>();
		int opSol,opQue;
		if(queen.getSoldierNumber() == 22) {
			opSol = 1;
			opQue = 11;
		}else {
			opSol = 2;
			opQue = 22;
		}

		if(lastTileInBias == null) {
			lastTileInBias = queen.getPosition();
		}
		if(direction.equals("TR")) {
			if(lastTileInBias.getX() == 0 && lastTileInBias.getY() == 7) {
				extraMoves = topRightMovement(queen,new Tile(7,0));
			}else if(lastTileInBias.getX() == 0) {
				extraMoves = topRightMovement(queen,new Tile(7,lastTileInBias.getY() + 1));
			}else if(lastTileInBias.getY() == 7) {
				extraMoves = topRightMovement(queen, new Tile(lastTileInBias.getX() - 1, 0));
			}

			else if(lastTileInBias.getX() - 1 == 0 && lastTileInBias.getY() + 1 == 7 && (getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)).getSoldierNumber() == opSol 
					|| getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(7,0)) == null) {
					extraMoves.put(new Tile(7,0), getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)));
					queen.getQueenMoves().put(new Tile(7,0), getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)));
				}
			}else if(lastTileInBias.getX() - 1 == 0 && (getTileContent(new Tile(lastTileInBias.getX() - 1 , lastTileInBias.getY() + 1)).getSoldierNumber() == opSol || 
					getTileContent(new Tile(lastTileInBias.getX() - 1 , lastTileInBias.getY() + 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(7,lastTileInBias.getY() + 2)) == null) {
					extraMoves.put(new Tile(7,lastTileInBias.getY() + 2), getTileContent(new Tile(lastTileInBias.getX() - 1 , lastTileInBias.getY() + 1)));
					queen.getQueenMoves().put(new Tile(7,lastTileInBias.getY() + 2), getTileContent(new Tile(lastTileInBias.getX() - 1 , lastTileInBias.getY() + 1)));
				}
			}else if(lastTileInBias.getY() + 1 == 7 && (getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)).getSoldierNumber() == opSol || 
					getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(lastTileInBias.getX() - 2, 0)) == null) {
					extraMoves.put(new Tile(lastTileInBias.getX() - 2, 0),getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)));
					queen.getQueenMoves().put(new Tile(lastTileInBias.getX() - 2, 0),getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() + 1)));
				}
			}
		}else if(direction.equals("TL")) {
			if(lastTileInBias.getX() == 0) {
				extraMoves = topLeftMovement(queen, new Tile(7,lastTileInBias.getY() - 1));
			}else if(lastTileInBias.getY() == 0) {
				extraMoves = topLeftMovement(queen, new Tile(lastTileInBias.getX() - 1, 7));
			}else if(lastTileInBias.getX() - 1 == 0 && (getTileContent(new Tile(lastTileInBias.getX() - 1, lastTileInBias.getY() - 1)).getSoldierNumber() == opSol || 
					getTileContent(new Tile(lastTileInBias.getX() - 1, lastTileInBias.getY() - 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(7,lastTileInBias.getY() - 2)) == null) {
					extraMoves.put(new Tile(7,lastTileInBias.getY() - 2) , getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() - 1)));
					queen.getQueenMoves().put(new Tile(7,lastTileInBias.getY() - 2) , getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() - 1)));
				}
			}else if(lastTileInBias.getY() - 1 == 0 &&(getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opSol ||
					getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(lastTileInBias.getX() - 2, 7 )) == null) {
					extraMoves.put(new Tile(lastTileInBias.getX() - 2, 7), getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() - 1)));
					queen.getQueenMoves().put(new Tile(lastTileInBias.getX() - 2, 7), getTileContent(new Tile(lastTileInBias.getX() - 1,lastTileInBias.getY() - 1)));
				}
			}
		}else if(direction.equals("BR")) {
			if(lastTileInBias.getX() == 7) {
				extraMoves = bottomRightMovement(queen, new Tile(0,lastTileInBias.getY() + 1));
			}else if(lastTileInBias.getY() == 7) {
				extraMoves = bottomRightMovement(queen, new Tile(lastTileInBias.getX() + 1,0));
			}else if(lastTileInBias.getX() + 1 == 7 && (getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)).getSoldierNumber() ==opSol || 
					getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)).getSoldierNumber() ==opQue)) {
				if(getTileContent(new Tile(0,lastTileInBias.getY() + 2)) == null) {
					extraMoves.put(new Tile(0,lastTileInBias.getY() + 2),getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)));
					queen.getQueenMoves().put(new Tile(0,lastTileInBias.getY() + 2),getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)));

				}
			}else if(lastTileInBias.getY() + 1 == 7 && (getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)).getSoldierNumber() == opSol || 
					getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(lastTileInBias.getX() + 2,0)) == null) {
					extraMoves.put(new Tile(lastTileInBias.getX() + 2,0), getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)));
					queen.getQueenMoves().put(new Tile(lastTileInBias.getX() + 2,0), getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() + 1)));
				}
			}
		}else if(direction.equals("BL")) {
			if(lastTileInBias.getX() == 7 && lastTileInBias.getY() == 0) {
				extraMoves = bottomLeftMovement(queen, new Tile(0,7));
			}else if(lastTileInBias.getX() == 7) {
				extraMoves = bottomLeftMovement(queen, new Tile(0,lastTileInBias.getY() - 1));
			}else if(lastTileInBias.getY() == 0) {
				extraMoves = bottomLeftMovement(queen, new Tile(lastTileInBias.getX() + 1, 7));
			}else if(lastTileInBias.getX() + 1 == 7 && lastTileInBias.getY() - 1 == 0 && (getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opSol || 
					getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opQue)){
				if(getTileContent(new Tile(0,7)) == null) {
					extraMoves.put(new Tile(0,7), getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)));
					queen.getQueenMoves().put(new Tile(0,7), getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)));
				}
			}else if(lastTileInBias.getX() + 1 == 7 && (getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opSol ||
					getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(0,lastTileInBias.getY() - 2)) == null) {
					extraMoves.put(new Tile(0,lastTileInBias.getY() - 2), getTileContent(new Tile(lastTileInBias.getX()+ 1,lastTileInBias.getY() - 1)));
					queen.getQueenMoves().put(new Tile(0,lastTileInBias.getY() - 2), getTileContent(new Tile(lastTileInBias.getX()+ 1,lastTileInBias.getY() - 1)));
				}
			}else if(lastTileInBias.getY() - 1 == 0 && (getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opSol || 
					getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)).getSoldierNumber() == opQue)) {
				if(getTileContent(new Tile(lastTileInBias.getX() + 2,7)) == null) {
					extraMoves.put(new Tile(lastTileInBias.getX() + 2,7),getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)));
					queen.getQueenMoves().put(new Tile(lastTileInBias.getX() + 2,7),getTileContent(new Tile(lastTileInBias.getX() + 1,lastTileInBias.getY() - 1)));
				}
			}
		}
		return extraMoves;
	}

	/***
	 * This method returns all the Top-Right movements
	 * @param queen
	 * @param tile
	 * @return
	 */
	public HashMap<Tile,Soldier> topRightMovement(Queen queen,Tile tile ){
		if(tile == null)
			return null;
		HashMap<Tile,Soldier> toReturn = new HashMap<>();
		int opSol,opQue;
		int x = tile.getX();
		int y = tile.getY();
		if(queen.getSoldierNumber() == 22) {
			opSol = 1;
			opQue = 11;
		}else {
			opSol = 2;
			opQue = 22;
		}
		while(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) == null ) {
			toReturn.put(new Tile(x,y), null);
			queen.getQueenMoves().put(new Tile(x,y), null);
			x--;
			y++;
		}
		if(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) != null && (getTileContent(new Tile(x,y)).getSoldierNumber() == opSol || 
				getTileContent(new Tile(x,y)).getSoldierNumber() == opQue) && isTileInFrame(new Tile(x-1,y+1)) && getTileContent(new Tile(x-1,y+1)) == null) {
			toReturn.put(new Tile(x-1,y+1), getTileContent(new Tile(x,y)));
			queen.getQueenMoves().put(new Tile(x-1,y+1), getTileContent(new Tile(x,y)));

		}
		return toReturn;
	}

	/***
	 * This method returns all the Top-Left movements
	 * @param queen
	 * @param tile
	 * @return
	 */
	public HashMap<Tile,Soldier> topLeftMovement(Queen queen,Tile tile ){
		if(tile == null)
			return null;
		HashMap<Tile,Soldier> toReturn = new HashMap<>();
		int opSol,opQue;
		int x = tile.getX();
		int y = tile.getY();
		if(queen.getSoldierNumber() == 22) {
			opSol = 1;
			opQue = 11;
		}else {
			opSol = 2;
			opQue = 22;
		}
		while(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) == null ) {
			toReturn.put(new Tile(x,y), null);
			queen.getQueenMoves().put(new Tile(x,y), null);

			x--;
			y--;
		}
		if(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) != null && (getTileContent(new Tile(x,y)).getSoldierNumber() == opSol || 
				getTileContent(new Tile(x,y)).getSoldierNumber() == opQue) && isTileInFrame(new Tile(x-1,y-1)) && getTileContent(new Tile(x-1,y-1)) == null) {
			toReturn.put(new Tile(x-1,y-1), getTileContent(new Tile(x,y)));
			queen.getQueenMoves().put(new Tile(x-1,y-1), getTileContent(new Tile(x,y)));
		}
		return toReturn;
	}

	/***
	 * This method returns all the Bottom-Right movements
	 * @param queen
	 * @param tile
	 * @return
	 */
	public HashMap<Tile,Soldier> bottomRightMovement(Queen queen,Tile tile ){
		if(tile == null)
			return null;
		HashMap<Tile,Soldier> toReturn = new HashMap<>();
		int opSol,opQue;
		int x = tile.getX();
		int y = tile.getY();
		if(queen.getSoldierNumber() == 22) {
			opSol = 1;
			opQue = 11;
		}else {
			opSol = 2;
			opQue = 22;
		}
		while(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) == null ) {
			toReturn.put(new Tile(x,y), null);
			queen.getQueenMoves().put(new Tile(x,y), null);

			x++;
			y++;
		}
		if(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) != null && (getTileContent(new Tile(x,y)).getSoldierNumber() == opSol || 
				getTileContent(new Tile(x,y)).getSoldierNumber() == opQue) && isTileInFrame(new Tile(x+1,y+1)) && getTileContent(new Tile(x+1,y+1)) == null) {
			toReturn.put(new Tile(x+1,y+1), getTileContent(new Tile(x,y)));
			queen.getQueenMoves().put(new Tile(x+1,y+1), getTileContent(new Tile(x,y)));
		}
		return toReturn;
	}

	/***
	 * This method returns all the Bottom-Left movements
	 * @param queen
	 * @param tile
	 * @return
	 */
	public HashMap<Tile,Soldier> bottomLeftMovement(Queen queen,Tile tile ){
		if(tile == null)
			return null;
		HashMap<Tile,Soldier> toReturn = new HashMap<>();
		int opSol,opQue;
		int x = tile.getX();
		int y = tile.getY();
		if(queen.getSoldierNumber() == 22) {
			opSol = 1;
			opQue = 11;
		}else {
			opSol = 2;
			opQue = 22;
		}
		while(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) == null ) {
			toReturn.put(new Tile(x,y), null);
			queen.getQueenMoves().put(new Tile(x,y), null);
			x++;
			y--;
		}
		if(isTileInFrame(new Tile(x,y)) && getTileContent(new Tile(x,y)) != null && (getTileContent(new Tile(x,y)).getSoldierNumber() == opSol || 
				getTileContent(new Tile(x,y)).getSoldierNumber() == opQue) && isTileInFrame(new Tile(x+1,y-1)) && getTileContent(new Tile(x+1,y-1)) == null) {
			toReturn.put(new Tile(x+1,y-1), getTileContent(new Tile(x,y)));
			queen.getQueenMoves().put(new Tile(x+1,y-1), getTileContent(new Tile(x,y)));
		}
		return toReturn;
	}




	  
	public HashMap<Tile,Soldier> getQueenAllDirectionsMoves(Queen queen){
		HashMap<Tile,Soldier> allMoves = new HashMap<>();
		allMoves.putAll(getQueenBiasMoves(queen, "TR"));
		allMoves.putAll(getQueenBiasMoves(queen, "TL"));
		allMoves.putAll(getQueenBiasMoves(queen, "BR"));
		allMoves.putAll(getQueenBiasMoves(queen, "BL"));
		return allMoves;
	}

	/***
	 * This method checks if there are no more moves for the player to make.
	 * @param turn
	 * @return
	 */
	public boolean noMoreMovesForPlayer(Color turn) {
		ArrayList<Tile> blackMoves = new ArrayList<Tile>();
		ArrayList<Tile> whiteMoves = new ArrayList<Tile>();
		if(turn.equals(Color.Black)) {
			for(Tile tile : board.getPlayerPositions(Color.Black)) {
				if(getTileContent(tile).getSoldierNumber() == 2) {
					if(getPossibleMovesForBlackSoldier(getTileContent(tile)) != null) {
						blackMoves.addAll(getPossibleMovesForBlackSoldier(getTileContent(tile)));
					}
				}else if(getTileContent(tile).getSoldierNumber() == 22) {
					if(getQueenAllDirectionsMoves((Queen)getTileContent(tile)) != null) {
						blackMoves.addAll(getQueenAllDirectionsMoves((Queen)getTileContent(tile)).keySet());
					}
				}
			}
		}else {

			for(Tile tile : board.getPlayerPositions(Color.White)) {
				if(getTileContent(tile).getSoldierNumber() == 1) {
					if(getPossibleMovesForWhiteSoldier(getTileContent(tile)) != null) {
						whiteMoves.addAll(getPossibleMovesForWhiteSoldier(getTileContent(tile)));
					}
				}else if(getTileContent(tile).getSoldierNumber() == 11) {
					if(getQueenAllDirectionsMoves((Queen)getTileContent(tile)) != null) {
						whiteMoves.addAll(getQueenAllDirectionsMoves((Queen)getTileContent(tile)).keySet());
					}
				}
			}
		}

		if(turn.equals(Color.Black) && blackMoves.isEmpty())
			return true;
		if(turn.equals(Color.White) && whiteMoves.isEmpty())
			return true;
		return false;
	}	
	/***
	 * Checks if the game is over or not.
	 * @return
	 */
	public boolean isGameOver() {
		if((this.whitePlayerSoldiers == 0 && this.whitePlayerQueens == 0 ) || (this.blackPlayerQueens == 0 && this.blackPlayerSoldiers == 0) || (noMoreMovesForPlayer(Color.Black) || noMoreMovesForPlayer(Color.White)))
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
			if((this.whitePlayerSoldiers==0 && this.whitePlayerQueens==0) || (this.blackPlayerQueens + this.blackPlayerSoldiers > this.whitePlayerSoldiers + this.whitePlayerQueens))
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
