package Model;

import java.util.HashMap;

public class Queen extends Soldier {

	private HashMap<Tile,Soldier> queenMoves;
	public Queen(int soldierNumber) {
		super(soldierNumber);
		queenMoves = new HashMap<Tile,Soldier>();
		
	}
	public HashMap<Tile, Soldier> getQueenMoves() {
		return queenMoves;
	}
	public void setQueenMoves(HashMap<Tile, Soldier> queenMoves) {
		this.queenMoves = queenMoves;
	}
	
	
	

}
