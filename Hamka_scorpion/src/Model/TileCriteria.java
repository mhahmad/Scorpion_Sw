package Model;

import java.util.ArrayList;
import java.util.HashMap;


import Model.Soldier;
import Model.Tile;

public interface TileCriteria {
public ArrayList<Tile> meetCriteria( HashMap<Tile,Soldier> tiles);
}