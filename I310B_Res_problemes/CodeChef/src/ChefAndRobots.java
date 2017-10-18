import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ChefAndRobots {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static boolean found;

    private static class Case {
        int ligne;
        int colonne;

        Case(int ligne, int colonne) {
            this.ligne = ligne;
            this.colonne = colonne;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Case aCase = (Case) o;

            return ligne == aCase.ligne && colonne == aCase.colonne;
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
    private static Case robot1;
    private static Case robot2;
    private static Set<Case> rfrobot1;
    private static Set<Case> rfrobot2;
    private static Deque<Case> frobot1;
    private static Deque<Case> frobot2;
    private static int nbIte;


    public static void main(String[] args) {
        int testCases = scanner.nextInt();
        for(int i = 0; i < testCases; i++) {
            creation();
            initialisation();
        }
    }

    private static void creation() {
        //N
        int nbRows = scanner.nextInt();
        //M
        int nbColumns = scanner.nextInt();
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
        frobot1 = new ArrayDeque<>();
        frobot2 = new ArrayDeque<>();
    }

    private static void initialisation() {
        //Initialisation Robot 1
        rfrobot1.add(robot1);
        frobot1.add(robot1);
        //Initialisation Robot 2
        rfrobot2.add(robot2);
        frobot2.add(robot2);
        found = false;
        nbIte = 0;
        while(!frobot1.isEmpty() && !frobot2.isEmpty()) {
            Case cRobot1 = frobot1.pop();
            Case cRobot2 = frobot2.pop();
            succ1(cRobot1);
            succ2(cRobot2);
        }
        if(found) {
            System.out.println(nbIte);
        } else {
            System.out.println(-1);
        }
    }

    private static void succ1(Case c) {
        chain1(0, c);
    }

    private static void chain1(int deplacements, Case c) {
        if(!rfrobot1.contains(c) && map[c.ligne][c.colonne] != 1) {
            rfrobot1.add(c);
            frobot1.add(c);
        }
        if(deplacements < nbMovesRobot1) {
            //Haut
            if(c.ligne < map.length - 1) {
                chain1(deplacements + 1, new Case(c.ligne + 1, c.colonne));
            }
            //Bas
            if(c.ligne != 0) {
                chain1(deplacements + 1, new Case(c.ligne - 1, c.colonne));
            }
            //Gauche
            if(c.colonne != 0) {
                chain1(deplacements + 1, new Case(c.ligne, c.colonne - 1));
            }
            //Droite
            if(c.colonne < map[c.ligne].length - 1) {
                chain1(deplacements + 1, new Case(c.ligne, c.colonne + 1));
            }
        }
    }

    private static void succ2(Case c) {
        if(!found)
            nbIte += 1;
        chain2(0, c);
    }

    private static void chain2(int deplacements, Case c) {
        if(!rfrobot2.contains(c) && map[c.ligne][c.colonne] != 1) {
            rfrobot2.add(c);
            frobot2.add(c);
        }
        if(rfrobot1.contains(c)) {
            found = true;
        }
        if(deplacements < nbMovesRobot2) {
            //Haut
            if(c.ligne < map.length - 1) {
                chain2(deplacements + 1, new Case(c.ligne + 1, c.colonne));
            }
            //Bas
            if(c.ligne != 0) {
                chain2(deplacements + 1, new Case(c.ligne - 1, c.colonne));
            }
            //Gauche
            if(c.colonne != 0) {
                chain2(deplacements + 1, new Case(c.ligne, c.colonne - 1));
            }
            //Droite
            if(c.colonne < map[c.ligne].length - 1) {
                chain2(deplacements + 1, new Case(c.ligne, c.colonne + 1));
            }
        }
    }



}