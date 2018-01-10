import java.io.*;
import java.util.StringTokenizer;

// ConcreteStrategy, Leaf
public class ListerPalindromes implements ListerMotsStrategy{ // mal foutu

    private boolean estPalindrome(String mot) {
        if (mot == null)
            return false;
        StringBuilder stringbuffer = new StringBuilder(mot);
        return mot.equals(stringbuffer.reverse().toString());
    }

    @Override
    public boolean traiterLigne(String ligne) {
        StringTokenizer mots = new StringTokenizer(ligne,
                " \t.;(){}\"'*=:!/\\");
        while (mots.hasMoreTokens()) {
            String mot = mots.nextToken();
            if (estPalindrome(mot))
                return true;
        }
        return false;
    }
}