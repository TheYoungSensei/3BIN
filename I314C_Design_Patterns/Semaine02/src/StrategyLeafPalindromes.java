import java.util.StringTokenizer;

public class StrategyLeafPalindromes implements Strategy {

    @Override
    public boolean isPrintable(String line) {
        StringTokenizer mots = new StringTokenizer(line, " \t.;()\"'*=:!/\\");
        while(mots.hasMoreTokens()){
            String mot = mots.nextToken();
           return estPalindrome(mot);
        }
        return false;

    }
    private boolean estPalindrome(String mot) {
        return mot != null && mot.equals(new StringBuilder(mot).reverse().toString());
    }
}
