import java.util.Arrays;

public class DetailedPosition {
	int[] position;
	Orientation orientation;
	
	public DetailedPosition(int[] position, Orientation orientation) {
		this.position = position;
		this.orientation = orientation;
	}
	@Override
	public String toString() {
		return "Position: " + Arrays.toString(position) + ",\nOrientation: " + orientation.toString();
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DetailedPosition)) {
			return false;
		}
		DetailedPosition dp = (DetailedPosition) o;
		if (dp.orientation.equals(this.orientation)) {
			for (int i = 0; i < 3; i++) {
				if (dp.position[i] != this.position[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
