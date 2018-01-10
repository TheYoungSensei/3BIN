import java.io.*;
import java.util.*;

// ConcreteStrategy, Leaf
public class ListerMotsLongueur implements ListerMotsStrategy{

    private int longueur;

    public ListerMotsLongueur(int longueur) {
        this.longueur = longueur;
    }

    @Override
    public boolean traiterLigne(String ligne) {
        StringTokenizer mots = new StringTokenizer(ligne,
                " \t.;(){}\"'*=:!/\\");
        while (mots.hasMoreTokens()) {
            String mot = mots.nextToken();
            if (mot.length() == longueur)
                return true;
        }
        return false;
    }
}

