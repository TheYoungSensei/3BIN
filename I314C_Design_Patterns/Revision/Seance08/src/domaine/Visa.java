package domaine;

import java.util.Calendar;

public class Visa extends CarteDeCredit {

	public Visa(String numero, Calendar dateExpiration, String nom) {
		super(numero, dateExpiration, nom);
	}

	@Override
	public String getType() {
		return "Visa";
	}

}
