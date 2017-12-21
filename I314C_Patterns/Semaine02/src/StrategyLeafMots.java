import java.util.StringTokenizer;

public class StrategyLeafMots implements Strategy {
    @Override
    public boolean isPrintable(String line) {
        StringTokenizer mots = new StringTokenizer(line, " \t.;()\"'*=:!/\\");
        while(mots.hasMoreTokens()){
            String mot = mots.nextToken();
            if(mot.charAt(0) =='t'){
                return true;
            }
        }
        return false;

    }
}
