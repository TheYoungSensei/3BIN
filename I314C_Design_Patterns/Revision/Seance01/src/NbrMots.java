import java.util.Observable;

public class NbrMots implements Subject.Observer {

    private int nbrMots;

    public int getNbrMots() {
        return nbrMots;
    }

    @Override
    public void update(String ligne) {
        for(String mot:ligne.trim().split(" ")) {
            nbrMots++;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(nbrMots);
    }
}
