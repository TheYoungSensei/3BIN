public class NbrBelgique implements Subject.Observer {

    private int nbrBelgique;

    public int getNbrBelgique() {
        return nbrBelgique;
    }

    @Override
    public void update(String ligne) {
        if (ligne.contains("Belgique")) {
            nbrBelgique++;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(nbrBelgique);
    }
}
