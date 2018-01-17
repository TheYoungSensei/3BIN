import java.util.Scanner;

class EX03_FloydPaloAlto {

    private static final int LIGNE = 0;
    private static final int COLONNE = 1;

    private static Scanner scanner = new Scanner(System.in);

    //croquis courant
    private static char[][] croquis;

    // Numéro de ligne de Floyd
    private static int ligneFloyd;
    // Numéro de colonne de Floyd
    private static int colonneFloyd;
    // Numéro de ligne de Tarjan
    private static int ligneTarjan;
    // Numéro de colonne de Tarjan
    private static int colonneTarjan;

    // Table de présence des états visité
    // rf est un tableau rectangulaire de nbLignes lignes et nbColonnes colonnes.
    // rf[i][j] == true <==> la case dont le numéro de ligne vaut i et
    // dont le numéro de colonne vaut j a été visité
    private static boolean[][] rf;

    // Coordonnées des cases appartenant à frontière.
    // f a une taille logique de nbLignes x nbColonnes;
    // f[0] contient des numéros de lignes.
    // f[1] contient des numéros de colonnes.
    private static int[][] f;

    // Taille logique de f
    // 0 <= f <= nbLignes x nbColonnes;
    private static int tailleLogique;

    private static int nb;

    public static void main(String[] args) {
        int nbCroquis = scanner.nextInt();
        int i = 0;
        while (i != nbCroquis) {
            readInputs();
            analyzeMap();
            i++;
        }
    }

    // addPile fait main => Pourquoi se simplifier la vie
    private static void addPile(int ligne, int colonne) {
        nb++;
        rf[ligne][colonne] = true;
        f[LIGNE][tailleLogique] = ligne;
        f[COLONNE][tailleLogique] = colonne;
        tailleLogique++;
    }

    // popPile fait main => :'(
    private static int[][] popPile() {
        int elem[][] = new int[2][1];
        elem[LIGNE][0] = f[LIGNE][tailleLogique - 1];
        elem[COLONNE][0] = f[1][tailleLogique - 1];
        tailleLogique--;
        return elem;
    }

    private static void analyzeMap() {
        nb = 0;
        // Ajout de la position de depart de Floyd
        rf[ligneFloyd][colonneFloyd] = true;
        addPile(ligneFloyd, colonneFloyd);
        // À la recherche de Tarjan
        boolean trouve = false;
        // Tarjan était juste sous nos pieds
        if(ligneFloyd == ligneTarjan && colonneFloyd == colonneTarjan) {
            System.out.println("0 true");
        }
        // Tant qu'il y a de l'espoir (ou des coordonnees) on continue de chercher
        while(tailleLogique != 0) {
            int coordonnees[][] = popPile();
            succ(coordonnees[LIGNE][0], coordonnees[COLONNE][0]); // Où pourrais je aller par la suite
            if (croquis[coordonnees[LIGNE][0]][coordonnees[COLONNE][0]] == 'T') { // Voici enfin Tarjan
                trouve = true;
            }
        }
        if (trouve) {
            System.out.println(nb+" true"); // Ils vécurent heureux et eurent beaucoup d'enfant
        } else {
            System.out.println(nb+" false"); // La légende raconte que Floyd erre toujours ... :'(
        }
    }

    private static void succ(int ligne, int colonne) {
        if (ligne < croquis.length - 1) { // Puis je aller en haut ?
            check(ligne + 1, colonne);
        }
        if (ligne != 0) { // Puis je aller en bas ?
            check(ligne - 1, colonne);
        }
        if (colonne < croquis[ligne].length - 1) { // Puis je aller à droite ?
            check(ligne, colonne + 1);
        }
        if (colonne != 0) { // Puis je aller à gauche ?
            check(ligne, colonne -1);
        }
    }

    private static void check(int ligne, int colonne) {
        // Si je n'ai pas visité cette case et que je peux y accéder (Il s'agit d'un point ou de Tarjan <3)
        if(!rf[ligne][colonne] && (croquis[ligne][colonne] == '.' || croquis[ligne][colonne] == 'T')) {
            addPile(ligne, colonne); // Je me dis peut être que j'irais par là tantôt
        }
    }


    // Cette méthode lit les informations sur l'entrée standard
    // et initialise les attributs relatifs au croquis courant
    // et son parcours.
    private static void readInputs() {
        int nbLignes = scanner.nextInt();
        int nbColonnes = scanner.nextInt();
        scanner.nextLine();

        croquis = new char[nbLignes][nbColonnes];
        rf = new boolean[nbLignes][nbColonnes];
        f = new int[nbLignes * nbColonnes][2];
        tailleLogique = 0;

        int i = 0;
        while (i != nbLignes) {
            croquis[i] = scanner.nextLine().toCharArray();

            int j = 0;
            while (j != nbColonnes) {
                if (croquis[i][j] == 'F') {
                    ligneFloyd = i;
                    colonneFloyd = j;
                }
                if (croquis[i][j] == 'T') {
                    ligneTarjan = i;
                    colonneTarjan = j;
                }
                j++;
            }
            i++;
        }
    }
}