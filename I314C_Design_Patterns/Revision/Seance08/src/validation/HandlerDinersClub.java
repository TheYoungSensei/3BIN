package validation;

import domaine.AmEx;
import domaine.CarteDeCredit;
import domaine.DinersClub;

import java.util.Calendar;

public class HandlerDinersClub extends Generateur {
    public HandlerDinersClub(Generateur succ) {
        super(succ);
    }

    @Override
    public boolean valider(String numero) {
        return numero.substring(0,2).equals("36") && numero.length() == 14;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if(this.valider(numero)) {
            return new DinersClub(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
