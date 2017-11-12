package vie;
import java.util.*;
public class Cellule {
    private int ligne;
    private int colonne;
    private Situation situation;
    public Cellule(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.situation = EstMort.getInstance();
    }
    public void vit() {
        situation = situation.vit();
    }
    public void meurt() {
        situation = situation.meurt();
    }
    public boolean estVivante() {
        return situation.estVivante();
    }
    public void toggle() {
        situation = situation.toggle();
    }
    public void ajouterAuxVoisinsVivants(List<Situation> voisinsVivants) {
        situation.ajouterAuxVoisinsVivants(voisinsVivants);
    }
    // Compte le nombre de voisins vivants de cette cellule ci dans le jeu
    public int nombreDeVoisins(JeuDeLaVie jeu) {
        int x = 0;
        int y = ligne - 1;
        List<Situation> voisinsVivants = new ArrayList<Situation>();
        if (y < 0) {
            y = jeu.getLignes() - 1;
        }
        for (int liCpt = 1; liCpt <= 3; liCpt++) {
            x = colonne - 1;
            if (x < 0) {
                x = jeu.getColonnes() - 1;
            }
            for (int coCpt = 1; coCpt <= 3; coCpt++) {
                if (x != colonne || y != ligne) {
                    jeu.cellulleEn(y, x).ajouterAuxVoisinsVivants(voisinsVivants);
                }
                x = (x + 1) % jeu.getColonnes();
            }
            y = (y + 1) % jeu.getLignes();
        }
        return voisinsVivants.size();
    }
    public void générer(JeuDeLaVie jeu, List<Activité> activités, Parcourir parcourt) {
        situation.générer(this, jeu, activités, parcourt);
    }
}