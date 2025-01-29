public class Joueur {
    private int couleur;

    // Constructeur
    public Joueur(int couleur) {
        this.couleur = couleur; 
    }

    public int getCouleur() {
        return couleur;
    }

    // Vérifie si le joueur a encore des pièces sur le plateau
    public boolean aDesPieces(Plateau plateau) {
        for (int ligne = 0; ligne < 10; ligne++) { 
            for (int colonne = 0; colonne < 10; colonne++) {
                Case currentCase = plateau.getCase(ligne, colonne);
                if (currentCase.getPiece() != null && currentCase.getPiece().getCouleur()==(this.couleur)) {
                    return true; 
                }
            }
        }
        return false;
    }
}