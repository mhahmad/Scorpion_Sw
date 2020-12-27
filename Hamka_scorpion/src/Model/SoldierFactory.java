package Model;

public class SoldierFactory {


    public Soldier getInstance(Tile tile , int solNumber) {

        if(solNumber == 1 || solNumber == 2) {
            Soldier s = new Soldier(solNumber);
            s.setPosition(tile);
            return s;
        }else  {
            Soldier s = new Queen(solNumber);
            s.setPosition(tile);
            return s;
        }
    }
}