public class Counter implements ListerMotsStrategy {

    private ListerMotsStrategy strategy;
    private int compteur;

    public Counter(ListerMotsStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean traiterLigne(String ligne) {
        if(strategy.traiterLigne(ligne)) {
            this.compteur++;
            return true;
        }
        return false;
    }

    public int getCompteur() {
        return compteur;
    }
}
