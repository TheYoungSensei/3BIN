package goldmine;

// ConcreteClass
public class DwarvenTunnelDigger extends DwarvenMineWorker {

  // subMethod
  @Override
  public void work() {
    System.out.println(name()+" crée un nouveau tunnel, cela sent l'or.");
  }

  // subMethod
  @Override
  public String name() {
    return "Tunnelier nain";
  }
}
