public class Piece {
    public String couleur;
    public int line;
    public int column;
    public String role;

    public Piece(String couleur, int line, int column, String role) {
        this.couleur = couleur;
        this.line = line;
        this.column = copublic class Piece {
            public String couleur;
            public int line;
            public int column;
            public String role;
        
            public Piece(String couleur, int line, int column, String role) {
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
        
            public int[] getPosition () {
                int[] position = {line, column};
                return position;
            }
        
        }
        
    }
}