package vie;
public abstract class Activité {
    private Cellule cellule;
    public Activité(Cellule cellule) {
        this.cellule = cellule;
    }
    // Envoyer la requête sauvée (vit ou meurt) à la cellule. public abstract void
    activer();
    public Cellule getCellule() {
        return cellule;
    }
}