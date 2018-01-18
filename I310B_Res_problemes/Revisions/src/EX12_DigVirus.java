import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class EX12_DigVirus {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    // modifs et modifsStep vous nous permettre réagir en deux temps
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
        // On parcoure la chaine
        for (int i = 0; i < digits.length; i++) {
            modifs.add(i); // Je considère que chaque digit sera une modif
            temp[i]= digits[i]; // =Je retiens la valeur actuelle de mon digit
        }
        count = 0;
        while (true) {
            count++;
            while (!modifs.isEmpty()) {
                int i= modifs.poll(); // Je récupère mes modifs une à une
                for (int j = Math.max(0, i - 9) ; j < Math.min(digits.length, i + 9); j++) { //Je n'itère que sur la zone intéréssante (9 cases autour du virus plus puissant).
                    /* Code Chef Conditions */
                    if (digits[i] - digits[j] >= Math.abs(i-j) && temp[j]< digits[i]) {
                        /* InProcess prend la valeur du virus plus puissant */
                        temp[j]= digits[i];
                        /* Si je ne l'avais pas encore visité pdt cette étape
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