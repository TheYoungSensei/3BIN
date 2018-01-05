public class ConcreteRobot implements Robot {

    private int pv; //Points de vie
    private final int puissanceCanon;
    private final int puissanceBouclier;
    private final int freqTir;
    private final String nom;

    private ConcreteRobot(RobotBuilder builder) {
        this.pv = builder.pv;
        this.puissanceCanon = builder.puissanceCanon;
        this.puissanceBouclier = builder.puissanceBouclier;
        this.freqTir = builder.freqTir;
        this.nom = builder.nom;
    }

    @Override
    public int getCanon() {
        return this.puissanceCanon;
    }

    @Override
    public int getShield() {
        return this.puissanceBouclier;
    }

    @Override
    public int getFreq() {
        return this.freqTir;
    }

    @Override
    public String getName() {
        return this.nom;
    }

    @Override
    public int diffLife(int i) {
        this.pv += i;
        return this.pv;
    }

    public static class RobotBuilder {

        private int pv; //Points de vie
        private int puissanceCanon;
        private int puissanceBouclier;
        private int freqTir;
        private final String nom;

        public RobotBuilder(String nom) {
            pv = 100;
            puissanceCanon = 1;
            puissanceBouclier = 1;
            freqTir = 100;
            this.nom = nom;
        }

        public RobotBuilder setPv(int pv) {
            this.pv = pv;
            return this;
        }

        public RobotBuilder setPuissanceCanon(int puissanceCanon) {
            this.puissanceCanon = puissanceCanon;
            return this;
        }

        public RobotBuilder setPuissanceBouclier(int puissanceBouclier) {
            this.puissanceBouclier = puissanceBouclier;
            return this;
        }

        public RobotBuilder setFreqTir(int freqTir) {
            this.freqTir = freqTir;
            return this;
        }

        public Robot generateRobot() {
            return new ConcreteRobot(this);
        }
    }
}
