package Model;

import java.util.ArrayList;

import Model.Tile;

public class Soldier {
	
	private static int globalid=0;
	private int id;
	protected int soldierNumber;
    protected Tile position ;
    protected Color color; 
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	private ArrayList<Soldier> possibleKills;
	protected ArrayList<Tile> possibleMoves;
	
	
	public Soldier(int soldierNumber) {
		super();
		globalid++;
		id=globalid;
		this.soldierNumber = soldierNumber;
		
		this.possibleKills = new ArrayList<Soldier>();
		this.possibleMoves = new ArrayList<Tile>();
		
		if(soldierNumber == 1 || soldierNumber == 11 ) {
			setColor(Color.White);
		}else if(soldierNumber == 2 || soldierNumber == 22) {
			setColor(Color.Black);
		}else {
			System.out.println("No Soldier/Queen with such Number!");
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + soldierNumber;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soldier other = (Soldier) obj;
		if(id != other.id)
			return false;
		if (soldierNumber != other.soldierNumber)
			return false;
		
		return true;
	}
	@Override
	public String toString() {
		return  soldierNumber +" ";
	}
	public  int getId() {
		return id;
	}
	public  void setId(int id) {
		this.id = id;
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
