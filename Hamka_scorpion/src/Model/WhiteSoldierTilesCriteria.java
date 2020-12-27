package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import Model.Soldier;
import Model.Tile;

public class WhiteSoldierTilesCriteria implements TileCriteria{

    @Override
    public ArrayList<Tile> meetCriteria(HashMap<Tile, Soldier> tiles) {
        ArrayList<Tile> toReturn = new ArrayList<Tile>();
        //HashMap<Tile,Soldier> toReturn = new HashMap<Tile, Soldier>();
        for(Map.Entry<Tile, Soldier> entry : tiles.entrySet()) {
            if(entry.getValue()!= null) {
                if(entry.getValue().getSoldierNumber() == 1 ) {
                    toReturn.add(entry.getKey());
                }
            }
        }
        return toReturn;

    }


}