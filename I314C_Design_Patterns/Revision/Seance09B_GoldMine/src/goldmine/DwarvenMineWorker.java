package goldmine;

public abstract class DwarvenMineWorker {

  public void goToSleep() {
    System.out.println(name()+" va dormir.");
  }

  public void wakeUp() {
    System.out.println(name()+" se réveille.");
  }

  public void goHome() {
    System.out.println(name()+" rentre chez lui.");
  }

  public void goToMine() {
    System.out.println(name()+" va à la mine.");
  }

  private void action(Action action) {
    switch (action) {
      case GO_TO_SLEEP:
        goToSleep();
        break;
      case WAKE_UP:
        wakeUp();
        break;
      case GO_HOME:
        goHome();
        break;
      case GO_TO_MINE:
        goToMine();
        break;
      case WORK:
        work();
        break;
      default:
        System.out.println("Action non définie");
        break;
    }
  }

  public void action(Action... actions) {
    for (Action action : actions) {
      action(action);
    }
  }

  public abstract void work();

  public abstract String name();

  static enum Action {
    GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
  }
}
