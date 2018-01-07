package vie;
import java.util.*;
/** Cette classe implémente le jeu de la vie */
public class JeuDeLaVie {
    private int lignes;
    private int colonnes;
    private Cellule grille[][];
    private List<Espion> espions;
    private Parcourir parcourt;
    public JeuDeLaVie(int lignes, int colonnes, Parcourir parcourt) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.parcourt = parcourt;
        grille = new Cellule[lignes][colonnes];
        espions = new ArrayList<Espion>();
        for(int i =0; i < lignes; i++){
            for(int j=0; j < colonnes; j++){
                grille[i][j] = new Cellule(i, j);
            }
        }
    }
    protected void effacerGrille(Cellule[][] g){
        for(int i =0; i < lignes; i++){
            for(int j=0; j < colonnes; j++){
                g[i][j] = new Cellule(i,j);
            }
        }
    }
    public int getLignes(){
        return lignes;
    }
    public int getColonnes(){
        return colonnes;
    }
    public boolean estVivante(int li, int co){
        return grille[li][co].estVivante();
    }
    public Cellule cellulleEn(int li, int co){
        return grille[li][co];
    }
    // Inverse le statut de la cellule de position li,co
    public void toggle(int li, int co){
        grille[li][co].toggle();
        prévenirEspions();
    }
    // Cette méthode implemente les règles du Jeu de la Vie.
    // Pour chaque cellule,
    // on trouve le nombre de voisins et on rend la cellule vivante selon
    // les règles définies dans le parcourt
    public void avancer(){
        ArrayList<Activité> activités = new ArrayList<Activité>();
        for(int i = 0; i < lignes; i++)
            for(int j = 0; j < colonnes; j++)
                grille[i][j].générer(this, activités, parcourt);
        for (Activité activité: activités)
            activité.activer();
        prévenirEspions();
    }
    // Ajoute un espion.
    public void attacher(Espion espion){
        if (espion == null) return; this.espions.add(espion);
    }
    // Supprime un espion.
    public void détacher(Espion espion){
        this.espions.remove(espion);
    }
// Informe tous les espions de tenir compte des renseignements obtenus.public void
prévenirEspions(){
 for (Espion espion: espions)
            espion.utiliserRenseignements();
}
}