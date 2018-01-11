package validation;

        import domaine.CarteDeCredit;
        import domaine.Discover;

        import java.util.Calendar;

public class HandlerDiscover extends Generateur {
    public HandlerDiscover(Generateur succ) {
        super(succ);
    }

    @Override
    public boolean valider(String numero) {
        return (numero.substring(0,4).equals("6011") || numero.substring(0,2).equals("65")) && numero.length() == 16;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if(this.valider(numero)) {
            return new Discover(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
