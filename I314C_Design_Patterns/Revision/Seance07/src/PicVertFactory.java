// ConcreteFactory, ConcreteFlyWeight
public class PicVertFactory implements RobotFactory {
    @Override
    public Robot create() {
        return new PeauDure(new ConcreteRobot.RobotBuilder("Pic Vert").setLife(70).setFreq(30).setCanon(2).build());
    }
}
