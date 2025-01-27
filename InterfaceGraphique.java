import javax.swing.*;

public class InterfaceGraphique {
    private JFrame frame; // Fenêtre principale
    private JPanel plateauPanel; // Plateau de jeu
    private JButton[][] boutons; // Boutons représentant les cases
    private Jeu jeu; // Lien vers la logique du jeu

    private Icon pionBlanc;
    private Icon pionNoir;
    private Icon dameBlanc;
    private Icon dameNoir;

    // Constructeur
    public InterfaceGraphique(Jeu jeu) {
        this.jeu = jeu; // Associe la logique du jeu
        boutons = new JButton[10][10]; // Crée le plateau graphique
        initialiserInterface();
        chargerIcones(); //charge les images des pion et dame
    }
}
