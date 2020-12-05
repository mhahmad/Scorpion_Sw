package Model;


public class Tile {
	private int x;// row
	private int y;//col
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



	public Tile(int x,int y){
		this.x = x;
		this.y = y;
	/*	if(checkLegalTile(x, y)) {

		}
		else {
		System.out.println("not legal position!");
		}*/
	}
	public String toString() {
		return "[x="+x+",y="+y+"]";
	}
	
	
/*	private boolean checkLegalTile(int x, int y) {
		if(x > 7 || x < 0 || y > 7 || y < 0 ) {

			return false;	
		}
		
		return true;
	}*/
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Tile other = (Tile) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	
	
	
}