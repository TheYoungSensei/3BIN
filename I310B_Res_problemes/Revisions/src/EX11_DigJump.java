import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class EX11_DigJump {

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
        // On récupère tous les chiffres (Tous nn un village d'irréductibles gaulois résiste encore et toujours à l'envahisseur)
        String ligne = scanner.next();
        // Je crée une liste pour chaque chiffre
        for (int i = 0; i < 10; i++) {
            digitNodes[i] = new ArrayList<Integer>();
        }
        // Initialisation
        digits = new int[ligne.length()];
        visited = new boolean[ligne.length()];
        previous = new int[ligne.length()];
        // Pour chaque chiffre
        for (int i = 0; i < ligne.length(); i++) {
            // Je récupère le chiffre
            int digit = ligne.charAt(i) - '0';
            // Je l'ajoute à mon tableau de digit
            digits[i]=digit;
            // Je l'ajoute à la liste correspondant à son chiffre
            digitNodes[digit].add(i);
        }
        return ligne;
    }

    private static void result(String ligne) {
        int steps = 0;
        // On part du dernier élément
        int current = ligne.length() - 1;
        while (current != 0) {
            // On remonte la chaine via le previous
            current = previous[current];
            steps++; // On compte le nombre de steps
        }
        System.out.println(steps); // On affiche ce nombre
    }

    private static void bfs() {
        // Initialisation
        Queue<Integer> f = new LinkedList<Integer>();
        // Cas initial
        f.add(0);
        visited[0]=true;
        Integer digit;
        // Tant que j'ai un digit à traiter
        while ((digit = f.poll()) != null) {
            // Si maintenant mon digit en question n'est pas à la position zéro
            if(digit > 0) {
                // Je vais à gauche uniquement si je ne l'ai pas déjà visité
                if(!visited[digit - 1]) {
                    f.add(digit - 1);
                    visited[digit - 1]=true;
                    // Je retiens d'où je viens
                    previous[digit - 1] = digit;
                }
            }
            // Si maintenant mon digit n'est pas le dernier de la liste
            if(digit < visited.length - 1) {
                // Je vais à droite
                if(!visited[digit + 1]) {
                    f.add(digit + 1);
                    visited[digit + 1]=true;
                    previous[digit + 1] = digit;
                }
            }
            // Pour chaque digit ayant la même valeur que mon digit
            for (int digitSameValue : digitNodes[digits[digit]]) {
                // Si je ne l'ai pas encore visité
                if (!visited[digitSameValue]) {
                    // Je l'ajoute aux éléments à visiter
                    f.add(digitSameValue);
                    visited[digitSameValue]=true;
                    previous[digitSameValue] = digit;
                }
            }
            // Une fois qu'ils ont été traités : remettre à zéro pour éviter le O de n :)
            digitNodes[digits[digit]]=new ArrayList<Integer>();
        }
    }
}