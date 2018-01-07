import java.io.*;
public class Lecteur {
    private PushbackReader buffer;
    private Fabriquant fabriquant = new Fabriquant();
    public Lecteur(String fichier) throws FileNotFoundException {
        buffer = new PushbackReader(new BufferedReader(new FileReader(fichier)));
    }
    public Partie construire() throws IOException {
        int car;
        while ((car = buffer.read()) != -1) {
            if (car == '(') {
                fabriquant.construireGroupe();
            } else if (car == ')') {
                fabriquant.fermerGroupe();
            } else if (Character.isDigit((char)car)) {
                int nombre = 0;
                do {
                    nombre *= 10;
                    nombre += Character.digit((char)car, 10);
                    if ((car = buffer.read()) == -1) break;
                } while(Character.isDigit((char)car));
                if (car != -1) buffer.unread(car);
                fabriquant.construireValeur(nombre);
            }
        }
        return fabriquant.getRésultat();
    }
}