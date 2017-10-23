import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Counter counter = new Counter(new Or(new StrategyLeafMots(), new StrategyLeafPalindromes()));
        new ContextListerMots(counter, "texte.txt").execute();
        System.out.println("Compteur : " + counter.getCounter());
    }
}
