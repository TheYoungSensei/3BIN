import java.util.HashMap;
import java.util.Map;

public abstract class MagasinFactory {

    private Map<String, Produit> presentoir= new HashMap<>();

    final void ajouterProduit(String name, int anneeDeParution){
        presentoir.put(name,createProduit(name, anneeDeParution));
    }
    public final Produit retourneProduit(String name){
        return presentoir.get(name);
    }

    public abstract Produit createProduit(String name, int anneeDeParution);
}
