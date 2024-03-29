package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Soldier;
import Model.Tile;

public class OccupiedTileCriteria implements TileCriteria{

    @Override
    public ArrayList<Tile> meetCriteria(HashMap<Tile, Soldier> tiles) {
        ArrayList<Tile> toReturn = new ArrayList<Tile>();
        for(Map.Entry<Tile, Soldier> entry : tiles.entrySet()) {
            if(entry.getValue() != null ) {
                toReturn.add(entry.getKey());
            }
            }
        return toReturn;
    }

}