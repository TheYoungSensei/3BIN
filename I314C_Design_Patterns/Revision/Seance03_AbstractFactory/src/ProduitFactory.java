// Abstract Factory
public interface ProduitFactory {

    // createProductA
    public DVD creerDVD(String name, int anneeParution);
    // createProductB
    public Livre creerLivre(String name, int anneeParution);

}
