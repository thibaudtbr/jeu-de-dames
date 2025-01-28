public class Pion extends Piece {
    
    public Pion (int couleur, int positionX, int positionY, int role) {
        super(couleur, positionX, positionY, role);

    }
    public boolean deplacementValide(Case caseDepart, Case caseArrivee, Plateau plateau) {
        int startpositionX = caseDepart.getpositionX();
        int startpositionY = caseDepart.getpositionY();
        int endpositionX = caseArrivee.getpositionX();
        int endpositionY = caseArrivee.getpositionY();

        // Calcul des différences
        int diffLigne = endpositionX - startpositionX;
        int diffColonne = Math.abs(endpositionY - startpositionY);

        // Vérification des limites du plateau
        if (endpositionX < 0 || endpositionX >= 10 || endpositionY < 0 || endpositionY >= 10) {
            return false;
        }
        // Vérification que le déplacement est en diagonale
        if (diffLigne != diffColonne) {
            return false; // Pas un mouvement diagonal
        }

        // Vérification que la case cible est vide
        if (caseArrivee.getPiece() != null) {
            return false; // On ne peut pas aller sur une case occupée
        }
        // déplacement classique en diagonale d'une case
        if (diffColonne == 1 && Math.abs(diffLigne) == 1) {
            if (this.getCouleur()==0) {
                return diffLigne == -1; // Blancs montent
            } else {
                return diffLigne == 1; // Noirs descendent
            }
        }

        // Vérification de la capture : deux cases en diagonale
        if (diffColonne == 2 && Math.abs(diffLigne) == 2) {
            int midpositionX = (startpositionX + endpositionX) / 2;
            int midpositionY = (startpositionY + endpositionY) / 2;
            Case caseMilieu = plateau.getCase(midpositionX, midpositionY);

            // Vérifie s'il y a une pièce adverse sur la case du milieu
            if (caseMilieu.getPiece() != null && (caseMilieu.getPiece().getCouleur()==this.getCouleur()) == false) {
                return true;
            }
        }
        return false;
    }
}
