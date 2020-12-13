package Tests;

import Model.Game;
import Model.Soldier;
import Model.Tile;
import Model.Board;
import Model.Color;
import junit.framework.Assert;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * All methods Tested on the board
 * 
			{ {-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,1,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{11,-1,1,-1,1,-1,1,-1} };
	
	and all expected results will be checked to that board,
	for the correct results the board in the game class should be changed to it, otherwise some are going to fail.
	Please before testing change the board in the Game class to the shown board above, otherwise some are going to fail.
	If set successfully all tests should be successful.
 */
class ClassTests {
	/*
	 * Please before testing change the board in the Game class to the shown board above, otherwise some are going to fail.
	 */
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private Game gm;
	//private Tile expected;
	private int[][] board = {
			{-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,1,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{11,-1,1,-1,1,-1,1,-1} };
	
	/*
	 * Initialize a game and a set of Tile (Used frequently in a lot of tests.)
	 */
//	@BeforeEach
//	public void setup() {
//		//gm = new Game("P1", "P2" , );
//		expected =  gm.getTile(1,2);
//	}

	/*
	 * Game.getTileContent() method test
	 * ID = 1
	 * Success.
	 */
	@Test
	void testGameGetContent() {
		//getTileContent when given the Tile (1,2) should return 2.
		Game gm = Game.getInstance("p1" , "p2" , board) ; 
		Tile expected = new Tile(1,2) ; 
		assertTrue((gm.getTileContent(expected).getSoldierNumber()) == (new Soldier(2).getSoldierNumber()));
		expected = gm.getTile(7,0);
		//getTileContent when given the Tile (3,2) should return 11.
		assertTrue((gm.getTileContent(expected).getSoldierNumber()) == (new Soldier(11).getSoldierNumber()));
	}
	
	/*
	 * Game.GetPossibleMovesForBlackSoldier() method test.
	 * ID = 2
	 * Success
	 */
	@Test
	void testGetPossibleMovesForBlackSoldier() {
	//	Tile Tile = gm.getTile(2, 1);
		Game gm = Game.getInstance("p1" , "p2" , board) ;       
		Soldier sol ; 
		sol= gm.getBoard().getSoldier(new Tile(6,1)) ;
		//getPossibleMovesForBlackSoldier when given obj=1 should return null.
		assertNull(gm.getPossibleMovesForBlackSoldier(sol));
		Soldier  sol2   ;
		sol2 =  gm.getBoard().getSoldier(new Tile(2,1)) ;
		System.out.println(sol2.getSoldierNumber()+ " //"+ sol2.getPosition());
		//sol.setPosition(new Tile(2, 1));
		ArrayList<Tile> moves = new ArrayList<Tile>();
		moves.add(gm.getTile(3,0));
		moves.add(gm.getTile(3, 2));
		//getPossibleMovesForBlackSoldier when given obj=2 and the (2,1) Tile  it should return an arraylist of Tiles similar to moves.
		//assertEquals(moves, gm.getPossibleMovesForBlackSoldier(obj, Tile));
		System.err.println("***********"	+gm.getBoard().getTileOfSoldier(sol2) ); 
	     assertTrue(gm.getPossibleMovesForBlackSoldier(sol2).equals(moves));
	}
	
	
	/*
	 * Game.GetPossibleMovesForWhiteSoldier() method test.
	 * Success
	 */
//	@Test
//	void testGetPossibleMovesForWhiteSoldier() {
//		Tile tile1 = gm.getTile(2, 1);
//		int obj = 2;
//		Soldier sol = new Soldier(2) ; 
//		sol.setPosition(tile);
//		//getPossibleMovesForWhiteSoldier when given obj=2 should return null.
//		assertNull(gm.getPossibleMovesForWhiteSoldier(sol) );
//		
//		tile1 = gm.getTile(5, 4);
//		ArrayList<Tile> moves = new ArrayList<Tile>();
//		moves.add(gm.getTile(4, 3));
//		moves.add(gm.getTile(4, 5));
//		//getPossibleMovesForWhiteSoldier when given obj=1 and the (5,4) Tile  it should return an arraylist of Tiles similar to moves.
//		assertEquals(moves, gm.getPossibleMovesForWhiteSoldier(obj, Tile));
	//	assertTrue(gm.getPossibleMovesForWhiteSoldier(obj, Tile).equals(moves));
//	}
	
	
	/*
	 * Game.MoveBlackSoldier() method test.
	 * ID = 3
	 * Success
	 */
	@Test
	void testMoveBlackSoldier() {
		Game gm = Game.getInstance("p1" , "p2" , board) ;       

		Tile currPos = gm.getTile(2, 1);
		Tile nextPos = gm.getTile(3, 2);
		Soldier sol ; 
		sol= gm.getBoard().getSoldier(currPos) ;
		ArrayList<Tile> moves = new ArrayList<Tile>();
		moves = gm.getPossibleMovesForBlackSoldier(sol);
		gm.moveBlackSoldier(sol ,nextPos,moves );
		
		/*The board before the move (Original board)
		/*
		{-1,2,-1,2,-1,2,-1,2},
		{2,-1,2,-1,2,-1,2,-1},
		{-1,2,-1,2,-1,2,-1,2},
		{0,-1,0,-1,0,-1,0,-1},
		{-1,0,-1,0,-1,0,-1,0},
		{1,-1,1,-1,1,-1,1,-1},
		{-1,1,-1,1,-1,1,-1,1},
		{11,-1,1,-1,1,-1,1,-1} };
		*/
		int[][] board = gm.getBoard().getBoard();
		int[][] expectedBoard =  {
				{-1,2,-1,2,-1,2,-1,2},
				{2,-1,2,-1,2,-1,2,-1},
				{-1,0,-1,2,-1,2,-1,2},
				{0,-1,2,-1,0,-1,0,-1},
				{-1,0,-1,0,-1,0,-1,0},
				{1,-1,1,-1,1,-1,1,-1},
				{-1,1,-1,1,-1,1,-1,1},
				{11,-1,1,-1,1,-1,1,-1} };
		
		
    
		//Test if board and expectedBoard are equall.
		for(int i=0; i<8;i++) {
			for(int j=0; j<8; j++) {
				assertTrue(board[i][j]==expectedBoard[i][j] );
			}
		}
		
	}
	
	/*
	 * Game.MoveWhiteSoldier() method test.
	 * Success
	 */
//	@Test
//	void testMoveWhiteSoldier() {
//		Tile currPos = gm.getTile(3, 4);
//		Tile nextPos = gm.getTile(2, 3);
//		
//		ArrayList<Tile> moves = new ArrayList<Tile>();
//		moves = gm.getPossibleMovesForBlackSoldier(1, currPos);
//		gm.moveWhiteSoldier(currPos, nextPos,moves );
//		
//		/*The board before the move (Original board)
//		 * {-1,2,-1,2,-1,2,-1,2},
//			{2,-1,2,-1,2,-1,2,-1},
//			{-1,2,-1,2,-1,2,-1,2},
//			{0,-1,11,-1,1,-1,0,-1},
//			{-1,0,-1,0,-1,0,-1,0},
//			{1,-1,0,-1,1,-1,1,-1},
//			{-1,2,-1,1,-1,1,-1,1},
//			{1,-1,0,-1,1,-1,1,-1}
//		 */
//		int[][] board = gm.getBoard();
//		//The board shouldn't change, since the move is invalid!.
//		int[][] expectedBoard =  {{-1,2,-1,2,-1,2,-1,2},
//				{2,-1,2,-1,2,-1,2,-1},
//				{-1,2,-1,2,-1,2,-1,2},
//				{0,-1,11,-1,1,-1,0,-1},
//				{-1,0,-1,0,-1,0,-1,0},
//				{1,-1,0,-1,1,-1,1,-1},
//				{-1,2,-1,1,-1,1,-1,1},
//				{1,-1,0,-1,1,-1,1,-1}};
//    
//		//First test - Test if board and expectedBoard are equall.
//		for(int i=0; i<8;i++) {
//			for(int j=0; j<8; j++) {
//				assertTrue(board[i][j]==expectedBoard[i][j] );
//			}
//		}
//		
//		//Second  test - Test if board and expectedBoard2 are equal.
//		
//		Game gm2 = new Game("P1", "P2");
//		currPos = gm2.getTile(5, 4);
//		nextPos = gm2.getTile(4, 5);
//		moves = gm2.getPossibleMovesForWhiteSoldier(1, currPos);
//		gm2.moveWhiteSoldier(currPos, nextPos,moves );
//		
//		board = gm2.getBoard();
//		//The board shouldn't change, since the move is invalid!.
//		int[][] expectedBoard2 =  {{-1,2,-1,2,-1,2,-1,2},
//				{2,-1,2,-1,2,-1,2,-1},
//				{-1,2,-1,2,-1,2,-1,2},
//				{0,-1,11,-1,1,-1,0,-1},
//				{-1,0,-1,0,-1,1,-1,0},
//				{1,-1,0,-1,0,-1,1,-1},
//				{-1,2,-1,1,-1,1,-1,1},
//				{0,-1,0,-1,1,-1,1,-1}};
//		
//    
//		for(int i=0; i<8;i++) {
//			for(int j=0; j<8; j++) {
//				assertTrue(board[i][j]==expectedBoard2[i][j] );
//			}
//		}
//		
//	}
//	
	
	/*
	 * Game.getKills() method test.
	 * ID = 4
	 * Success
	 */
	@Test
	void testGetSoldierWithKill() {
		
		int [][] bordo = {
				{-1,2,-1,2,-1,2,-1,2},
	            {2,-1,2,-1,2,-1,2,-1},
	            {-1,2,-1,2,-1,2,-1,2},
	            {0,-1,11,-1,1,-1,0,-1},
	            {-1,0,-1,0,-1,0,-1,0},
	            {1,-1,0,-1,1,-1,1,-1},
	            {-1,2,-1,1,-1,1,-1,1},
	            {1,-1,0,-1,1,-1,1,-1} };
			
		// gm.setBoard(new Board(bordo)); 
		Game gm = Game.getInstance("p1" , "p2" , bordo) ;       
		gm.setBoard(new Board(bordo)); 
		/*The board before the move (Original board)
		/*
		{-1,2,-1,2,-1,2,-1,2},
		{2,-1,2,-1,2,-1,2,-1},
		{-1,2,-1,2,-1,2,-1,2},
		{0,-1,0,-1,0,-1,0,-1},
		{-1,0,-1,0,-1,0,-1,0},
		{1,-1,1,-1,1,-1,1,-1},
		{-1,1,-1,1,-1,1,-1,1},
		{11,-1,1,-1,1,-1,1,-1} };
		*/
		
		ArrayList<Tile> kills = new ArrayList<Tile>();
		kills = gm.getKills(Color.Black);

		ArrayList<Tile> expectedKills = new ArrayList<Tile>();
		expectedKills.add(gm.getTile(4, 5));
		expectedKills.add(gm.getTile(4, 3));
		expectedKills.add(gm.getTile(4, 1));
		expectedKills.add(gm.getTile(4, 3));

		/*
		 * We need to check manually if kills contains all Tiles in expectedKills and vice versa
		 * Alternatively if we use assertEquals it needs the same order which in turn could lead to failing the test when in fact it is a success.
		 */
		boolean flag = true;
		for (Tile Tile : expectedKills) {
			if(!kills.contains(Tile))
				flag =false;
		}
		for (Tile Tile : kills) {
			if(!expectedKills.contains(Tile))
				flag =false;
		}
		 gm.setBoard(new Board(board));     // Game is A Singleton Class Changing Board- Affects Other Tests !   

		assertTrue(flag);
	}
	
	
	/*
	 * Game.getMiddleEnemySoldier() method test.
	 * ID = 5
	 * Success
	 * 
	 */
	@Test
	void testgetMiddleEnemySoldier() {
		
		int [][] bordo = {
				{-1,2,-1,2,-1,2,-1,2},
	            {2,-1,2,-1,2,-1,2,-1},
	            {-1,2,-1,2,-1,2,-1,2},
	            {0,-1,11,-1,1,-1,0,-1},
	            {-1,0,-1,0,-1,0,-1,0},
	            {1,-1,0,-1,1,-1,1,-1},
	            {-1,2,-1,1,-1,1,-1,1},
	            {1,-1,0,-1,1,-1,1,-1} };
				
		Game gm = Game.getInstance("p1" , "p2" , bordo) ;       
		gm.setBoard(new Board(bordo)); 

		
		Tile expected = gm.getTile(3, 2);
		Tile curr = gm.getTile(2, 1);
		Tile next = gm.getTile(4, 3);
		Soldier sol ; 
		sol= gm.getBoard().getSoldier(curr) ;
		Tile actual = gm.getMiddleEnemySoldier(sol, next);
		
		 gm.setBoard(new Board(board));     // Game is A Singleton Class Changing Board- Affects Other Tests !   

		assertEquals(expected, actual);
	}
	
	


	
	

	

	
}  // Class END
