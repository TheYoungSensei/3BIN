import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// ConcreteStrategy, Composite
public class ListerMotsAnd implements ListerMotsStrategy {

    private List<ListerMotsStrategy> strategies;

    public ListerMotsAnd(ListerMotsStrategy... strategies) {
        this.strategies = new ArrayList<>();
        Collections.addAll(this.strategies, strategies);
    }

    @Override
    public boolean traiterLigne(String ligne) {
        for(ListerMotsStrategy strategy : this.strategies) {
            if(!strategy.traiterLigne(ligne))
                return false;
        }
        return true;
    }
}
