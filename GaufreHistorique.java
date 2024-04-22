import java.util.ArrayList;
import java.util.List;

public class GaufreHistorique {

    // une pile pour stocker les gaufres à chaque coup
    private List<Gauffre[][]> historique;
    private int index;

    // Constructeur
    public GaufreHistorique() {
        historique = new ArrayList<>();
        // historique.add(copieProfonde(gaufre));
        index = 0;
    }

    // Méthode pour ajouter un coup à l'historique
    public void ajouterCoup(Gauffre[][] gaufre) {
        historique.add(copieProfonde(gaufre));
        index++;
    }

    // Méthode pour annuler le dernier coup
    public Gauffre[][] annulerCoup() {
        if (index > 0) {
            index--;
            return copieProfonde(historique.get(index));
        }
        return null;
    }

    private Gauffre[][] copieProfonde(Gauffre[][] original) {
        int rows = original.length;
        int cols = original[0].length;
        Gauffre[][] copy = new Gauffre[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Gauffre newCell = new Gauffre();
                newCell.setEmpoisonnée(original[i][j].isEmpoisonnée());
                newCell.setCroquée(original[i][j].isCroquée());
                newCell.setHovered(original[i][j].isHovered());
                copy[i][j] = newCell;
            }
        }

        return copy;
    }

    // get length of the stack
    public int getLength() {
        return historique.size();
    }

    // Méthode pour vérifier si l'historique est vide
    public boolean estVide() {
        if (getLength() == 1) {
            return true;
        }
        return false;
    }

    // print the stack
    public void printStack() {
        for (Gauffre[][] gaufre : historique) {
            for (int i = 0; i < gaufre.length; i++) {
                for (int j = 0; j < gaufre[i].length; j++) {
                    System.out.print(gaufre[i][j].isCroquée() ? "X" : "O");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}