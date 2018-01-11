package goldmine;

// AbstractClass
public abstract class DwarvenMineWorker {

    // handleRequest
  public void goToSleep() {
    System.out.println(name()+" va dormir.");
  }

  // handleRequest
  public void wakeUp() {
    System.out.println(name()+" se réveille.");
  }

  // handleRequest
  public void goHome() {
    System.out.println(name()+" rentre chez lui.");
  }

  // handleRequest
  public void goToMine() {
    System.out.println(name()+" va à la mine.");
  }

  // templateMethod
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

  // subMethod, handleRequest
  public abstract void work();

  // subMethod
  public abstract String name();

  // Handler
  static enum Action {
      // ConcreteHandlers
    GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
  }
}
