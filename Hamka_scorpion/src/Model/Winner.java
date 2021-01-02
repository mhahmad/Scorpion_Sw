package Model;

public class Winner implements Comparable<Winner> {

		
	private String winnerName ;
	private int winnerPoints;
	private String date;
	
	public Winner(String winner , int points , String date) {
		this.winnerName = winner;
		this.winnerPoints = points;
		this.date = date;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public int getWinnerPoints() {
		return winnerPoints;
	}

	public String getDate() {
		return date;
	}

	@Override
	public int compareTo(Winner arg0) {
		// TODO Auto-generated method stub
		return (arg0.getWinnerPoints() - this.getWinnerPoints() );
		
	}

	@Override
	public String toString() {
		return "Winner [winnerName=" + winnerName + ", winnerPoints=" + winnerPoints + ", date=" + date + "]";
	}


	
}
