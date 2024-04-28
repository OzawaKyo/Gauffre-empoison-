import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectAIView extends JPanel {
    private GaufreController controller;
    private JButton buttonAI;
    private JButton buttonPlayer;

    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 25);
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TITLE_COLOR = Color.WHITE;
    private static final Color BUTTON_COLOR = Color.BLUE;
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_SPACING = 20;

    public SelectAIView(GaufreModel model, GaufreController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(800, 600)); // Set the size of the view

        buttonAI = new JButton("Random AI");
        buttonAI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.setAiLevel(0);
            }
        });

        buttonPlayer = new JButton("Medium AI");
        buttonPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.setAiLevel(1);
            }
        });

        // Set button styles
        buttonAI.setBackground(BUTTON_COLOR);
        buttonAI.setForeground(BUTTON_TEXT_COLOR);
        buttonAI.setFont(TITLE_FONT);
        buttonAI.setMargin(new Insets(10, 10, 10, 10));

        buttonPlayer.setBackground(BUTTON_COLOR);
        buttonPlayer.setForeground(BUTTON_TEXT_COLOR);
        buttonPlayer.setFont(TITLE_FONT);
        buttonPlayer.setMargin(new Insets(10, 10, 10, 10));

        // Add buttons to the panel
        add(buttonAI);
        add(buttonPlayer);
    }

    @Override
    protected void paintComponent(Graphics gRaw) {
        Graphics2D g = (Graphics2D) gRaw;

        super.paintComponent(g);

        // Draw background
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw title
        g.setColor(TITLE_COLOR);
        g.setFont(TITLE_FONT);
        String title = "Le jeu de la gaufre empoisonnée";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        int titleX = (getWidth() - titleWidth) / 2;
        int titleY = getHeight() / 4;
        g.drawString(title, titleX, titleY);

        // Draw buttons
        int buttonX = (getWidth() - BUTTON_WIDTH) / 2;
        int buttonY = getHeight() / 2;

        buttonAI.setBounds(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonPlayer.setBounds(buttonX, buttonY + BUTTON_HEIGHT + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}