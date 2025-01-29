import java.util.ArrayList;

import javax.swing.JOptionPane; // Autorisé car partie de Swing

public class Jeu {
    private Plateau plateau; // Le plateau de jeu
    private Joueur joueur1; // Joueur 1
    private Joueur joueur2; // Joueur 2
    private Joueur joueurCourant; // Le joueur dont c'est le tour
    private ArrayList<Case> casesValides = new ArrayList<>();

    // Constructeur
    public Jeu() {
        plateau = new Plateau(); // Initialise un nouveau plateau
        joueur1 = new Joueur(0); // Joueur 1 joue les pièces blanches
        joueur2 = new Joueur(1); // Joueur 2 joue les pièces noires
        joueurCourant = joueur1; // Le joueur blanc commence
    }

        // Méthode pour récupérer le plateau
    public Plateau getPlateau() {
        return this.plateau;
    }

    private Case caseSelectionnee = null;  // Pour mémoriser la case actuellement sélectionnée

    public Case getCaseSelectionnee() {
        return caseSelectionnee;
    }
    
    public void setCaseSelectionnee(Case caseSelectionnee) {
        this.caseSelectionnee = caseSelectionnee;
    }

    public ArrayList<Case> getCasesValides() {
        return casesValides;
    }
    

    public void calculerCasesValides(Case caseDepart) {
        casesValides.clear();
        if (caseDepart.getPiece() != null) {
            casesValides = caseDepart.getPiece().casesValides(plateau);
        }
    }
    

     

    // Lance le jeu
    public void lancerJeu() {
        boolean jeuEnCours = true;

        while (jeuEnCours) {
            plateau.afficherPlateau(); // Affiche le plateau
            JOptionPane.showMessageDialog(null, "Tour de " + joueurCourant.getCouleur());

            // Demander au joueur de choisir une pièce et une case cible
            Case caseDepart = demanderCase("Sélectionnez une pièce à déplacer (ligne, colonne) : ");
            Case caseArrivee = demanderCase("Sélectionnez la case cible (ligne, colonne) : ");

            // Valider et exécuter le mouvement
            if (validerDeplacement(caseDepart, caseArrivee)) {
                effectuerDeplacement(caseDepart, caseArrivee);
                caseArrivee.getPiece().isDame();

                // Vérifier les conditions de victoire
                if (verifierVictoire()) {
                    JOptionPane.showMessageDialog(null, joueurCourant.getCouleur() + " a gagné !");
                    jeuEnCours = false;
                } else {
                    changerJoueur(); // Passe au joueur suivant
                }
            } else {
                JOptionPane.showMessageDialog(null, "Déplacement invalide. Réessayez.");
            }
        }
    }

    // Méthode pour demander une case au joueur
    public Case demanderCase(String message) {
        int ligne = demanderEntier(message + " (Ligne)");
        int colonne = demanderEntier(message + " (Colonne)");
        return plateau.getCase(ligne, colonne);
    }

    // Méthode pour valider un déplacement
    public boolean validerDeplacement(Case caseDepart, Case caseArrivee) {
        if (caseDepart.getPiece() != null && caseDepart.getPiece().getCouleur()==joueurCourant.getCouleur()) {
            return caseDepart.getPiece().deplacementValide(caseDepart, caseArrivee, plateau);
        }
        return false;
    }

    // Méthode pour effectuer un déplacement
    public void effectuerDeplacement(Case caseDepart, Case caseArrivee) {
        caseArrivee.setPiece(caseDepart.getPiece()); // Place la pièce sur la case cible
        caseDepart.setPiece(null); // Vide la case de départ
    
        // Vérifier et promouvoir le pion si nécessaire
        promouvoirSiNecessaire(caseArrivee);
    }

    // Méthode pour vérifier et promouvoir un pion en dame si nécessaire
    public void promouvoirSiNecessaire(Case caseArrivee) {
        Piece piece = caseArrivee.getPiece();
        if (piece instanceof Pion) {
            Pion pion = (Pion) piece;
        
            // Vérification si le pion atteint la dernière ligne
            if ((pion.getCouleur() == 0 && caseArrivee.getpositionX() == 0) || 
                (pion.getCouleur() == 1 && caseArrivee.getpositionX() == 9)) {
                // Promouvoir le pion en dame
                caseArrivee.setPiece(new Dame(pion.getCouleur(), pion.getpositionX(), pion.getpositionY(), 3)); // Remplace le pion par une dame
            }
        }
    }

    // Méthode pour changer de joueur
    public void changerJoueur() {
        joueurCourant = (joueurCourant == joueur1) ? joueur2 : joueur1;
        JOptionPane.showMessageDialog(null, "C'est au tour du Joueur " + tourJoueur());
    }
                
    public String tourJoueur() {
        if (getJoueurCourant().getCouleur() == 0) {
            return"Joueur Blanc";
        } else {
            return "Joueur Noir";
        }
    }
    

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    // Vérifie si un joueur a gagné
    public boolean verifierVictoire() {
        // Un joueur gagne si l'adversaire n'a plus de pièces ou ne peut plus jouer
        return !joueur2.aDesPieces(plateau) || !joueur1.aDesPieces(plateau);
    }

    // Demande un entier via une boîte de dialogue
    public int demanderEntier(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, message);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Action annulée.");
                    System.exit(0); // Quitte le programme si l'utilisateur annule
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
            }
        }
    }

    public void lancerJeuAvecInterface() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new InterfaceGraphique(this); // Associe l'interface graphique au jeu
        });
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu(); // Crée une nouvelle partie
        //jeu.lancerJeu(); // Lance la partie
        jeu.lancerJeuAvecInterface();
    }

    

    
}
