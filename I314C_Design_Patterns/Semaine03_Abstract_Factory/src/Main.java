public class Main {

    public static void main(String[] args) {
        Magasin magasinLivres = new Magasin(new LivreFactory());
        magasinLivres.ajouterDVD("DVD", 1234);
    }
}
