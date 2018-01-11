// ConcreteProduct
public class GrosBouclier extends Amelioration {
    public GrosBouclier(Robot robot) {
        super(robot);
    }

    @Override
    public int getShield() {
        return super.getShield() * 2;
    }
}
