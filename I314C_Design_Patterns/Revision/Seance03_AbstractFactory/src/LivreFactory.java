// ConcreteFactory
public class LivreFactory implements ProduitFactory {

    @Override
    public DVD creerDVD(String name, int anneeParution) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Livre creerLivre(String name, int anneeParution) {
        return new Livre(name, anneeParution);
    }
}
