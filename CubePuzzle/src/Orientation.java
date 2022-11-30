import java.util.Arrays;

public class Orientation {
	
	int[] point;
	int[] slab;
	
	public Orientation(int[] point, int[] slab) {
		this.point = point;
		this.slab = slab;
	}
	
	@Override
	public String toString() {
		return "Point: " + Arrays.toString(point) + ", Slab: " + Arrays.toString(slab);
	}
	
	public Orientation getCompatibleOrientation() {
		int[] compatiblePoint = UtilityMethods.cross(point,slab);
		int [] compatibleSlab = UtilityMethods.minus(slab);
		return new Orientation(compatiblePoint,compatibleSlab);
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Orientation)) {
			return false;
		}
		Orientation ori = (Orientation) o;
		for (int i = 0; i< 3 ; i++) {
			if (ori.point[i] != this.point[i] || ori.slab[i] != this.slab[i]) {
				return false;
			}
		}
		return true;
	}
}