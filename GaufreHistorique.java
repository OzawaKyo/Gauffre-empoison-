import java.util.Stack;

public class GaufreHistorique {

    // une pile pour stocker les gaufres à chaque coup
    private Stack<Gauffre[][]> historique;

    // Constructeur
    public GaufreHistorique(Gauffre[][] gaufre) {
        historique = new Stack<Gauffre[][]>();
        historique.push(gaufre);
    }

    // Méthode pour ajouter un coup à l'historique
    public void ajouterCoup(Gauffre[][] gaufre) {
        historique.push(gaufre);
    }

    // Méthode pour annuler le dernier coup
    public Gauffre[][] annulerCoup() {
        if (!estVide()) {
            historique.pop();
        }
        if (!estVide()) {
            System.out.println("Removing the last move");
            return historique.pop();
        }
        return null;
    }

    // Méthode pour vérifier si l'historique est vide
    public boolean estVide() {
        if (getLength() == 1) {
            return true;
        }
        return false;
    }

    // get length of the stack
    public int getLength() {
        return historique.size();
    }

}