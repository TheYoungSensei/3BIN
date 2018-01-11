package core;

import java.io.IOException;

public class Proxy implements Fichier {

    private Fichier fichier;
    private boolean liste;
    private boolean charge;
    private boolean sauve;
    private boolean efface;

    public Proxy(Fichier fichier, boolean liste, boolean charge, boolean sauve, boolean efface) {
        this.fichier = fichier;
        this.liste = liste;
        this.charge = charge;
        this.sauve = sauve;
        this.efface = efface;
    }

    @Override
    public String[] liste(String repertoire) throws IOException {
        if(liste)
            return fichier.liste(repertoire);
        throw new IllegalAccessError();
    }

    @Override
    public byte[] charge(String chemin) throws IOException {
        if(charge)
            return fichier.charge(chemin);
        throw new IllegalAccessError();
    }

    @Override
    public void sauve(String chemin, byte[] donnees) throws IOException {
        if(sauve) {
            fichier.sauve(chemin, donnees);
        } else {
            throw new IllegalAccessError();
        }
    }

    @Override
    public void efface(String chemin) throws IOException {
        if(efface) {
            fichier.efface(chemin);
        } else {
            throw new IllegalAccessError();
        }
    }
}
