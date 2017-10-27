/*
2
4 4 1 1
0 1 1 0
0 1 1 0
0 1 1 0
0 0 0 0
4 4 1 1
0 1 1 0
0 1 1 0
0 1 1 0
1 0 0 1
 */

import java.util.*;

public class ChefAndRobots {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static Case found;

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
    private static int mapRobot1[][][];
    private static int mapRobot2[][][];
    private static int nbMovesRobot2;
    private static int nbMovesRobot1;
    private static Case robot1;
    private static Case robot2;
    private static Set<Case> rfrobot1;
    private static Set<Case> rfrobot2;
    private static Case frobot1[];
    private static int szfrobot1;
    private static int szfrobot2;
    private static int teteRobot1;
    private static int teteRobot2;
    private static Case frobot2[];
    private static final int ROBOT1 = 1;
    private static final int ROBOT2 = 2;

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
        mapRobot1 = new int[nbRows][nbColumns][1];
        mapRobot2 = new int[nbRows][nbColumns][1];
        for(int i = 0; i < nbRows; i++) {
            for(int j = 0; j < nbColumns; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        robot1 = new Case(0,0);
        robot2 = new Case(0, nbColumns - 1);
        rfrobot1 = new HashSet<>();
        rfrobot2 = new HashSet<>();
        frobot1 = new Case[nbRows * nbColumns];
        szfrobot1 = 0;
        teteRobot1 = 0;
        teteRobot2 = 0;
        frobot2 = new Case[nbRows * nbColumns];
        szfrobot2 = 0;
    }

    private static void addLast(Case c, int robot) {
        if(robot == 1) {
            frobot1[teteRobot1] = c;
            szfrobot1++;
        } else {
            frobot2[teteRobot2] = c;
            szfrobot2++;
        }
    }

    private static Case removeFirst(int robot) {
        Case c = null;
        if(robot == 1) {
            c = frobot1[teteRobot1];
            teteRobot1++;
            szfrobot1--;
        } else {
            c = frobot2[teteRobot2];
            teteRobot2++;
            szfrobot2--;
        }
        return c;
    }

    private static void initialisation() {
        //Initialisation Robot 1
        rfrobot1.add(robot1);
        addLast(robot1, ROBOT1);
        //Initialisation Robot 2
        rfrobot2.add(robot2);
        addLast(robot2, ROBOT2);
        found = null;
        while(szfrobot1 != 0 && szfrobot2 != 0 && found == null) {
            Case cRobot1 = removeFirst(ROBOT1);
            Case cRobot2 = removeFirst(ROBOT2);
            succ(cRobot1, ROBOT1);
            succ(cRobot2, ROBOT2);
        }
        if(found != null) {
            if(mapRobot1[found.ligne][found.colonne][0] < mapRobot2[found.ligne][found.colonne][0]) {
                System.out.println(mapRobot2[found.ligne][found.colonne][0]);
            } else {
                System.out.println(mapRobot1[found.ligne][found.colonne][0]);
            }
        } else {
            System.out.println(-1);
        }
    }

    private static void succ(Case c, int robot) {
        chain(0, c, c, robot);
    }

    private static void chain(int deplacements, Case c, Case parent, int robot) {
        if(robot == ROBOT1) {
            if(!rfrobot1.contains(c) && map[c.ligne][c.colonne] != 1) {
                rfrobot1.add(c);
                addLast(c, ROBOT1);
                if(!c.equals(parent)) {
                    if(mapRobot1[parent.ligne][parent.colonne][0] + 1 <  mapRobot1[c.ligne][c.colonne][0] || mapRobot1[c.ligne][c.colonne][0] == 0) {
                        mapRobot1[c.ligne][c.colonne][0] = mapRobot1[parent.ligne][parent.colonne][0] + 1;
                    }
                }
            }
            if(rfrobot2.contains(c)) {
                found = c;
            }
            if(deplacements >= nbMovesRobot1) {
                return;
            }
        } else {
            if(!rfrobot2.contains(c) && map[c.ligne][c.colonne] != 1) {
                rfrobot2.add(c);
                addLast(c, ROBOT2);
            }
            if(!c.equals(parent)) {
                if(mapRobot2[parent.ligne][parent.colonne][0] + 1 <  mapRobot2[c.ligne][c.colonne][0] || mapRobot2[c.ligne][c.colonne][0] == 0) {
                    mapRobot2[c.ligne][c.colonne][0] = mapRobot2[parent.ligne][parent.colonne][0] + 1;
                }
            }
            if(rfrobot1.contains(c)) {
                found = c;
            }
            if(deplacements >= nbMovesRobot2) {
                return;
            }
        }
        //Haut
        if(c.ligne < map.length - 1) {
            chain(deplacements + 1, new Case(c.ligne + 1, c.colonne), parent, robot);
        }
        //Bas
        if(c.ligne != 0) {
            chain(deplacements + 1, new Case(c.ligne - 1, c.colonne), parent, robot);
        }
        //Gauche
        if(c.colonne != 0) {
            chain(deplacements + 1, new Case(c.ligne, c.colonne - 1), parent, robot);
        }
        //Droite
        if(c.colonne < map[c.ligne].length - 1) {
            chain(deplacements + 1, new Case(c.ligne, c.colonne + 1), parent, robot);
        }
    }
}

