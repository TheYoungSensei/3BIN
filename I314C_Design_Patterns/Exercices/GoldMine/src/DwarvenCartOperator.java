public class DwarvenCartOperator extends DwarvenMineWorker {

  @Override
  public void work() {
    System.out.println(name()+" enlève du minerai d'or de la mine.");
  }

  @Override
  public String name() {
    return "Opérateur de chariot nain";
  }
}
