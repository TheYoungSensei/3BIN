import java.util.HashMap;
import java.util.Map;

public class MagasinDeLivre extends MagasinDeProduit {

	@Override
	public Produit createProduit(String name, int anneeDeParution) {
		return new Livre(name, anneeDeParution);
	}
}
