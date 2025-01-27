public class Dame extends Piece{
    
    public Dame (int couleur, int line, int column, int role) {
        super(couleur,line, column, role);
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

        // Vérification que le chemin est libre
        int directionLigne = (endLine - startLine) / diffLigne; // +1 ou -1
        int directionColonne = (endColumn - startColumn) / diffColonne; // +1 ou -1

        int ligneCourante = startLine + directionLigne;
        int colonneCourante = startColumn + directionColonne;

        while (ligneCourante != endLine && colonneCourante != endColumn) {
            Case caseIntermediaire = plateau.getCase(ligneCourante, colonneCourante);
            if (caseIntermediaire.getPiece() != null) {
                return false; // Chemin bloqué
            }
            ligneCourante += directionLigne;
            colonneCourante += directionColonne;
        }

        // Vérification de la capture si une pièce est rencontrée
        Case caseIntermediaire = plateau.getCase((startLine + endLine) / 2, (startColumn + endColumn) / 2);
        if (caseIntermediaire.getPiece() != null &&
            (caseIntermediaire.getPiece().getCouleur()==(this.getCouleur()))==false) {
            return true; // Capture valide
        }

        // Si tout est bon, le déplacement est valide
        return true;
    }
}
