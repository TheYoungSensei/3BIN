package vie;
public class Meurt extends Activité {
    public Meurt(Cellule cellule) {
        super(cellule);
    }
    public void activer() {
        getCellule().meurt();
    }
}