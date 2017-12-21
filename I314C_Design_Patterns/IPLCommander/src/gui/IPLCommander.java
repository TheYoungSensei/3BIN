package gui;
import java.io.IOException;

import javax.swing.JFrame;

import core.Logger;
import core.Fichier;
import core.FichierImpl;
import core.ProxyHttp;

public class IPLCommander {
	public static void main(String[] args) throws IOException {
		Fichier racine= new ProxyHttp("http://localhost:8080");
		JFrame gui=new GUI(racine);
		gui.setVisible(true);
	}
}
