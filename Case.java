public class Case {
    private int color;
    private int positionX;
    private int positionY;
    private Piece piece;

    public Case(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = setColor(positionX, positionY);
    }


    public int getpositionX() {
        return positionX;
    }

    public int getpositionY() {
        return positionY;
    }

    // Calcule la couleur en fonction des coordonnées
    public int setColor(int positionX, int positionY) {
        if ((positionX % 2 == 0 && positionY % 2 == 0) || (positionX % 2 == 1 && positionY % 2 == 1)) {
            return 0; // Blanc
        }
        return 1; // Noir
    }

    public int getColor() {
        return color;
    }


    public Piece getPiece() { 
        return piece; 
    }
    public void setPiece(Piece piece) { 
        this.piece = piece; 
    }

    public boolean Vide() { 
        return piece == null; 
    }
}
