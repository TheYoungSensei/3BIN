public class SuperBouclier extends Amelioration {

    public SuperBouclier(Robot robot) {
        super(robot);
    }

    @Override
    public int getShield() {
        return 2 * super.getShield();
    }
}
