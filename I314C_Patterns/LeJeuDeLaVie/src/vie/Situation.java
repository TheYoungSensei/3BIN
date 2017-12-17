package vie;
import java.util.List;

public abstract class Situation {
    public abstract Situation vit();
    public abstract Situation meurt();
    public abstract Situation toggle();
    public abstract boolean estVivante();
    public abstract void ajouterAuxVoisinsVivants(List<Situation> voisinsVivants);
    public abstract void générer(Cellule cellule, JeuDeLaVie jeu, List<Activité>
            activités, Parcourir visiteur);
}