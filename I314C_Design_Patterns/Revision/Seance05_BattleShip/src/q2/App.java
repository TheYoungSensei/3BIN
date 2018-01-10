package q2;

public class App {

  public static void main(String[] args) {
    Captain captain = new Captain(new BattleFishingBoat());
    captain.move(new DistanceKM(500));
    captain.fire();
  }
}
