package validation;

import domaine.CarteDeCredit;
import domaine.MasterCard;
import domaine.Visa;

import java.util.Calendar;

public class HandlerVisa extends Generateur {
    public HandlerVisa(Generateur succ) {
        super(succ);
    }

    @Override
    public boolean valider(String numero) {
        return numero.charAt(0) == '4' && numero.length() == 16;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if(this.valider(numero)) {
            return new Visa(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
