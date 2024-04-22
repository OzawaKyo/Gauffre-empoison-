import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaufreRandomAI {

    private Random random;

    public GaufreRandomAI() {
        this.random = new Random();
    }

    // Méthode pour obtenir un mouvement aléatoire parmi les mouvements valides
    public int[] getRandomMove(GaufreModel model) {
        List<int[]> validMoves = getValidMoves(model);
        if (validMoves.isEmpty()) {
            return null; // Aucun mouvement valide
        }
        int randomIndex = random.nextInt(validMoves.size());
        return validMoves.get(randomIndex);
    }

    // Méthode pour obtenir une liste de tous les mouvements valides
    private List<int[]> getValidMoves(GaufreModel model) {
        List<int[]> validMoves = new ArrayList<>();
        Gauffre[][] gaufre = model.getGaufre();

        for (int i = 0; i < gaufre.length; i++) {
            for (int j = 0; j < gaufre[i].length; j++) {
                if (model.isValidMove(i, j)) {
                    validMoves.add(new int[] { i, j });
                }
            }
        }

        return validMoves;
    }
}
