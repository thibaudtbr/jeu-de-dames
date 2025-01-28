public class Dame extends Piece{
    
    public Dame (int couleur, int positionX, int positionY, int role) {
        super(couleur,positionX, positionY, role);
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

        // Vérification que le chemin est libre
        int directionLigne = (endpositionX - startpositionX) / diffLigne; // +1 ou -1
        int directionColonne = (endpositionY - startpositionY) / diffColonne; // +1 ou -1

        int ligneCourante = startpositionX + directionLigne;
        int colonneCourante = startpositionY + directionColonne;

        while (ligneCourante != endpositionX && colonneCourante != endpositionY) {
            Case caseIntermediaire = plateau.getCase(ligneCourante, colonneCourante);
            if (caseIntermediaire.getPiece() != null) {
                return false; // Chemin bloqué
            }
            ligneCourante += directionLigne;
            colonneCourante += directionColonne;
        }

        // Vérification de la capture si une pièce est rencontrée
        Case caseIntermediaire = plateau.getCase((startpositionX + endpositionX) / 2, (startpositionY + endpositionY) / 2);
        if (caseIntermediaire.getPiece() != null &&
            (caseIntermediaire.getPiece().getCouleur()==(this.getCouleur()))==false) {
            return true; // Capture valide
        }

        // Si tout est bon, le déplacement est valide
        return true;
    }
}
