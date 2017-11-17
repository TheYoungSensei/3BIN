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

import java.util.ArrayDeque;
import java.util.Arrays;

public class ChefAndRobots {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int n;
    private static int m;
    private static int k[] = new int[2];

    private static int couts[][][];
    private static int map[][];
    private static boolean rf[][][];

    private static int ROBOT1 = 0;
    private static int ROBOT2 = 1;
    private static int LIGNE = 0;
    private static int COLONNE = 1;

    public static void main(String[] args) {
        int nbCases = scanner.nextInt();
        for(int i = 0; i < nbCases; i++) {
            init();
            int depart[] = new int[2];
            depart[LIGNE] = 0;
            depart[COLONNE] = 0;
            doIt(depart, ROBOT1);
            depart[LIGNE] = 0;
            depart[COLONNE] = m - 1;
            doIt(depart, ROBOT2);
            if(minDeplacements != Integer.MAX_VALUE) {
                System.out.println(minDeplacements);
            } else {
                System.out.println(-1);
            }
        }
    }

    private static void init() {
        n = scanner.nextInt();
        m = scanner.nextInt();
        k[ROBOT1] = scanner.nextInt();
        k[ROBOT2] = scanner.nextInt();
        couts = new int[n][m][2];
        rf = new boolean[n][m][2];
        map = new int[n][m];
        minDeplacements = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
                couts[i][j][ROBOT1] = Integer.MAX_VALUE;
                couts[i][j][ROBOT2] = Integer.MAX_VALUE;
            }
        }
    }

    private static void doIt(int[] depart, int robot) {
        ArrayDeque<int[]> pile = new ArrayDeque<>();
        pile.addLast(depart);
        couts[depart[LIGNE]][depart[COLONNE]][robot] = 0;
        rf[depart[LIGNE]][depart[COLONNE]][robot] = true;
        while(!pile.isEmpty()) {
            int c[] = pile.removeFirst();
            deplacement(0, c, c, robot, pile);
        }

    }

    private static int intermediateC[] = new int[2];
    private static int minDeplacements;

    private static void deplacement(int nbDeplacements, int[] c, int[] parent, int robot, ArrayDeque<int[]> pile) {
        if(map[c[LIGNE]][c[COLONNE]] != 1 && !rf[c[LIGNE]][c[COLONNE]][robot]) {
            rf[c[LIGNE]][c[COLONNE]][robot] = true;
            couts[c[LIGNE]][c[COLONNE]][robot] = couts[parent[LIGNE]][parent[COLONNE]][robot] + 1;
            pile.add(Arrays.copyOf(c, 2));
            if(rf[c[LIGNE]][c[COLONNE]][ROBOT1]
                    && rf[c[LIGNE]][c[COLONNE]][ROBOT2]
                    && (Math.max(couts[c[LIGNE]][c[COLONNE]][ROBOT1], couts[c[LIGNE]][c[COLONNE]][ROBOT2]) < minDeplacements)) {
                minDeplacements = Math.max(couts[c[LIGNE]][c[COLONNE]][ROBOT1], couts[c[LIGNE]][c[COLONNE]][ROBOT2]);
            }
        }
        if(c[LIGNE] != 0 && nbDeplacements < k[robot]) {
            intermediateC[COLONNE] = c[COLONNE];
            intermediateC[LIGNE] = c[LIGNE] - 1;
            deplacement(nbDeplacements +1, intermediateC, parent, robot, pile);
        }
        if(c[COLONNE] != 0 && nbDeplacements < k[robot]) {
            intermediateC[LIGNE] = c[LIGNE];
            intermediateC[COLONNE] = c[COLONNE] - 1;
            deplacement(nbDeplacements + 1, intermediateC, parent, robot, pile);
        }
        if(c[LIGNE] < map.length - 1 && nbDeplacements < k[robot]) {
            intermediateC[COLONNE] = c[COLONNE];
            intermediateC[LIGNE] = c[LIGNE] + 1;
            deplacement(nbDeplacements + 1, intermediateC, parent, robot, pile);
        }
        if(c[COLONNE] < map[LIGNE].length - 1 && nbDeplacements < k[robot]) {
            intermediateC[LIGNE] = c[LIGNE];
            intermediateC[COLONNE] = c[COLONNE] + 1;
            deplacement(nbDeplacements + 1, intermediateC, parent, robot, pile);
        }
    }
}
