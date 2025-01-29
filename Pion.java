

public class Pion extends Piece {
    
    public Pion (int couleur, int positionX, int positionY, int role) {
        super(couleur, positionX, positionY, role);

    }
    public boolean deplacementValide(Case caseDepart, Case caseArrivee, Plateau plateau) {
        int startpositionX = caseDepart.getpositionX();
        int startpositionY = caseDepart.getpositionY();
        int endpositionX = caseArrivee.getpositionX();
        int endpositionY = caseArrivee.getpositionY();

        int diffLigne = endpositionX - startpositionX;
        int diffColonne = Math.abs(endpositionY - startpositionY);

        // Déplacement classique en diagonale d'une case
        if (diffColonne == 1 && Math.abs(diffLigne) == 1) {
            // Le pion ne peut se déplacer que vers l'avant pour sa couleur
            if (this.getCouleur() == 0) {
                // Blancs montent : diffLigne doit être négatif
                if (diffLigne >= 0) {
                    return false; // Ne peut pas aller en arrière
                }
            } else {
                // Noirs descendent : diffLigne doit être positif
                if (diffLigne <= 0) {
                    return false; // Ne peut pas aller en arrière
                }
            }

            // Vérifie si la case d'arrivée est vide
            if (caseArrivee.getPiece() != null) {
                return false; // Ne peut pas se déplacer si la case d'arrivée est occupée
            }

            return true;
        }

        // Vérification de la capture (deux cases en diagonale)
        if (diffColonne == 2 && Math.abs(diffLigne) == 2) {
            int midpositionX = (startpositionX + endpositionX) / 2;
            int midpositionY = (startpositionY + endpositionY) / 2;
            Case caseMilieu = plateau.getCase(midpositionX, midpositionY);

            // Vérifie s'il y a une pièce adverse sur la case du milieu
            if (caseMilieu.getPiece() != null && (caseMilieu.getPiece().getCouleur() != this.getCouleur())) {
                // Vérifie si la case d'arrivée est libre
                if (caseArrivee.getPiece() == null) {
                    // Le pion ne peut capturer que dans la direction de sa couleur
                    if (this.getCouleur() == 0) {
                        // Blancs montent, donc diffLigne doit être négatif
                        if (diffLigne >= 0) {
                            return false; // Ne peut pas capturer en arrière
                        }
                    } else {
                        // Noirs descendent, donc diffLigne doit être positif
                        if (diffLigne <= 0) {
                            return false; // Ne peut pas capturer en arrière
                        }
                    }

                    // Capture valide : Supprimer la pièce capturée
                    caseMilieu.setPiece(null); // Supprime la pièce capturée
                    return true;
                }
            }
        }

        return false;
    }

    
}