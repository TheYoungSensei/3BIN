import java.util.Iterator;
import java.util.Vector;
public class ListeurNom implements Traitement {
    private Vector lesNoms = new Vector();
    private int enCours = -1;
    public void traiteValeur(Valeur valeur) {
        String val = valeur.getValeur() + " ";
        if (enCours == -1) {
            lesNoms.add(val);
        } else {
            String s = (String) lesNoms.get(enCours);
            lesNoms.set(enCours, s + val);
        }
    }
    public void traiteGroupe(Groupe groupe) {
        if (enCours != -1) {
            String s = (String) lesNoms.get(enCours);
            lesNoms.set(enCours, s + groupe.getNom() + " ");
        }
        int exEnCours = enCours;
        enCours = groupe.getNuméro();
        lesNoms.setSize(enCours + 1);
        lesNoms.set(enCours, groupe.getNom() + ": ");
        Iterator it = groupe.getParties();
        while(it.hasNext()) {
            Partie p = (Partie)it.next();
            p.demande(this);
        }
        enCours = exEnCours;
    }
    public Iterator getLesNoms() {
        return lesNoms.iterator();
    }
}