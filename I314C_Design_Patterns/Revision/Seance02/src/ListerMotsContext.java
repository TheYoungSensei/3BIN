import java.io.*;
import java.util.*;

// Context
public class ListerMotsContext {

    public static void main(String[] args) throws IOException {
        String fichier = "test";
        BufferedReader input = new BufferedReader(new FileReader(fichier));
        Counter strategy = new Counter(
                new ListerMotsNot(new ListerMotsAnd(new ListerPalindromes(), new ListerMotsLongueur(3))));
        String buffer = null;
        while ((buffer = input.readLine()) != null) {
           if(strategy.traiterLigne(buffer)) {
               System.out.println(buffer);
           }
        }
        System.out.println(strategy.getCompteur());
    }
}

