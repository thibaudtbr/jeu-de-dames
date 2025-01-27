public class Plateau {
    private Case[][] damier;

    //Constructeur
    public Plateau() {
        this.damier =  new Case[10][10];
        startlaunchingDamier();
    }


    public Case getCase(int line, int column) {
        if (line >= 0 && line < 10 && column >= 0 && column < 10) {
            return damier[line][column];
        }
        return null; // Retourne null si les coordonnées sont hors limites
    }


    public Case[][] GetDamier () {
        return damier;
    }

    private void startlaunchingDamier() {
        for (int line = 0; line < 10; line++) {
            for (int column = 0; column < 10; column++) {
                cases[line][column] = new Case(line, column);

                // Placement des pions pour les joueurs sur les cases noires
                if (line < 4 && (line + column) % 2 != 0) {
                    cases[line][column].setPiece(new Pion(1, line, column, 2)); // Pions noirs
                } else if (line > 5 && (line + column) % 2 != 0) {
                    cases[line][column].setPiece(new Pion(0, line, column, 2)); // Pions blancs
                }
            }
        }
    }


    public void afficherPlateau() {
        for (int line = 0; line < 10; line++) {
            for (int column = 0; column < 10; column++) {
                Case currentCase = cases[line][column];
                if (currentCase.getPiece() == null) {
                    System.out.print("[ ]"); // Case vide
                } else if (currentCase.getPiece() instanceof Pion) {
                    System.out.print(currentCase.getPiece().getCouleur().charAt(0) + "P "); // B ou N pour Pion
                } else if (currentCase.getPiece() instanceof Dame) {
                    System.out.print(currentCase.getPiece().getCouleur().charAt(0) + "D "); // B ou N pour Dame
                }
            }
            System.out.println(); // Nouvelle line pour chaque rangée
        }
    }


    public getCasesValides(Case caseCliquee) {}

    }
}