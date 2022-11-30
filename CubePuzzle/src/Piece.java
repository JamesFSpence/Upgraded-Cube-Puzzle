public class Piece {
	
	PieceHalf top;
	PieceHalf bottom;
	
	public Piece(PieceHalf top, PieceHalf bottom) {
		this.top = top;
		this.bottom = bottom;
	}
	
	@Override
	public String toString() {
		return "Top: " + top.toString()	+ " Bottom: " + bottom.toString();
	} 
}