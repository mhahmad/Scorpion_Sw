package Tests;

import Model.Game;
import Model.Tile;
import Model.Color;
import junit.framework.Assert;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * All methods Tested on the board
 * {-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,11,-1,1,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,0,-1,1,-1,1,-1},
			{-1,2,-1,1,-1,1,-1,1},
			{1,-1,0,-1,1,-1,1,-1}
	
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
	private Tile expected;
	
	
	/*
	 * Initialize a game and a set of Tile (Used frequently in a lot of tests.)
	 */
	@BeforeEach
	public void setup() {
		gm = new Game("P1", "P2");
		expected =  gm.getTile(1,2);
	}

	/*
	 * Game.getTileContent() method test
	 * Success.
	 */
	@Test
	void testGameGetContent() {
		//getTileContent when given the Tile (1,2) should return 2.
		assertTrue(gm.getTileContent(expected)==2);
		expected = gm.getTile(3,2);
		//getTileContent when given the Tile (3,2) should return 11.
		assertTrue(gm.getTileContent(expected)==11);
	}
	
	/*
	 * Game.GetPossibleMovesForBlackSoldier() method test.
	 * Success
	 */
	@Test
	void testGetPossibleMovesForBlackSoldier() {
		Tile Tile = gm.getTile(2, 1);
		int obj = 1;
		//getPossibleMovesForBlackSoldier when given obj=1 should return null.
		assertTrue(gm.getPossibleMovesForBlackSoldier(obj, Tile) == null);
		obj = 2;
		ArrayList<Tile> moves = new ArrayList<Tile>();
		moves.add(gm.getTile(3, 0));
		moves.add(gm.getTile(4, 3));
		//getPossibleMovesForBlackSoldier when given obj=2 and the (2,1) Tile  it should return an arraylist of Tiles similar to moves.
		assertEquals(moves, gm.getPossibleMovesForBlackSoldier(obj, Tile));
	//	assertTrue(gm.getPossibleMovesForBlackSoldier(obj, Tile).equals(moves));
	}
	
	
	/*
	 * Game.GetPossibleMovesForWhiteSoldier() method test.
	 * Success
	 */
	@Test
	void testGetPossibleMovesForWhiteSoldier() {
		Tile Tile = gm.getTile(2, 1);
		int obj = 2;
		//getPossibleMovesForWhiteSoldier when given obj=2 should return null.
		assertTrue(gm.getPossibleMovesForWhiteSoldier(obj, Tile) == null);
		obj = 1;
		Tile = gm.getTile(5, 4);
		ArrayList<Tile> moves = new ArrayList<Tile>();
		moves.add(gm.getTile(4, 3));
		moves.add(gm.getTile(4, 5));
		//getPossibleMovesForWhiteSoldier when given obj=1 and the (5,4) Tile  it should return an arraylist of Tiles similar to moves.
		assertEquals(moves, gm.getPossibleMovesForWhiteSoldier(obj, Tile));
	//	assertTrue(gm.getPossibleMovesForWhiteSoldier(obj, Tile).equals(moves));
	}
	
	
	/*
	 * Game.MoveBlackSoldier() method test.
	 * Success
	 */
	@Test
	void testMoveBlackSoldier() {
		Tile currPos = gm.getTile(2, 1);
		Tile nextPos = gm.getTile(4, 3);
		
		ArrayList<Tile> moves = new ArrayList<Tile>();
		moves = gm.getPossibleMovesForBlackSoldier(2, currPos);
		gm.moveBlackSoldier(currPos, nextPos,moves );
		
		/*The board before the move (Original board)
		 * {-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,11,-1,1,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,0,-1,1,-1,1,-1},
			{-1,2,-1,1,-1,1,-1,1},
			{1,-1,0,-1,1,-1,1,-1}
		 */
		int[][] board = gm.getBoard();
		int[][] expectedBoard =  {{-1,2,-1,2,-1,2,-1,2},
				{2,-1,2,-1,2,-1,2,-1},
				{-1,0,-1,2,-1,2,-1,2},
				{0,-1,0,-1,1,-1,0,-1},
				{-1,0,-1,2,-1,0,-1,0},
				{1,-1,0,-1,1,-1,1,-1},
				{-1,2,-1,1,-1,1,-1,1},
				{1,-1,0,-1,1,-1,1,-1}};
    
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
	@Test
	void testMoveWhiteSoldier() {
		Tile currPos = gm.getTile(3, 4);
		Tile nextPos = gm.getTile(2, 3);
		
		ArrayList<Tile> moves = new ArrayList<Tile>();
		moves = gm.getPossibleMovesForBlackSoldier(1, currPos);
		gm.moveWhiteSoldier(currPos, nextPos,moves );
		
		/*The board before the move (Original board)
		 * {-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,11,-1,1,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,0,-1,1,-1,1,-1},
			{-1,2,-1,1,-1,1,-1,1},
			{1,-1,0,-1,1,-1,1,-1}
		 */
		int[][] board = gm.getBoard();
		//The board shouldn't change, since the move is invalid!.
		int[][] expectedBoard =  {{-1,2,-1,2,-1,2,-1,2},
				{2,-1,2,-1,2,-1,2,-1},
				{-1,2,-1,2,-1,2,-1,2},
				{0,-1,11,-1,1,-1,0,-1},
				{-1,0,-1,0,-1,0,-1,0},
				{1,-1,0,-1,1,-1,1,-1},
				{-1,2,-1,1,-1,1,-1,1},
				{1,-1,0,-1,1,-1,1,-1}};
    
		//First test - Test if board and expectedBoard are equall.
		for(int i=0; i<8;i++) {
			for(int j=0; j<8; j++) {
				assertTrue(board[i][j]==expectedBoard[i][j] );
			}
		}
		
		//Second  test - Test if board and expectedBoard2 are equal.
		
		Game gm2 = new Game("P1", "P2");
		currPos = gm2.getTile(5, 4);
		nextPos = gm2.getTile(4, 5);
		moves = gm2.getPossibleMovesForWhiteSoldier(1, currPos);
		gm2.moveWhiteSoldier(currPos, nextPos,moves );
		
		board = gm2.getBoard();
		//The board shouldn't change, since the move is invalid!.
		int[][] expectedBoard2 =  {{-1,2,-1,2,-1,2,-1,2},
				{2,-1,2,-1,2,-1,2,-1},
				{-1,2,-1,2,-1,2,-1,2},
				{0,-1,11,-1,1,-1,0,-1},
				{-1,0,-1,0,-1,1,-1,0},
				{1,-1,0,-1,0,-1,1,-1},
				{-1,2,-1,1,-1,1,-1,1},
				{0,-1,0,-1,1,-1,1,-1}};
		
    
		for(int i=0; i<8;i++) {
			for(int j=0; j<8; j++) {
				assertTrue(board[i][j]==expectedBoard2[i][j] );
			}
		}
		
	}
	
	
	/*
	 * Game.getKills() method test.
	 * Success
	 */
	@Test
	void testGetSoldierWithKill() {
		
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
		assertTrue(flag);
	}
	
	
	/*
	 * Game.getMiddleEnemySoldier() method test.
	 * Success
	 */
	@Test
	void testgetMiddleEnemySoldier() {
		
		expected = gm.getTile(3, 2);
		Tile curr = gm.getTile(2, 1);
		Tile next = gm.getTile(4, 3);
	
		Tile actual = gm.getMiddleEnemySoldier(2,curr , next);
		
		assertEquals(expected, actual);
	}
	
	
	/*
	 * Game.ifKillExists() method test.
	 * Success
	 */
	@Test
	void testIfKillExists() {
		
		Tile curr = gm.getTile(2, 5);
		assertTrue(gm.ifKillExist(curr, gm.getPossibleMovesForBlackSoldier(2, curr)));
	}
	
	
	/*
	 * Game.getKilMove() method test.
	 * Success
	 */
	@Test
	void testGetKillMove() {
		
		Tile curr = gm.getTile(2, 3);
		ArrayList<Tile> expected = new ArrayList<Tile>();
		ArrayList<Tile> actual = new ArrayList<Tile>();
		actual = gm.getKillMove(gm.getPossibleMovesForBlackSoldier(2, curr), curr);
		expected.add(gm.getTile(4, 1));
		expected.add(gm.getTile(4, 5));
		
		/*
		 * Similiar to testGetSoldierWithKill()
		 * We need to check manually if actual contains all Tiles in expected and vice versa
		 * Alternatively if we use assertEquals it needs the same order which in turn could lead to failing the test when in fact it is a success.
		 */
		boolean flag = true;
		for (Tile Tile : expected) {
			if(!actual.contains(Tile))
				flag =false;
		}
		for (Tile Tile : actual) {
			if(!expected.contains(Tile))
				flag =false;
		}
		assertTrue(flag);
	}

	
	/*
	 * Game.moveQueen() method test.
	 * Success
	 */
	@Test
	public void queenMovementTest() throws Exception {
		setup();
	      int[][] board = {{-1,2,-1,2,-1,2,-1,2},
	    		  			{2,-1,2,-1,2,-1,2,-1},
	    		  			{-1,22,-1,2,-1,2,-1,2},
	    		  			{0,-1,0,-1,1,-1,0,-1},
							{-1,0,-1,0,-1,0,-1,0},
							{1,-1,1,-1,1,-1,1,-1},
							{-1,1,-1,1,-1,1,-1,1},
							{1,-1,1,-1,1,-1,1,-1}
};
	      gm.setBoard(board);
	    gm.moveQueen(gm.getContentWithXandY(2, 1), gm.getTile(2, 1), gm.getTile(4, 3), gm.getQueenMoves(gm.getContentWithXandY(2, 1), gm.getTile(2, 1)));
	     boolean flag = true;
	     int [][] expectedBoard = {{-1,2,-1,2,-1,2,-1,2},
						  			{2,-1,2,-1,2,-1,2,-1},
						  			{-1,0,-1,2,-1,2,-1,2},
						  			{0,-1,0,-1,1,-1,0,-1},
									{-1,0,-1,22,-1,0,-1,0},
									{1,-1,1,-1,1,-1,1,-1},
									{-1,1,-1,1,-1,1,-1,1},
									{1,-1,1,-1,1,-1,1,-1}
};
	     for(int i = 0; i < 8 ; i++)
	    	 for(int j=0;j < 8;j++) {
	    		 if(gm.getBoard()[i][j] != expectedBoard[i][j])
	    			 flag = false;
	    	 }
		    gm.moveQueen(gm.getContentWithXandY(4, 3), gm.getTile(4, 3), gm.getTile(3, 2), gm.getQueenMoves(gm.getContentWithXandY(4, 3), gm.getTile(4, 3)));
		    int [][]  expectedBoard2 = {{-1,2,-1,2,-1,2,-1,2},
							  			{2,-1,2,-1,2,-1,2,-1},
							  			{-1,0,-1,2,-1,2,-1,2},
							  			{0,-1,22,-1,1,-1,0,-1},
										{-1,0,-1,0,-1,0,-1,0},
										{1,-1,1,-1,1,-1,1,-1},
										{-1,1,-1,1,-1,1,-1,1},
										{1,-1,1,-1,1,-1,1,-1}
};
		    for(int i = 0; i < 8 ; i++)
		    	 for(int j=0;j < 8;j++) {
		    		 if(gm.getBoard()[i][j] != expectedBoard2[i][j])
		    			 flag = false;
		    	 }
	      assertTrue(flag);	
	      }
	
	/*
	 * Game.getQueenMoves() method test.
	 * Success
	 */
	@Test
	public void possibleQueenMovesTest() {
		setup();
		 int[][] board = {{-1,2,-1,2,-1,2,-1,2},
		  			{2,-1,2,-1,2,-1,2,-1},
		  			{-1,22,-1,2,-1,2,-1,2},
		  			{0,-1,0,-1,1,-1,0,-1},
					{-1,0,-1,0,-1,0,-1,0},
					{1,-1,1,-1,1,-1,1,-1},
					{-1,1,-1,1,-1,1,-1,1},
					{1,-1,1,-1,1,-1,1,-1}
};
		 gm.setBoard(board);
			Map<Tile,Tile> map = gm.getQueenMoves(22, gm.getTile(2, 1));
			assertTrue(map.containsKey(gm.getTile(3, 2)) && map.get(gm.getTile(3, 2)) == null);
			assertTrue(map.containsKey(gm.getTile(4, 3)) && map.get(gm.getTile(4, 3)) == null);
			assertTrue(map.containsKey(gm.getTile(3, 0)) && map.get(gm.getTile(3, 0)) == null);
			assertTrue(map.containsKey(gm.getTile(4,7)) && map.get(gm.getTile(4, 7)) == null);


	}
	
	/*
	 * Game.getQueenCrossBoardMoves() method test.
	 * Success
	 */
	@Test
	public void queenCrossMovesTest() {
		setup();
		 int[][] board = {{-1,2,-1,2,-1,2,-1,2},
		  			{2,-1,2,-1,2,-1,2,-1},
		  			{-1,22,-1,2,-1,2,-1,2},
		  			{0,-1,0,-1,1,-1,0,-1},
					{-1,0,-1,0,-1,0,-1,0},
					{1,-1,1,-1,1,-1,1,-1},
					{-1,1,-1,1,-1,1,-1,1},
					{1,-1,1,-1,1,-1,1,-1}
};
		 gm.setBoard(board);
		HashMap<Tile,Tile> arr = gm.getQueenCrossBoardMoves(22,gm.getTile(2, 1),gm.getQueenMoves(22, gm.getTile(2,1)));
		assertTrue(arr.containsKey(gm.getTile(4,7)) && arr.get(gm.getTile(4, 7)) == null);
		assertTrue(!arr.isEmpty());

	}
	
	
}  // Class END
