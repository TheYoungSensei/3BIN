package domaine;

import java.util.Calendar;

public class MasterCard extends CarteDeCredit {

	public MasterCard(String numero, Calendar dateExpiration, String nom) {
		super(numero, dateExpiration, nom);
	}

	@Override
	public String getType() {
		return "MasterCard";
	}

}
