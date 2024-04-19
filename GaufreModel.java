public class GaufreModel {
    private Gauffre[][] gaufre;
    private boolean gameOver;
    private int currentPlayer;

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
        if (row < 0 || row >= gaufre.length || col < 0 || col >= gaufre[0].length)
            return false; // Coup en dehors de la gaufre
        return gaufre[row][col].isCroquée() == false; // Le coup est valide si la case est comestible
    }

    // Méthode pour jouer un coup
    public void playMove(int row, int col) {
        // Vérifier si le coup est valide
        if (isValidMove(row, col)) {
            // Mettre à jour la gaufre en retirant les cases
            for (int i = row; i < gaufre.length; i++) {
                for (int j = col; j < gaufre[i].length; j++) {
                    gaufre[i][j].setCroquée(true); // Marquer la case et les cases à droite/bas comme croquées
                }
            }
            // Vérifier si le coup perdant a été joué
            if (row == 0 && col == 0) {
                gameOver = true;
            } else {
                // Changer de joueur
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
    }
}
