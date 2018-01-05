package vie;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
public class JeuDeLaViePanel extends JPanel implements ConstantesJeu {
    private JeuDeLaVie jeu;
    private int hauteurCellule;
    private int largeurCellule;
    private Color couleurVie;
    private Color couleurMort;
    public JeuDeLaViePanel(JeuDeLaVie jeu, Color couleurVie, Color couleurMort){
        this.jeu = jeu;
        this.largeurCellule = LARGEUR_GRILLE / jeu.getColonnes();
        this.hauteurCellule = HAUTEUR_GRILLE / jeu.getLignes();
        this.couleurVie = couleurVie;
        this.couleurMort = couleurMort;
        initialiser();
    }
    private void initialiser(){
        this.setSize(LARGEUR_GRILLE, HAUTEUR_GRILLE);
        this.setPreferredSize(this.getSize());
        this.setBackground(couleurMort);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        // Ajouter un listener sur la souris afin de mettre
        // la cellule où se trouve la souris on ou off.
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                jeu.toggle(e.getPoint().y / hauteurCellule,e.getPoint().x / largeurCellule);
            }
        });
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        dessinerGrille(g);
        dessineVie(g);
    }
    // Dessine les lignes de la grille.
    private void dessinerGrille(Graphics g){
        g.setColor(couleurVie);
        for(int i = 0; i <= jeu.getLignes(); i++){
            g.drawLine(0, i*hauteurCellule,LARGEUR_GRILLE,i*hauteurCellule);
        }
        for(int i = 0; i <= jeu.getColonnes(); i++){
            g.drawLine(i*largeurCellule, 0,i*largeurCellule,HAUTEUR_GRILLE);
        }
    }
    // Dessine toutes les cellules en vie.
    private void dessineVie(Graphics g){
        g.setColor(couleurVie);
        for(int i =0; i < jeu.getLignes(); i++){
            for(int j = 0; j < jeu.getColonnes(); j++){
                if ( jeu.estVivante(i,j) ){
                    g.fillRect(j*largeurCellule, i*hauteurCellule,largeurCellule, hauteurCellule);
                }
            }
        }
    }
}