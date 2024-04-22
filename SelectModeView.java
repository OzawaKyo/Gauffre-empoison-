import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SelectModeView extends JPanel {

  public SelectModeView(GaufreModel model) {
    setPreferredSize(new Dimension(400, 300)); // Taille de la vue
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    // Calculate the center coordinates of the panel
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;

    // Set the button size and position
    int buttonWidth = 100;
    int buttonHeight = 50;
    int buttonX = centerX - buttonWidth / 2;
    int buttonY = centerY - buttonHeight / 2;

    // Draw the button
    g.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
    g.drawString("Click Me", buttonX + 20, buttonY + 30);

  }

}
