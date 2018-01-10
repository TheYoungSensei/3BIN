package q2;

// ConcreteStrategy
public class DistanceMiles implements Distance {

	private int miles;
	
	public DistanceMiles(int miles) {
		this.miles=miles;
	}

	// execute
	@Override
	public String getDistance() {
		return miles+" miles";
	}

}
