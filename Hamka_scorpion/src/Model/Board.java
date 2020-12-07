package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	public int[][] board;

          //////Soldiers By Tiles \\\\\\
	private HashMap<Tile, Soldier> soldiersByTiles;
	private HashMap<Soldier,Tile> tilesBySoldiers;

	
	
	
	public Board(int [][] board) {	
		soldiersByTiles = new HashMap<Tile,Soldier>();
		tilesBySoldiers = new HashMap<Soldier,Tile>();
		this.board = board;
		for(int i =0; i<8; i++) {
			for(int j=0; j<8 ;j++) {
				
				if((i%2 ==0 && j%2 == 1)  || (i%2 ==1 && j%2 == 0) ) {
					Tile t = new Tile(i,j);
					if(board[i][j]==1) {
						Soldier s = new Soldier(1);
						s.position = t;
						soldiersByTiles.put(t, s);
						
						tilesBySoldiers.put(s,t);
					}else if(board[i][j]==11) {
						Queen q = new Queen(11);
						q.position = t;
						soldiersByTiles.put(t, q);
						tilesBySoldiers.put(q,t);
					}else if(board[i][j]==2) {
						Soldier s = new Soldier(2);
						s.position = t;
						soldiersByTiles.put(t, s);
						tilesBySoldiers.put(s,t);
					}else if(board[i][j]==22) {
						Queen q = new Queen(22);
						q.position = t;
						soldiersByTiles.put(t, q);
						tilesBySoldiers.put(q,t);
					}else {
						soldiersByTiles.put(t, null);
					}
				}
			}
		}
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public HashMap<Tile, Soldier> getSoldiersByTiles() {
		return soldiersByTiles;
	}

	public void setSoldiersByTiles(HashMap<Tile, Soldier> soldiersByTiles) {
		this.soldiersByTiles = soldiersByTiles;
	}

	public HashMap<Soldier, Tile> getTilesBySoldiers() {
		return tilesBySoldiers;
	}

	public void setTilesBySoldiers(HashMap<Soldier, Tile> tilesBySoldiers) {
		this.tilesBySoldiers = tilesBySoldiers;
	}

	public Soldier getSoldier(Tile tile) {
		if(soldiersByTiles.get(tile) == null) {
			return null; /// if null then the tile is empty.
		}
		return soldiersByTiles.get(tile);
	}
	
	public Tile getTileOfSoldier(Soldier s) {
		if(tilesBySoldiers.get(s) == null || !tilesBySoldiers.containsKey(s)) {
			return null;
		}
		return tilesBySoldiers.get(s);
	}
	
	public void setSoldier(Soldier s,Tile t) {
		soldiersByTiles.put(t, s);
		s.setPosition(t);
		tilesBySoldiers.put(s, t);
		board[t.getX()][t.getY()] = s.getSoldierNumber();
	}
	
	public void removeSoldier(Soldier s,Tile t) {
		soldiersByTiles.put(t, null);
		tilesBySoldiers.remove(s);
		board[t.getX()][t.getY()] = 0;
		s=null;
	}

	public HashMap<Tile,Soldier> getSameColorSoldiers(int num){
		if(num != 1 && num!=2 ) return null;
		HashMap<Tile,Soldier> toReturn = new HashMap<Tile, Soldier>();
		for(Map.Entry<Tile, Soldier> entry : soldiersByTiles.entrySet()) {
			Tile t = entry.getKey();
			Soldier s = entry.getValue();
			if(s != null) {
				if(s.getSoldierNumber() == num ) {
					toReturn.put(t,s);
				}
			}
			
		}
		return toReturn;
	}
	
	
	public ArrayList<Tile> getPlayerPositions(Color color){
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for(Map.Entry<Tile, Soldier> entry : soldiersByTiles.entrySet()) {
			Soldier s =entry.getValue();
			if(s!=null) {
				if(s.color.equals(color)) 
					tiles.add(entry.getKey());
			}
		
			}
		return tiles;
	}
	
	
	public ArrayList<Tile> getEmptyTiles(){
		ArrayList<Tile> toReturn = new ArrayList<Tile>();
		for(Map.Entry<Tile, Soldier> entry : soldiersByTiles.entrySet()) {
			if(entry.getValue() == null ) {
				toReturn.add(entry.getKey());
			}
			}
		return toReturn;
	}
	
	public int countPiece(int type) {
		int count =0;
		for(Map.Entry<Tile, Soldier> entry : soldiersByTiles.entrySet()) {
			if(entry.getValue().getSoldierNumber() == type ) {
				count++;
			}
			}
		return count;
	}
	
	
	public void printBoard() {
		for(int i =0; i< 8; i++) {
			for(int j=0 ; j<8 ; j++) {	
				System.out.print(board[i][j] + ",");
				 System.out.println();
			}
		}
	}
	

}
