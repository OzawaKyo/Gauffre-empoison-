import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectModeView extends JPanel {
  private GaufreModel model;
  private boolean hoverMode = false;
  private JLabel currentPlayerLabel;

  public SelectModeView(GaufreModel model) {
    this.model = model;
    setPreferredSize(new Dimension(400, 300)); // Taille de la vue

    // Créer le label pour afficher le joueur courant
    currentPlayerLabel = new JLabel("Joueur courant : " + model.getCurrentPlayer());
    add(currentPlayerLabel, BorderLayout.NORTH);

  }

  public void updateCurrentPlayerLabel() {
    currentPlayerLabel.setText("Joueur courant : " + model.getCurrentPlayer());
  }

  public void toggleHoverMode() {
    hoverMode = !hoverMode;
    repaint(); // Actualiser la vue
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Gauffre[][] gaufre = model.getGaufre();
    int cellWidth = getWidth() / gaufre[0].length;
    int cellHeight = getHeight() / gaufre.length;

    for (int i = 0; i < gaufre.length; i++) {
      for (int j = 0; j < gaufre[i].length; j++) {
        if (gaufre[i][j].isEmpoisonnée()) {
          g.setColor(Color.GREEN); // Couleur pour la case empoisonnée
        } else if (!gaufre[i][j].isCroquée()) {
          g.setColor(Color.WHITE); // Couleur pour les cases comestibles
        } else if (gaufre[i][j].isCroquée()) {
          g.setColor(Color.GRAY); // Couleur pour les cases croquées
        }
        if (gaufre[i][j].isHovered() && !gaufre[i][j].isCroquée() && hoverMode) {
          g.setColor(Color.YELLOW); // Couleur pour la case survolée
        }
        g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
        g.setColor(Color.BLACK);
        g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
      }
    }
  }

}
