import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectModeView extends JPanel {
  private GaufreController controller;
  JButton buttonAI;
  JButton buttonPlayer;

  public SelectModeView(GaufreModel model, GaufreController controller) {
    this.controller = controller;
    setPreferredSize(new Dimension(800, 600)); // Taille de la vue

    buttonAI = new JButton("Random AI");
    buttonAI.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.setGamemode(0);
      }
    });

    buttonPlayer = new JButton("2 players");
    buttonPlayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.setGamemode(1);
      }
    });

    setLayout(null);
    add(buttonAI);
    add(buttonPlayer);
  }

  @Override
  protected void paintComponent(Graphics gRaw) {

    Graphics2D g = (Graphics2D) gRaw;

    super.paintComponent(g);

    // Draw background
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());

    // Draw title
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 25));
    String title = "Le jeu de la gaufre empoisonnée";
    int titleWidth = g.getFontMetrics().stringWidth(title);
    int titleX = (getWidth() - titleWidth) / 2;
    int titleY = getHeight() / 4;
    g.drawString(title, titleX, titleY);

    // Draw buttons
    int buttonWidth = 200;
    int buttonHeight = 50;
    int buttonX = (getWidth() - buttonWidth) / 2;
    int buttonY = getHeight() / 2;
    int buttonSpacing = 20;

    buttonAI.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
    buttonPlayer.setBounds(buttonX, buttonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);

  }

}