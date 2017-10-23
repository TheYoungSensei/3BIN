import java.util.StringTokenizer;

public class StrategyLeafLignes implements Strategy {
    private int longueur;


    public StrategyLeafLignes(int longueur) {
        this.longueur = longueur;
    }

    @Override
    public boolean isPrintable(String line) {
        StringTokenizer mots = new StringTokenizer(line, " \t.;()\"'*=:!/\\");
        while(mots.hasMoreTokens()){
            String mot = mots.nextToken();
            if(mot.length() ==longueur){
                return true;
            }
        }
        return false;

    }
}
