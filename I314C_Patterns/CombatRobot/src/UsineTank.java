public class UsineTank implements UsineRobot {
    @Override
    public Robot createRobot() {
        return new SuperPV(new SuperBouclier(new ConcreteRobot.RobotBuilder("tank").setPv(200)
                .setFreqTir(125).setPuissanceCanon(1).generateRobot()));
    }
}
