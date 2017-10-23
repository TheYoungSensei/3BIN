package question;

public class Piece {
	private String nom;
	private int valeur; // en cents
	
	public static final Piece vingtCents = new Piece("20 cents", 20);
	public static final Piece cinquanteCents = new Piece("50 cents", 50);
	public static final Piece unEuro = new Piece("1 Euro", 100);
	public static final Piece deuxEuros = new Piece("2 Euros", 200);
	
	
	private Piece(String nom, int valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}

	public String getNom() {
		return nom;
	}

	public int getValeur() {
		return valeur;
	}

	@Override
	public String toString() {
		return nom;
	}
	
	
	
}
