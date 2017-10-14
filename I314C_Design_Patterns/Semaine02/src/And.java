import java.util.ArrayList;
import java.util.Collections;

public class And implements Strategy {

    private ArrayList<Strategy> strategies;
    @Override
    public boolean isPrintable(String line) {
        for(Strategy sc: this.strategies) {
            if(!sc.isPrintable(line))
                return false;
        }
        return true;
    }

    And(Strategy... strategies) {
        this.strategies= new ArrayList<>();
        Collections.addAll(this.strategies, strategies);
    }
}
