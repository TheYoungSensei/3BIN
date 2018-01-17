import java.util.*;

public class EX07_FCar {

    private static Scanner scanner = new Scanner(System.in);

    // Graph
    private static int nbTowns;
    private static int nbRoads;
    private static Map<Integer, Town> towns = new HashMap<>();

    // Dijkstra
    private static long[] costs;
    private static TreeSet<Integer> f = new TreeSet<>((o1, o2) -> {
        if(costs[o1] != costs[o2])
            return (int) costs[o1] - (int) costs[o2];
        else
            return o1 - o2;
    });

    private static boolean[] rf;

    private static final int COST = 1;
    private static final int DEST = 0;

    public static void main(String[] args) {
        creatingGraphs();
        nowABitOfDijkstra();
        printResult();
    }

    private static void creatingGraphs() {
        //First Line
        nbTowns = scanner.nextInt();
        nbRoads = scanner.nextInt();
        // 2 * pour Blue Graph + Green Graph
        // + 2 pour les cases de debut (X) et de fin (Y)
        costs = new long[2 * nbTowns + 2];
        rf = new boolean[(2 * nbTowns) + 2];

        //Line 2 -> Initiate the towns heights
        for (int i = 0; i < nbTowns; i++) {
            int height = scanner.nextInt();
            towns.put(i * 2, new Town(height)); //Blue Graph
            towns.put((i * 2) + 1, new Town(height)); //Green Graph
        }
        //Line 3 -> Initiate the towns costs
        townsCosts();
        //All the others lines
        edges();
    }

    private static void edges() {
        for (int i = 0; i < nbRoads; i++) {
            int src = scanner.nextInt() - 1;
            int dst = scanner.nextInt() - 1;

            //Up the road mode
            if (towns.get(src * 2).height <= towns.get(dst * 2).height) {
                towns.get(src * 2).edges.add(new int[]{dst * 2, 0});
                towns.get((dst * 2) + 1).edges.add(new int[]{(src * 2) + 1, 0});
            }

            //Down the road mode
            if (towns.get(src * 2).height >= towns.get(dst * 2).height) {
                towns.get(dst * 2).edges.add(new int[]{src * 2, 0});
                towns.get((src * 2) + 1).edges.add(new int[]{(dst * 2) + 1, 0});
            }
        }
    }

    private static void townsCosts() {
        // On va parcourir toutes les villes
        for (int i = 0; i < nbTowns; i++) {
            int cost = scanner.nextInt();
            // Beacause X always want to be the first
            if (i == 0) {
                towns.put(nbTowns * 2, new Town(0)); // => Ajout en nbTowns * 2 pour faciliter l'écriture + facile à recup
                // Je rajoutes les liens Blue Graph - Green Graph
                towns.get(nbTowns * 2).edges.add(new int[]{0, cost});
                towns.get(nbTowns * 2).edges.add(new int[]{1, cost});
            }
            //Add my egde for the current town (Blue Graph - Green Graph)
            towns.get(i * 2).edges.add(new int[]{(i * 2) + 1, cost});
            towns.get((i * 2) + 1).edges.add(new int[]{i * 2, cost});
        }
        // Because Y always want to be the last
        towns.put((nbTowns * 2) + 1, new Town(0));
        // Ajout d'un lien de la dernière case du graphe vers Y
        towns.get((2 * nbTowns) - 1).edges.add(new int[]{(nbTowns * 2) + 1, 0}); // Blue Graph
        towns.get((2 * nbTowns) - 2).edges.add(new int[]{(nbTowns * 2) + 1, 0}); // Green Graph
    }

    private static void printResult() {
        // Si on a trouvÃ© 2 * nbTowns + 1 => Y
        if (!rf[2 * nbTowns + 1]) {
            System.out.println(-1);
        } else {
            System.out.println(costs[2 * nbTowns + 1]);
        }
    }

    private static void nowABitOfDijkstra() {
        //Init case
        costs[2 * nbTowns] = 0;
        f.add(2 * nbTowns);

        while (!f.isEmpty()) {
            int current = f.pollFirst();
            rf[current] = true;
            for (int[] edge : towns.get(current).edges) {
                int destination = edge[DEST];
                int cost = edge[COST];
                // Si maintenant cette case n'avait pas encore été visitée
                if (!rf[destination] && !f.contains(destination)) {
                    costs[destination] = costs[current] + cost;
                    f.add(destination);
                    // Si maintenant current + cost de l'edge est plus petit que le cout de la destination :
                } else if (!rf[destination] && costs[current] + cost < costs[destination]) {
                    f.remove(destination);
                    costs[destination] = costs[current] + cost;
                    f.add(destination);
                }
            }
        }
    }

    private static class Town {
        int height;
        List<int[]> edges;

        Town(int height) {
            super();
            this.height = height;
            this.edges = new ArrayList<>();
        }
    }
}