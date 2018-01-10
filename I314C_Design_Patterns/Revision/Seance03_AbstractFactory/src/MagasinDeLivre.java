import java.util.HashMap;
import java.util.Map;

public class MagasinDeLivre {
	private Map<String,Livre> etagere= new HashMap<String,Livre>();
	public void ajouterLivre(Livre livre){
		etagere.put(livre.getName(),livre);
	}
	public Livre retourneLivre(String name){
		return etagere.get(name);
	}
}
