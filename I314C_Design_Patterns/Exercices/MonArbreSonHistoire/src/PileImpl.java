public class PileImpl implements Pile {
    private NoeudPile tête;
    private int taille;
    public PileImpl() {
        this.tête = null;
    }
    public void push(Object élément) {
        this.tête = new NoeudPile(élément, this.tête);
        this.taille++;
    }
    public Object pop() {
        Object résultat = sommet();
        this.tête = this.tête.getSuivant();
        this.taille--;
        return résultat;
    }
    public Object sommet() {
        return this.tête.getElément();
    }
    public boolean estVide() {
        return this.tête == null;
    }
    public int taille() {
        return this.taille;
    }
    public String toString() {
        NoeudPile courant = this.tête;
        String résultat = "";
        while (courant != null) {
            résultat += courant.getElément();
            résultat += " ";
            courant = courant.getSuivant();
        }
        return résultat;
    }
    class NoeudPile {
        private Object élément;
        private NoeudPile suivant;
        public NoeudPile(Object élément) {
            this(élément, null);
        }
        public NoeudPile(Object élément, NoeudPile suivant) {
            setElément(élément);
            setSuivant(suivant);
        }
        public void setElément(Object élém) {
            this.élément = élém;
        }
        public void setSuivant(NoeudPile suiv) {
            this.suivant = suiv;
        }
        public Object getElément() {
            return this.élément;
        }
        public NoeudPile getSuivant() {
            return this.suivant;
        }
    }
}