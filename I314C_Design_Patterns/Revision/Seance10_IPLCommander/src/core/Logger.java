package core;

import java.io.IOException;

// Decorator, ConcreteDecorator
public class Logger implements Fichier {

    private Fichier fichier;

    // added behaviour
    public void logger(String message) {
        System.out.println(message);
    }

    public Logger(Fichier fichier) {
        this.fichier = fichier;
    }

    // operation
    @Override
    public String[] liste(String repertoire) throws IOException {
        logger("L'utilisateur liste le contenu de "+repertoire);
        return fichier.liste(repertoire);
    }

    // operation
    @Override
    public byte[] charge(String chemin) throws IOException {
        logger("L'utilisateur charge le contenu présent à "+chemin);
        return fichier.charge(chemin);
    }

    // operation
    @Override
    public void sauve(String chemin, byte[] donnees) throws IOException {
        logger("L'utilisateur sauve les donnees à l'emplacement : "+chemin);
        fichier.sauve(chemin, donnees);
    }

    // operation
    @Override
    public void efface(String chemin) throws IOException {
        logger("L'utilisateur efface le fichier présent à l'emplacement : "+chemin);
        fichier.efface(chemin);
    }
}
