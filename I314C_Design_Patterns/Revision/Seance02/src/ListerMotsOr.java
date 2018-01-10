// ConcreteStrategy, Composite
public class ListerMotsOr implements ListerMotsStrategy {

    private ListerMotsStrategy strategy1;
    private ListerMotsStrategy strategy2;

    public ListerMotsOr(ListerMotsStrategy strategy1, ListerMotsStrategy strategy2) {
        this.strategy1 = strategy1;
        this.strategy2 = strategy2;
    }

    @Override
    public boolean traiterLigne(String ligne) {
        return strategy1.traiterLigne(ligne) || strategy2.traiterLigne(ligne);
    }
}
