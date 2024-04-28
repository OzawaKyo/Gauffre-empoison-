import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class GaufreMediumAI {


    // Methode pour recuperer le mouvement
    public int[] GetCoupAjoue (GaufreModel model){
        // On verifie si la case (1;1) a ete mangee si ce n'est pas le cas on la mange
        if (model.isValidMove(1,1)){
            return new int[] {1,1};
        }
        return MakeItSquared(model);
    }
    private int LastRowIndex (GaufreModel model){
        Gauffre[][] gaufre = model.getGaufre();
        for (int i = 1; i < gaufre.length; i++) {
            if (!model.isValidMove(i,0)){
                return i-1;
            }
        }
        return gaufre.length-1;
    }
    private int LastColIndex (GaufreModel model){
        Gauffre[][] gaufre = model.getGaufre();
        for (int i = 1; i < gaufre[0].length; i++) {
            if (!model.isValidMove(0,i)){
                return i-1;
            }
        }
        return gaufre[0].length-1;
    }

    private boolean isSquare(int i , int j){

        return (i == j);
    }
    private int[] MakeItSquared (GaufreModel model) {
        int i = LastRowIndex(model);
        int j = LastColIndex(model);
//        int m = Math.min(LastRowIndex(model),LastColIndex(model));
        if (!isSquare(i, j)){
            if ( i < j) {
                if (model.isValidMove(0,i+1)){
                    return new int[] {0,i+1};
                }
            }
            else if (model.isValidMove(j+1,0)) {
                return new int[]{j+1, 0};
            }
        }
//        if (model.isValidMove(0,m)){
//            return new int[] {0,m};
//        }
//        else if (model.isValidMove(m,0)) {
//            return new int[]{m, 0};
//        }
//        return new int[]{0, 0};
        GaufreRandomAI randomAI = new GaufreRandomAI();
        return randomAI.getRandomMove(model);
    }
}
