import java.util.HashMap;
import java.util.Map;

public class Magasin {

    private ProduitFactory factory;
    private Map<String,Produit> presentoir = new HashMap<>();

    public Magasin(ProduitFactory factory) {
        this.factory = factory;
    }

    public void ajouterDVD(String name, int anneeDeParution){
        presentoir.put(name,factory.createProduit(name,anneeDeParution));
    }
    public Produit retourneDVD(String name){
        return presentoir.get(name);
    }
}
