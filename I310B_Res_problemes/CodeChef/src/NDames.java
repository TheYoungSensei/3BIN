import java.util.Arrays;
import java.util.Random;

public class NDames {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static Random random = new Random();

    private static int plateau[];

    private static int lignes[];
    private static int diaDesc[];
    private static int diaMont[];
    private static int n;

    public static void main(String[] args) {
        int nbPlateaux = scanner.nextInt();
        for(int i = 0; i < nbPlateaux; i++) {
            init();
            premierePhase();
            deuxiemePhase();
            imprimerPlateau();
        }

    }

    private static void init() {
        n = scanner.nextInt();
        plateau = new int[n];
        lignes = new int[n];
        diaDesc = new int[(n * 2) - 1];
        diaMont = new int[(n * 2) - 1];
    }

    //Pour gérer ma première phase
    private static int toucheAleatoire[];
    private static int tailleToucheAleatoire;

    private static void premierePhase() {
        toucheAleatoire  = new int[n];
        tailleToucheAleatoire = n;
        for(int i = 0; i < n; i++) {
            toucheAleatoire[i] = i;
        }
        while(tailleToucheAleatoire != 0) {
            int colonne = obtenirColonneAleatoire();
            int lignes[] = new int[n];
            int tailleInd = getTailleInd(colonne, lignes);
            int ligneChoisie = hasard(0, tailleInd - 1);
            NDames.lignes[lignes[ligneChoisie]] += 1;
            diaMont[lignes[ligneChoisie] + colonne] += 1;
            diaDesc[n - 1 + colonne - lignes[ligneChoisie]] += 1;
            plateau[colonne] = lignes[ligneChoisie];
        }
    }

    private static int getTailleInd(int colonne, int[] indices) {
        int conflits[] = new int[n];
        int tailleInd = 0;
        int minConflits = n;
        for(int ligne = 0; ligne < n; ligne++) {
            conflits[ligne] += lignes[ligne];
            conflits[ligne] += diaMont[ligne + colonne];
            conflits[ligne] += diaDesc[n - 1 + colonne - ligne];
            if(conflits[ligne] < minConflits) {
                minConflits = conflits[ligne];
                indices[0] = ligne;
                tailleInd = 1;
            } else if(conflits[ligne] == minConflits) {
                indices[tailleInd] = ligne;
                tailleInd++;
            }
        }
        return tailleInd;
    }

    private static void deuxiemePhase() {
        while(true) {
            int queen = getMyQueen();
            if(queen == -1) {
                break;
            }
            int alea = hasard(0,9);
            if(alea == 0) {
                // Completement aleatoire
                int colonne = hasard(0, n - 1);
                int ligne = hasard(0, n - 1);
                lignes[plateau[colonne]] -= 1;
                diaMont[plateau[colonne] + colonne] -= 1;
                diaDesc[n - 1 + colonne - plateau[colonne]] -= 1;
                lignes[ligne] += 1;
                diaMont[ligne + colonne] += 1;
                diaDesc[n - 1 + colonne - ligne] += 1;
                plateau[colonne] = ligne;
            } else {
                // On a la reine
                int indices[] = new int[n];
                int tailleInd = getTailleInd(queen, indices);
                int ligne = hasard(0, tailleInd - 1);
                lignes[plateau[queen]] -= 1;
                diaMont[plateau[queen] + queen] -= 1;
                diaDesc[n - 1 + queen - plateau[queen]] -= 1;
                lignes[indices[ligne]] += 1;
                diaMont[indices[ligne] + queen] += 1;
                diaDesc[n - 1 + queen - indices[ligne]] += 1;
                plateau[queen] = indices[ligne];
            }
        }
    }

    private static int getMyQueen() {
        int indices[] = new int[n];
        int maxConflits = 0;
        int tailleInd = 0;
        int conflits[] = new int[n];
        for(int i = 0; i < n; i++) {
            conflits[i] += lignes[plateau[i]];
            conflits[i] += diaMont[i + plateau[i]];
            conflits[i] += diaDesc[n - 1 + i - plateau[i]];
            if(conflits[i] >  maxConflits) {
                indices[0] = i;
                maxConflits = conflits[i];
                tailleInd = 1;
            } else if (conflits[i] == maxConflits) {
                indices[tailleInd] = i;
                tailleInd++;
            }
        }
        if(maxConflits == 3) {
            return -1;
        } else {
            int colonne = hasard(0, tailleInd - 1);
            return indices[colonne];
        }
    }

    private static void imprimerPlateau()  {
        for(int ligne = 0; ligne < n; ligne++) {
            StringBuilder string = new StringBuilder();
            for(int colonne = 0; colonne < n; colonne++) {
                if(plateau[colonne] == ligne) {
                    string.append('R');
                } else {
                    string.append('.');
                }
            }
            System.out.println(string);
        }
    }

    private static int obtenirColonneAleatoire() {
        int colonneToucheAleatoire = hasard(0, tailleToucheAleatoire - 1);
        int colonne = toucheAleatoire[colonneToucheAleatoire];
        toucheAleatoire[colonneToucheAleatoire] = toucheAleatoire[tailleToucheAleatoire - 1];
        tailleToucheAleatoire--;
        return colonne;
    }

    private static int hasard(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
