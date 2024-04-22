import java.util.Stack;

public class GaufreHistorique {

    // une pile pour stocker les gaufres à chaque coup
    private Stack<Gauffre[][]> historique;

    // une pile pour refaire les coups annulés
    private Stack<Gauffre[][]> refaire;

    // Constructeur
    public GaufreHistorique(Gauffre[][] gaufre) {
        historique = new Stack<>();
        historique.push(copieProfonde(gaufre));
        refaire = new Stack<>();
    }

    // Méthode pour ajouter un coup à l'historique
    public void ajouterCoup(Gauffre[][] gaufre) {
        historique.add(copieProfonde(gaufre));
    }

    // Méthode pour annuler le dernier coup
    public Gauffre[][] annulerCoup() {
        if (!HistoriqueEstVide()) {
            refaire.push(historique.pop());
            return historique.peek();
        }
        return null;
    }

    // Méthode pour refaire le dernier coup annulé
    public Gauffre[][] refaireCoup() {
        if (!refaire.isEmpty()) {
            historique.push(refaire.pop());
            return historique.peek();
        }
        return null;
    }

    // Méthode pour vider la pile refaire
    public void viderRefaire() {
        refaire.clear();
    }

    // Méthode pour ajouter un état à la pile refaire
    public void ajouterRefaire(Gauffre[][] gaufre) {
        refaire.push(copieProfonde(gaufre));
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

    // get length of the historique stack
    public int getHistoriqueLength() {
        return historique.size();
    }

    // get length of the refaire stack
    public int getRefaireLength() {
        return refaire.size();
    }

    // Méthode pour vérifier si l'historique est vide
    public boolean HistoriqueEstVide() {
        if (getHistoriqueLength() == 1) {
            return true;
        }
        return false;
    }

    // Méthode pour vérifier si la pile refaire est vide
    public boolean RefaireEstVide() {
        if (getRefaireLength() == 0) {
            return true;
        }
        return false;
    }

    // print the stack
    public void printHistoriqueStack() {
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

    // print the refaire stack
    public void printRefaireStack() {
        if (refaire.isEmpty()) {
            System.out.println("Refaire stack is empty");
            return;
        }
        for (Gauffre[][] gaufre : refaire) {
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