public class UsineGrosseBerta implements UsineRobot {

    @Override
    public Robot createRobot() {
        return new SuperCanon(new ConcreteRobot.RobotBuilder("grosse-berta").
                setPuissanceBouclier(3).setPuissanceCanon(10).setFreqTir(250).setPv(130).generateRobot());
    }
}
