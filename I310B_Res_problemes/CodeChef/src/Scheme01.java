import java.util.*;

class Scheme01 {

    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static Town[] towns;
    private static SortedSet<Integer> f;
    private static Set<Integer> rf;

    private static int source;
    private static int destination;

    public static void main(String[] args) {
        int nbTest = scanner.nextInt();
        for (int i = 0; i < nbTest; i++) {
            initialisation();
            boolean found = processing();

            if (!found)
                System.out.println(-1);
            else
                System.out.println(towns[destination].cost * 10);
        }
    }

    private static boolean processing() {
        f.add(source);
        towns[source].cost = 0;
        boolean found = destination == source;

        while (!f.isEmpty() && !found) {
            int current = f.first();
            f.remove(current);
            rf.add(current);
            found = current == destination;

            for (Road road : towns[current].succ) {
                int state = road.arrivee;
                int cost = road.cost;

                if (!rf.contains(state)) {
                    update(current, state, cost);
                }
            }
        }
        return found;
    }

    private static void initialisation() {
        f = new TreeSet<>(new Comparator<Integer>() {
            public int compare(Integer arg0, Integer arg1) {
                if (Objects.equals(arg0, arg1) || towns[arg0].cost == towns[arg1].cost)
                    return arg0 - arg1;
                return towns[arg0].cost - towns[arg1].cost;
            }
        });
        rf = new HashSet<>();

        int nbTowns = scanner.nextInt();
        int nbRoads = scanner.nextInt();

        towns = new Town[nbTowns];
        for (int i = 0; i < nbTowns; i++) {
            Town s = new Town(i);
            towns[i] = s;
        }

        for (int i = 0; i < nbRoads; i++) {
            int depart = scanner.nextInt() - 1;
            int arrivee = scanner.nextInt() - 1;
            int cost = scanner.nextInt();
            towns[depart].succ.add(new Road(arrivee, cost));
        }

        for (int i = 0; i < nbTowns; i++) {
            towns[i].dec = scanner.nextInt();
        }

        source = scanner.nextInt() - 1;
        destination = scanner.nextInt() - 1;
    }

    private static void update(int parent, int succ, int cost) {
        Town p = towns[parent];
        Town s = towns[succ];

        int toAdd = p.cost + cost;
        if (cost > s.dec) {
            toAdd -= s.dec;
        }

        if (s.parent == null) {
            s.parent = p;
            s.cost = toAdd;
            f.add(succ);
        } else if (toAdd < s.cost) {
            f.remove(succ);
            s.parent = p;
            s.cost  = toAdd;
            f.add(succ);
        }
    }

    private static class Town {
        Town parent;
        int town;
        int cost = 0;
        List<Road> succ = new ArrayList<>();
        int dec;

        Town(int etat) {
            this.town = etat;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + town;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Town))
                return false;
            Town other = (Town) obj;
            return town == other.town;
        }
    }

    private static class Road {
        int arrivee;
        int cost;

        Road(int arrivee, int cost) {
            this.arrivee = arrivee;
            this.cost = cost;
        }
    }
}