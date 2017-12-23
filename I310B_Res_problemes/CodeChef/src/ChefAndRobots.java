public class ChefAndRobots {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static int n;

    private static int m;

    private static int k1;
    private static int k2;
    private static int k;

    private static int map[][];

    private static int couts1[][];
    private static int couts2[][];
    private static int couts[][];

    private static int rf1[][];
    private static int rf2[][];
    private static int rf[][];

    private static int[][] f1;
    private static int h1;
    private static int t1;

    private static int[][] f2;
    private static int h2;
    private static int t2;

    public static void main(String[] args) {
        int nbCases = scanner.nextInt();
        for (int i = 0; i < nbCases; i++) {
            init();

            k = k1;
            couts = couts1;
            rf = rf1;
            doIt(0, 0);

            k = k2;
            couts = couts2;
            rf = rf2;
            doIt(0, m - 1);

            if (minDeplacements != Integer.MAX_VALUE) {
                System.out.println(minDeplacements);
            } else {
                System.out.println(-1);
            }
        }
    }

    private static void init() {
        n = scanner.nextInt();
        m = scanner.nextInt();

        k1 = scanner.nextInt();
        k2 = scanner.nextInt();

        couts1 = new int[n][m];
        couts2 = new int[n][m];

        rf1 = new int[n][m];
        rf2 = new int[n][m];

        f1 = new int[n * m][2];
        f2 = new int[n * m][3];

        map = new int[n][m];

        minDeplacements = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
                couts1[i][j] = Integer.MAX_VALUE;
                couts2[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static void doIt(int sx, int sy) {
        h1 = 0;
        t1 = 0;
        visiteState(sx, sy, 0, 1);

        int tour = 2;
        while (h1 != t1) {
            int x = f1[h1][0];
            int y = f1[h1][1];
            h1++;

            f2[0][0] = x;
            f2[0][1] = y;
            f2[0][2] = k;
            h2 = 0;
            t2 = 1;
            deplacement(couts[x][y] + 1, tour);
            tour++;
        }
    }

    private static int minDeplacements;

    private static void deplacement(int cost, int tour) {
        while (h2 != t2) {
            int x = f2[h2][0];
            int y = f2[h2][1];
            int k = f2[h2][2];
            h2++;

            if (x != 0 && k != 0 && rf[x - 1][y] != tour) {
                visiteState(x - 1, y, cost, tour);
                f2[t2][0] = x - 1;
                f2[t2][1] = y;
                f2[t2][2] = k - 1;
                t2++;
            }

            if (y != 0 && k != 0 && rf[x][y - 1] != tour) {
                visiteState(x, y - 1, cost, tour);
                f2[t2][0] = x;
                f2[t2][1] = y - 1;
                f2[t2][2] = k - 1;
                t2++;
            }

            if (x != n - 1 && k != 0 && rf[x + 1][y] != tour) {
                visiteState(x + 1, y, cost, tour);
                f2[t2][0] = x + 1;
                f2[t2][1] = y;
                f2[t2][2] = k - 1;
                t2++;
            }

            if (y != m - 1 && k != 0 && rf[x][y + 1] != tour) {
                visiteState(x, y + 1, cost, tour);
                f2[t2][0] = x;
                f2[t2][1] = y + 1;
                f2[t2][2] = k - 1;
                t2++;
            }
        }
    }

    private static void visiteState(int x, int y, int cost, int tour) {
        if (map[x][y] != 1 && rf[x][y] == 0) {
            couts[x][y] = cost;
            f1[t1][0] = x;
            f1[t1][1] = y;
            t1++;
        }

        rf[x][y] = tour;
        int max = Math.max(couts1[x][y], couts2[x][y]);
        if (map[x][y] != 1 && rf1[x][y] != 0 && rf2[x][y] != 0 && max < minDeplacements) {
            minDeplacements = max;
        }
    }
} 