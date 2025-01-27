import javax.swing.*; // Bibliothèque pour créer des interfaces graphiques en Java
import java.awt.*; // Contient les classes pour la gestion des composants graphiques comme les couleurs et les mises en page
import java.awt.event.ActionEvent; // Permet de gérer les événements d'action, comme les clics sur les boutons
import java.awt.event.ActionListener; // Interface pour écouter et répondre aux événements d'action
import java.awt.event.ComponentAdapter; // Classe adaptateur pour gérer les événements de composant, comme le redimensionnement
import java.awt.event.ComponentEvent; // Représente un événement lié à un composant, comme un changement de taille
import java.util.ArrayList; // Classe utilitaire pour manipuler des listes dynamiques

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
        chargerIcones();
    }

    // Initialise l'interface graphique
    private void initialiserInterface() {
        frame = new JFrame("Jeu de Dames");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        plateauPanel = new JPanel();
        plateauPanel.setLayout(new GridLayout(10, 10));

        // Initialisation des boutons pour chaque case
        for (int ligne = 0; ligne < 10; ligne++) {
            for (int colonne = 0; colonne < 10; colonne++) {
                JButton bouton = new JButton();
                boutons[ligne][colonne] = bouton;

                final int l = ligne;
                final int c = colonne;

                bouton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jeu.gestionClic(l, c); // Transmet le clic à la logique du jeu
                    }
                });

                plateauPanel.add(bouton);
            }
        }

        frame.add(plateauPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Ajouter un écouteur pour redimensionner dynamiquement les icônes
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                chargerIcones(); // Recharge les icônes avec la nouvelle taille
                mettreAJourPlateau(jeu.getPlateau(), new ArrayList<>(), new ArrayList<>()); // Met à jour l'affichage
            }
        });
    }

    // Crée une icône redimensionnée
    private Icon creerIconeRedimensionnee(String cheminImage, int largeur, int hauteur) {
        ImageIcon icone = new ImageIcon(cheminImage);
        Image image = icone.getImage();
        Image imageRedimensionnee = image.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(imageRedimensionnee);
    }

    // Charge les icônes avec les dimensions adaptées
    private void chargerIcones() {
        int tailleBouton = Math.min(plateauPanel.getWidth() / 10, plateauPanel.getHeight() / 10);
        pionBlanc = creerIconeRedimensionnee("pion_blanc.png", tailleBouton, tailleBouton);
        pionNoir = creerIconeRedimensionnee("pion_noir.png", tailleBouton, tailleBouton);
        dameBlanc = creerIconeRedimensionnee("dame_blanc.png", tailleBouton, tailleBouton);
        dameNoir = creerIconeRedimensionnee("dame_noir.png", tailleBouton, tailleBouton);
    }

    // Met à jour l'affichage du plateau
    public void mettreAJourPlateau(Plateau plateau, ArrayList<Case> casesDeplacement, ArrayList<Case> casesCapture) {
        for (int ligne = 0; ligne < 10; ligne++) {
            for (int colonne = 0; colonne < 10; colonne++) {
                Case currentCase = plateau.getCase(ligne, colonne);
                JButton bouton = boutons[ligne][colonne];

                // Colorer les cases en damier
                if ((ligne + colonne) % 2 == 0) {
                    bouton.setBackground(Color.LIGHT_GRAY); // Case blanche
                } else {
                    bouton.setBackground(Color.DARK_GRAY); // Case noir
                }

                // Réinitialise l'icône
                bouton.setIcon(null);
                bouton.setText("");

                // Afficher les pièces sur les cases
                if (currentCase.getPiece() != null) {
                    Piece piece = currentCase.getPiece();
                    if (piece instanceof Pion) {
                        bouton.setIcon(piece.getCouleur().equals("blanc") ? pionBlanc : pionNoir);
                    } else if (piece instanceof Dame) {
                        bouton.setIcon(piece.getCouleur().equals("blanc") ? dameBlanc : dameNoir);
                    }
                }

                // Met en surbrillance les cases valides
                if (casesDeplacement.contains(currentCase)) {
                    bouton.setBackground(Color.YELLOW); // Déplacement valide
                } else if (casesCapture.contains(currentCase)) {
                    bouton.setBackground(Color.RED); // Capture valide
                }

                // Réinitialiser la bordure des cases du damier
                bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }
}
