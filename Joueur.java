public class Joueur {
    private int couleur; // Couleur des pièces du joueur (Blanc ou Noir)

    // Constructeur
    public Joueur(int couleur) {
        this.couleur = couleur; // Initialise la couleur du joueur
    }

    // Getter pour la couleur
    public int getCouleur() {
        return couleur;
    }

    // Vérifie si le joueur a encore des pièces sur le plateau
    public boolean aDesPieces(Plateau plateau) {
        for (int ligne = 0; ligne < 10; ligne++) { // Parcourt le plateau (10x10)
            for (int colonne = 0; colonne < 10; colonne++) {
                Case currentCase = plateau.getCase(ligne, colonne);
                if (currentCase.getPiece() != null && currentCase.getPiece().getCouleur()==(this.couleur)) {
                    return true; // Trouve au moins une pièce appartenant au joueur
                }
            }
        }
        return false; // Aucune pièce trouvée
    }
}