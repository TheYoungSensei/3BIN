import java.util.HashMap;
import java.util.Map;

// FlyWeightFactory
public class RobotFlyWeightFactory {

    public static final int PICVERT = 0;
    public static final int GROSSEBERTA = 1;
    public static final int TANK = 2;

    private Map<Integer, RobotFactory> flyweights;


    RobotFlyWeightFactory() {
        flyweights = new HashMap<>();
        flyweights.put(PICVERT, new PicVertFactory());
        flyweights.put(GROSSEBERTA, new GrosseBertaFactory());
        flyweights.put(TANK, new TankFactory());
    }

    public RobotFactory getFlyWeight(int flyweight) {
        return flyweights.get(flyweight);
    }

}
