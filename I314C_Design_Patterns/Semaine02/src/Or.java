import java.util.ArrayList;
import java.util.Collections;

public class Or implements Strategy {


    private ArrayList<Strategy> strategies;
    @Override
    public boolean isPrintable(String line) {
        for(Strategy sc: this.strategies) {
            if(sc.isPrintable(line))
                return true;
        }
        return false;
    }

    public Or(Strategy... strategies) {
        this.strategies= new ArrayList<>();
        Collections.addAll(this.strategies, strategies);
    }
}
