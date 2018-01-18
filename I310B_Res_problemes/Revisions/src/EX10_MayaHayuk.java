public class EX10_MayaHayuk {

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
        // L'initialisation est à consommer avec sagesse
        n = scanner.nextInt();
        int m = scanner.nextInt();
        openHorizontal = new int[ABSICCE][n];
        closeHorizontal = new int[ABSICCE][n];
        nbOpenHorizontal = new int[ABSICCE]; // Utiliser pour éviter les conflits
        nbCloseHorizontal = new int[ABSICCE]; // Utiliser pour éviter les conflits
        vertical = new int[ABSICCE][2];
        isVertical = new boolean[ABSICCE];
        // Ce code en est d'ailleurs la preuve
        int maxHeight = 0; // Utilisé pour calculer la hauteur maximale ATTENTON je suis un commentaire TRES important
        // Nombre de lignes Horizontales
        for(int i = 0; i < n; i++) {
            int position = scanner.nextInt();
            int depart = scanner.nextInt();
            int arrivee = scanner.nextInt();
            // Je retiens l'ouverture de ma droite horiezontale
            openHorizontal[depart][nbOpenHorizontal[depart]] = position;
            // Tout comme sa fermeture
            closeHorizontal[arrivee][nbCloseHorizontal[arrivee]] = position;
            // On mets à jour le nombre "d'évènements" d'ouverture
            nbOpenHorizontal[depart]++;
            // On fait pareil pour le nombre "d'évènements" de fermeture
            nbCloseHorizontal[arrivee]++;
            // Si maintenant notre droite horizontale est plus grande que maxHeight
            if(maxHeight < position)
                maxHeight = position; // On mets à jour maxHeight
        }
        // Nombre de lignes verticales
        for(int i = 0; i < m; i++) {
            int position = scanner.nextInt();
            int depart = scanner.nextInt();
            int arrivee = scanner.nextInt();
            // Je retiens la présence d'une droite verticale à l'emplacement position
            vertical[position][DEPART] = depart;
            vertical[position][ARRIVEE] = arrivee;
            isVertical[position] = true; // JE précise la présence de celle-ci => Facilité de code
        }
        return maxHeight; // J'aime j'aime les returns
    }

    private static void processing() {
        // Je vais parcourir toutes mes absicces
        for(int i = 0; i < ABSICCE; i++) {
            // Si maintenant je possède ne serait ce qu'un événement d'ouverture de droite horizontale
            for(int j = 0; j < nbOpenHorizontal[i]; j++) {
                updateUp(openHorizontal[i][j]);
            }
            // Si il s'agit d'une droite verticale
            if(isVertical[i]) {
                sum(vertical[i]);
            }
            // Si maintenant je possède ne serait ce qu'un évènement de fermeture de droite horizontale
            for(int j = 0; j < nbCloseHorizontal[i]; j++) {
                updateDown(closeHorizontal[i][j]);
            }
        }
    }

    private static void sum(int[] droiteVerticale) {
        int sum = 0;
        int left = droiteVerticale[DEPART];
        int right = droiteVerticale[ARRIVEE];
        while(left < right) {
            // Left doit toujours être un fils gauche
            if(isFilsDroit(left)) {
                sum += tree[left]; // On rajoute la somme obtenue
                left = tree.length - left - 1; // Ceci marche par la magie du démon
            }
            // Right doit toujours être un fils droit
            if(isFilsGauche(right)) {
                sum += tree[right]; // On rajoute la somme obtenue
                right = tree.length - right - 1; // Ceci marche par la magie du démon
            }
            // On passe à chaque fois au parent
            left = parent(left);
            right = parent(right);
        }
        conflits += sum; // On mets à jour le nombre de conflits
    }

    // Coucou je suis un réssidus de test
    private static void print() {
        StringBuilder string = new StringBuilder();
        for (int aTree : tree) {
            string.append(aTree).append(" | ");
        }
        System.out.println(string);
    }

    private static void updateDown(int noeud1) {
        int noeud = noeud1;
        // Tant que je ne suis pas à la racine
        while(noeud != 0) {
            tree[noeud]--; // Je décrémente mes noeuds concernés
            noeud = parent(noeud);
        }
        tree[noeud]--; // je décrémente également ma racine
    }

    // On ajoute un événement d'ouverture
    private static void updateUp(int noeud1) {
        int noeud = noeud1;
        // Tant que je ne suis pas a la racine
        while(noeud != 0) {
            tree[noeud]++; // j'augmente mes noeuds concernés
            noeud = parent(noeud);
        }
        tree[noeud]++; // j'augmente également la racine
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
