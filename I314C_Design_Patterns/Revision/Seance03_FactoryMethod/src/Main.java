public class Main {

    public static void main(String[] args) {
        MagasinDeProduit magasinDeLivres = new MagasinDeLivre();
        magasinDeLivres.ajouterProduit("yolo1", 1997);
        magasinDeLivres.ajouterProduit("yolo2", 1997);
        magasinDeLivres.ajouterProduit("yolo3", 1997);
        magasinDeLivres.ajouterProduit("yolo4", 1997);
        MagasinDeProduit magasinDeDVD = new MagasinDeDVD();
        magasinDeDVD.ajouterProduit("yolo1", 1997);
        magasinDeDVD.ajouterProduit("yolo2", 1997);
        magasinDeDVD.ajouterProduit("yolo3", 1997);
    }
}
