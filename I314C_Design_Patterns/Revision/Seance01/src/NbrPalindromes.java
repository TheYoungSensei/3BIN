public class NbrPalindromes implements Subject.Observer {

    private int nbrPalindromes;

    public int getNbrPalindromes() {
        return nbrPalindromes;
    }

    @Override
    public void update(String ligne) {
        for(String mot:ligne.trim().split(" ")) {
            StringBuilder temp=new StringBuilder(mot);
            if (mot.equals(temp.reverse().toString())) {
                nbrPalindromes++;
            }
        }
    }

    @Override
    public String toString() {
        return Integer.toString(nbrPalindromes);
    }
}
