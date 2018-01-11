// Component, Product
public interface Robot {
	
	int getCanon(); // retourne la puissance du canon

	int getShield(); // retourne la puissance du bouclier

	int getFreq(); // retourne la fr�quence de tir
	
	String getName(); // retourne le nom du robot

	int diffLife(int i); // change les points de vie du robot et retourne la nouvelle valeur de ces points de vie. 
	 // Si le param�tre est n�gatif, le robot perd de la vie.
	 // Si le param�tre est positif, le robot est soign�.
	 // Si le param�tre est z�ro, la m�thode agit comme un getter sur les points de vie.
}
