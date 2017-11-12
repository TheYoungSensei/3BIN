import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
public class Groupe implements Partie {
    private static int dernier = 0;
    private Vector parties = new Vector();
    private int niveau;
    private String nom;
    private int numéro;
    public Groupe(String nom, int niveau) {
        this.nom = nom;
        this.niveau = niveau;
        numéro = dernier++;
    }
    public void add(Partie nouveau) {
        parties.add(nouveau);
    }
    public Iterator getParties() {
        return Collections.unmodifiableList(parties).iterator();
    }
    public int getNiveau() {
        return niveau;
    }
    public String getNom() {
        return nom;
    }
    public int getNuméro() {
        return numéro;
    }
    public void demande(Traitement traitement) {
        traitement.traiteGroupe(this);
    }
}