package Model;

import java.util.ArrayList;

import Model.Tile;

public class Soldier {

	protected int soldierNumber;
    protected Tile position ;
    protected Color color; 
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	protected ArrayList<Soldier> possibleKills;
	protected ArrayList<Tile> possibleMoves;
	
	
	public Soldier(int soldierNumber,Tile position) {
		super();
		this.soldierNumber = soldierNumber;
		this.position = position;
		this.possibleKills = new ArrayList<Soldier>();
		this.possibleMoves = new ArrayList<Tile>();
	}
	public int getSoldierNumber() {
		return soldierNumber;
	}
	public void setSoldierNumber(int soldierNumber) {
		this.soldierNumber = soldierNumber;
	}

	public Tile getPosition() {
		return position;
	}
	public void setPosition(Tile position) {
		this.position = position;
	}
	public void setPosition(int x , int y) {
		Tile pos = new Tile(x,y);
		this.position = pos;
	}
	public ArrayList<Soldier> getPossibleKills() {
		return possibleKills;
	}
	public void setPossibleKills(ArrayList<Soldier> possibleKills) {
		this.possibleKills = possibleKills;
	}
	public ArrayList<Soldier> getKills() {
		return possibleKills;
	}
	public void setKills(ArrayList<Soldier> kills) {
		this.possibleKills = kills;
	}
	public ArrayList<Tile> getPossibleMoves() {
		return possibleMoves;
	}
	public void setPossibleMoves(ArrayList<Tile> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	
	
	
	
}
