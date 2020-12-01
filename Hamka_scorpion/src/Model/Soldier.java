package Model;

import java.util.ArrayList;

import Model.Game.Pair;

public class Soldier {

	private int soldierNumber;
	private int x;
	private int y;
	private ArrayList<Soldier> possibleKills;
	private ArrayList<Pair> possibleMoves;
	
	
	public Soldier(int soldierNumber, int x, int y) {
		super();
		this.soldierNumber = soldierNumber;
		this.x = x;
		this.y = y;
		this.possibleKills = new ArrayList<Soldier>();
		this.possibleMoves = new ArrayList<Pair>();
	}
	public int getSoldierNumber() {
		return soldierNumber;
	}
	public void setSoldierNumber(int soldierNumber) {
		this.soldierNumber = soldierNumber;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ArrayList<Soldier> getKills() {
		return possibleKills;
	}
	public void setKills(ArrayList<Soldier> kills) {
		this.possibleKills = kills;
	}
	public ArrayList<Pair> getPossibleMoves() {
		return possibleMoves;
	}
	public void setPossibleMoves(ArrayList<Pair> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	
	
}
