package domaine;

import java.util.Calendar;

public abstract class CarteDeCredit {
	String nom;
	String numero;
	Calendar dateExpiration;
	
	public CarteDeCredit(String numero, Calendar dateExpiration, String nom) {
		this.numero = numero;
		this.dateExpiration = dateExpiration;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public String getNumero() {
		return numero;
	}

	public Calendar getDateExpiration() {
		return dateExpiration;
	}
	
	public abstract String getType();
}
