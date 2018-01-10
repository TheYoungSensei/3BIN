import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader lecteurAvecBuffer=null;
        String ligne;
        try {
            lecteurAvecBuffer = new BufferedReader(
                    new FileReader("test"));
        } catch(FileNotFoundException e) {
            System.out.println("Erreur d'ouverture");
        }
        Subject subject = new Subject();
        NbrLignes nbrLignes = new NbrLignes();
        subject.attach(nbrLignes);
        NbrBelgique nbrBelgique = new NbrBelgique();
        subject.attach(nbrBelgique);
        NbrMots nbrMots = new NbrMots();
        subject.attach(nbrMots);
        NbrPalindromes  nbrPalindromes = new NbrPalindromes();
        subject.attach(nbrPalindromes);

        subject.readContent(lecteurAvecBuffer);

        assert lecteurAvecBuffer != null;
        lecteurAvecBuffer.close();

        System.out.println("Il y avait "+nbrLignes+" lignes.");
        System.out.println("Il y avait "+nbrMots+" mots.");
        System.out.println("Il y avait "+nbrPalindromes+" palindromes.");
        System.out.println("Il y avait "+nbrBelgique+
                " lignes contenant Belgique.");
    }
}