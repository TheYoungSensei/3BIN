import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SalonThe implements CommandeThe {

    private Map<Table, Double> chiffres = new HashMap<>();
    private Carte carte;
    private Salle salle;

    SalonThe(Carte carte, Salle salle) {
        this.carte = carte;
        this.salle = salle;
    }

    @Override
    public void servirThe(int quantite, Parfum parfum, Table table) {
        System.out.println(quantite + " parfum(s) " + parfum.getNom() + " pour la table " + table.getNumero());
    }

    void prendreCommande(String parfum, int quantite, int numeroTable) {
        Parfum parf = carte.getParfum(parfum);
        if(parf == null)
            throw new ParfumInexistantException();
        Table table = salle.getTable(numeroTable);
        if(table == null)
            throw new TableInexistanteException();
        chiffres.merge(table, quantite * parf.getPrix(), (a, b) -> a + (b));
        servirThe(quantite, parf, table);
    }

    Map<Table, Double> getChiffres() {
        return Collections.unmodifiableMap(chiffres);
    }
}
