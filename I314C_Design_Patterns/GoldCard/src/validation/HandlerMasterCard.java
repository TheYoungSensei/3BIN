package validation;

import domaine.AmEx;
import domaine.CarteDeCredit;
import domaine.MasterCard;

import java.util.Calendar;

public class HandlerMasterCard extends Generateur {
    public HandlerMasterCard(Generateur succ) {
        super(succ);
    }

    @Override
    public boolean valider(String numero) {
        return numero.charAt(0) == '5' && (numero.charAt(1) <= '5' && numero.charAt(1) >= '1') && numero.length() == 16;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if(this.valider(numero)) {
            return new MasterCard(numero, dateExpiration, nom);
        } else {
            return super.creerCarte(numero, dateExpiration, nom);
        }
    }
}
