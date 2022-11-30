import java.util.ArrayList;
import java.util.Arrays;

public class UtilityMethods {
	
	
	public static int[] cross(int[] v1,int[] v2) {
		int[] v = new int[3];
		
		v[0] = v1[1] * v2[2] - v1[2] * v2[1];
        v[1] = v1[2] * v2[0] - v1[0] * v2[2];
        v[2] = v1[0] * v2[1] - v1[1] * v2[0];
        
        return v;
	}
	
	public static int[] minus(int[] v) {
		int[] vminus = {-v[0], - v[1], - v[2]};
		return vminus;
	}
	
	// method to check if our array of coordinates, arr, contains the index [row, column]
	public static boolean existsIn(int[] vector, int[][] arr) {
		int count = 0;
		for (int i=0; i<arr.length; i++) {
			count = 0;
			for (int j = 0; j< vector.length; j++) {
				if (vector[j] != arr[i][j]) {
					break;
				}
				count++;
				if (count == vector.length) {
					return true;
				}
			}
			
			
		}
		return false;
	}
	
	
	
	public static int[][] getRotationMatrix(PieceHalf piecehalf, Orientation destination) {
		//TOTEST
		int[][] rotationMatrix = new int[3][3];
		int[][] used = new int[3][2];
		int count = 0;
		int temp = 1;
		
		for (int i = 0; i< 3; i++ ) {
			for (int j = 0; j<3; j++) {
				rotationMatrix[i][j] = piecehalf.point[i]*destination.point[j] + piecehalf.slab[i] * destination.slab[j];
				if (rotationMatrix[i][j] != 0) {
					temp *= rotationMatrix[i][j];
					used[count][0] = i;
					used[count][1] = j;
					count++;				}
			}
		}
		// finding the row and column with all zeros and inserting a value at their intersection
		for (int i = 0;i<3;i++) {
			if (used[0][0] != i && used[1][0] != i) {
				for (int j = 0;j<3;j++) {
					if (used[0][1] != j && used[1][1] != j) {
//						System.out.println(i);
//						System.out.println(j);
						rotationMatrix[i][j] = temp;
						used[2][0] = i;
						used[2][1] = j;
					}
				}
			}
		}
		// correct the coefficient of the input value so that the matrix has a determinant of 1 (and so represents a rotation and not reflection)
		int coeff = 1;
		if (existsIn(new int[] {0,0}, used)) {
			if (existsIn(new int[] {1,1}, used)) {
				coeff = 1;
			} else {
				coeff = -1;
			}
		} else if (existsIn(new int[] {1,0}, used)) {
			if (existsIn(new int[] {2,1}, used)) {
				coeff = 1;
			} else {
				coeff = -1;
			}			
		} else if (existsIn(new int[] {2,0}, used)) {
			if (existsIn(new int[] {1,2}, used)) {
				coeff = 1;
			} else {
				coeff = -1;
			}
		} else {
			System.out.println("Error in existsIn if statement!");
		}
		rotationMatrix[used[2][0]][used[2][1]] *= coeff;

		return rotationMatrix;
	}
	
	public static int[] getNewPosition(int[] currentPosition, int[][] rotationMatrix, int topOrBot) {
		//TOTEST
		int[] newPosition = new int[3];
		int value;
		int[] direction = {0,0,0};
		
		if (topOrBot == 0) {
			direction[2] = -1;
		} else {
			direction[2] = 1;
		}
		for (int i =0; i<3; i++) {
			value = 0;
			for (int j = 0; j<3; j++) {
				value += direction[j] * rotationMatrix[j][i]; // may have to switch i and j here
			}
			newPosition[i] = currentPosition[i] + value;
		}
		return newPosition;
	}
	
	public static Orientation getNewOrientation(int[][] rotationMatrix,Piece attachingPiece, int topOrBot) {
		//TOTEST
		int pointValue;
		int slabValue;
		PieceHalf activeHalf;
		int[] point = new int[3];
		int[] slab = new int[3];
		
		if (topOrBot == 0) {
			activeHalf = attachingPiece.bottom;
		} else {
			activeHalf = attachingPiece.top;
		}
		for (int i =0; i<3; i++) {
			slabValue = 0;
			pointValue = 0;
			for (int j = 0; j<3; j++) {
				pointValue += activeHalf.point[j] * rotationMatrix[j][i]; // may have to switch i and j here
				slabValue += activeHalf.slab[j] * rotationMatrix[j][i]; // may have to switch i and j here
			}
			point[i] = pointValue;
			slab[i] = slabValue;
		}
		Orientation newOrientation = new Orientation(point,slab);
		return newOrientation;
	}
	
	public static DetailedPosition AttachPiece(Piece piece, DetailedPosition currentDetailedPosition, int topOrBot) {
		//TOTEST
		Orientation destination = currentDetailedPosition.orientation.getCompatibleOrientation();
		PieceHalf origin;
		
		if (topOrBot == 0) {
			origin = piece.top;
		} else {
			origin = piece.bottom;

		}
		int[][] rotationMatrix = UtilityMethods.getRotationMatrix(origin, destination);
		int[] position = UtilityMethods.getNewPosition(currentDetailedPosition.position, rotationMatrix, topOrBot);
		Orientation orientation = UtilityMethods.getNewOrientation(rotationMatrix,piece,topOrBot);;
		return new DetailedPosition(position, orientation);
	}
	
	public static ArrayList<String> getPermutations(String numbers) {
		ArrayList<String> perms = new ArrayList<String>();
		if (numbers.length() == 1) {
			perms.add(numbers);
			return perms;
		}
		for (int i = 0; i<numbers.length(); i++) {
			char chosen = numbers.charAt(i);
			String remaining = numbers.substring(0,i) + numbers.substring(i+1);
			ArrayList<String> innerPermutations = getPermutations(remaining);

	        for (int j = 0; j < innerPermutations.size(); j++) {
	            perms.add(chosen + innerPermutations.get(j));
	        }
		}
		return perms;
	}
	
	public static void SolvePuzzle(Piece[] piecesInPlay, DetailedPosition start) {
		int count = 0;
		ArrayList<String> permutations = getPermutations("0123456");
		DetailedPosition finalDetailedPosition = new DetailedPosition(new int[] {0,0,0},new Orientation(new int[] {1,0,0}, new int[] {0,1,0}));
		for (int i = 0; i<128; i++) {
			String binaryString = Integer.toBinaryString(i);
			int l = binaryString.length();
			String formattedBinaryString = ("0000000" + Integer.toBinaryString(i)).substring(l);
			String[] flippings = formattedBinaryString.split("(?!^)");
			for (String perm:permutations) {
				int i6 = (perm.indexOf('6'));
				if (flippings[i6].equals("1")) {
					continue;
				}
				
				DetailedPosition currentDetailedPosition = start;
				int[][] visited = {{0,0,0},{1,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
				for (int j = 0; j<7; j++) {
					int pieceIndex = perm.charAt(j) - '0';
					currentDetailedPosition = AttachPiece(piecesInPlay[pieceIndex],currentDetailedPosition,Integer.parseInt(flippings[j]));
					if (j == 6 && currentDetailedPosition.equals(finalDetailedPosition)) {
						int i2 = (perm.indexOf("2"));
						int i3 = (perm.indexOf("3"));
						int i4 = (perm.indexOf("4"));
						int i5 = (perm.indexOf("5"));
						if (!((i2-i3-Integer.parseInt(flippings[i2])+ Integer.parseInt(flippings[i2]) == 0)||(i4-i5-Integer.parseInt(flippings[i4])+ Integer.parseInt(flippings[i5]) == 0))) {
							System.out.println("\n------------------------------\n" );
							System.out.println(++count + ":");
							System.out.println(perm + "\n" + formattedBinaryString);
							
						}
					}
					int[] currentPosition = currentDetailedPosition.position;
					if (existsIn(currentPosition,visited) || j == 6) {
						break;
					}
					for (int k = 0; k<3; k++) {
						visited[j+2][k] = currentPosition[k];
					}
				}
			}
		}
	}
}