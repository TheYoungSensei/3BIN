public class SuperPV extends Amelioration {

    public SuperPV(Robot robot) {
        super(robot);
    }

    @Override
    public int diffLife(int i) {
        if(i >= 0) {
            return super.diffLife(i);
        }
        return super.diffLife(i / 2);
    }
}
