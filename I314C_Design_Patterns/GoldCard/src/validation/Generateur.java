package validation;

import java.util.Calendar;

import domaine.CarteDeCredit;

public abstract class Generateur {

    private Generateur succ;

    public Generateur(Generateur succ) {
        this.succ = succ;
    }

	public abstract boolean valider(String numero);
	public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if(succ != null) {
            return succ.creerCarte(numero, dateExpiration, nom);
        }
        return null;
    }
}
