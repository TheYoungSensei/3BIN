import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DigJump {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static boolean visited[];
    private static int[] previous;
    private static ArrayList<Integer>[] digitNodes = new ArrayList[10];
    private static int[] digits;

    public static void main(String[] args) {
        String ligne = init();
        bfs();
        result(ligne);
    }

    private static String init() {
        String ligne = scanner.next();
        for (int i = 0; i < 10; i++) {
            digitNodes[i] = new ArrayList<Integer>();
        }
        digits = new int[ligne.length()];
        visited = new boolean[ligne.length()];
        previous = new int[ligne.length()];
        for (int i = 0; i < ligne.length(); i++) {
            int digit = ligne.charAt(i) - '0';
            digits[i]=digit;
            digitNodes[digit].add(i);
        }
        return ligne;
    }

    private static void result(String ligne) {
        int steps = 0;
        int current = ligne.length() - 1;
        while (current != 0) {
            current = previous[current];
            steps++;
        }
        System.out.println(steps);
    }

    private static void bfs() {
        Queue<Integer> f = new LinkedList<Integer>();
        f.add(0);
        visited[0]=true;
        Integer digit;
        while ((digit = f.poll()) != null) {
            if(digit > 0) {
                if(!visited[digit - 1]) {
                    f.add(digit - 1);
                    visited[digit - 1]=true;
                    previous[digit - 1] = digit;
                }
            }
            if(digit < visited.length - 1) {
                if(!visited[digit + 1]) {
                    f.add(digit + 1);
                    visited[digit + 1]=true;
                    previous[digit + 1] = digit;
                }
            }
            for (int digitSameValue : digitNodes[digits[digit]]) {
                if (!visited[digitSameValue]) {
                    f.add(digitSameValue);
                    visited[digitSameValue]=true;
                    previous[digitSameValue] = digit;
                }
            }
            digitNodes[digits[digit]]=new ArrayList<Integer>();
        }
    }
}