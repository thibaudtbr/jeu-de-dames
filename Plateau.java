public class Plateau {
    private Case[][] damier;
    //Constructeur
    public Plateau() {
        this.damier =  new Case[10][10];
        for (int line=0; line<10; line++) {
            for (int column=0; column < 10; column++) {
                damier[line][column] = new Case(line, column);
            }
        }
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


    public static void main(String[] args) {
        Plateau plateau = new Plateau();

        Case[][] damier = plateau.GetDamier();
        System.out.println("Affichage du damier : ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int couleur = damier[i][j].GetColor(i,j);  // Récupère la couleur de la case
                System.out.print(couleur + " ");  // Affiche 0 ou 1
            }
            System.out.println();  // Passe à la line suivante
        }
    }
}