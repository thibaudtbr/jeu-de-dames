import javax.swing.JOptionPane; // Autorisé car partie de Swing

public class Jeu {
    private Plateau plateau; // Le plateau de jeu
    private Joueur joueur1; // Joueur 1
    private Joueur joueur2; // Joueur 2
    private Joueur joueurCourant; // Le joueur dont c'est le tour

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

        // Méthode pour gérer un clic sur une case
    public void gestionClic(int ligne, int colonne) {
        Case caseCliquee = plateau.getCase(ligne, colonne); // Récupère la case cliquée
        if (caseCliquee != null) {
            if (caseCliquee.getPiece() != null) {
                System.out.println("Case cliquée avec une pièce : " + caseCliquee.getPiece());
                // Logique pour gérer la sélection ou le déplacement
            } else {
                System.out.println("Case vide cliquée.");
            }
        } else {
            System.out.println("Clic hors limites !");
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
            if (validerDeplacement(caseDepart, caseArrivee, plateau)) {
                effectuerDeplacement(caseDepart, caseArrivee);

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
    private Case demanderCase(String message) {
        int ligne = demanderEntier(message + " (Ligne)");
        int colonne = demanderEntier(message + " (Colonne)");
        return plateau.getCase(ligne, colonne);
    }

    public boolean validerDeplacement(Case caseDepart, Case caseArrivee, Plateau plateau) {
        if (caseArrivee.getPiece() != null) {
            return false; // La case cible doit être vide
        }
    
        int direction = (this.getCouleur() == 0) ? 1 : -1; // Blancs avancent vers le bas, Noirs vers le haut
                int deltaLigne = caseArrivee.getpositionX() - caseDepart.getpositionX();
                int deltaColonne = Math.abs(caseArrivee.getpositionY() - caseDepart.getpositionY());
            
                // Déplacement simple (une case en diagonale)
                if (deltaLigne == direction && deltaColonne == 1) {
                    return true;
                }
            
                // Saut par-dessus une pièce adverse
                if (deltaLigne == 2 * direction && deltaColonne == 2) {
                    int ligneIntermediaire = caseDepart.getpositionX() + direction;
                    int colonneIntermediaire = (caseDepart.getpositionY() + caseArrivee.getpositionY()) / 2;
                    Case caseIntermediaire = plateau.getCase(ligneIntermediaire, colonneIntermediaire);
            
                    if (caseIntermediaire.getPiece() != null && caseIntermediaire.getPiece().getCouleur() != this.getCouleur()) {
                        return true;
                    }
                }
            
                return false;
            }
            
        
            private int getCouleur() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getCouleur'");
            }
        
            // Méthode pour effectuer un déplacement
    private void effectuerDeplacement(Case caseDepart, Case caseArrivee) {
        caseArrivee.setPiece(caseDepart.getPiece()); // Place la pièce sur la case cible
        caseDepart.setPiece(null); // Vide la case de départ
    }

    // Méthode pour changer de joueur
    private void changerJoueur() {
        joueurCourant = (joueurCourant == joueur1) ? joueur2 : joueur1;
    }

    // Vérifie si un joueur a gagné
    private boolean verifierVictoire() {
        // Un joueur gagne si l'adversaire n'a plus de pièces ou ne peut plus jouer
        return !joueur2.aDesPieces(plateau) || !joueur1.aDesPieces(plateau);
    }

    // Demande un entier via une boîte de dialogue
    private int demanderEntier(String message) {
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
    public static void main(String[] args) {
        Jeu jeu = new Jeu(); // Crée une nouvelle partie
        //jeu.lancerJeu(); // Lance la sur le terminal
        // Version graphique :
        new InterfaceGraphique(jeu);
    }
}
