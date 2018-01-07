public class UsinePicVert implements UsineRobot {

    @Override
    public Robot createRobot() {
        return new SuperCanon(new ConcreteRobot.RobotBuilder("pic-vert").setPuissanceBouclier(2).
                setPuissanceCanon(2).setFreqTir(50).setPv(70).generateRobot());
    }
}
