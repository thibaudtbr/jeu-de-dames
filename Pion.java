public class Pion extends Piece {
    
    public Pion (int couleur, int line, int column, int role) {
        super(couleur, line, column, role);

    }
    @Override
    public boolean deplacementValide(Case caseDepart, Case caseArrivee, Plateau plateau) {
        int startLine = caseDepart.getLine();
        int startColumn = caseDepart.getColumn();
        int endLine = caseArrivee.getLine();
        int endColumn = caseArrivee.getColumn();

        // Calcul des différences
        int diffLigne = endLine - startLine;
        int diffColonne = Math.abs(endColumn - startColumn);

        // Vérification des limites du plateau
        if (endLine < 0 || endLine >= 10 || endColumn < 0 || endColumn >= 10) {
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
            int midLine = (startLine + endLine) / 2;
            int midColumn = (startColumn + endColumn) / 2;
            Case caseMilieu = plateau.getCase(midLine, midColumn);

            // Vérifie s'il y a une pièce adverse sur la case du milieu
            if (caseMilieu.getPiece() != null && (caseMilieu.getPiece().getCouleur()==this.getCouleur()) == false) {
                return true;
            }
        }
        return false;
    }
}
