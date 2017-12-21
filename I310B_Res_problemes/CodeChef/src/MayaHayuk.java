public class MayaHayuk {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static final int ARRIVEE = 1;
    private static final int DEPART = 0;
    private static final int ABSICCE = 65000;

    private static int tree[];
    private static int n;

    private static int openHorizontal[][]; //[i] = tableau de hauteur [i][hauteur droites horizontales] (i = absicce)
    private static int closeHorizontal[][]; //[i] = tableau de hauteur [i][hauteur droites horizontales] (i = absicce)

    private static int nbOpenHorizontal[]; //[i] = nbEvents (i = abscisse)
    private static int nbCloseHorizontal[]; //[i] = nbEvents (i = absicce)

    private static int vertical[][]; //[i] = absicce [DEPART] = debut [ARRIVEE] = fin
    private static boolean isVertical[]; //[i] == true si ligne verticale

    private static int conflits;

    public static void main(String[] args) {
        int maxHeight = initialisation();
        initateTree(maxHeight);
        processing();
        System.out.println(conflits);
    }

    private static int initialisation() {
        n = scanner.nextInt();
        int m = scanner.nextInt();
        openHorizontal = new int[ABSICCE][n];
        closeHorizontal = new int[ABSICCE][n];
        nbOpenHorizontal = new int[ABSICCE];
        nbCloseHorizontal = new int[ABSICCE];
        vertical = new int[ABSICCE][2];
        isVertical = new boolean[ABSICCE];
        int maxHeight = 0;
        for(int i = 0; i < n; i++) {
            int position = scanner.nextInt();
            int depart = scanner.nextInt();
            int arrivee = scanner.nextInt();
            openHorizontal[depart][nbOpenHorizontal[depart]] = position;
            closeHorizontal[arrivee][nbCloseHorizontal[arrivee]] = position;
            nbOpenHorizontal[depart]++;
            nbCloseHorizontal[arrivee]++;
            if(maxHeight < position)
                maxHeight = position;
        }
        for(int i = 0; i < m; i++) {
            int position = scanner.nextInt();
            int depart = scanner.nextInt();
            int arrivee = scanner.nextInt();
            vertical[position][DEPART] = depart;
            vertical[position][ARRIVEE] = arrivee;
            isVertical[position] = true;
        }
        return maxHeight;
    }

    private static void processing() {
        for(int i = 0; i < ABSICCE; i++) {
            for(int j = 0; j < nbOpenHorizontal[i]; j++) {
                updateUp(openHorizontal[i][j]);
            }
            if(isVertical[i]) {
                sum(vertical[i]);
            }
            for(int j = 0; j < nbCloseHorizontal[i]; j++) {
                updateDown(closeHorizontal[i][j]);
            }
        }
    }

    private static void sum(int[] ints) {
        print();
        int sum = 0;
        int left = ints[DEPART];
        int right = ints[ARRIVEE];
        while(left < right) {
            if(isFilsDroit(left)) {
                sum += tree[left];
                left = tree.length - left - 1;
            }
            if(isFilsGauche(right)) {
                sum += tree[right];
                right = tree.length - right - 1;
            }
            left = parent(left);
            right = parent(right);
        }
        conflits += sum;
    }

    private static void print() {
        StringBuilder string = new StringBuilder();
        for (int aTree : tree) {
            string.append(aTree).append(" | ");
        }
        System.out.println(string);
    }

    private static void updateDown(int noeud1) {
        int noeud = noeud1;
        while(noeud != 0) {
            tree[noeud]--;
            noeud = parent(noeud);
        }
        tree[noeud]--;
    }

    private static void updateUp(int noeud1) {
        int noeud = noeud1;
        while(noeud != 0) {
            tree[noeud]++;
            noeud = parent(noeud);
        }
        tree[noeud]++;
    }

    private static void initateTree(int maxHeight) {
        int temp = 1;
        while(temp < maxHeight) {
            temp *= 2;
        }
        tree = new int[(2 *temp) - 1];
    }

    private static boolean isFilsDroit(int i) {
        return i % 2 == 0;
    }

    private static boolean isFilsGauche(int i) {
        return i % 2 != 0;
    }

    private static int parent(int i) {
        return (i - 1)/2;
    }
}
