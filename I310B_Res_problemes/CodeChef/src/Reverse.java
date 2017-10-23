import java.util.*;

public class Reverse {


    static java.util.Scanner scanner = new java.util.Scanner(System.in);

    static int t;
    static int n;
    static int m;
    static int q;

    static int[][] matrice;
    static int[] cost;
    static int[] parent;
    static int[][] enfants;

    static int tailleMesEnfans[];

    static TreeSet<Integer> f = new TreeSet<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer i, Integer j) {
            if(i == j || cost[i] == cost[j]) {
                return i - j;
            } else {
                return cost[i] - cost[j];
            }
        }
    });

    public static void main(String[] args) {
        t = scanner.nextInt();
        for(int i = 0; i < t; i++) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            q = scanner.nextInt();
            matrice = new int[n][n]; //valeur = cout
            cost = new int[n];
            parent = new int[n];
            enfants = new int[n][0];
            tailleMesEnfans = new int[n];
            for(int j = 0; j < m; j++) {
                int a = scanner.nextInt() - 1;
                int b = scanner.nextInt() - 1;
                int w = scanner.nextInt();
                addNewEntry(a, b, w);
            }
            print();
        }
    }

    private static void addNewEntry(int a, int b, int w) {
        if(tailleMesEnfans[a] == enfants[a].length){
            int[] temp = new int[enfants[a].length];
            System.arraycopy(enfants[a], 0, temp, 0, enfants[a].length);
            enfants[a] = new int[(tailleMesEnfans[a] * 2) + 1];
            System.arraycopy(temp, 0, enfants[a], 0, temp.length);
        }
        enfants[a][tailleMesEnfans[a]] = b;
        tailleMesEnfans[a]++;
        matrice[a][b] = w;
    }

    private static void print() {
        for(int i = 0; i < enfants.length; i++) {
            StringBuilder s = new StringBuilder();
            s.append(i).append(" : ");
            for(int j = 0; j < enfants[i].length; j++) {
                s.append(enfants[i][j]);
            }
            System.out.println(s);
        }
    }

}