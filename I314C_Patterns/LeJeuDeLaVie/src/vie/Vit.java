package vie;
public class Vit extends Activité {
    public Vit(Cellule cellule) {
        super(cellule);
    }
    public void activer() {
        getCellule().vit();
    }
}