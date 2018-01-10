package q2;

// ConcreteStrategy
public class DistanceKM implements Distance {

	private int km;
	
	public DistanceKM(int km) {
		this.km=km;
	}

	// execute
	@Override
	public String getDistance() {
		return km+" kms";
	}

}
