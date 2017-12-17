package vie;
import java.util.List;
public class ParcourtNormal extends Parcourir {
    // Une cellule devient vivante si :
    // soit elle était vivante et a 2 ou 3 voisins vivants // soit elle était morte et a
    exactement 3
    // voisins vivants.
    // Dans les autres cas la cellule meurt ou reste morte.
    public void parcourtCelluleVivante(Cellule cellule, JeuDeLaVie jeu, List<Activité>
            activités) {
        int n = cellule.nombreDeVoisins(jeu);
        if (n != 2 && n != 3) {
            activités.add(new Meurt(cellule));
        }
    }
    public void parcourtCelluleMorte(Cellule cellule, JeuDeLaVie jeu, List<Activité>
            activités) {
        int n = cellule.nombreDeVoisins(jeu);
        if (n == 3) {
            activités.add(new Vit(cellule));
        }
    }
}