import java.util.Arrays;

public class PuzzleSolver {

	public static void main(String[] args) {
		
		final int[] RIGHT = {1,0,0};
		final int[] LEFT = {-1,0,0};
		final int[] FORWARD = {0,1,0};
		final int[] BACKWARD = {0,-1,0};
		final int[] UP = {0,0,1};
		final int[] DOWN = {0,0,-1};
		
		Orientation startOrientation = new Orientation(RIGHT,UP);
		int[] startPosition = {1,0,0};
		
		// Defining each PieceHalf of the puzzle
		PieceHalf hook = new PieceHalf(RIGHT,UP);
		PieceHalf pointing1 = new PieceHalf(DOWN,FORWARD);
		PieceHalf pointing2 = new PieceHalf(DOWN,LEFT);
		PieceHalf loop1 = new PieceHalf(UP, BACKWARD);
		PieceHalf loop2 = new PieceHalf(UP, RIGHT);
		PieceHalf arm1 = new PieceHalf(BACKWARD,LEFT);
		PieceHalf arm2 = new PieceHalf(LEFT,FORWARD);
		PieceHalf pointing3 = new PieceHalf(UP,RIGHT);
		PieceHalf pointing4 = new PieceHalf(DOWN,BACKWARD);
		// PieceHalf pointing5 = new PieceHalf(UP,RIGHT);
		// PieceHalf conjoined = new PieceHalf(RIGHT,FORWARD);
		
		// Defining each Piece of the puzzle
		Piece pieceA = new Piece(hook, pointing1);
		Piece pieceB = new Piece(hook, pointing2);
		Piece pieceC = new Piece(hook, loop1);
		Piece pieceD = new Piece(hook, loop2);
		Piece pieceE = new Piece(hook, arm1);
		Piece pieceF = new Piece(hook, arm2);
		Piece pieceG = new Piece(pointing3, pointing4);
		// Piece pieceH = new Piece(pointing5, conjoined);
		
		// pieceH is not 'toAttach' as it is used as the starting piece (and therefore also doesn't need to be instantiated)
		Piece[] piecesToAttach  = {pieceA,pieceB,pieceC,pieceD,pieceE,pieceF,pieceG}; 
		
		DetailedPosition startDetailedPosition = new DetailedPosition(startPosition, startOrientation);
		System.out.println(startDetailedPosition);

		UtilityMethods.SolvePuzzle(piecesToAttach,startDetailedPosition);
	}
	
}
