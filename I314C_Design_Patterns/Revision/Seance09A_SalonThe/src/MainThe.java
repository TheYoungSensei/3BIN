import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Map.Entry;

class MainThe {


	public static void main(String[] args) {
		Carte carte=new Carte();
		Salle salle =new Salle();
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("carte.properties"));
			for(Entry<Object,Object> s : props.entrySet()) {
				carte.ajouterParfum(new Parfum((String) s.getKey(), new Double((String) s.getValue())));
			}
			props.load(new FileInputStream("salon.properties"));
			String tables = props.getProperty("tables");
			StringTokenizer placesDisponibles = new StringTokenizer(tables, ",");
			while(placesDisponibles.hasMoreTokens()) {
				salle.ajouterTable(new Integer(placesDisponibles.nextToken()));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		SalonThe salonThe = new SalonThe(carte, salle);
		try {
			salonThe.prendreCommande("Thé vert", 2, 5);
		} catch (ParfumInexistantException e) {
			System.out.println("Le parfum demandé n'existe pas");
		} catch (TableInexistanteException e) {
			System.out.println("Le numéro de table indiqué est incorrect");
		}
		try {
			salonThe.prendreCommande("Camomille", 1, 1);
		} catch (ParfumInexistantException e) {
			System.out.println("Le parfum demandé n'existe pas");
		} catch (TableInexistanteException e) {
			System.out.println("Le numéro de table indiqué est incorrect");
		}
		try {
			salonThe.prendreCommande("Menthe", 1, 1);
		} catch (ParfumInexistantException e) {
			System.out.println("Le parfum demandé n'existe pas");
		} catch (TableInexistanteException e) {
			System.out.println("Le numéro de table indiqué est incorrect");
		}
		try {
			salonThe.prendreCommande("Earl Grey", 1, 1);
		} catch (ParfumInexistantException e) {
			System.out.println("Le parfum demandé n'existe pas");
		} catch (TableInexistanteException e) {
			System.out.println("Le numéro de table indiqué est incorrect");
		}

		for (Table table : salonThe.getChiffresAffaire().keySet())
			System.out.println("Table " + table.getNumero() + " : "
					+ salonThe.getChiffresAffaire().get(table) + " ?");
	}



}