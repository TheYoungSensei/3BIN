// ConcreteProduct
public class GrosCanon extends Amelioration {
    public GrosCanon(Robot robot) {
        super(robot);
    }

    @Override
    public int getCanon() {
        return super.getCanon() * 2;
    }
}
