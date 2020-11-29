package Tests;

import Model.Game;
import Model.Game.Pair;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class ClassTests {
	
	private Game gm;
	private Pair expected;
	
	@Before
	public void setup() {
		gm = new Game("P1", "P2");
		expected =  gm.getPair(1,2);
	}

	/*
	 * Success.
	 */
	@Test
	void testGameGetContent() {
		setup();
		//getTileContent when given the pair (1,2) should return 2.
		assertTrue(gm.getTileContent(expected)==2);
		expected = gm.getPair(3,2);
		//getTileContent when given the pair (3,2) should return 11.
		assertTrue(gm.getTileContent(expected)==11);
	}
	
	/*
	 * --.
	 */
	@Test
	void testGetPossibleMovesForBlackSoldier() {
		setup();
		Pair pair = gm.getPair(2, 1);
		int obj = 1;
		//getPossibleMovesForBlackSoldier when given obj=1 should return null.
		assertTrue(gm.getPossibleMovesForBlackSoldier(obj, pair) == null);
		obj = 2;
		ArrayList<Pair> moves = new ArrayList<Pair>();
		moves.add(gm.getPair(3, 0));
		moves.add(gm.getPair(4, 3));
		//getPossibleMovesForBlackSoldier when given obj=2 and the (2,1) pair  it should return an arraylist of pairs similar to moves.
		assertTrue(gm.getPossibleMovesForBlackSoldier(obj, pair).equals(moves));
		
	}
	
	
	
	
	
	
	@Test
	void testMoveBlackSoldier() {
		setup();
		Pair currPos = gm.getPair(2, 1);
		Pair nextPos = gm.getPair(4, 3);
		
		
	}

	

}
