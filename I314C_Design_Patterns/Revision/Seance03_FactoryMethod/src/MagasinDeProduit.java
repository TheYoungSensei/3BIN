import java.util.HashMap;
import java.util.Map;

public abstract class MagasinDeProduit {
    private Map<String,Produit> presentoir= new HashMap<String,Produit>();
    public void ajouterProduit(String name, int anneeDeParution){
        presentoir.put(name,createProduit(name, anneeDeParution));
    }
    public Produit retourneLivre(String name) {
        return presentoir.get(name);
    }

    public abstract Produit createProduit(String name, int anneeDeParution);
}

