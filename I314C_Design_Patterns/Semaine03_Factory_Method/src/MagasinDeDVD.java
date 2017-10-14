public class MagasinDeDVD extends MagasinFactory {

	@Override
	public Produit createProduit(String name, int anneeDeParution) {
		return new DVD(name, anneeDeParution);
	}
}
