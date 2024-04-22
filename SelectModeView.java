import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectModeView extends JPanel {
  private GaufreController controller;

  public SelectModeView(GaufreModel model, GaufreController controller) {
    this.controller = controller;
    setPreferredSize(new Dimension(400, 300)); // Taille de la vue

    JButton button1 = new JButton("Mode 0");
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.setGamemode(0);
      }
    });

    JButton button2 = new JButton("Mode 1");
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        controller.setGamemode(1);
      }
    });

    add(button1);
    add(button2);
  }

  // other methods...
}