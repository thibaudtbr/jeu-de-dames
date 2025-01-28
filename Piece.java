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
        return role;
    }

    public boolean isDame() {
        return role==3;
    }

    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

        if (isDame() == false) {
            promotion();
        }
    }

    public void promotion() {
        // Si le pion atteint la dernière ligne de l'adversaire
        if (this.getCouleur() == 0 && this.getpositionX() == 0) {
            // Pion blanc atteint la ligne 0, devient Dame
            this.role = 3; // Rôle 3 représente la dame
            System.out.println("Le pion blanc est devenu une dame !");
        } else if (this.getCouleur() == 1 && this.getpositionX() == 9) {
            // Pion noir atteint la ligne 9, devient Dame
            this.role = 3; // Rôle 3 représente la dame
            System.out.println("Le pion noir est devenu une dame !");
        }
    }

    public abstract boolean deplacementValide(Case caseDepart, Case caseArrivee, Plateau plateau);
}
