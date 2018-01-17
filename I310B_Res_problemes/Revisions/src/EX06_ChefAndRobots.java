import java.util.ArrayDeque;
import java.util.Scanner;

class EX06_ChefAndRobots {

    private static Scanner sc = new Scanner(System.in);

    private static int lignes;
    private static int colonnes;
    private static int deplRobot1;
    private static int deplRobot2;

    private static int[][] map;

    private static boolean[][] rfRobot1;
    private static ArrayDeque<int[]> fRobot1;

    private static boolean rfRobot2[][];
    private static ArrayDeque<int[]> fRobot2;

    public static void main(String[] args) {
        int nbtestsCases = sc.nextInt();

        for (int i = 0; i < nbtestsCases; i++) {
            initialisation();
            int[][] coutsRobot1 = new int[lignes][colonnes];
            int[][] coutsRobot2 = new int[lignes][colonnes];
            // On effectue le parcours pour le robot1
            bfs(new int[]{0, 0}, deplRobot1, coutsRobot1);
            // On effectue le parcours pour le robot2
            bfs(new int[]{0, colonnes - 1}, deplRobot2, coutsRobot2);
            // On part du principe que l'on trouvera mieux que Integer.MAX_VALUE
            int sum = Integer.MAX_VALUE;
            for (int j = 0; j < lignes; j++) {
                for (int k = 0; k < colonnes; k++) {
                    // Si maintenant la case a été visitée par les deux robots
                    if (coutsRobot1[j][k] != 0 && coutsRobot2[j][k] != 0) {
                        // On prend le coûts le plus haut des deux
                        int temp = Math.max(coutsRobot1[j][k], coutsRobot2[j][k]);
                        // On prend soit sum soit temp dans le cas où un meilleur chemin est trouvé
                        sum = Math.min(sum, temp);
                    }
                }
            }
            if(sum == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(sum-1);
            }
        }
    }

    private static void bfs(int[] position, int nbDepl, int[][] couts) {
        rfRobot1 = new boolean[lignes][colonnes];
        fRobot1 = new ArrayDeque<>();
        // Cas initial
        fRobot1.addLast(position); // Ajout au sein de f

        rfRobot1[position[0]][position[1]] = true; // Ajout au sein de rf

        couts[position[0]][position[1]] = 1; // Initialisation du cout à 1

        // Tant que j'ai des éléments à parcourir
        while (!fRobot1.isEmpty()) {
            // On choisis une case à traiter
            int ligne = fRobot1.getFirst()[0];
            int colonne = fRobot1.getFirst()[1];
            // On la supprime de f
            fRobot1.removeFirst();

            // Initialisation
            rfRobot2 = new boolean[lignes][colonnes];
            fRobot2 = new ArrayDeque<>();

            // On ajoute la case actuellement en vérification
            fRobot2.addLast(new int[] {ligne, colonne});
            rfRobot2[ligne][colonne] = true;

            // Tant que l'autre robot peut encore aller sur des cases
            while (!fRobot2.isEmpty()) {
                int a = fRobot2.getFirst()[0];
                int b = fRobot2.getFirst()[1];
                fRobot2.removeFirst();
                if(a < lignes - 1) // Si je peux aller en haut
                    iterate(a + 1, b, ligne, colonne, couts, nbDepl);
                if(a != 0) // Si je peux aller en bas
                    iterate(a - 1, b, ligne, colonne, couts, nbDepl);
                if(b < colonnes - 1) // Si je peux aller à droite
                    iterate(a, b + 1, ligne, colonne, couts, nbDepl);
                if(b != 0) // Si je peux aller à gauche
                    iterate(a, b - 1, ligne, colonne, couts, nbDepl);
            }
        }
    }

    private static void iterate(int ligne2, int colonne2, int ligne1, int colonne1, int[][] couts, int nbDepl) {
        // Si on est déjà passé par ici return
        if(rfRobot2[ligne2][colonne2])
            return;

        // Afin de restreindre mes deplacements possibles
        if (Math.abs(ligne2 - ligne1) + Math.abs(colonne2 - colonne1) > nbDepl) {
            return;
        }

        // Si mtn je peux m'arrêter sur la case et que le robot ne l'a pas encore visitée
        if (map[ligne2][colonne2] == 0 && !rfRobot1[ligne2][colonne2]) {
            // Je la rajoute au robot1
            fRobot1.addLast(new int[] {ligne2, colonne2});
            rfRobot1[ligne2][colonne2] = true;
            couts[ligne2][colonne2] = couts[ligne1][colonne1] + 1;
        }
        // Dans tous les cas je la rajoute pour mon autre robot
        fRobot2.addLast(new int[] {ligne2, colonne2});
        rfRobot2[ligne2][colonne2] = true;
    }

    private static void initialisation() {
        lignes = sc.nextInt();
        colonnes = sc.nextInt();
        deplRobot1 = sc.nextInt();
        deplRobot2 = sc.nextInt();
        map = new int[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                // 0 => mon robot peut aller dessus
                // 1 => mon robot ne peut pas aller dessus
                map[i][j] = sc.nextInt();
            }
        }
    }
}