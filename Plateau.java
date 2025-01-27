public class Plateau {
    private Case[][] damier = new Case[10][10];

    //Constructeur
    public Plateau() {
        for (int line=0; line<10; line++) {
            for (int column=0; column < 10; column++) {
                damier[line][column] = new Case(line, column);
            }
        }
    }
    public Case getCase(int ligne, int colonne) {
        if (ligne >= 0 && ligne < 10 && colonne >= 0 && colonne < 10) {
            return damier[ligne][colonne];
        }
        return null; // Retourne null si les coordonnées sont hors limites
    }
    public Case[][] GetDamier () {
        return damier;
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
            System.out.println();  // Passe à la ligne suivante
        }
    }
}