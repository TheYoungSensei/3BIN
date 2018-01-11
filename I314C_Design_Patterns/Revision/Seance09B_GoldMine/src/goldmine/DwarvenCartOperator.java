package goldmine;

// ConcreteClass
public class DwarvenCartOperator extends DwarvenMineWorker {

  // subMethod
  @Override
  public void work() {
    System.out.println(name()+" enlève du minerai d'or de la mine.");
  }

  // subMethod
  @Override
  public String name() {
    return "Opérateur de chariot nain";
  }
}
