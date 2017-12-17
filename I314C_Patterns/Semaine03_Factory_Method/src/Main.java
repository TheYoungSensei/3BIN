public class Main {

    public static void main(String[] args) {
        MagasinFactory magasin = new MagasinDeLivre();
        magasin.ajouterProduit("livre1", 2017);
        magasin.ajouterProduit("livre2", 2017);
        magasin.ajouterProduit("livre3", 2017);
        magasin.ajouterProduit("livre4", 2017);
        MagasinFactory magasin2 = new MagasinDeDVD();
        magasin2.ajouterProduit("dvd1", 2017);
        magasin2.ajouterProduit("dvd2", 2017);
        magasin2.ajouterProduit("dvd3", 2017);


    }
}
