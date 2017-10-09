import java.util.*;

class MagicalMountain {

	private static final int LEFT_SON = 1;
	private static final int RIGHT_SON = 2;
	private static final int DAD = 0;
	private static final int LEVEL = 3;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int nbMountains = scanner.nextInt();
		for (int i = 0; i < nbMountains; i++) { //Pour chaque montagne (Test Case)
			Map<Integer, Integer[]> tree = new HashMap<>();
			Map<Integer, LinkedList<Integer>> levels = new HashMap<>();
			List<Integer> path = new ArrayList<>();
			int nbStones = scanner.nextInt();
			// Le premier jour Java créa la montagne
			creation(nbStones, tree);
			// Le deuxième jour Java se balada sur les montagnes
			iteration(tree, levels, 1);
			//Le troisième jour JAva isola les pierres importantes
			isolation(levels, path);
			//Le quatrième jour Java tria le chemin
			path.sort(Comparator.comparingInt(o -> o));
			//Le cinquième jour Java afficha le chemin qu'il a parcourus et créa la javadoc
			path.forEach(System.out::println);
		}
	}

	private static void isolation(Map<Integer, LinkedList<Integer>> levels, List<Integer> path) {
		for (Map.Entry<Integer, LinkedList<Integer>> entry : levels.entrySet()) {
            path.add(entry.getValue().removeFirst());
            //I am a level with only a stone sorry :'(
            if (!entry.getValue().isEmpty()) {
                path.add(entry.getValue().removeLast());
            }
        }
	}

	private static void creation(int nbStones, Map<Integer, Integer[]> tree) {
		for (int j = 0; j < nbStones - 1; j++) {
            int leftInteger = scanner.nextInt();
            int rightInteger = scanner.nextInt();
			// Il s'agit de Adam la première pierre
            if (!tree.containsKey(leftInteger)) {
                tree.put(leftInteger, new Integer[4]);
            }
            if (tree.get(leftInteger)[LEFT_SON] == null) {
                tree.get(leftInteger)[LEFT_SON] = rightInteger;
            } else {
                tree.get(leftInteger)[RIGHT_SON] = rightInteger;
            }
            if (!tree.containsKey(rightInteger)) {
                tree.put(rightInteger, new Integer[4]);
            }
            tree.get(rightInteger)[DAD] = leftInteger; //Tout fils désire connaître son parent
        }
	}

	private static void iteration(Map<Integer, Integer[]> tree, Map<Integer, LinkedList<Integer>> levels,
								  int stone) {
		int level;
		if(tree.get(stone)[DAD] == null) { //Hello i'm the first stone
			level = 0;
			tree.get(stone)[LEVEL] = level;
		} else { //Hello we are the others stones
			level = tree.get(tree.get(stone)[DAD])[LEVEL] + 1;
			tree.get(stone)[LEVEL] = level;
		}
		if(!levels.containsKey(level)) {
			levels.put(level, new LinkedList<>());
		}
		levels.get(level).add(stone);
		if(tree.get(stone)[LEFT_SON] != null) //Used to check if we can iteration on first children
			iteration(tree, levels, tree.get(stone)[LEFT_SON]);
		if (tree.get(stone)[RIGHT_SON] != null) //USed to check if we can iteration on second child
			iteration(tree, levels, tree.get(stone)[RIGHT_SON]);
	}

}