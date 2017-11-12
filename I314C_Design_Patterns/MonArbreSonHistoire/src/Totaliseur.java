import java.util.Iterator;
public class Totaliseur implements Traitement {
    private int nombreDeValeurs = 0;
    private int nombreDeGroupes = 0;
    private int sommeDesValeurs = 0;
    public void traiteValeur(Valeur valeur) {
        nombreDeValeurs++;
        sommeDesValeurs += valeur.getValeur();
    }
    public void traiteGroupe(Groupe groupe) {
        nombreDeGroupes++;
        Iterator it = groupe.getParties();
        while(it.hasNext()) {
            Partie p = (Partie)it.next();
            p.demande(this);
        }
    }
    public int getNombreDeGroupes() {
        return nombreDeGroupes;
    }
    public int getNombreDeValeurs() {
        return nombreDeValeurs;
    }
    public int getSommeDesValeurs() {
        return sommeDesValeurs;
    }
}