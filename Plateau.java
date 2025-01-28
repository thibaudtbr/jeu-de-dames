public class Plateau {
    private Case[][] damier;

    //Constructeur
    public Plateau() {
        this.damier =  new Case[10][10];
        startlaunchingDamier();
    }


    public Case getCase(int positionX, int positionY) {
        if (positionX >= 0 && positionX < 10 && positionY >= 0 && positionY < 10) {
            return damier[positionX][positionY];
        }
        return null; // Retourne null si les coordonnées sont hors limites
    }


    public Case[][] GetDamier () {
        return damier;
    }

    private void startlaunchingDamier() {
        for (int positionX = 0; positionX < 10; positionX++) {
            for (int positionY = 0; positionY < 10; positionY++) {
                damier[positionX][positionY] = new Case(positionX, positionY);

                // Placement des pions pour les joueurs sur les damier noires
                if (positionX < 4 && (positionX + positionY) % 2 != 0) {
                    damier[positionX][positionY].setPiece(new Pion(1, positionX, positionY, 2)); // Pions noirs
                } else if (positionX > 5 && (positionX + positionY) % 2 != 0) {
                    damier[positionX][positionY].setPiece(new Pion(0, positionX, positionY, 2)); // Pions blancs
                }
            }
        }
    }


    public void afficherPlateau() {
        for (int positionX = 0; positionX < 10; positionX++) {
            for (int positionY = 0; positionY < 10; positionY++) {
                Case currentCase = damier[positionX][positionY];
                if (currentCase.getPiece() == null) {
                    System.out.print(damier[positionX][positionY].getColor()); // Case vide donc renvoie la case normale 
                } else if (currentCase.getPiece() instanceof Pion) {
                    System.out.print(String.valueOf(currentCase.getPiece().getCouleur()) + String.valueOf(currentCase.getPiece().getRole())); // 02 (Pion blanc) ou 12 (Pion noir)
                } else if (currentCase.getPiece() instanceof Dame) {
                    System.out.print(String.valueOf(currentCase.getPiece().getCouleur()) + String.valueOf(currentCase.getPiece().getRole())); // 03 (Dame blanche) ou 13 (Dame noire)
                }
            }
            System.out.println(); // Nouvelle positionX pour chaque rangée
        }
    }



}