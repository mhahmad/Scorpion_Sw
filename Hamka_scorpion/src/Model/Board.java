package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board implements Subject {
	public int[][] board;

          //////Soldiers By Tiles \\\\\\
	private HashMap<Tile, Soldier> soldiersByTiles;
	private HashMap<Soldier,Tile> tilesBySoldiers;

	private EmptyTileCriteria emptyTilesCriteria= new EmptyTileCriteria();
    private OccupiedTileCriteria occupiedTilesCriteria = new OccupiedTileCriteria();
    private WhiteSoldierTilesCriteria whiteSoldierCriteria = new WhiteSoldierTilesCriteria();
    private BlackSoldierTileCriteria blackSoldierCriteria = new BlackSoldierTileCriteria();
	
    
    SoldierFactory SF = new SoldierFactory();

    public Board(int[][] board) {
        soldiersByTiles = new HashMap<Tile, Soldier>();
        tilesBySoldiers = new HashMap<Soldier, Tile>();
        this.board = board;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    Tile t = new Tile(i, j);
                    if (board[i][j] != 0) {
                        Soldier s = SF.getInstance(t, board[i][j]);
                        tilesBySoldiers.put(s, t);
                        soldiersByTiles.put(t, s);

                    } else {
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
	
	@Override
	public void setSoldier(Soldier s,Tile t) {
		soldiersByTiles.put(t, s);
		s.updatePosition(t);
		tilesBySoldiers.put(s, t);
		board[t.getX()][t.getY()] = s.getSoldierNumber();
	}
	
	@Override
	public void removeSoldier(Soldier s,Tile t) {
		soldiersByTiles.put(t, null);
		tilesBySoldiers.remove(s);
		board[t.getX()][t.getY()] = 0;
		s=null;
	}

	public HashMap<Tile,Soldier> getSameColorSoldiers(int num){
        if(num != 1 && num!=2 ) return null;
        HashMap<Tile,Soldier> toReturn = new HashMap<Tile, Soldier>();
        ArrayList<Tile> temp = new ArrayList<Tile>();
        if(num==1) {
            temp = whiteSoldierCriteria.meetCriteria(soldiersByTiles); 
        }else {
            temp = blackSoldierCriteria.meetCriteria(soldiersByTiles);
        }
        for(Tile t : temp) {
            toReturn.put(t, soldiersByTiles.get(t));
        }
        return toReturn;
    }
	
	public ArrayList<Tile> getPlayerPositions(Color color){
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for(Map.Entry<Tile, Soldier> entry : soldiersByTiles.entrySet()) {
			Soldier s =entry.getValue();
			if(s!=null) {
				System.out.println(s.getColor());
				if(s.color.equals(color)) 
					tiles.add(entry.getKey());
			}
		
			}
		return tiles;
	}
	
	
	public ArrayList<Tile> getEmptyTiles(){
        return emptyTilesCriteria.meetCriteria(soldiersByTiles);
    }


    public ArrayList<Tile> getOccupiedTiles(){
        return occupiedTilesCriteria.meetCriteria(soldiersByTiles);
    }
	
	
	public int countPiece(int type) {
		int count =0;
		for(Map.Entry<Tile, Soldier> entry : soldiersByTiles.entrySet()) {
			if(entry.getValue() != null) {
			if(entry.getValue().getSoldierNumber() == type ) {
				count++;
			}
		}
			}
		return count;
	}
	
	
	public void printBoard() {
		for(int i =0; i< 8; i++) {
			for(int j=0 ; j<8 ; j++) {	
				System.out.print(board[i][j] + ",");
			}
			 System.out.println();
		}
	}
	

}
