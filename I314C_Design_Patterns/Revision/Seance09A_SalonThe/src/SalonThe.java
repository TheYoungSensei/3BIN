import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SalonThe implements CommandeThe {

    private Map<Table, Double> chiffresAffaire = new HashMap<>();
    private Carte carte;
    private Salle salle;

    public SalonThe(Carte carte, Salle salle) {
        this.carte = carte;
        this.salle = salle;
    }

    void prendreCommande(String parfum, int quantite, int numeroTable) {
        Parfum parf = carte.getParfum(parfum);
        if(parf == null) {
            throw new ParfumInexistantException();
        }
        Table table = salle.getTable(numeroTable);
        if(table == null)
            throw new TableInexistanteException();
        chiffresAffaire.merge(table, quantite*parf.getPrix(), (a,b)->a+(b));

    }

    public Map<Table, Double> getChiffresAffaire() {
        return Collections.unmodifiableMap(chiffresAffaire);
    }

    @Override
    public void servirThe(int quantite, Parfum parfum, Table table) {
        System.out.println(quantite+" parfum(s) "+parfum.getNom()+" pour la table "+table.getNumero());
    }


}
