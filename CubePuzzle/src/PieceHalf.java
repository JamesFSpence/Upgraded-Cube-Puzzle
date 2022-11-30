import java.util.Arrays;

public class PieceHalf {
	int[] point;
	int[] slab;
	
	public PieceHalf(int[] point, int[] slab) {
		this.point = point;
		this.slab = slab;
	}
	
	public String toString() {
		return Arrays.toString(point) + Arrays.toString(slab);
	}
}

//					 ________
//					/       /|
//				   /       / |_________
//				  /       /  /        /|
//				 /       /  /        / |
//		    	/       /  /_______ / /|
//			   /_______/  /        | / |                  /
//			   | 	   | /_________|/  /                 /    - POINT VECTOR
//             |_______|/          /  /                |/_ 
//              /                 /  /                -------------------------
//			   /_________________/  /                  /|\
//			   |                 | /                    |     - SLAB VECTOR
//             |_________________|/                     |