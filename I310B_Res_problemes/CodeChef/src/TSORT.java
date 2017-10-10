import java.util.Arrays;
 
public class TSORT {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
 
	public static void main(String[] args) {
		int nbLignes;
		nbLignes = scanner.nextInt();
		int elems[] = new int[1000000];
		for(int i = 0; i < nbLignes; i++) {
			int elem = scanner.nextInt();
			elems[i] = elem;
		}
		Arrays.sort(elems);
		for(int i = 0; i < elems.length; i++) {	
				System.out.println(elems[i]);
		}
		
	}
} 