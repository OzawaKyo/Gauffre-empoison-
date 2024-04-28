import java.awt.Point;

public class GaufreModel {
    private Gauffre[][] gaufre;
    private boolean gameOver;
    private int currentPlayer;
    private GaufreHistorique historique;

    public GaufreModel(int rows, int cols) {
        // Initialisation de la gaufre
        this.gaufre = new Gauffre[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gaufre[i][j] = new Gauffre();
            }
        }
        // Marquer la case empoisonnée
        gaufre[0][0].setEmpoisonnée(true);
        this.gameOver = false;
        this.currentPlayer = 1; // Joueur 1 commence
        this.historique = new GaufreHistorique(this.gaufre);

    }

    public Gauffre[][] getGaufre() {
        return gaufre;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isFull() {
        // Vérifier si toutes les cases sont rouges
        for (int i = 0; i < gaufre.length; i++) {
            for (int j = 0; j < gaufre[i].length; j++) {
                if (gaufre[i][j].isEmpoisonnée() == true) {
                    continue;
                } else if (gaufre[i][j].isCroquée() == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Méthode pour vérifier si un coup est valide
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= gaufre.length || col < 0 || col >= gaufre[0].length || gaufre[row][col].isEmpoisonnée())
            return false; // Coup en dehors de la gaufre
        return gaufre[row][col].isCroquée() == false; // Le coup est valide si la case est comestible
    }

    // Méthode pour hover sur une case
    public void hoverMove(int row, int col) {
        if (isValidMove(row, col)) {
            // gaufre[row][col].setHovered(true); // Marquer la case survolée
            for (int i = row; i < gaufre.length; i++) {
                for (int j = col; j < gaufre[i].length; j++) {
                    if (!gaufre[i][j].isCroquée()) {
                        gaufre[i][j].setHovered(true); // Marquer la case et les cases à droite/bas comme survolées
                    }
                }
            }
        }
    }

    // Méthode pour unhover une case
    public void unhoverMove(int row, int col) {
        // gaufre[row][col].setHovered(false); // Démarquer la case survolée
        for (int i = row; i < gaufre.length; i++) {
            for (int j = col; j < gaufre[i].length; j++) {
                if (!gaufre[i][j].isCroquée())
                    gaufre[i][j].setHovered(false); // Démarquer la case et les cases à droite/bas comme survolées
            }
        }
    }

    // Méthode pour jouer un coup
    public void playMove(int row, int col) {
        // Vérifier si le coup est valide
        if (isValidMove(row, col)) {
            // Mettre à jour la gaufre en retirant les cases
            for (int i = row; i < gaufre.length; i++) {
                for (int j = col; j < gaufre[i].length; j++) {
                    if (!gaufre[i][j].isCroquée()){
                        gaufre[i][j].setCroquée(true); // Marquer la case et les cases à droite/bas comme croquées
                        gaufre[i][j].setPlayerColor(currentPlayer);
                    }
                }
            }
            // Sauvegarder dans l'historique
            historique.ajouterCoup(gaufre);
            // Vérifier si le coup perdant a été joué
            if (row == 0 && col == 0) {
                gameOver = true;
            } else {
                // Changer de joueur
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
            // Vider la pile refaire
            historique.viderRefaire();
        }
    }

    // Méthode pour annuler le dernier coup
    public void undoMove() {
        if (historique.HistoriqueEstVide())
            return;
        Gauffre[][] newGaufre = historique.annulerCoup();
        if (newGaufre == null)
            return;
        this.gaufre = newGaufre;
        currentPlayer = (currentPlayer == 1) ? 2 : 1; // Changer de joueur
    }

    // Méthode pour refaire le dernier coup annulé
    public void redoMove() {
        if (historique.RefaireEstVide())
            return;
        Gauffre[][] newGaufre = historique.refaireCoup();
        if (newGaufre == null)
            return;
        this.gaufre = newGaufre;
        currentPlayer = (currentPlayer == 1) ? 2 : 1; // Changer de joueur
    }

    public void printGaufre() {
        for (int i = 0; i < gaufre.length; i++) {
            for (int j = 0; j < gaufre[i].length; j++) {
                if (gaufre[i][j].isEmpoisonnée()) {
                    System.out.print("X ");
                } else if (!gaufre[i][j].isCroquée()) {
                    System.out.print("O ");
                } else if (gaufre[i][j].isCroquée()) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    public void printHistorique() {
        historique.printHistoriqueStack();
    }

    public void printRefaire() {
        historique.printRefaireStack();
    }
}