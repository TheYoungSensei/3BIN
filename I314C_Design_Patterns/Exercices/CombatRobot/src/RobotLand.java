import java.util.HashMap;
import java.util.Map;

public class RobotLand {

    public static final String PICVERT = "picVert";
    public static final String GROSSEBERTA = "grosseBerta";
    public static final String TANK = "tank";

    Map<String, UsineRobot> mapUsines = new HashMap<>();

    public RobotLand() {
        mapUsines.put(PICVERT, new UsinePicVert());
        mapUsines.put(GROSSEBERTA, new UsineGrosseBerta());
        mapUsines.put(TANK, new UsineTank());
    }

    public Robot createRobot(String key) {
        return mapUsines.get(key).createRobot();
    }
}
