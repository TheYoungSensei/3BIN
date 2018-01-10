package question;

// Context
public class MachineACafe {
    // State
	public enum State {
	    // ConcreteState
	    IDLE {
	        // handle
            @Override
            public void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson) {
                machineACafe.afficherPasAssez(toucheBoisson);
            }

            // handle
            @Override
            public void rendreMonnaie(MachineACafe machineACafe) {
                // DO NOTHING
            }
        },
        // ConcreteState
        COLLECTE {
	        //handle
            @Override
            public void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson) {
                if (toucheBoisson.getPrix() > machineACafe.montantEnCours) {
                    machineACafe.boisson = toucheBoisson;
                    machineACafe.afficherPasAssez(machineACafe.boisson);
                    machineACafe.boisson = toucheBoisson;
                    machineACafe.etatCourant =  PASASSEZ;
                    return;
                }
                machineACafe.montantEnCours -= toucheBoisson.getPrix();
                machineACafe.afficherBoisson(toucheBoisson);
                machineACafe.afficherMontant();
                if (machineACafe.montantEnCours == 0)
                    machineACafe.etatCourant = IDLE;
                else
                    machineACafe.etatCourant = COLLECTE;
            }
        },
        // ConcreteState
        PASASSEZ {
	        // handle
            @Override
            public void entrerMonnaie(MachineACafe machineACafe, Piece piece) {
                if (machineACafe.boisson.getPrix() > machineACafe.montantEnCours) {
                    machineACafe.afficherPasAssez(machineACafe.boisson);
                } else {
                    machineACafe.montantEnCours -= machineACafe.boisson.getPrix();
                    machineACafe.afficherBoisson(machineACafe.boisson);
                    machineACafe.boisson = null;
                    machineACafe.afficherMontant();
                    if (machineACafe.montantEnCours == 0)
                        machineACafe.etatCourant =  IDLE;
                    else
                        machineACafe.etatCourant =  COLLECTE;
                }
            }

            // handle
            @Override
            public void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson) {
                throw new IllegalStateException();
            }
        };
	    // handle
        public void entrerMonnaie(MachineACafe machineACafe, Piece piece) {
            machineACafe.etatCourant = COLLECTE;
        }

        // handle
        public abstract void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson);

        // handle
        public void rendreMonnaie(MachineACafe machineACafe) {
            machineACafe.afficherRetour();
            machineACafe.montantEnCours = 0;
            machineACafe.boisson = null;
            machineACafe.etatCourant = IDLE;
        }
    }
	
	private int montantEnCours = 0;
	private State etatCourant = State.IDLE;
	private ToucheBoisson boisson = null;
	
	private void afficherMontant() {
		System.out.println(montantEnCours + " cents disponibles");
	}
	
	public void afficherRetour() {
		System.out.println(montantEnCours + " cents rendus");
	}
	
	private void afficherPasAssez(ToucheBoisson toucheBoisson) {
		System.out.println("Vous n'avez pas introduit un montant suffisant pour un " + toucheBoisson);
		System.out.println("Il manque encore " + (toucheBoisson.getPrix() - montantEnCours) + " cents");
	}

	private void afficherBoisson(ToucheBoisson toucheBoisson) {
		System.out.println("Voici un " + toucheBoisson);
		
	}

	// request
    public void entrerMonnaie(Piece piece) {
        montantEnCours += piece.getValeur();
        afficherMontant();
        etatCourant.entrerMonnaie(this, piece);
    }

    // request
    public void selectionnerBoisson(ToucheBoisson toucheBoisson) {
       etatCourant.selectionnerBoisson(this, toucheBoisson);
    }

    // request
    public void rendreMonnaie() {
        etatCourant.rendreMonnaie(this);
    }
}
