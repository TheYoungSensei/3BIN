import java.io.IOException;
import java.util.Iterator;
public class Main {
    public static void main(String[] args) throws IOException {
        Lecteur lecteur = new Lecteur("question.txt");
        Partie partie = lecteur.construire();
        Listeur listeur = new Listeur();
        Totaliseur total = new Totaliseur();
        partie.demande(listeur);
        System.out.println();
        System.out.println();
        partie.demande(total);
        System.out.println("nombre de valeurs : " + total.getNombreDeValeurs());
        System.out.println("nombre de groupes : " + total.getNombreDeGroupes());
        System.out.println("somme des valeurs : " + total.getSommeDesValeurs());
        System.out.println("moyenne des valeurs : " + total.getSommeDesValeurs() / (double) total.getNombreDeValeurs());
        System.out.println();
        ListeurNom listeurNom = new ListeurNom();
        partie.demande(listeurNom);
        Iterator itérateur = listeurNom.getLesNoms();
        while(itérateur.hasNext()) {
            String s = (String) itérateur.next();
            System.out.println(s);
        }
        System.out.println();
    }
}