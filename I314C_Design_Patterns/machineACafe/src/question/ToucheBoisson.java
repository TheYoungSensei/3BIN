package question;

public class ToucheBoisson {
	private String type;
	private boolean auLait;
	private boolean sucre;
	private int prix; // en cents
	
	public static final ToucheBoisson cafeNoir = new ToucheBoisson("Cafe", false, false, 50);
	public static final ToucheBoisson cafeAuLait = new ToucheBoisson("Cafe", true, false, 70);
	public static final ToucheBoisson cafeNoirSucre = new ToucheBoisson("Cafe", false, true, 70);
	public static final ToucheBoisson cafeAuLaitSucre = new ToucheBoisson("Cafe", true, true, 90);
	
	public static final ToucheBoisson expresso = new ToucheBoisson("Expresso", false, false, 100);
	public static final ToucheBoisson expressoAuLait = new ToucheBoisson("Expresso", true, false, 120);
	public static final ToucheBoisson expressoSucre = new ToucheBoisson("Expresso", false, true, 120);
	public static final ToucheBoisson expressoAuLaitSucre = new ToucheBoisson("Expresso", true, true, 140);
	
	public static final ToucheBoisson decaNoir = new ToucheBoisson("Cafe decafeine", false, false, 50);
	public static final ToucheBoisson decaAuLait = new ToucheBoisson("Cafe decafeine", true, false, 70);
	public static final ToucheBoisson decaNoirSucre = new ToucheBoisson("Cafe decafeine", false, true, 70);
	public static final ToucheBoisson decaAuLaitSucre = new ToucheBoisson("Cafe decafeine", true, true, 90);
	
	public static final ToucheBoisson choco = new ToucheBoisson("Chocolat chaud", false, false, 70);
	public static final ToucheBoisson chocoAuLait = new ToucheBoisson("Chocolat chaud", true, false, 90);
	public static final ToucheBoisson chocoSucre = new ToucheBoisson("Chocolat chaud", false, true, 90);
	public static final ToucheBoisson chocoAuLaitSucre = new ToucheBoisson("Chocolat chaud", true, true, 110);

	public static final ToucheBoisson cappuccino = new ToucheBoisson("Cappuccino", false, false, 120);
	public static final ToucheBoisson viennois = new ToucheBoisson("Café Viennois", false, false, 120);
	public static final ToucheBoisson laitRusse = new ToucheBoisson("Lait russe", false, false, 100);
	public static final ToucheBoisson chocafe = new ToucheBoisson("Choco-Café", false, false, 120);
	
	public static final ToucheBoisson lait = new ToucheBoisson("Lait chaud", false, false, 70);
	public static final ToucheBoisson eau = new ToucheBoisson("Eau chaud", false, false, 0);
	
	
	private ToucheBoisson(String type, boolean lait, boolean sucre, int prix) {
		this.type = type;
		auLait = lait;
		sucre = sucre;
		this.prix = prix;
	}

	public String getType() {
		return type;
	}

	public boolean auLait() {
		return auLait;
	}

	public boolean estSucre() {
		return sucre;
	}

	public int getPrix() {
		return prix;
	}

	@Override
	public String toString() {
		return type + (auLait()?" au lait":"") + (estSucre()?" sucre":"");
	}
	
	
	
}
