package domaine;

import java.util.Calendar;

public class AmEx extends CarteDeCredit {

	public AmEx(String numero, Calendar dateExpiration, String nom) {
		super(numero, dateExpiration, nom);
	}

	@Override
	public String getType() {
		return "America Express";
	}

}
