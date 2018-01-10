import java.util.Observable;

public class NbrLignes implements Subject.Observer {

    private int nbrLignes;

    public int getNbrLignes() {
        return nbrLignes;
    }

    @Override
    public void update(String ligne) {
        nbrLignes++;
    }

    @Override
    public String toString() {
        return Integer.toString(nbrLignes);
    }
}
