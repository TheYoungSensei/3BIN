import java.util.HashMap;
import java.util.Map;

//Flyweight Factory
class Salle {

    private Map<Integer, Table> tables = new HashMap<>(); //Une Table = un Flyweight
    //Un UnsharedFlyweight serait un parfum / une table non connus de nos systèmes
    private int i;

    Table getTable(int table) {
        return tables.get(table);
    }

    void ajouterTable(int placesDispo) {
        this.tables.put(i, new Table(i, placesDispo));
        i++;
    }
}
