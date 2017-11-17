public interface Robot {
	
	int getCanon(); // retourne la puissance du canon

	int getShield(); // retourne la puissance du bouclier

	int getFreq(); // retourne la fréquence de tir
	
	String getName(); // retourne le nom du robot

	int diffLife(int i); // change les points de vie du robot et retourne la nouvelle valeur de ces points de vie. 
	 // Si le paramètre est négatif, le robot perd de la vie.
	 // Si le paramètre est positif, le robot est soigné.
	 // Si le paramètre est zéro, la méthode agit comme un getter sur les points de vie.
}
