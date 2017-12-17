public class LivreFactory implements ProduitFactory{


    @Override
    public Produit createProduit(String name,int anneeDeParution) {
        return new Livre(name, anneeDeParution);
    }
}
