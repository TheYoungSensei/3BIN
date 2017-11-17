public class SuperCanon extends Amelioration {

    public SuperCanon(Robot robot) {
        super(robot);
    }

    @Override
    public int getCanon() {
        return 2 * super.getCanon();
    }
}
