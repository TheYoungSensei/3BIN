package vie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class JeuDeLaVieGUI extends JFrame implements ConstantesJeu, Espion {
    private final static Color COULEUR_VIVANT_PAR_DEFAUT = Color.RED;
    private final static Color COULEUR_MORT_PAR_DEFAUT = Color.BLACK;
    private JeuDeLaVie jeu;
    private JeuDeLaViePanel vue;
    private JPanel contentPane;
    private JButton boutonAvance;
    public JeuDeLaVieGUI(String titre, JeuDeLaVie jeu) {
        this(titre, jeu, COULEUR_VIVANT_PAR_DEFAUT,COULEUR_MORT_PAR_DEFAUT);
    }
    public JeuDeLaVieGUI(String titre, JeuDeLaVie jeu, Color couleurVie,
                         Color couleurMort){
        super(titre);
        this.jeu = jeu;
        vue = new JeuDeLaViePanel(jeu, couleurVie, couleurMort);
        boutonAvance = new JButton("Avance");
        contentPane = new JPanel();
        jeu.attacher(this);
        initialiser();
    }
    // Construit le GUI et permet au bouton "Avance" de passer à
    // génération suivante.
    private void initialiser(){
        this.setSize(vue.getWidth()+PAD_EN_LARGEUR,
                vue.getHeight()+PAD_EN_HAUTEUR);
        contentPane.setLayout(new FlowLayout());
        contentPane.add(vue);
        contentPane.add(boutonAvance);
        this.setContentPane(contentPane);
        this.setVisible(true);
        boutonAvance.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jeu.avancer();
            }
        });
        this.setDefaultCloseOperation(DISPOSE ON CLOSE);
    }
    // Redessine pour afficher les changements.
    public void utiliserRenseignements(){
        repaint();
    }
}