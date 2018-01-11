// ConcreteComponent, ConcreteProduct
public class ConcreteRobot implements Robot {

    private int life;
    private final int canon;
    private final int shield;
    private final int freq;
    private final String name;

    private ConcreteRobot(RobotBuilder builder) {
        this.life = builder.life;
        this.canon = builder.canon;
        this.shield = builder.shield;
        this.freq = builder.freq;
        this.name = builder.name;
    }

    @Override
    public int getCanon() {
        return canon;
    }

    @Override
    public int getShield() {
        return shield;
    }

    @Override
    public int getFreq() {
        return freq;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int diffLife(int i) {
        life += i;
        return life;
    }

    // Builder, ConcreteBuilder
    public static class RobotBuilder {

        private int life;
        private int canon;
        private int shield;
        private int freq;
        private final String name;

        RobotBuilder(String name) {
            this.name = name;
            this.life = 100;
            this.canon = 1;
            this.shield = 1;
            this.freq = 100;
        }

        // buildPart
        public RobotBuilder setLife(int life) {
            this.life = life;
            return this;
        }

        // buildPart
        public RobotBuilder setCanon(int canon) {
            this.canon = canon;
            return this;
        }

        // buildPart
        public RobotBuilder setShield(int shield) {
            this.shield = shield;
            return this;
        }

        // buildPart
        public RobotBuilder setFreq(int freq) {
            this.freq = freq;
            return this;
        }

        // getResult
        public Robot build() {
            return new ConcreteRobot(this);
        }
    }
}
