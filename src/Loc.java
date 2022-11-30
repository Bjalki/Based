
public class Loc {
	
	int layer;
	int index;
	
	public Loc(int layer, int index) {
		this.layer = layer;
		this.index = index;
	}
	
	@Override
	public String toString() {
		return "Loc(" + layer + ", " + index + ")";
	}
	
	@Override
	public boolean equals(Object loc) {
		if(!(loc instanceof Loc)) {
			return false;
		}
		Loc locLoc = (Loc)loc;
		int layer2 = locLoc.layer;
		int index2 = locLoc.index;
		return (layer == layer2) && (index == index2);
	}

}
