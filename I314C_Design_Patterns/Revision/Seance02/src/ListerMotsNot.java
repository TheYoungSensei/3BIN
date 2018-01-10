// ConcreteStrategy, Composite
public class ListerMotsNot implements ListerMotsStrategy {

    private ListerMotsStrategy strategy;

    public ListerMotsNot(ListerMotsStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean traiterLigne(String ligne) {
        return !strategy.traiterLigne(ligne);
    }
}
