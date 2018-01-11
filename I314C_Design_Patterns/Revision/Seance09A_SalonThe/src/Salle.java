import java.util.HashMap;
import java.util.Map;

public class Salle {

    private Map<Integer, Table> tables = new HashMap<>();

    private int i;

    public Table getTable(int table) {
        return tables.get(table);
    }

    void ajouterTable(int placesDispo) {
        this.tables.put(i, new Table(i, placesDispo));
        i++;
    }
}
