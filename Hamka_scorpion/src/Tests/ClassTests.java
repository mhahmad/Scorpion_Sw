package Tests;

import Model.Game;
import Model.Game.Pair;
import Model.Game.PlayerTurn;
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

class ClassTests {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private Game gm;
	private Pair expected;
	
	
	/*
	 * Initialize a game and a set of pair (Used frequently in a lot of tests.)
	 */
	@BeforeEach
	public void setup() {
		gm = new Game("P1", "P2");
		expected =  gm.getPair(1,2);
	}

	/*
	 * Game.getTileContent() method test
	 * Success.
	 */
	@Test
	void testGameGetContent() {
		//getTileContent when given the pair (1,2) should return 2.
		assertTrue(gm.getTileContent(expected)==2);
		expected = gm.getPair(3,2);
		//getTileContent when given the pair (3,2) should return 11.
		assertTrue(gm.getTileContent(expected)==11);
	}
	
	/*
	 * Game.GetPossibleMovesForBlackSoldier() method test.
	 * Success
	 */
	@Test
	void testGetPossibleMovesForBlackSoldier() {
		Pair pair = gm.getPair(2, 1);
		int obj = 1;
		//getPossibleMovesForBlackSoldier when given obj=1 should return null.
		assertTrue(gm.getPossibleMovesForBlackSoldier(obj, pair) == null);
		obj = 2;
		ArrayList<Pair> moves = new ArrayList<Pair>();
		moves.add(gm.getPair(3, 0));
		moves.add(gm.getPair(4, 3));
		//getPossibleMovesForBlackSoldier when given obj=2 and the (2,1) pair  it should return an arraylist of pairs similar to moves.
		assertEquals(moves, gm.getPossibleMovesForBlackSoldier(obj, pair));
	//	assertTrue(gm.getPossibleMovesForBlackSoldier(obj, pair).equals(moves));
	}
	
	
	/*
	 * Game.GetPossibleMovesForWhiteSoldier() method test.
	 * Success
	 */
	@Test
	void testGetPossibleMovesForWhiteSoldier() {
		Pair pair = gm.getPair(2, 1);
		int obj = 2;
		//getPossibleMovesForWhiteSoldier when given obj=2 should return null.
		assertTrue(gm.getPossibleMovesForWhiteSoldier(obj, pair) == null);
		obj = 1;
		pair = gm.getPair(5, 4);
		ArrayList<Pair> moves = new ArrayList<Pair>();
		moves.add(gm.getPair(4, 3));
		moves.add(gm.getPair(4, 5));
		//getPossibleMovesForWhiteSoldier when given obj=1 and the (5,4) pair  it should return an arraylist of pairs similar to moves.
		assertEquals(moves, gm.getPossibleMovesForWhiteSoldier(obj, pair));
	//	assertTrue(gm.getPossibleMovesForWhiteSoldier(obj, pair).equals(moves));
	}
	
	
	/*
	 * Game.MoveBlackSoldier() method test.
	 * Success
	 */
	@Test
	void testMoveBlackSoldier() {
		Pair currPos = gm.getPair(2, 1);
		Pair nextPos = gm.getPair(4, 3);
		
		ArrayList<Pair> moves = new ArrayList<Pair>();
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
		Pair currPos = gm.getPair(3, 4);
		Pair nextPos = gm.getPair(2, 3);
		
		ArrayList<Pair> moves = new ArrayList<Pair>();
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
		currPos = gm2.getPair(5, 4);
		nextPos = gm2.getPair(4, 5);
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
		
		ArrayList<Pair> kills = new ArrayList<Pair>();
		kills = gm.getKills(PlayerTurn.Black);
		
		ArrayList<Pair> expectedKills = new ArrayList<Pair>();
		expectedKills.add(gm.getPair(4, 5));
		expectedKills.add(gm.getPair(4, 3));
		expectedKills.add(gm.getPair(4, 1));
		expectedKills.add(gm.getPair(4, 3));
		
		/*
		 * We need to check manually if kills contains all pairs in expectedKills and vice versa
		 * Alternatively if we use assertEquals it needs the same order which in turn could lead to failing the test when in fact it is a success.
		 */
		boolean flag = true;
		for (Pair pair : expectedKills) {
			if(!kills.contains(pair))
				flag =false;
		}
		for (Pair pair : kills) {
			if(!expectedKills.contains(pair))
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
		
		expected = gm.getPair(3, 2);
		Pair curr = gm.getPair(2, 1);
		Pair next = gm.getPair(4, 3);
	
		Pair actual = gm.getMiddleEnemySoldier(2,curr , next);
		
		assertEquals(expected, actual);
	}
	
	
	/*
	 * Game.ifKillExists() method test.
	 * Success
	 */
	@Test
	void testIfKillExists() {
		
		Pair curr = gm.getPair(2, 5);
		assertTrue(gm.ifKillExist(curr, gm.getPossibleMovesForBlackSoldier(2, curr)));
	}
	
	
	/*
	 * Game.getKilMove() method test.
	 * Success
	 */
	@Test
	void testGetKillMove() {
		
		Pair curr = gm.getPair(2, 3);
		ArrayList<Pair> expected = new ArrayList<Pair>();
		ArrayList<Pair> actual = new ArrayList<Pair>();
		actual = gm.getKillMove(gm.getPossibleMovesForBlackSoldier(2, curr), curr);
		expected.add(gm.getPair(4, 1));
		expected.add(gm.getPair(4, 5));
		
		/*
		 * Similiar to testGetSoldierWithKill()
		 * We need to check manually if actual contains all pairs in expected and vice versa
		 * Alternatively if we use assertEquals it needs the same order which in turn could lead to failing the test when in fact it is a success.
		 */
		boolean flag = true;
		for (Pair pair : expected) {
			if(!actual.contains(pair))
				flag =false;
		}
		for (Pair pair : actual) {
			if(!expected.contains(pair))
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
	    gm.moveQueen(gm.getContentWithXandY(2, 1), gm.getPair(2, 1), gm.getPair(4, 3), gm.getQueenMoves(gm.getContentWithXandY(2, 1), gm.getPair(2, 1)));
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
		    gm.moveQueen(gm.getContentWithXandY(4, 3), gm.getPair(4, 3), gm.getPair(3, 2), gm.getQueenMoves(gm.getContentWithXandY(4, 3), gm.getPair(4, 3)));
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
			Map<Pair,Pair> map = gm.getQueenMoves(22, gm.getPair(2, 1));
			assertTrue(map.containsKey(gm.getPair(3, 2)) && map.get(gm.getPair(3, 2)) == null);
			assertTrue(map.containsKey(gm.getPair(4, 3)) && map.get(gm.getPair(4, 3)) == null);
			assertTrue(map.containsKey(gm.getPair(3, 0)) && map.get(gm.getPair(3, 0)) == null);
			assertTrue(map.containsKey(gm.getPair(4,7)) && map.get(gm.getPair(4, 7)) == null);


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
		HashMap<Pair,Pair> arr = gm.getQueenCrossBoardMoves(22,gm.getPair(2, 1),gm.getQueenMoves(22, gm.getPair(2,1)));
		assertTrue(arr.containsKey(gm.getPair(4,7)) && arr.get(gm.getPair(4, 7)) == null);
		assertTrue(!arr.isEmpty());

	}
	
	
}  // Class END
