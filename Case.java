public class Case {
    private int color;
    private int line;
    private int column;

    public Case(int line, int column) {
        this.line = line;
        this.column = column;
        this.color = GetColor(line, column);
    }


    public int GetLine() {
        return line;
    }

    public int GetColumn() {
        return column;
    }

    // Calcule la couleur en fonction des coordonnées
    public int GetColor(int line, int column) {
        if ((line % 2 == 0 && column % 2 == 0) || (line % 2 == 1 && column % 2 == 1)) {
            return 0; // Blanc
        }
        return 1; // Noir
    }

    public static void main(String[] args) {
        Case case00 = new Case(0, 0);

        // Appeler la méthode DonneLigne et afficher le résultat
        int ligne = case00.GetLine();
        System.out.println("La ligne de la case est : " + ligne);

        // Appeler la méthode DonneColonne et afficher le résultat
        int colonne = case00.GetColumn();
        System.out.println("La colonne de la case est : " + colonne);

        // Afficher la couleur de la case
        int couleur = case00.GetColor(0, 0);
        System.out.println("La couleur de la case est : " + (couleur == 0 ? "blanc" : "noir"));
    }
}
