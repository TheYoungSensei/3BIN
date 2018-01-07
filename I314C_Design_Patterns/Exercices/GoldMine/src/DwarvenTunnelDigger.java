//Facade
public class DwarvenTunnelDigger extends DwarvenMineWorker {

  @Override
  public void work() {
    System.out.println(name()+" crée un nouveau tunnel, cela sent l'or.");
  }

  @Override
  public String name() {
    return "Tunnelier nain";
  }
}
