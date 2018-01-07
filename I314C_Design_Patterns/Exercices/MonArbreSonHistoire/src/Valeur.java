public class Valeur implements Partie {
    private int nombre;
    private int niveau = 0;
    public Valeur(int nombre, int niveau) {
        this.nombre = nombre;
        this.niveau = niveau;
    }
    public void demande(Traitement traitement) {
        traitement.traiteValeur(this);
    }
    public int getValeur() {
        return nombre;
    }
    public int getNiveau() {
        return niveau;
    }
}