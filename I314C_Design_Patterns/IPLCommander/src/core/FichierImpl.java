package core;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

//ConcreteComponent
// cette classe doit rester finale et inchangée !
public final class FichierImpl implements Fichier {
	private File root;

	public FichierImpl(String racine) {
		root=new File(racine);
	}
	
	@Override
	public String[] liste(String repertoire) throws IOException {
		File sub=new File(root,repertoire);
		if (!sub.getAbsolutePath().startsWith(root.getAbsolutePath())) throw new IOException("Chemin invalide");
		ArrayList<String> ret=new ArrayList<>();
		for(File f:sub.listFiles()) {
			if (f.isDirectory()) {
				ret.add(f.getName()+"/");
			} else {
				ret.add(f.getName());				
			}
		}
		return ret.toArray(new String[ret.size()]);
	}
	
	@Override
	public byte[] charge(String chemin) throws IOException {
		File sub=new File(root,chemin);
		if (!sub.getAbsolutePath().startsWith(root.getAbsolutePath())) throw new IOException("Chemin invalide");
		return Files.readAllBytes(sub.toPath());		
	}
	
	@Override
	public void sauve(String chemin, byte[] donnees) throws IOException {
		File sub=new File(root,chemin);
		if (!sub.getAbsolutePath().startsWith(root.getAbsolutePath())) throw new IOException("Chemin invalide");
		Files.write(sub.toPath(), donnees, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	@Override
	public void efface(String chemin) throws IOException {
		File sub=new File(root,chemin);
		if (!sub.getAbsolutePath().startsWith(root.getAbsolutePath())) throw new IOException("Chemin invalide");
		Files.delete(sub.toPath());
	}
}
