package core;

import java.io.IOException;
import java.util.Arrays;

//Concrete Decorator et Decorator / RealSubject
public class Logger implements Fichier {

    //Added State
    private Fichier fichier;

    public Logger(Fichier fichier) {
        this.fichier = fichier;
    }

    //Added behaviour
    private void logger(String message) {
        System.out.println(message);
    }

    @Override
    public String[] liste(String repertoire) throws IOException {
       logger("L'utilisateur liste(" + repertoire + ")");
        return fichier.liste(repertoire);
    }

    @Override
    public byte[] charge(String chemin) throws IOException {
        logger("L'utilisateur charge(" + chemin + ")");
        return fichier.charge(chemin);
    }

    @Override
    public void sauve(String chemin, byte[] donnees) throws IOException {
        logger("L'utilisateur sauve(" + chemin + ", " + Arrays.toString(donnees) + ")" );
        fichier.sauve(chemin, donnees);
    }

    @Override
    public void efface(String chemin) throws IOException {
        logger("L'utilisateur efface(" + chemin + ")");
        fichier.efface(chemin);
    }
}
