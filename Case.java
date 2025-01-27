public class Case {
    private int color;
    private int line;
    private int column;
    private Piece piece;

    public Case(int line, int column) {
        this.line = line;
        this.column = column;
        this.color = setColor(line, column);
    }


    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    // Calcule la couleur en fonction des coordonnées
    public int setColor(int line, int column) {
        if ((line % 2 == 0 && column % 2 == 0) || (line % 2 == 1 && column % 2 == 1)) {
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
