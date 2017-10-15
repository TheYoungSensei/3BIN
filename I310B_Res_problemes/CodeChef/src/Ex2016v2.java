//I301B, 015, Examen S1 2016, E08, S03, Q1, 2017-2018
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex2016v2 {

    static Scanner scanner = new Scanner(System.in);

    static int dpt; // état inital
    static int tgt; // état goal

    static boolean[] rf;

    static int[] f;
    static int szf;

    static boolean[][] mtx;

    static Map<Integer, Integer> parents = new HashMap<>();

    static void init() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        dpt = scanner.nextInt();
        tgt = scanner.nextInt();

        rf = new boolean[n];
        f = new int[n];
        mtx = new boolean[n][n];
        szf = 0;

        int i = 0;
        while (i != m) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            mtx[x][y] = true;
            i++;
        }
    }

    public static void main(String[] args) {
        init();
        rf[dpt] = true;
        addToPile(dpt);
        while(szf != 0) {
            int c = popPile();
            if(c == tgt) {
                System.out.println(createPath(c));
                return;
            }
            rf[c] = true;
            int t = succ(c);
            if(t != -1) {
                addToPile(t);
                parents.put(t, c);
            }
        }
        System.out.println("0");
    }

    private static String createPath(int g) {
        //On récupère l'état initial
        Map<Integer, Integer> map = new HashMap<>();
        int nbIterations = 1;
        while (parents.containsKey(g)) {
            int p = parents.get(g);
            map.put(p, g);
            g = p;
        }
        //Création du chemin
        StringBuilder toReturn = new StringBuilder();
        while(map.containsKey(g)) {
            toReturn.append(g).append(" ");
            g = map.get(g);
            nbIterations += 1;
        }
        toReturn.append(g);
        toReturn.insert(0, nbIterations + " ");
        return toReturn.toString();
    }

    private static int popPile() {
        int toReturn = f[szf - 1];
        szf--;
        return toReturn;
    }

    private static void addToPile(int entier) {
        f[szf] = entier;
        szf++;
    }

    private static int succ(int entier) {
        for(int i = 0; i < mtx[entier].length; i++) {
            if(mtx[entier][i] && !rf[i] ) {
                return i;
            }
        }
        return -1;
    }
}