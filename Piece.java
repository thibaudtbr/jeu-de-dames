public class Piece {
    public int couleur;
    public int line;
    public int column;
    public int role;

    public Piece(int couleur, int line, int column, int role) {
        this.couleur = couleur;
        this.line = line;
        this.column = column;
        this.role = role;
    }
    
    public int getCouleur() {
        return couleur;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getRole() {
        return couleur;
    }
}
