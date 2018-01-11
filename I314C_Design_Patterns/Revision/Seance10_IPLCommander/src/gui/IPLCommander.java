package gui;
import java.io.IOException;

import javax.swing.JFrame;

import core.*;

public class IPLCommander {
	public static void main(String[] args) throws IOException {
		Fichier racine= new ProxyHTTP("http://localhost:8080", true, false, false, true);
		JFrame gui=new GUI(racine);
		gui.setVisible(true);
	}
}
