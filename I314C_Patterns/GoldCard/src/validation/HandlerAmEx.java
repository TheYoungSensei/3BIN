package validation;

import domaine.AmEx;
import domaine.CarteDeCredit;

import java.util.Calendar;

public class HandlerAmEx extends Generateur {

    public HandlerAmEx(Generateur succ) {
        super(succ);
    }

    @Override
    public boolean valider(String numero) {
        return (numero.substring(0,2).equals("34") || numero.substring(0, 2).equals("37")) && numero.length() == 15; //34 ou 37
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if(this.valider(numero)) {
            return new AmEx(numero, dateExpiration, nom);
        } else {
            return super.creerCarte(numero, dateExpiration, nom);
        }
    }
}
