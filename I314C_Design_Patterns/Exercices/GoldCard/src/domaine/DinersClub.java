package domaine;

import java.util.Calendar;

public class DinersClub extends CarteDeCredit {

	public DinersClub(String numero, Calendar dateExpiration, String nom) {
		super(numero, dateExpiration, nom);
	}

	@Override
	public String getType() {
		return "Diners Club";
	}

}
