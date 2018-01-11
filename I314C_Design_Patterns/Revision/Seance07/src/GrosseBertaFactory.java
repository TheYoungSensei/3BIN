// ConcreteFactory, ConcreteFlyWeight
public class GrosseBertaFactory implements RobotFactory {
    @Override
    public Robot create() {
        return new GrosCanon(new ConcreteRobot.RobotBuilder("Grosse Berta").setLife(150).setFreq(150).setCanon(10).build());
    }
}
