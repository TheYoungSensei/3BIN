package q2;

// Adaptee
public class FishingBoat {

  // adaptedOperation
  public void sail(Distance distance) {
    System.out.println("The Boat is moving "+distance.getDistance());
  }

  // adaptedOperation
  public void fish() {
	  System.out.println("fishing ...");
  }

}
