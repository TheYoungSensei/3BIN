import java.io.*;
import java.util.*;

// ConcreteStrategy, Leaf
public class ListerMotsCommencantPar implements ListerMotsStrategy{

    private String begin;

    public ListerMotsCommencantPar(String begin) {
        this.begin = begin;
    }

    @Override
    public boolean traiterLigne(String ligne) {
        StringTokenizer mots = new StringTokenizer(ligne,
                " \t.;(){}\"'*=:!/\\");
        while (mots.hasMoreTokens()) {
            String mot = mots.nextToken();
            if(mot.length() >= begin.length()) {
                if (mot.substring(0, begin.length()).equals(begin))
                   return true;
            }
        }
        return false;
    }
}