import java.util.HashMap;
import java.util.Map;


public class MagasinDeDVD extends MagasinDeProduit {

	@Override
	public Produit createProduit(String name, int anneeDeParution) {
		return new DVD(name, anneeDeParution);
	}
}
