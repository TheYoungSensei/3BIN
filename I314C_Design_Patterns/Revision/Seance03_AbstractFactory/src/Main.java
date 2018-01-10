public class Main {

    public static void main(String[] args) {
        MagasinDeLivre magasin = new MagasinDeLivre();
        LivreFactory livreFactory = new LivreFactory();
        magasin.ajouterLivre(livreFactory.creerLivre("yolo1", 1997));
        magasin.ajouterLivre(livreFactory.creerLivre("yolo2", 1997));
        magasin.ajouterLivre(livreFactory.creerLivre("yolo3", 1997));
        MagasinDeDVD magasinDeDVD = new MagasinDeDVD();
        DVDFactory dvdFactory = new DVDFactory();
        magasinDeDVD.ajouterDVD(dvdFactory.creerDVD("yolo1", 1997));
        magasinDeDVD.ajouterDVD(dvdFactory.creerDVD("yolo2", 1997));
        magasinDeDVD.ajouterDVD(dvdFactory.creerDVD("yolo3", 1997));
        magasinDeDVD.ajouterDVD(dvdFactory.creerDVD("yolo4", 1997));
    }
}
