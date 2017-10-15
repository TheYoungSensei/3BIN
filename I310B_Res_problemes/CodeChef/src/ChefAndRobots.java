import java.util.HashSet;
import java.util.Set;

public class ChefAndRobots {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static class Case {
        int ligne;
        int colonne;

        public Case(int ligne, int colonne) {
            this.ligne = ligne;
            this.colonne = colonne;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Case aCase = (Case) o;

            if (ligne != aCase.ligne) return false;
            return colonne == aCase.colonne;
        }

        @Override
        public int hashCode() {
            int result = ligne;
            result = 31 * result + colonne;
            return result;
        }
    }

    private static int map[][];
    private static int nbMovesRobot2;
    private static int nbMovesRobot1;
    private static int nbColumns;
    private static int nbRows;
    private static Case robot1;
    private static Case robot2;
    private static Set<Case> rfrobot1;
    private static Set<Case> rfrobot2;
    private static Set<Case> frobot1;
    private static Set<Case> frobot2;

    public static void main(String[] args) {
        int testCases = scanner.nextInt();
        for(int i = 0; i < testCases; i++) {
            creation();
            initialisation();
        }
    }

    private static void creation() {
        //N
        nbRows = scanner.nextInt();
        //M
        nbColumns = scanner.nextInt();
        //K1
        nbMovesRobot1 = scanner.nextInt();
        //K2
        nbMovesRobot2 = scanner.nextInt();
        map = new int[nbRows][nbColumns];
        for(int i = 0; i < nbRows; i++) {
            for(int j = 0; j < nbColumns; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        robot1 = new Case(0,0);
        robot2 = new Case(0, nbColumns - 1);
        rfrobot1 = new HashSet<>();
        rfrobot2 = new HashSet<>();
        frobot1 = new HashSet<>();
        frobot2 = new HashSet<>();
    }

    private static void initialisation() {
        //Initialisation Robot 1
        rfrobot1.add(robot1);
        frobot1.add(robot1);
        //Initialisation Robot 2
        rfrobot2.add(robot2);
        frobot2.add(robot2);
        boolean found = false;
        while(found) {

        }
    }


}
