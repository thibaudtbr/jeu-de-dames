public class Dame extends Piece{
    
    public Dame (int couleur, int positionX, int positionY, int role) {
        super(couleur,positionX, positionY, role);
    }

    public boolean deplacementValide(Case caseDepart, Case caseArrivee, Plateau plateau) {
        int startpositionX = caseDepart.getpositionX();
        int startpositionY = caseDepart.getpositionY();
        int endpositionX = caseArrivee.getpositionX();
        int endpositionY = caseArrivee.getpositionY();

        int diffLigne = Math.abs(endpositionX - startpositionX);
        int diffColonne = Math.abs(endpositionY - startpositionY);

        // Déplacement classique en diagonale (n'importe quelle distance)
        if (diffLigne == diffColonne) {
            // Vérifier s'il y a une pièce adverse à capturer
            int directionLigne = (endpositionX - startpositionX) / diffLigne;
            int directionColonne = (endpositionY - startpositionY) / diffColonne;
            
            int x = startpositionX + directionLigne;
            int y = startpositionY + directionColonne;
            
            // Vérifier s'il y a des pièces à capturer entre la case de départ et la case d'arrivée
            while (x != endpositionX && y != endpositionY) {
                Case caseIntermediaire = plateau.getCase(x, y);
                if (caseIntermediaire.getPiece() != null) {
                    // Si une pièce est présente sur la case intermédiaire, vérifier si elle est de la couleur adverse
                    if (caseIntermediaire.getPiece().getCouleur() != this.getCouleur()) {
                        // Si la case d'arrivée est vide, c'est une capture valide
                        if (caseArrivee.getPiece() == null) {
                            caseIntermediaire.setPiece(null); // Supprimer la pièce capturée
                            return true;
                        }
                    } else {
                        // Une pièce amie bloque le mouvement
                        return false;
                    }
                }
                x += directionLigne;
                y += directionColonne;
            }
            // Si le déplacement est sans capture, vérifier que la case d'arrivée est vide
            return caseArrivee.getPiece() == null;
        }

        return false; // Le déplacement n'est pas valide si ce n'est pas en diagonale
    }

    
}
