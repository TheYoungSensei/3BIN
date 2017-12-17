public class MagasinDeLivre extends MagasinFactory {


	@Override
	public Produit createProduit(String name, int anneeDeParution) {
		return new Livre(name, anneeDeParution);
	}
}
