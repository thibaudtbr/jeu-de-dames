public abstract class Piece {
    public int couleur;
    public int positionX;
    public int positionY;
    public int role;

    public Piece(int couleur, int positionX, int positionY, int role) {
        this.couleur = couleur;
        this.positionX = positionX;
        this.positionY = positionY;
        this.role = role;
    }
    
    public int getCouleur() {
        return couleur;
    }

    public int getpositionX() {
        return positionX;
    }

    public int getpositionY() {
        return positionY;
    }

    public int getRole() {
        return couleur;
    }

    public abstract boolean deplacementValide();
}
