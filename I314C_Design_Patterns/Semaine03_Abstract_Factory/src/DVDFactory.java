public class DVDFactory implements ProduitFactory {

    @Override
    public Produit createProduit(String name, int anneeDeParution) {
        return new DVD(name, anneeDeParution);
    }
}
