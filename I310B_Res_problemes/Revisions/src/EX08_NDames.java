import java.util.Arrays;
import java.util.Random;

public class EX08_NDames {

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
        // toucheAleatoire va tenir les différentes colonnes sur lesquelles on pourrait effectuer un traitement
        for(int i = 0; i < n; i++) {
            toucheAleatoire[i] = i;
        }
        // Tant que je ne suis pas passé par toutes les colonnes
        while(tailleToucheAleatoire != 0) {
            int colonne = obtenirColonneAleatoire();
            int indices[] = new int[n]; // Va contenir les différentes lignes avec le conflit minimum
            int tailleInd = getTailleInd(colonne, indices); // Va permettre de déterminer sur quelle portée je peux effectuer mon aléatoire
            int ligneChoisie = hasard(0, tailleInd - 1);
            EX08_NDames.lignes[indices[ligneChoisie]] += 1; // On rajoute la dame sur la ligne ayant le moins de conflits et tirée au hasard
            diaMont[indices[ligneChoisie] + colonne] += 1; // On fais de même pour les diagoneles Mont
            diaDesc[n - 1 + colonne - indices[ligneChoisie]] += 1; // Et Desc
            plateau[colonne] = indices[ligneChoisie]; // On retient la ligne sur laquelle est la reine
            // indices[ligneChoisie] => une des lignes ayant le min de conflits
        }
    }

    private static int getTailleInd(int colonne, int[] indices) {
        int conflits[] = new int[n]; // va permettre de compter les différents conflits présents au sein de ce fichier
        int tailleInd = 0;
        int minConflits = n;
        // Je parcoure toutes les lignes
        for(int ligne = 0; ligne < n; ligne++) {
            // Si il y a un conflit sur la ligne actuelle ++
            conflits[ligne] += lignes[ligne];
            // Si il y en a un sur les diagonales Mont ++
            conflits[ligne] += diaMont[ligne + colonne];
            // Si il y en a un sur les diagonales Desc ++
            conflits[ligne] += diaDesc[n - 1 + colonne - ligne];
            // Si les conflits de la lignes représentent le minimum de conflits
            if(conflits[ligne] < minConflits) {
                // On mets à jour le nombre min de conflits
                minConflits = conflits[ligne];
                // Indices va avoir pour première occurence la ligne actuelle
                indices[0] = ligne;
                // On mets à jour la taille
                tailleInd = 1;
            } else if(conflits[ligne] == minConflits) { // La ligne a un nombre de conflits équivalent au précédent minConflits
                indices[tailleInd] = ligne; // On l'ajoute à indices
                tailleInd++; // On mets à jour la taille
            }
        }
        return tailleInd;
    }

    private static void deuxiemePhase() {
        // Les while(true) c'est comme chiffrer des données : c'est un must
        while(true) {
            int queen = getMyQueen(); // ligne au hasard parmis celle étant le plus en conflits
            if(queen == -1) { // Je n'ai plus de conflits => Job Done
                break;
            }
            int alea = hasard(0,9);
            if(alea == 0) {
                // Completement aleatoire
                int colonne = hasard(0, n - 1);
                int ligne = hasard(0, n - 1);
                // On déplace la reine présente à l'emplacement colonne
                lignes[plateau[colonne]] -= 1;
                diaMont[plateau[colonne] + colonne] -= 1;
                diaDesc[n - 1 + colonne - plateau[colonne]] -= 1;
                // On la rajoute au sein de la ligne choisie au hasard
                lignes[ligne] += 1;
                diaMont[ligne + colonne] += 1;
                diaDesc[n - 1 + colonne - ligne] += 1;
                plateau[colonne] = ligne;
            } else {
                // On a la reine
                int indices[] = new int[n]; // va contenir les lignes ayant le moins de conflits
                int tailleInd = getTailleInd(queen, indices);
                int ligne = hasard(0, tailleInd - 1); // On en choisis une au hasard
                // On enlève la reine de sa ligne précédente
                lignes[plateau[queen]] -= 1;
                diaMont[plateau[queen] + queen] -= 1;
                diaDesc[n - 1 + queen - plateau[queen]] -= 1;
                // On l'ajoute au sein de la ligne choisie au hasard
                lignes[indices[ligne]] += 1;
                diaMont[indices[ligne] + queen] += 1;
                diaDesc[n - 1 + queen - indices[ligne]] += 1;
                plateau[queen] = indices[ligne];
                // indices[ligne] => une des lignes ayant le moins de conflit (choisie au hasard)
            }
        }
    }

    private static int getMyQueen() {
        int indices[] = new int[n];
        int maxConflits = 0;
        int tailleInd = 0;
        int conflits[] = new int[n];
        // On passe à travers chaque lignes
        for(int i = 0; i < n; i++) {
            // On ajoute les conflits des lignes
            conflits[i] += lignes[plateau[i]];
            // On ajoute les conflits des diaMont
            conflits[i] += diaMont[i + plateau[i]];
            // On ajoute les conflits des diaDesc
            conflits[i] += diaDesc[n - 1 + i - plateau[i]];
            // Si mtn mon nombre de conflits est plus grand que le max de conflits précédent / actuel
            if(conflits[i] >  maxConflits) {
                // On utilise la ligne actuelle comme référence pour le nombre max de conflits
                indices[0] = i;
                maxConflits = conflits[i];
                tailleInd = 1;
            } else if (conflits[i] == maxConflits) { // Si elle est égale au nombre de conflits
                indices[tailleInd] = i; // On l'ajoute aux lignes ayant un maxConflits
                tailleInd++;
            }
        }
        // On aura toujours au minimum trois conflits : la dame est présente à la fois sur les lignes, sur les diaMont et sur les diaDesc
        if(maxConflits == 3) {
            return -1;
        } else {
            int colonne = hasard(0, tailleInd - 1);
            return indices[colonne]; // On va retourner une ligne au hasard (parmis celle ayant maxConflits)
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
        // On obtient un numero au hasard
        int colonneToucheAleatoire = hasard(0, tailleToucheAleatoire - 1);
        // On récupère la colonne associé à ce numéro hasardeux
        int colonne = toucheAleatoire[colonneToucheAleatoire];
        // On remplace le numero déjà traité par celui en dernière position
        toucheAleatoire[colonneToucheAleatoire] = toucheAleatoire[tailleToucheAleatoire - 1];
        // On mets le tout à jour
        tailleToucheAleatoire--;
        return colonne;
    }

    private static int hasard(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
