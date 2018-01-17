import java.util.*;

public class EX01_MagicalMountain {

    private static final Scanner scanner = new Scanner(System.in);

    private static final int DAD = 0;
    private static final int LEFT_SON = 1;
    private static final int RIGHT_SON = 2;
    private static final int LEVEL = 3;

    private static Map<Integer, Integer[]> tree;
    private static Map<Integer, LinkedList<Integer>> levels;


    public static void main(String[] args) {
        int nbMountains = scanner.nextInt();
        for(int i = 0; i < nbMountains; i++) {
            tree = new HashMap<>();
            levels = new HashMap<>();
            List<Integer> path = new ArrayList<>();
            int nbStones = scanner.nextInt();
            for(int j = 0; j < nbStones - 1; j++) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                // Uniquement utilisé pour la première pierre (Adam)
                if (!tree.containsKey(left)) {
                    tree.put(left, new Integer[4]);
                    // tree[0] = DAD (le papa de la petite pierre) => la pierre au dessus
                    // tree[1] = LEFT_SON (le fils situé à la gauche mais en dessous de la pierre)
                    // tree[2] = RIGHT_SON (le fils situé à la droite mais en dessous de la pierre)
                    // tree[4] = LEVEL (où se situe la pierre sur l'arbre)
                }
                if(tree.get(left)[LEFT_SON] == null) { // Je n'ai pas de fils gauche :'(
                    tree.get(left)[LEFT_SON] = right; // J'ai un fils gauche :)
                } else {
                    tree.get(left)[RIGHT_SON] = right; // J'ai même un fils droit :D
                }
                if(!tree.containsKey(right)) { // Je rajoute mon fils dans l'arbre
                    tree.put(right, new Integer[4]);
                }
                tree.get(right)[DAD] = left; // J'enseigne à mon fils qui est le papa
            }
            iteration(1);
            for(Map.Entry<Integer, LinkedList<Integer>> level : levels.entrySet()) {
                path.add(level.getValue().removeFirst()); // la pierre s'ajoute au chemin
                // First car je prends à partir de gauche
                if(!level.getValue().isEmpty()) { // level n'avait pas d'autre pierre ....
                    path.add(level.getValue().removeLast());
                    // Last car je prends à partir de droite
                }
            }
            path.sort(Comparator.comparingInt(o -> o)); // On mets en peu d'ordre dans tout cela
            // LES STREAMS C'EST TROP BIEN (et je parle ne parle pas que de ceux de twitch)
            path.forEach(System.out::println);
        }

    }

    // En vrai je fais presque tout le boulot au sein de ce code ...
    public static void iteration(int stone) {
        int level; // comme dans Mario
        if(tree.get(stone)[DAD] == null) { // Je suis Adam
            level = 0;
            tree.get(stone)[LEVEL] = level; // Je ne suis qu'un zéro :'(
        } else {
            // Je suis un fils à papa qui dois toujours tout lui demander ...
            level = tree.get(tree.get(stone)[DAD])[LEVEL] + 1;
            // Mais grâce à lui je sais me situer dans la vie
            tree.get(stone)[LEVEL] = level;
        }
        if(!levels.containsKey(level)) {
            // Chaque niveau comporte ses pierres (RIP l'esclavagisme des pierres)
            levels.put(level, new LinkedList<>());
        }
        levels.get(level).add(stone); // Une nouvelle acquisition ...
        if(tree.get(stone)[LEFT_SON] != null) //Pierre est dans la place
            iteration(tree.get(stone)[LEFT_SON]);
        if(tree.get(stone)[RIGHT_SON] != null) // À présent c'est Caillou son petit frère
            iteration(tree.get(stone)[RIGHT_SON]);

    }
}
