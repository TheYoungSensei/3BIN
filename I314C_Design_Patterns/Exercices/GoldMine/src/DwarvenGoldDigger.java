public class DwarvenGoldDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    System.out.println(name()+" creuse pour trouver de l'or.");
  }

  @Override
  public String name() {
    return "Chercheur d'or nain";
  }
}
