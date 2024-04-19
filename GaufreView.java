import javax.swing.*;
import java.awt.*;

public class GaufreView extends JPanel {
    private GaufreModel model;

    public GaufreView(GaufreModel model) {
        this.model = model;
        setPreferredSize(new Dimension(400, 300)); // Taille de la vue
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] gaufre = model.getGaufre();
        int cellWidth = getWidth() / gaufre[0].length;
        int cellHeight = getHeight() / gaufre.length;
    
        for (int i = 0; i < gaufre.length; i++) {
            for (int j = 0; j < gaufre[i].length; j++) {
                if (gaufre[i][j] == 1) {
                    g.setColor(Color.GREEN); // Couleur pour la case empoisonnée
                } else if (gaufre[i][j] == 0) {
                    g.setColor(Color.WHITE); // Couleur pour les cases comestibles
                } else if (gaufre[i][j] == 3) {
                    g.setColor(Color.GRAY); // Couleur pour les cases croquées
                }
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }
    
    
}
