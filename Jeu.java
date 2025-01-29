import javax.swing.JOptionPane;

public class Jeu {
    private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurCourant;
    private Case caseSelectionnee = null;  // Pour mémoriser la case actuellement sélectionnée
    private Case caseCliquee = null;

    public Jeu() {
        plateau = new Plateau(); 
        joueur1 = new Joueur(0); 
        joueur2 = new Joueur(1); 
        joueurCourant = joueur1; 
    }


    public Plateau getPlateau() {
        return this.plateau;
    }


        // Méthode pour gérer un clic sur une case
    public void gestionClic(int ligne, int colonne) {
        caseCliquee = plateau.getCase(ligne, colonne);
    if (caseSelectionnee == null) {
        if (caseCliquee.getPiece() != null && caseCliquee.getPiece().getCouleur() == joueurCourant.getCouleur()) {
            caseSelectionnee = caseCliquee;
            System.out.println("Case sélectionnée : " + caseCliquee);

        } else if (caseCliquee.getPiece() == null ){
            JOptionPane.showMessageDialog(null, "Case vide, sélectionnée une autre case");
        }
        else {
            JOptionPane.showMessageDialog(null, "ce n'est pas votre tour");
        }
    } else {
        if (caseCliquee == caseSelectionnee) {
            System.out.println("Désélection de la case sélectionnée.");
            caseSelectionnee = null; 
            caseCliquee =null;
            JOptionPane.showMessageDialog(null, "Désélection de la case sélectionnée.");
            System.out.println("caseSelectionnee après désélection : " + caseSelectionnee);
        }
        // Si une case a déjà été sélectionnée, tenter de déplacer la pièce
        if (validerDeplacement(caseSelectionnee, caseCliquee) == true) {
            effectuerDeplacement(caseSelectionnee, caseCliquee);
            promouvoirSiNecessaire(caseCliquee);
            changerJoueur(); 
            caseSelectionnee = null;
        } else {
            JOptionPane.showMessageDialog(null, "Déplacement invalide.");
        }
    }
    }

    public void CaseSelectionneeVidee (Case caseSelectionnee) {
        caseSelectionnee = null;
    }

    
    

    public void lancerJeu() {
        boolean jeuEnCours = true;

        while (jeuEnCours) {
            plateau.afficherPlateau();
            JOptionPane.showMessageDialog(null, "Tour de " + joueurCourant.getCouleur());


            Case caseDepart = demanderCase("Sélectionnez une pièce à déplacer (ligne, colonne) : ");
            Case caseArrivee = demanderCase("Sélectionnez la case cible (ligne, colonne) : ");


            if (validerDeplacement(caseDepart, caseArrivee)) {
                effectuerDeplacement(caseDepart, caseArrivee);
                caseArrivee.getPiece().isDame();

            
                if (verifierVictoire()) {
                    JOptionPane.showMessageDialog(null, joueurCourant.getCouleur() + " a gagné !");
                    jeuEnCours = false;
                } else {
                    changerJoueur();
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
        caseArrivee.setPiece(caseDepart.getPiece());
        caseDepart.setPiece(null); 
    }

    // Méthode pour vérifier et promouvoir un pion en dame si nécessaire
    public void promouvoirSiNecessaire(Case caseArrivee) {
        Piece piece = caseArrivee.getPiece();
        if (piece instanceof Pion) {
            Pion pion = (Pion) piece;
        
            // Vérification si le pion atteint la dernière ligne
            if ((pion.getCouleur() == 0 && caseArrivee.getpositionX() == 0) || 
                (pion.getCouleur() == 1 && caseArrivee.getpositionX() == 9)) {
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
        return !joueur2.aDesPieces(plateau) || !joueur1.aDesPieces(plateau);
    }

   
    public int demanderEntier(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, message);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Action annulée.");
                    System.exit(0); 
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
        Jeu jeu = new Jeu();
        //jeu.lancerJeu();
        jeu.lancerJeuAvecInterface();
    }

    

    
}
