import java.util.Iterator;
public class Listeur implements Traitement {
    public void traiteValeur(Valeur valeur) {
        System.out.print(valeur.getValeur() + " ");
    }
    public void traiteGroupe(Groupe groupe) {
        System.out.print("( ");
        Iterator it = groupe.getParties();
        while(it.hasNext()) {
            Partie p = (Partie)it.next(); p.demande(this);
        }
        System.out.print(") ");
    }
}