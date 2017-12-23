import java.util.ArrayDeque;
import java.util.Queue;

class DigVirus {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static Queue<Integer> modifsStep;
    private static Queue<Integer> modifs;
    private static char[] temp;
    private static int[] visited;
    private static char[] digits;
    private static int count;

    public static void main(String[] args)
    {
        int nbTestCases = scanner.nextInt();
        for (int i=0; i < nbTestCases; i++) {
            init();
            process();
            System.out.println(count);
        }
    }

    private static void process() {
        for (int i = 0; i < digits.length; i++) {
            modifs.add(i);
            temp[i]= digits[i];
        }
        count = 0;
        while (true) {
            count++;
            while (!modifs.isEmpty()) {
                int i= modifs.poll(); // Je récupère mes modifs une à une
                for (int j = 0; j < digits.length; j++) { // Je parcoure mon échatillon
                    /* Code Chef Conditions */
                    if (digits[i] - digits[j] >= Math.abs(i-j) && temp[j]< digits[i]) {
                        /* InProcess prend la valeur du virus plus puissant */
                        temp[j]= digits[i];
                        /* Si je ne l'avais pas encore cisité pdt cette étape
                            je vais l'ajouter aux modifications apportées cette étape ci
                         */
                        if ( visited[j] != count) {
                            modifsStep.add(j);
                            visited[j] = count;
                        }
                    }
                }
            }
            /* J'ai terminé mon traitement - Aucun digit n'a été augmenté*/
            if (modifsStep.isEmpty()) {
                count--; // L'étape actuelle ne compte pas
                break;
            }
            /* On va devoir itérer une nouvelle fois - Au moins un digit à été augmenté*/
            /* Je supprime les modifs de l'étape précédente */
            modifs.clear();
            while (!modifsStep.isEmpty()) {
                /* J'applique mes changements */
                int b= modifsStep.poll();
                digits[b] = temp[b];
                modifs.add(b);
            }
            /* Je réinitialise mon étape */
            modifsStep.clear();
        }
    }

    private static void init() {
        String s = scanner.next();
        modifs = new ArrayDeque<>();
        modifsStep = new ArrayDeque<>();
        digits = s.toCharArray();
        temp = new char[digits.length];
        visited = new int[digits.length];
    }
}