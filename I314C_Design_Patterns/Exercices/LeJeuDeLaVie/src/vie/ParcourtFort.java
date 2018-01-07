package vie;
import java.util.List;
public class ParcourtFort extends Parcourir {
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
        if (n == 3 || n == 2) {
            activités.add(new Vit(cellule));
        }
    }
}