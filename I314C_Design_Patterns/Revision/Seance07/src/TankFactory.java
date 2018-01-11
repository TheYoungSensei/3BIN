// ConcreteFactory, ConcreteFlyWeight
public class TankFactory implements RobotFactory {
    @Override
    public Robot create() {
        return new GrosBouclier(new PeauDure(new ConcreteRobot.RobotBuilder("Tank").setCanon(2).setLife(300).setFreq(90).build()));
    }
}
