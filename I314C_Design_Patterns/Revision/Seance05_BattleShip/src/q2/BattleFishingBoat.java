package q2;

// ConcreteAdapter, ConcreteComponent
public class BattleFishingBoat implements BattleShip {

  // adaptee
  private FishingBoat boat;

  public BattleFishingBoat() {
    boat = new FishingBoat();
  }

  // operation
  @Override
  public void fire() {
    boat.fish();
  }

  // operation
  @Override
  public void move(Distance distance) {
    boat.sail(distance);
  }
}
