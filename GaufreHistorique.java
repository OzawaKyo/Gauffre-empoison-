import java.awt.Point;
import java.util.Stack;

public class GaufreHistorique {

    // une pile pour stocker les coups joués
    private Stack<Point> historique;

    public GaufreHistorique() {
        historique = new Stack<>();
    }

    // Méthode pour ajouter un coup à l'historique
    public void ajouterCoup(int row, int col) {
        historique.push(new Point(row, col));
    }

    // Méthode pour annuler le dernier coup
    public Point annulerCoup() {
        if (!this.estVide()) {
            return historique.pop();
        }
        return null;
    }

    // Méthode pour vérifier si l'historique est vide
    public boolean estVide() {
        return historique.isEmpty();
    }

}