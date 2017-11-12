import java.util.*;

public class FCar {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static final int DEST = 0;
    private static final int COST = 1;
    private static final int NEXT = 2;

    private static int nbTowns;
    private static int nbRoads;

    private static int[] heights;
    private static int[] modeCosts;

    private static int[][] edges;
    private static int[] adj;
    private static int nbEdges;

    private static boolean rf[];
    private static int costs[];

    private static TreeSet<Integer> f = new TreeSet<Integer>(new Comparator<Integer>() {

        @Override
        public int compare(Integer integer, Integer t1) {
            return costs[integer] - costs[t1];
        }
    });



    public static void main(String[] args) {
        nbTowns = scanner.nextInt();
        nbRoads = scanner.nextInt();
        heights = new int[nbTowns];
        for(int i = 0; i < nbTowns; i++){
            heights[i] = scanner.nextInt();
        }
        modeCosts = new int[nbTowns];
        for(int i = 0; i < nbTowns; i++){
            modeCosts[i] = scanner.nextInt();
        }
        edges = new int[4 * nbRoads + 2 * nbTowns + 4][3];
        adj = new int[2 * nbTowns + 2];
        //Adding roads between the same town
        for(int i = 0; i < nbTowns; i++) {
            addEdge(i * 2, modeCosts[i], i * 2 + 1);
            addEdge(i * 2 + 1, modeCosts[i], i * 2);
        }
        //Adding other edges
        for(int i = 0; i < nbRoads; i++) {
            int depart = scanner.nextInt();
            int arrival = scanner.nextInt();
            if(heights[depart] <= heights[arrival]) {
                addEdge(depart * 2, 0, arrival * 2);
                addEdge(depart * 2 + 1, 0, arrival * 2 + 1);
            }
            if(heights[arrival] <= heights[depart]) {
                addEdge(arrival * 2, 0, depart * 2);
                addEdge(arrival * 2 + 1, 0, depart * 2 + 1);
            }
        }
        //Adding the X state => initial state
        addEdge(2 * nbTowns, modeCosts[0], 0);
        addEdge(2 * nbTowns, modeCosts[0], 1);
        //Adding the Y state => final state
        addEdge(2 * nbTowns - 2, 0, 2 * nbTowns + 1);
        addEdge(2 * nbTowns - 1, 0, 2 * nbTowns + 1);

        //Now Dijkstra Processing
        rf = new boolean[2 * nbTowns + 2];
        costs = new int[2 * nbTowns + 2];
    }

    private static void addEdge(int destination, int cost, int next) {
        edges[nbEdges][DEST] = destination;
        edges[nbEdges][COST] = cost;
        adj[nbEdges] = next;
        edges[nbEdges][NEXT] = adj[next];
        nbEdges++;
    }

}
