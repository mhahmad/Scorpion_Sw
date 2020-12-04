package Model;

public class Board {
	private int[][] board = {{-1,2,-1,2,-1,2,-1,2},
			{2,-1,2,-1,2,-1,2,-1},
			{-1,2,-1,2,-1,2,-1,2},
			{0,-1,0,-1,0,-1,0,-1},
			{-1,0,-1,0,-1,0,-1,0},
			{1,-1,1,-1,1,-1,1,-1},
			{-1,1,-1,1,-1,1,-1,1},
			{1,-1,1,-1,1,-1,1,-1}
};
	private int whiteSoldiers;
	private int whiteQueens;
	private int blackSoldiers;
	private int blackQueens;
}
