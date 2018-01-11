package goldmine;

// ConcreteClass
public class DwarvenGoldDigger extends DwarvenMineWorker {

  // subMethod
  @Override
  public void work() {
    System.out.println(name()+" creuse pour trouver de l'or.");
  }

  // subMethod
  @Override
  public String name() {
    return "Chercheur d'or nain";
  }
}
