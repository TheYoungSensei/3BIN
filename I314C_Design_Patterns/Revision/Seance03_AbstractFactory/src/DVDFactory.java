// ConcreteFactory
public class DVDFactory implements ProduitFactory {


    @Override
    public DVD creerDVD(String name, int anneeParution) {
        return new DVD(name, anneeParution);
    }

    @Override
    public Livre creerLivre(String name, int anneeParution) {
        throw new UnsupportedOperationException();
    }
}
