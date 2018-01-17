import java.util.*;

public class EX05_Scheme01 {

    private static Scanner scanner = new Scanner(System.in);

    private static Town[] towns;
    private static SortedSet<Integer> f;
    private static Set<Integer> rf;

    private static int source;
    private static int destination;

    public static void main(String[] args) {
        int nbTests = scanner.nextInt();
        for(int i = 0; i < nbTests; i++) {
            initialisation();
            boolean found = processing();
            if (!found)
                System.out.println(-1);
            else
                System.out.println(towns[destination].cost * 10);
        }
    }

    private static boolean processing() {
        // Cas initial
        f.add(source);
        towns[source].cost = 0;
        // Cas le plus simple
        boolean found = destination == source;
        // On continue tant que l'on a des villes a parcourir / que l'on n'est pas arrivé à destination :'(
        while(!f.isEmpty() && !found) {
            int current = f.first(); // On récupère le noeud à parcourir
            f.remove(current); // On le supprime des noeuds à parcourir
            rf.add(current); // On l'ajoute au noeuds parcourus
            found = current == destination; // Ho on a trouvé la destination
            for(Road road : towns[current].succ) { // On parcourt les successeurs
                int arrivee = road.arrivee;
                int cost = road.cost;
                // Si je ne l'ai pas déjà parcourus
                if(!rf.contains(arrivee)) {
                    update(current, arrivee, cost);
                }
            }
        }
        return found;
    }

    private static void update(int current, int succ, int cost) {
        Town s = towns[succ];
        // toAdd sera égal au cout de la ville actuelle + au cout de la "road"
        int toAdd = towns[current].cost + cost;
        // Si maintenent le cout de la "road" est plus grand que la valeur dec de la ville
        if(cost > s.dec) {
            toAdd -= s.dec; // Je diminue ce que je dois ajouter
        }
        // Si maintenant il s'agit d'un meilleur chemin alors on modifie le cout du successeur en consequence
        if (toAdd < s.cost) {
            f.remove(succ); // Dans le but de mettre à jour
            s.cost = toAdd; // Mise à jour
            f.add(succ); // Dans le but de mettre à jour
        }
    }

    private static void initialisation() {
        // Création de notre magnifique comparateur
        f = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // Si le cout est le même entre les deux alors on utilise leurs noms avant de générer un int
                if(o1.equals(o2) || towns[o1].cost == towns[o2].cost)
                    return o1 - o2;
                // On va générer le compare sur base de leur cout
                return towns[o1].cost - towns[o2].cost;
            }
        });
        rf = new HashSet<>();
        int nbTowns = scanner.nextInt();
        int nbRoads = scanner.nextInt();
        // Ha les bon vieux tableaux <3
        towns = new Town[nbTowns];
        // Création des différentes villes
        for(int i = 0; i < nbTowns; i++) {
            Town town = new Town(i);
            towns[i] = town;
        }
        // Création des différentes routes
        for(int i = 0; i < nbRoads; i++) {
            int depart = scanner.nextInt() - 1; // Le -1 est utilisé afin d'éviter les ArrayIndexOutOfBound
            int arrivee = scanner.nextInt() - 1;
            int cost = scanner.nextInt();
            towns[depart].succ.add(new Road(arrivee, cost)); // Ajout de la ville d'arrivée au sein des successeurs
        }
        // Initialisation des différents nombres de points a faire décroitre
        for(int i = 0; i < nbTowns; i++) {
            towns[i].dec = scanner.nextInt();
        }
        source = scanner.nextInt() - 1;
        destination = scanner.nextInt() - 1;
    }

    private static class Town {
        int town;
        int cost;
        List<Road> succ;
        int dec;

        Town(int town) {
            this.succ = new ArrayList<>();
            this.town = town;
            this.cost = Integer.MAX_VALUE; // Nous permet de trouver un meilleur chemin (si il en existe un)
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Town town1 = (Town) o;
            return town == town1.town;
        }

        @Override
        public int hashCode() {

            return Objects.hash(town);
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
