package q2;

// Client, Decorator, ConcreteDecorator
public class Captain implements BattleShip {

  private BattleShip battleship;

  public Captain() {

  }

  public Captain(BattleShip battleship) {
    this.battleship = battleship;
  }

  @Override
  public void fire() {
	System.out.print("Captain's order : ");
    battleship.fire();
  }

  @Override
  public void move(Distance distance) {
    System.out.print("Captain's order : ");
    battleship.move(distance);
  }

}
