import java.util.HashMap;
import java.util.Map;


public class MagasinDeDVD {
	private Map<String,DVD> bac= new HashMap<String,DVD>();
	public void ajouterDVD(DVD dvd){
		bac.put(dvd.getName(),dvd);
	}
	public DVD retourneDVD(String name){
		return bac.get(name);
	}
}
