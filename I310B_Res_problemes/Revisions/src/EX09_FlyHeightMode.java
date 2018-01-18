import java.util.Arrays;

class EX09_FlyHeightMode {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static int HEIGHT = 0;
    private static int COLOR = 1;

    public static void main(String[] args) {
        int n = scanner.nextInt();
        // points[0] = height
        // points[1] = color
        int[][] points = new int[2 * n - 2][2]; // - 2 pour la hauteur de départ pas prise en compte car il faut une relation
        int precHeight = scanner.nextInt();
        int RED = 1;
        initialisation(points, precHeight, RED);
        sorting(points);
        System.out.println(finishHim(points, RED));
    }

    private static int finishHim(int[][] points, int RED) {
        // Initialisation
        int cpt = 0;
        int max = 1; // Je ne sais pas si c'est nécéssaire ? => Flemme
        for (int point[] : points) { // Pour chaque point de mon tableau
            if (point[COLOR] == RED) { // Si maintenant le point est justement rouge
                cpt++; // J'incrémente le compteur
            } else { // Ou sinon
                cpt--;  // Je décrémente le compteur
            }
            if (cpt > max) // Si maintenant le compteur est plus grand que max
                max = cpt; // on le mets à jour
        }
        return max;
    }

    private static void sorting(int[][] points) {
        // On le trie de façon à avoir les mêmes hauteurs regroupées et dans le cas d'une même hauteur, on aimerait avoir les verts devant
        Arrays.sort(points, (point1, point2) -> {
            if (point1[HEIGHT] == point2[HEIGHT]) {
                return point1[COLOR] - point2[COLOR];
            } else {
                return point1[HEIGHT] - point2[HEIGHT];
            }
        });
    }

    private static void initialisation(int[][] points, int precHeight, int RED) {
        // Pour chaque point que l'avion croisera
        for (int i = 0; i < points.length; i = i + 2) {
            // Je récupère le point en question
            int currentheight = scanner.nextInt();
            int GREEN = 0;
            // Si mon avion monte
            if (precHeight < currentheight) {
                // Hauteur précédente -> Rouge
                points[i][HEIGHT] = precHeight;
                points[i][COLOR] = RED;
                // Hauteur actuelle -> Verte
                points[i + 1][HEIGHT] = currentheight;
                points[i + 1][COLOR] = GREEN;
            } else { // Si l'avion descend
                // Hauteur précédente -> Verte
                points[i][HEIGHT] = precHeight;
                points[i][COLOR] = GREEN;
                // Hauteur actuelle -> Rouge
                points[i + 1][HEIGHT] = currentheight;
                points[i + 1][COLOR] = RED;
            }
            // Mise à jour de la hauteur précédente
            precHeight = currentheight;
        }
    }
}