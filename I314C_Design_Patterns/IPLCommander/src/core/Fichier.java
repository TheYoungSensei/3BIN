package core;
import java.io.IOException;

//Component / Subject
public interface Fichier {

	String[] liste(String repertoire) throws IOException;

	byte[] charge(String chemin) throws IOException;

	void sauve(String chemin, byte[] donnees) throws IOException;

	void efface(String chemin) throws IOException;

}