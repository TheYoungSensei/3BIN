package question;

class MachineACafe {
	public enum State {
		IDLE {
			@Override
			public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
				machine.afficherPasAssez(toucheBoisson);
			}

			@Override
			public void rendreMonnaie(MachineACafe machine) {

			}
		},
		COLLECTE {
			@Override
			public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
				if (toucheBoisson.getPrix() > machine.montantEnCours) {
					machine.boisson = toucheBoisson;
					machine.afficherPasAssez(machine.boisson);
					machine.boisson = toucheBoisson;
					machine.etatCourant =  PASASSEZ;
					return;
				}
				machine.montantEnCours -= toucheBoisson.getPrix();
				machine.afficherBoisson(toucheBoisson);
				machine.afficherMontant();
				if (machine.montantEnCours == 0)
					machine.etatCourant = IDLE;
				else
					machine.etatCourant = COLLECTE;
			}
		},
		PASASSEZ {
			@Override
			public void entrerMonnaie(MachineACafe machine, Piece piece) {
				if (machine.boisson.getPrix() > machine.montantEnCours) {
					machine.afficherPasAssez(machine.boisson);
				} else {
					machine.montantEnCours -= machine.boisson.getPrix();
					machine.afficherBoisson(machine.boisson);
					machine.boisson = null;
					machine.afficherMontant();
					if (machine.montantEnCours == 0)
						machine.etatCourant =  IDLE;
					else
						machine.etatCourant =  COLLECTE;
				}
			}

			@Override
			public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
				throw new IllegalStateException();
			}
		};
		public void entrerMonnaie(MachineACafe machine, Piece piece) {
			machine.etatCourant = COLLECTE;
		}
		public abstract void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson);
		public void rendreMonnaie(MachineACafe machine) {
			machine.afficherRetour();
			machine.montantEnCours = 0;
			machine.boisson = null;
			machine.etatCourant = IDLE;
		}
	}

	private int montantEnCours = 0;
	private State etatCourant = State.IDLE;
	private ToucheBoisson boisson = null;
	
	private void afficherMontant() {
		System.out.println(montantEnCours + " cents disponibles");
	}
	
	private void afficherRetour() {
		System.out.println(montantEnCours + " cents rendus");
	}
	
	private void afficherPasAssez(ToucheBoisson toucheBoisson) {
		System.out.println("Vous n'avez pas introduit un montant suffisant pour un " + toucheBoisson);
		System.out.println("Il manque encore " + (toucheBoisson.getPrix() - montantEnCours) + " cents");
	}

	private void afficherBoisson(ToucheBoisson toucheBoisson) {
		System.out.println("Voici un " + toucheBoisson);
		
	}

	void entrerMonnaie(Piece piece) {
		montantEnCours += piece.getValeur();
		afficherMontant();
		etatCourant.entrerMonnaie(this, piece);
	}
	
	void selectionnerBoisson(ToucheBoisson toucheBoisson) {
		etatCourant.selectionnerBoisson(this, toucheBoisson);
	}
	
	void rendreMonnaie() {
		etatCourant.rendreMonnaie(this);
	}
}
